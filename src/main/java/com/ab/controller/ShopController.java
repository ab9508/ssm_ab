package com.ab.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ab.entity.Result;
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
public class ShopController {
    @Autowired
    private ShopService shopService;

    @GetMapping("/api/shop/find")
    public Result<List<Shop>> find() {
        log.info("start findAll");
        List<Shop> list = shopService.findAll();
        log.info("list= {}", list);
        return new Result<>(200, "查询成功", list);
    }
}
