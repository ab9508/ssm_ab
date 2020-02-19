package com.ab.service;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ab.entity.Shop;
import com.ab.mapper.ShopMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * @author ab
 * @createTime 2020年2月17日
 */
@Service
@Slf4j
public class ShopServiceImpl implements ShopService {
    @Autowired
    private ShopMapper shopMapper;

    @Override
    public List<Shop> findAll() {
        return shopMapper.findAll();
    }

    @Override
    public List<Shop> find(ConcurrentHashMap<String, String> map) {
        return shopMapper.find(map);
    }

    @Override
    public void save(Shop shop) {
        int shopId = shopMapper.save(shop);
        log.info("shop= {}", shop);
    }

    @Override
    public void update(Shop shop) {
        shopMapper.update(shop);
    }

    @Override
    public void delete(int shopId) {
        shopMapper.delete(shopId);
    }

}
