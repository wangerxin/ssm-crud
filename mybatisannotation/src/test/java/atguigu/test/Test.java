package atguigu.test;

import com.atguigu.bean.User;
import com.atguigu.dao.IUserDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

public class Test {
    public static void main(String[] args) throws Exception {

        //获取全局配置文件输入流
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        //加载全局配置文件
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //获取sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建代理对象
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        //执行查询
        List<User> userList = userDao.findAll();
        for (User user : userList) {
            System.out.println(user);
        }
        //释放资源
        sqlSession.close();
        inputStream.close();
    }
}
