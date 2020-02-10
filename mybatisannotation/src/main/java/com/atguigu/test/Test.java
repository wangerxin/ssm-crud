package com.atguigu.test;

import com.atguigu.bean.Employee;
import com.atguigu.dao.EmployeeMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

public class Test {
    public static void main(String[] args) throws Exception {

        //https://www.bilibili.com/video/av68860896/?p=4
        //获取全局配置文件输入流
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        //加载全局配置文件
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //获取sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建代理对象
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
        //执行查询
        List<Employee> employeeList = employeeMapper.findAll();
        for (Employee employee : employeeList) {
            System.out.println(employee);
        }
        //释放资源
        sqlSession.close();
        inputStream.close();
    }
}
