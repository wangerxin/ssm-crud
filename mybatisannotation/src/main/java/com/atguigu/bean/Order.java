package com.atguigu.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class Order implements Serializable {

    /**
     * 原始属性
     */
    private Integer id;
    private String name;
    private Integer userId;

    /**
     * 额外属性，用于关联查询时封装User数据,例如希望查询订单的时候的时候把user查询出来
     */
    private User user;


}
