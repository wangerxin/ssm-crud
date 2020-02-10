package com.atguigu.dao;

import com.atguigu.bean.UserAndBook;
import org.apache.ibatis.annotations.*;

//https://www.bilibili.com/video/av68860896?p=5 多对多
public interface IUserBookDao {
    @Select("select * from user_book where id = #{id}")
    @Results({
            // 从上面的结果中获取user_id,调用getUserById方法，将结果封装在user里面
            @Result(column = "user_id",property = "user",one = @One(select = "com.atguigu.dao.IUserDao.getUserById")),
            @Result(column = "book_id",property = "bookList",many = @Many(select = "com.atguigu.dao.IBookDao.getBooks"))
    })
    UserAndBook findBookById(Integer id);
}
