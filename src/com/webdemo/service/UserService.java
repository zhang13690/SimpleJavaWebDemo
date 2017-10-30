package com.webdemo.service;

import com.webdemo.domain.User;
import com.webdemo.exception.UserAlreadyExistsException;

public interface UserService {

    /**
     * 用户注册
     * @param user 要注册的用户信息
     * @throws UserAlreadyExistsException 若注册的用户名已经存在了，就抛出此异常
     */
    void register(User user) throws UserAlreadyExistsException;

    /**
     * 用户登录
     * @param name 用户姓名
     * @param pwd 用户密码
     * @return 返回登录的用户信息。如果用户名或密码错误，则返回null。
     */
    User login(String name, String pwd);
}
