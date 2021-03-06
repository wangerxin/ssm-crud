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
    @Update("update user_order set name = #{name},user_id = #{userId} where id = #{id}")
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
     * 编程步骤：1.数据主体是order，数据附属是user，因此在order中添加user属性（User需要实现Serializable接口）
     *           2.查询出order数据
     *           3.根据user_id去user表中查询出user数据
     *           4.将user数据封装到order数据中
     * @param userId
     */
    @Select("select * from user_order where id = #{id}")
    @Results(
            id = "orderWithUserResultMap",
            value = {
                    @Result(id = true,column = "id",property = "id"),
                    @Result(column = "name",property = "name"),
                    @Result(column = "user_id",property = "userId"),
                    @Result(column = "id",property = "user",one = @One(select = "com.atguigu.dao.IUserDao.selectUser")),//执行过程，执行IUserDao.selectUser方法，参数是id,返回结果封装在user字段
            }
    )
    Order selectOrderWithUser(Integer userId);


    @Delete("delete from user_order where user_id = #{userId}")
    void deleteSonsByUserId(Integer userId);
}
