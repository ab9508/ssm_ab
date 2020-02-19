package com.ab.entity;

import java.io.Serializable;

import lombok.Data;

/**
 * @author ab
 * @createrTime 2020年2月17日
 */
@Data
public class Shop implements Serializable {
    /**
     * 商店id
     */
    private Integer shopId;
    /**
     * 商店编号
     */
    private String shopName;

}
