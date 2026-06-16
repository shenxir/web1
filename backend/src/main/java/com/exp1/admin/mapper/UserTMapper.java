package com.exp1.admin.mapper;

import com.exp1.admin.model.UserT;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserTMapper {

    /**
     * 根据用户名查询用户
     */
    UserT selectByName(@Param("name") String name);

    /**
     * 插入新用户（注册）
     */
    int insert(UserT userT);

    /**
     * 更新密码（修改密码）
     */
    int updatePassword(@Param("name") String name, @Param("password") String password);

    /**
     * 更新头像路径
     */
    int updateAvatar(@Param("name") String name, @Param("avatar") String avatar);
}
