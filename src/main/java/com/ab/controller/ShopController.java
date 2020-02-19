package com.ab.controller;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ab.entity.Shop;
import com.ab.service.ShopService;

import lombok.extern.slf4j.Slf4j;

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

    @ApiOperation(value = "shop表全查")
    @GetMapping("/api/shop/find")
    public ResponseEntity<List<Shop>> find(
            @ApiParam(value = "shopId", defaultValue = "") @RequestParam(value = "shopId", required = false, defaultValue = "") String shopId,
            @ApiParam(value = "shopName", defaultValue = "") @RequestParam(value = "shopName", required = false, defaultValue = "") String shopName
    ) {
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        map.put("shopId", shopId);
        map.put("shopName", shopName);
        List<Shop> list = shopService.find(map);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @ApiOperation(value = "添加")
    @PostMapping("/api/shop/save/{shop}")
    public ResponseEntity<Void> save(@RequestBody Shop shop) {
        shopService.save(shop);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "修改")
    @PutMapping("/api/shop/update/{shop}")
    public ResponseEntity<Void> update(@RequestBody Shop shop) {
        shopService.update(shop);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("/api/shop/delete/{shopId}")
    public ResponseEntity<Void> delete(
            @ApiParam(value = "shopId", required = true, defaultValue = "") @RequestParam(value = "shopId", required = true, defaultValue = "") int shopId
    ) {
        shopService.delete(shopId);
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
