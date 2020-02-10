package com.atguigu.dao;

import com.atguigu.bean.UserAndBook;
import org.apache.ibatis.annotations.*;

public interface IUserBookDao {
    @Select("select * from user_book where id = #{id}")
    @Results({
            @Result(column = "user_id",property = "user",one = @One(select = "com.atguigu.dao.IUserDao.getUserById")),
            @Result(column = "book_id",property = "bookList",many = @Many(select = "com.atguigu.dao.IBookDao.getBooks"))
    })
    UserAndBook findBookById(Integer id);
}
