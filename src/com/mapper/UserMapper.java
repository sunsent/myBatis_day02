package com.mapper;

import com.a_bean.QueryVo;
import com.a_bean.User;

import java.util.List;

public interface UserMapper {
    //遵循四个原则
    //接口 方法名  == User.xml 中 id 名
    //返回值类型  与  Mapper.xml文件中返回值类型要一致
    //方法的入参类型 与Mapper.xml中入参的类型要一致
    //命名空间 绑定此接口
    public User findUserById(Integer id);

    //查询数据条数
    public List<User> findUserByQueryVo(QueryVo queryVo);
    public Integer countUser();

    //根据性别和名字查询用户
    public List<User> selectUserBySexAndUsername(User user);
    //根据多个id查询用户信息
	//public List<User> selectUserByIds(Integer[] ids);
    //public List<User> selectUserByIds(List<Integer> ids);
	public List<User> selectUserByIds(QueryVo vo);
}
