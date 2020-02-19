package com.ab.entity;

import java.io.Serializable;

import lombok.Data;

/**
 * @author ab
 * @createrTime 2020年2月17日
 */
@Data
public class Shop implements Serializable {
    public static final String SHOP_ID = "shopId";
    public static final String SHOP_NAME = "shopName";

    /**
     * 商店id
     */
    private Integer shopId;
    /**
     * 商店编号
     */
    private String shopName;

}
