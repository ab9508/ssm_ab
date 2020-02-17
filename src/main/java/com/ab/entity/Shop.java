package com.ab.entity;

import java.io.Serializable;

import lombok.Data;

/**
 * @author ab
 *
 * @creatTime 2020年2月17日
 */
@Data
public class Shop implements Serializable {
	private Integer shopId;
	private String shopName;

}
