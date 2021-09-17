package com.eh00.mapper;

import com.eh00.entity.User;

import java.util.List;

public interface UserMapper {

    List<User> listUser();

    /**
     * 根据id查询
     * @param userId 用户id
     * @return 用户
     */
    User getUserById(Integer userId);

    void update(User user);

    void delete(Integer id);

    User getUserByNameOrEmail(String str);

    User getUserByName(String name);

    User getUserByEmail(String email);
}
