package com.exp1.admin.mapper;

import com.exp1.admin.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    /**
     * 查询所有用户列表
     */
    List<User> selectAll();

    /**
     * 分页查询用户
     */
    List<User> selectPage(@Param("offset") int offset, @Param("pageSize") int pageSize);

    /**
     * 查询用户总数
     */
    int selectCount();

    /**
     * 按名字模糊分页查询
     */
    List<User> selectPageByName(@Param("name") String name, @Param("offset") int offset, @Param("pageSize") int pageSize);

    /**
     * 按名字模糊查询总数
     */
    int selectCountByName(@Param("name") String name);

    /**
     * 插入用户展示记录
     */
    int insert(User user);

    /**
     * 根据用户名查询用户
     */
    User selectByName(@Param("name") String name);

    /**
     * 更新用户展示信息
     */
    int updateProfile(User user);
}
