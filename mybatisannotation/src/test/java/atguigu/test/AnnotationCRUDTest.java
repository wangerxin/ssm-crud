package atguigu.test;

import com.atguigu.bean.*;
import com.atguigu.dao.ISonDao;
import com.atguigu.dao.IUserBookDao;
import com.atguigu.dao.IUserDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.StringUtils;

import java.io.InputStream;
import java.sql.Connection;
import java.util.List;

public class AnnotationCRUDTest {
    private InputStream in;
    private SqlSessionFactory factory;
    private SqlSession session;
    private IUserDao userDao;
    private ISonDao sonDao;
    private IUserBookDao userBookDao;

    @Before
    public void init() throws Exception {
        in = Resources.getResourceAsStream("mybatis-config.xml");
        factory = new SqlSessionFactoryBuilder().build(in);
        session = factory.openSession();
        userDao = session.getMapper(IUserDao.class);
        sonDao = session.getMapper(ISonDao.class);
        userBookDao = session.getMapper(IUserBookDao.class);
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
        user.setUserName("zhangsan2");
        user.setAddress("beijing");
        user.setSex("man");
        user.setBirthday(null);
        userDao.insertUser(user);
    }

    /**
     * 删
     */
    @Test
    public void testDelete() {
        userDao.deleteUser(1);
        System.err.println("success");
    }

    /**
     * 改
     */
    @Test
    public void testUpdate() {
        User user = new User();
        user.setId(3);
        user.setAddress("hunan");
        userDao.updateUser(user);
        System.err.println("success");
    }

    @Test
    public void testSelect() {
        User user = userDao.selectUser(3);
        System.out.println(user);
    }

    @Test
    public void testSelectLike() {
        List<User> userList = userDao.selectUserLike("zhang");
        for (User user : userList) {
            System.out.println(user);
        }
    }

    @Test
    public void testFindSonsByUid() {
        UserAndSon userAndSon = userDao.findSonsByUid(1);
        List<Son> sonList = userAndSon.getSonList();
        for (Son son : sonList) {
            System.err.println(son);
        }
    }
    @Test
    public void testInsertSons() {
        Son son = new Son();
        son.setName("zhangnan");
        son.setUserId(66);
        User user = userDao.selectUser(son.getUserId());
        if (!StringUtils.isEmpty(user)) {
            System.err.println(sonDao);
            sonDao.insertSon(son);
            System.err.println("success");
        }
    }
    @Test
    public void testDeleteUser() {
        User user = new User();
        user.setId(1);
        Integer userId = user.getId();
        sonDao.deleteSonsByUserId(userId);
        userDao.deleteUser(user.getId());
    }@Test
    public void testFindBooks() {
        UserBook userBook = new UserBook();
        userBook.setId(1);
        Integer id = userBook.getId();
        UserAndBook userAndBook = userBookDao.findBookById(id);
        System.err.println(userAndBook);
    }

}
