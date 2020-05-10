package com.ab.mapper;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.ab.entity.Shop;

/**
 * @author ab
 *
 * @createTime 2020年2月17日
 */
public interface ShopMapper {

	/**
	 * @return
	 */
	List<Shop> findAll();

    List<Shop> find(ConcurrentHashMap<String, Object> map);

	int save(Shop shop);

	void update(Shop shop);

	void delete(int shopId);

}
