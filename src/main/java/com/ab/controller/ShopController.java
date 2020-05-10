package com.ab.controller;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import com.ab.entity.Shop;
import com.ab.service.ShopService;

import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;

/**
 * @author ab
 * <p>
 * 2020年2月17日
 */
@RestController
@Slf4j
@Api(value = "shop表增删改查")
public class ShopController {
    @Autowired
    private ShopService shopService;
    @Resource
    private ValueOperations<String, Object> valueOperations;

    @ApiOperation(value = "shop表查询")
    @GetMapping("/api/shop/find")
    public ResponseEntity<List<Shop>> find(
            @ApiParam(value = "shopId", defaultValue = "") @RequestParam(value = "shopId", required = true, defaultValue = "") String shopId,
            @ApiParam(value = "shopName", defaultValue = "") @RequestParam(value = "shopName", required = false, defaultValue = "") String shopName,
            @ApiParam(value = "shopIdOrder", defaultValue = "") @RequestParam(value = "shopIdOrder", required = false, defaultValue = "") String shopIdOrder,
            @ApiParam(value = "shopNameOrder", defaultValue = "") @RequestParam(value = "shopNameOrder", required = false, defaultValue = "") String shopNameOrder

    ) {
        ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<>();
        map.put("shopId", shopId);
        map.put("shopName", shopName);
        map.put("shopIdOrder", shopIdOrder);
        map.put("shopNameOrder", shopNameOrder);
        //先查询redis，如果redis中没有数据，在查询DB
        List<Shop> resultRedis = null;
        if (StringUtils.isNotBlank(shopId)) {
            //此处还需优化,可能类型转化异常
            resultRedis = (List<Shop>) valueOperations.get(Shop.class.getName() + shopId);
        }
        if (resultRedis != null) {
            return new ResponseEntity<>(resultRedis, HttpStatus.OK);
        }
        log.info("redis get key is null,select DB");
        List<Shop> list = shopService.find(map);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @ApiOperation(value = "添加")
    @PostMapping("/api/shop/save/{shop}")
    public ResponseEntity<Shop> save(@RequestBody Shop shop) {
        shopService.save(shop);
        //以（shopId，shop）存入redis
        valueOperations.set(Shop.class.getName() + shop.getShopId(), shop);
        MultiValueMap<String, String> header = new LinkedMultiValueMap<String, String>();
        return new ResponseEntity<>(shop, header, HttpStatus.OK);
    }

    @ApiOperation(value = "修改")
    @PutMapping("/api/shop/update/{shop}")
    public ResponseEntity<Shop> update(@RequestBody Shop shop) {
        shopService.update(shop);
        //以（shopId，shop）存入redis
        valueOperations.set(Shop.class.getName() + shop.getShopId(), shop);
        MultiValueMap<String, String> header = new LinkedMultiValueMap<String, String>();
        return new ResponseEntity<>(shop, header, HttpStatus.OK);
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("/api/shop/delete/{shopId}")
    public ResponseEntity<Void> delete(
            @ApiParam(value = "shopId", required = true, defaultValue = "") @RequestParam(value = "shopId", required = true, defaultValue = "") int shopId
    ) {
        shopService.delete(shopId);
        valueOperations.decrement(Shop.class.getName() + shopId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @ApiOperation(value = "shop表查询导出到excel")
    @GetMapping("/api/shop/find-execel")
    public ResponseEntity<ByteArrayResource> findToExecel() {
        List<Shop> list = shopService.findAll();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(Shop.SHOP_ID).append("\t").append(Shop.SHOP_NAME).append("\n");
        for (Shop shop : list) {
            stringBuffer.append(shop.getShopId()).append("\t").append(shop.getShopName()).append("\n");
        }
        //文件下载
        byte[] data = stringBuffer.toString().getBytes();
        ByteArrayResource resource = new ByteArrayResource(data);
        String fileName = DateFormatUtils.ISO_8601_EXTENDED_DATE_FORMAT.format(new Date()) + ".xlsx";
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileName)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(data.length)
                .body(resource);
    }

}
