package com.atguigu.dao;

import com.atguigu.bean.Order;
import com.atguigu.bean.User;
import com.atguigu.bean.UserAndSon;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * mybatis有两个配置文件，基于注解的开发一般是指使用注解代替mapper.xml文件
 * 注意：1.如果使用了注解，就不要userMapper.xml
 * 2. todo 更新问题
 */
@CacheNamespace(blocking = true)//开启二级缓存
public interface IUserDao {

    /**
     * 增
     * 第一个user_name是表字段，第二个userName是类字段名
     *
     * @param user
     */
    @Insert("insert into user(user_name,address,sex,birthday) values(#{userName},#{address},#{sex},#{birthday})")
    void insertUser(User user);

    /**
     * 删
     * 一对多删除：删除上级数据解决方案（看需求）
     * 1.先判断下级数据是否存在，如果存在不删除上级
     * 2.将上级数据和下级数据做逻辑删除
     *
     * @param id
     */
    @Delete("delete from user where id = #{id}")
    void deleteUser(Integer id);

    /**
     * 改
     * todo 问题：修改的字段作为参数传入
     *
     * @param user
     */
    @Update("update user set address = #{address} where id = #{id}")
    void updateUser(User user);

    /**
     * 查
     *
     * @param id
     * @return
     */
    @Select("select * from user where id = #{id}")
    /**
     *@Results可以解决：
     *(1）javabean属性名和mysql列名不一致的情况
     *(1)避免写join的sql，而是多次查mysql
     * @Results使用技巧
     * 设置id的值，可以复用
     */
    @Results(id = "userResults",
            value = {
                    @Result(id = true, column = "id", property = "id"),
                    @Result(column = "user_name", property = "userName"),
                    @Result(id = true, column = "address2", property = "address"),
                    @Result(id = true, column = "sex", property = "sex"),
                    @Result(id = true, column = "birthday", property = "birthday"),
            })
    User selectUser(Integer id);

    /**
     * 查（模糊查询）
     *
     * @param username
     * @return
     */
    //@Select("select * from user where user_name like #{value}")  调用的时候需要写%，比如%zhang%
    @Select("select * from user where user_name like '%${value}%' ")
    //调用的时候不需要写%，比如zhang
    List<User> selectUserLike(String username);

    /**
     * 查（查所有）
     *
     * @return
     */
    @Select("select * from user")
    List<User> findAll();

    /**
     * 查：一对多
     * 编程步骤：1.数据主体是user，数据附属是order，因此在user中添加order属性（Order需要实现Serializable接口）
     *           2.查询出user数据
     *           3.根据user_id去order表中查询出order数据
     *           4.将order数据封装到user数据中
     * 问题：为什么分开两次查，而不是用表join（为了sql的简洁性）
     * @param id
     * @return
     */
    @Select("select * from user where  id = #{id}")
    @Results(id = "UserWithOrderResult", value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "user_name", property = "userName"),
            @Result(column = "address", property = "address"),
            @Result(column = "sex", property = "sex"),
            @Result(column = "birthday", property = "birthday"),
            @Result(column = "id", property = "orderList", many = @Many(select = "com.atguigu.dao.IOrderDao.selectOrdersByUserId"))//执行顺序：id作为参数传给IOrderDao.selectOrdersByUserId，把结果赋值为orderList
    })
    User selectUserWithOrder(Integer id);

    @Select("select * from user where id = #{userId}")
    User getUserById(Integer userId);

    void insertOrder(Order order);
}
