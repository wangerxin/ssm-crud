package com.atguigu.bean;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data

public class User {
    /**
     * 原始属性
     */
    private Integer id;
    private String userName;
    private String address;
    private String sex;
    private Date birthday;

    /**
     * 额外属性
     */
    private List<Order> orderList;
}
