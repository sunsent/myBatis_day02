package com.Test;

import com.a_bean.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class TestMybatis {
    @Test
    public void testMybatis() throws Exception {
        //加载核心配置文件
        String resource = "sqlMapConfig.xml";
        InputStream in = Resources.getResourceAsStream(resource);
        //创建SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        //创建SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //执行Sql语句
        User user = sqlSession.selectOne("User.findUserById", 10);
        System.out.println(user);
    }
    @Test
    public void testMybatis2() throws Exception {
        //加载核心配置文件
        String resource = "sqlMapConfig.xml";
        InputStream in = Resources.getResourceAsStream(resource);
        //创建SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        //创建SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();


        List<User> users= sqlSession.selectList("User.findUserByName", "五");
        for(User u:users){
            System.out.println(u);
        }

    }
    //添加用户
    @Test
    public void testInsertUser() throws Exception {
        //加载核心配置文件
        String resource = "sqlMapConfig.xml";
        InputStream in = Resources.getResourceAsStream(resource);
        //创建SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        //创建SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //执行Sql语句
        User user = new User();
        user.setUsername("何炅");
        user.setBirthday(new Date());
        user.setAddress("sadfsafsafs");
        user.setSex("男");
        int i = sqlSession.insert("User.insertUser", user);
        sqlSession.commit();

        System.out.println(user.getId());

    }
    //更新用户
    @Test
    public void testUpdateUserById() throws Exception {
        //加载核心配置文件
        String resource = "sqlMapConfig.xml";
        InputStream in = Resources.getResourceAsStream(resource);
        //创建SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        //创建SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //执行Sql语句
        User user = new User();
        user.setId(27);
        user.setUsername("何炅292929");
        user.setBirthday(new Date());
        user.setAddress("222222s");
        user.setSex("女");
        int i = sqlSession.update("User.updateUserById", user);
        sqlSession.commit();
    }
    @Test
    public void testDelete() throws Exception {
        //加载核心配置文件
        String resource = "sqlMapConfig.xml";
        InputStream in = Resources.getResourceAsStream(resource);
        //创建SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        //创建SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();

        sqlSession.delete("User.deleteUserById", 27);
        sqlSession.commit();
    }
}
