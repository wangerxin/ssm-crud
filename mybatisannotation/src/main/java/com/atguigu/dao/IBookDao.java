package com.atguigu.dao;

import com.atguigu.bean.Book;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IBookDao {

    @Select("select * from book where id = #{bookId}")
    List<Book> getBooks(Integer bookId);
}
