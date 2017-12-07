package com.webdemo.service.impl;

import com.webdemo.dao.UserDao;
import com.webdemo.dao.impl.UserDaoDBImpl;
import com.webdemo.dao.impl.UserDaoXMLImpl;
import com.webdemo.domain.User;
import com.webdemo.exception.UserAlreadyExistsException;
import com.webdemo.service.UserService;
import com.webdemo.util.BeanFactory;

public class UserServiceImpl implements UserService {

    // 维护UserDao成员变量
    UserDao userDao = BeanFactory.getUserDao();

    @Override
    public void register(User user) throws UserAlreadyExistsException {
        // 首先，如果user是null，直接抛出非法参数异常。这是JDK提供的异常类型，可以直接使用。
        if (user == null) {
            throw new IllegalArgumentException("参数user不能为null");
            // 说明：虽然该方法签名声明的是抛出UserAlreadyExistsException，但方法体中主动抛出其他类型的异常也是没问题的。
            // 程序走到这里就会报出异常并停止，在这里这样处理并无不妥，因为参数为null这里显然是非法的，直接出错即可。
            // 这是调用者自然要注意的问题。而签名上的UserAlreadyExistsException则是提示调用者应该处理此异常，有此异常说明用户名重复，处理后程序也能正常运行。
        }
        // 查询用户名是否存在，如果存在则抛出UserAlreadyExistsException异常，否则保存user即可。
        if ( userDao.findUser(user.getName()) != null ) {
            throw new UserAlreadyExistsException("用户名：\"" + user.getName() + "\" 已经存在");
        } else {
            userDao.saveUser(user);
        }
    }

    @Override
    public User login(String name, String pwd) {
        return userDao.findUser(name, pwd);
    }
}
