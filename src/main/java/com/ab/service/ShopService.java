package com.ab.service;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.ab.entity.Shop;

/**
 * @author ab
 *
 * 2020年2月17日
 */
public interface ShopService {

	/**
	 * @return
	 */
	List<Shop> findAll();

	List<Shop> find(ConcurrentHashMap<String,Object> map);

	void save(Shop shop);

	void update(Shop shop);

	void delete(int shopId);

}
