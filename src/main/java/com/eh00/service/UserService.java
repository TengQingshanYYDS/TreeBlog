package com.eh00.service;

import com.eh00.entity.User;

import java.util.List;

public interface UserService {
    /**
     * 获得用户列表
     * @return
     */
    List<User> listUser();

    /**
     * 根据id查询用户信息
     * @param id
     * @return
     */
    User getUserById(Integer id);

    /**
     * 修改用户信息
     * @param user
     */
    void updateUser(User user);

    /**
     * 删除用户信息
     * @param id
     */
    void deleteUser(Integer id);

    /**
     * 添加用户
     * @param user
     * @return
     */
    User insertUser(User user);

    /**
     * 根据用户名和邮箱查询用户
     * @param str
     * @return
     */
    User getUserByNameOrEmail(String str);

    /**
     * 根据用户名查询用户
     * @param name
     * @return
     */
    User getUserByName(String name);

    /**
     * 根据邮箱查询用户
     * @param email
     * @return
     */
    User getUserByEmail(String email);
}
