package com.atguigu.dao;

import com.atguigu.bean.Son;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ISonDao {
    @Select("select * from son where user_id = #{id}")
    List<Son> sonLists(@Param("id") Integer id);

    @Insert("insert into son(name,user_id) values(#{name},#{userId})")
    void insertSon(Son son);

    @Delete("delete from son where user_id = #{userId}")
    void deleteSonsByUserId(Integer userId);
}
