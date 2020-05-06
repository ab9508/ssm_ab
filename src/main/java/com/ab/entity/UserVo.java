package com.ab.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserVo {

    public  static final String Table = "t_user";

    private String name;
    private String address;
    private Integer age;

}
