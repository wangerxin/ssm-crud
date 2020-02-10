package com.atguigu.bean;

import lombok.Data;

import java.util.List;

@Data
public class UserAndSon extends User {

    private List<Order> orderList;
}
