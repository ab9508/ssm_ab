package com.ab.service;

import java.util.List;

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

}
