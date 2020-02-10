package com.atguigu.dao;

import com.atguigu.bean.Order;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IOrderDao {

    /**
     * 增(单表)
     * @param order
     */
    @Insert("insert into user_order(name,user_id) values(#{name},#{userId})")
    Integer insertOrder(Order order);

    /**
     * 删（单表）
     * todo 报错：sql语法异常
     */
    @Delete("delete from user_order where id = #{id}")
    Integer deleteOrderById(Integer id);

    /**
     * 改（单表）
     */
    @Update("update user_order set name = #{name} where id = #{id}")
    Integer updateOrder(Order order);

    /**
     * 查（单表）
     */
    @Select("select * from user_order where id = #{id}")
    Order selectOrderById(Integer id);


    /**
     * 查（单表）
     * @param id
     * @return
     */
    @Select("select * from user_order where user_id = #{id}")
    List<Order> selectOrdersByUserId(@Param("id") Integer id);

    /**
     * 查（一对一）
     * 查出order信息，并查出user信息
     * @param userId
     */
    @Select("select * from user_order where id = #{id}")
    @Results(
            id = "orderWithUserResultMap",
            value = {
                    @Result(id = true,column = "id",property = "id"),
                    @Result(column = "name",property = "name"),
                    @Result(column = "user_id",property = "userId"),
                    @Result(column = "id",property = "user",one = @One(select = "com.atguigu.dao.IUserDao.selectUser")),
            }
    )
    Order queryOrderWithUser(Integer userId);


    @Delete("delete from user_order where user_id = #{userId}")
    void deleteSonsByUserId(Integer userId);
}
