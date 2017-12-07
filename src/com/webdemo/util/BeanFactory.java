package com.webdemo.util;

import com.webdemo.dao.UserDao;

import java.io.InputStream;
import java.util.Properties;

public class BeanFactory {

    private BeanFactory() {}

    private static String userDaoClass;
    static {
        try {
            // 读取配置文件
            InputStream inputStream = BeanFactory.class.getClassLoader().getResourceAsStream("dao.properties");
            Properties properties = new Properties();
            properties.load(inputStream);
            userDaoClass = properties.getProperty("userDaoClass");
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    /**
     * 根据配置文件返回创建的UserDao对象
     * @return UserDao对象
     */
    public static UserDao getUserDao() {
        try {
            return (UserDao) Class.forName(userDaoClass).newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
