package com.ab.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ab.entity.Shop;
import com.ab.mapper.ShopMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * @author ab
 *
 * @createTime 2020年2月17日
 */
@Service
@Slf4j
public class ShopServiceImpl implements ShopService {
	@Autowired
	private ShopMapper shopDao;

	@Override
	public List<Shop> findAll() {
		// TODO Auto-generated method stub
		log.info("ShopServiceImpl");
		return shopDao.findAll();
	}

}
