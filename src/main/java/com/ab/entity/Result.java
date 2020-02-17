package com.ab.entity;

import lombok.Data;

/**
 * @author ab
 *
 * @createTime 2020年2月17日
 */
@Data
public class Result<T> {
	private Integer code;
	private String message;
	private T date;

	public Result(Integer code, String message, T date) {
		super();
		this.code = code;
		this.message = message;
		this.date = date;
	}

}
