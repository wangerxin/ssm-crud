package com.atguigu.bean;

import lombok.Data;

import java.util.List;

@Data
public class UserAndBook {

    private Integer id;

    private User user;

    private List<Book> bookList;


}
