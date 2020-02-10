package com.atguigu.dao;

import com.atguigu.bean.Son;
import com.atguigu.bean.User;
import com.atguigu.bean.UserAndSon;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * mybatis有两个配置文件，usermapper.xml可以使用注解
 * 注意：如果使用了注解，就不要userMapper.xml
 */
public interface IUserDao {


    //第一个user_name是表字段，第二个userName是类字段名
    @Insert("insert into user(user_name,address,sex,birthday) values(#{userName},#{address},#{sex},#{birthday})")
    void insertUser(User user);

    /**
     * 删除上级数据解决方案（看需求）
     * 1.先判断下级数据是否存在，如果存在不删除上级
     * 2.将上级数据和下级数据做座机删除
     * @param id
     */
    @Delete("delete from user where id = #{id}")
    void deleteUser(Integer id);

    @Update("update user set address = #{address} where id = #{id}")
    void updateUser(User user);

    @Select("select * from user where id = #{id}")
    User selectUser(Integer id);

    //@Select("select * from user where user_name like #{value}")  调用的时候需要写%，比如%zhang%
    @Select("select * from user where user_name like '%${value}%' ") //调用的时候不需要写%，比如zhang
    List<User> selectUserLike(String username);

    @Select("select * from user")
    List<User> findAll();

    /**
     * 一对多
     * @param id
     * @return
     */
    @Select("select * from user where  id = #{id}")
    @Results({
            @Result(column = "user_name",property = "userName"),
            @Result(column = "address",property = "address"),
            @Result(column = "sex",property = "sex"),
            @Result(column = "birthday",property = "birthday"),
            @Result(column = "id",property = "sonList",many = @Many(select = "com.atguigu.dao.ISonDao.sonLists" ))
    })
    UserAndSon findSonsByUid(Integer id);

    @Select("select * from user where id = #{userId}")
    User getUserById(Integer userId);
}
