package atguigu.test;

import com.atguigu.bean.*;
import com.atguigu.dao.IOrderDao;
import com.atguigu.dao.IUserDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class OrderTest {
    private InputStream in;
    private SqlSessionFactory factory;
    private SqlSession session;
    private IUserDao userDao;
    private IOrderDao orderDao;

    @Before
    public void init() throws Exception {
        in = Resources.getResourceAsStream("mybatis-config.xml");
        factory = new SqlSessionFactoryBuilder().build(in);
        session = factory.openSession();
        userDao = session.getMapper(IUserDao.class);
        orderDao = session.getMapper(IOrderDao.class);
    }

    @After
    public void destroy() throws Exception {
        session.commit();
        session.close();
        in.close();
    }

    /**
     * 增
     * 注意：插入下级数据，应该先判断上级数据关联键是否存在，可以放在service判断
     */
    @Test
    public void testInsert() {
        Order order = new Order();
        order.setName("鞋子");
        order.setUserId(2);
        order.setUserId(2);
        orderDao.insertOrder(order);
    }

    /**
     * 删
     * 注意：如果这条记录不存在，也不会报错
     */
    @Test
    public void testDelete() {
        orderDao.deleteOrderById(2);
    }

    /**
     * 改
     * 注意：update的sql语句
     */
    @Test
    public void testUpdate() {
        Order order = new Order();
        order.setId(1);
        order.setUserId(10000);
        orderDao.updateOrder(order);
    }

    /**
     * 查（单表）
     */
    @Test
    public void testSelectUser() {
        Order order = orderDao.selectOrderById(1);
        System.out.println(order);
    }

    /**
     * 查（单表）
     */
    @Test
    public void testSelectLike() {
        List<User> userList = userDao.selectUserLike("zhang");
        for (User user : userList) {
            System.out.println(user);
        }
    }

    /**
     * 查（两表关联）
     */
    @Test
    public void testSelectOrderWithUser(){
        Order order = orderDao.selectOrderWithUser(1);
        System.out.println(order.getName());
        System.out.println(order.getUser());
    }

    /**
     * 一对多查，需要new新Bean

    @Test
    public void selectOrdersByUserId() {

        List<Order> orders = orderDao.selectOrdersByUserId(1);
        for (Order order : orders) {
            System.out.println(order);
        }
    }
    */
}
