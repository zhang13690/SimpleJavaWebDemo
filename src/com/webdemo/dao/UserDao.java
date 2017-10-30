package com.webdemo.dao;

import com.webdemo.domain.User;

public interface UserDao {

    /**
     * 根据姓名查询用户
     * @param name 姓名
     * @return 返回用户信息。如果不存在则返回null
     */
    User findUser(String name);

    /**
     * 根据姓名和密码查询用户
     * @param name 姓名
     * @param pwd 密码
     * @return 返回用户信息。如果不存在则返回null
     */
    User findUser(String name, String pwd);

    /**
     * 保存用户信息
     * @param user 用户信息
     */
    void saveUser(User user);
}
