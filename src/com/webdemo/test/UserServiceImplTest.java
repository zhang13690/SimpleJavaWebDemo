package com.webdemo.test;

import com.webdemo.domain.User;
import com.webdemo.exception.UserAlreadyExistsException;
import com.webdemo.service.UserService;
import com.webdemo.service.impl.UserServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import java.util.Date;

public class UserServiceImplTest {

    // 维护UserService成员变量
    UserService userService = new UserServiceImpl();

    @Test
    public void registerTest() throws UserAlreadyExistsException {
        User user = new User();
        user.setName("张三");
        user.setBirthday(new Date());
        user.setEmail("zhangsan@demo.com");
        user.setPwd("123456");
        userService.register(user);
    }

    // 添加用户名重复就会抛出UserAlreadyExistsException异常。
    // 使用JUnit也能测试异常的发生
    @Test(expected = UserAlreadyExistsException.class) // 期望的异常
    public void registerTest2() throws UserAlreadyExistsException {
        User user = new User();
        user.setName("张三");
        user.setBirthday(new Date());
        user.setEmail("zhangsan@demo.com");
        user.setPwd("123456");
        userService.register(user);
    }

    // 测试登录
    @Test
    public void loginTest() {
        User zsUser = userService.login("张三", "123456");
        Assert.assertNotNull(zsUser); // 断言zsUser不为null
        User lsUser = userService.login("李四", "123456");
        Assert.assertNull(lsUser); // 断言lsUser为null
    }
}
