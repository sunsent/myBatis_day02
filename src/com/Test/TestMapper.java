package com.Test;

import com.a_bean.Orders;
import com.a_bean.QueryVo;
import com.a_bean.User;
import com.mapper.OrderMapper;
import com.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class TestMapper {
    private SqlSessionFactory sqlSessionFactory;
    @Before
    public void before() throws Exception {
        //加载核心配置文件
        String resource = "sqlMapConfig.xml";
        InputStream in = Resources.getResourceAsStream(resource);
        //创建SqlSessionFactory
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
    }
    @Test
    public void testMapper() throws Exception {

        //创建SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //SqlSEssion帮我生成一个实现类  （给接口）
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        User user = userMapper.findUserById(10);
        System.out.println(user);
    }
    @Test
    public void testQueryVo() throws Exception {
        //创建SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //SqlSEssion帮我生成一个实现类  （给接口）
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        QueryVo vo = new QueryVo();
        User user = new User();
        user.setUsername("五");
        vo.setUser(user);

        List<User> us = userMapper.findUserByQueryVo(vo);
        for (User u : us) {
            System.out.println(u);

        }
    }
    @Test
    public void testInteger() throws Exception {
        //创建SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //SqlSEssion帮我生成一个实现类  （给接口）
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        Integer integer = userMapper.countUser();
        System.out.println(integer);
    }
    @Test
    public void testAllOrders() throws Exception {
        //创建SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //SqlSEssion帮我生成一个实现类  （给接口）
        OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
        List<Orders> orders = orderMapper.selectAllOrders();
        for (Orders order:orders){
            System.out.println(order);
        }
    }
    //	根据性别和名字查询用户
    @Test
    public void testfindUserBySexAndUsername() throws Exception {
        //创建SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = new User();
        user.setSex("1");
		user.setUsername("张小明");
        List<User> users = userMapper.selectUserBySexAndUsername(user);
        for (User user2 : users) {
            System.out.println(user2);
        }
    }
    //多个ID
    @Test
    public void testfindUserIDs() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<Integer> ids  = new ArrayList<>();
        ids.add(16);
        ids.add(22);
        ids.add(24);
		QueryVo vo = new QueryVo();
		vo.setIdsList(ids);

    	List<User> users = userMapper.selectUserByIds(vo);
/*		Integer[] ids = new Integer[3];
		ids[0] = 16;
		ids[2] = 22;
		ids[1] = 24;*/
        //List<User> users = userMapper.selectUserByIds(ids);
        for (User user2 : users) {
            System.out.println(user2);
        }
    }
}
