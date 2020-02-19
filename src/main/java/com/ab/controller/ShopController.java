package com.ab.controller;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<List<Shop>> find() {
        StringBuffer stringBuffer = new StringBuffer();
        List<Shop> list = shopService.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
