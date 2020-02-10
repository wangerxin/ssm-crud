package atguigu.test;

import com.atguigu.bean.*;
import com.atguigu.dao.IOrderDao;
import com.atguigu.dao.IUserDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class UserTest {
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
     */
    @Test
    public void testSave() {
        User user = new User();
        user.setUserName("zhangsan3");
        user.setAddress("beijing");
        user.setSex("man");
        userDao.insertUser(user);
    }

    /**
     * 删
     */
    @Test
    public void testDelete() {
        userDao.deleteUser(3);
        System.err.println("success");
    }

    /**
     * 改
     */
    @Test
    public void testUpdate() {
        User user = new User();
        user.setId(2);
        user.setUserName("lisi");
        user.setAddress("beijing");;
        userDao.updateUser(user);
        System.err.println("success");
    }

    /**
     * 查
     */
    @Test
    public void testSelectUser() {
        User user = userDao.selectUser(1);
        System.out.println(user);
    }

    /**
     * 查
     */
    @Test
    public void testSelectLike() {
        List<User> userList = userDao.selectUserLike("zhang");
        for (User user : userList) {
            System.out.println(user);
        }
    }

    /**
     * 一对多查，需要new新Bean
     */
    @Test
    public void testSelectUserWithOrders() {
        User user = userDao.selectUserWithOrder(1);
        System.out.println(user.getUserName());
        for (Order order : user.getOrderList()) {
            System.out.println(order);
        }
    }

    /**
     * 一对多插
     * 插入二级数据，需要先判断上级数据关联外键是否存在
     */
    @Test
    public void testInsertSons() {

    }

    /**
     * 一对多删
     * 删除上级数据，先判断下级数据是否存在
     */
    @Test
    public void testDeleteUser() {
    }

    /**
     * 多对多查
     * 
     */
    @Test
    public void testFindBooks() {
//        UserBook userBook = new UserBook();
//        userBook.setId(1);
//        Integer id = userBook.getId();
//        UserAndBook userAndBook = userBookDao.findBookById(id);
//        System.err.println(userAndBook);
    }

}
