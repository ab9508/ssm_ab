package com.ab.mapper;

import java.util.List;

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

}
