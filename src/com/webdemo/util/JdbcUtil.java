package com.webdemo.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JdbcUtil {
    // 工具类禁止创建实例
    private JdbcUtil() {}
    // 用于保存连接信息的Properties静态对象
    private static Properties properties;

    static {
        // 加载文件
        InputStream inputStream = JdbcUtil.class.getClassLoader().getResourceAsStream("db.properties");
        try {
            properties = new Properties();
            properties.load(inputStream);
        } catch (IOException ex) {
            // 直接抛出初始化异常错误，文件加载错误
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * 获得数据库连接对象
     * @return 数据库连接对象
     * @throws Exception 连接异常
     */
    public static Connection getConnection() throws Exception {
        Class.forName(properties.getProperty("driverClass"));
        // 获得connection时用到properties对象，但其中的driverClass和url键不是连接url的属性，
        // 因此这里克隆一个urlProp，并去掉其中的driverClass和url键
        Properties urlProp = (Properties) properties.clone();
        urlProp.remove("driverClass");
        urlProp.remove("url");
        // 获得连接
        return DriverManager.getConnection(properties.getProperty("url"), urlProp);
    }

    /**
     * 释放资源
     * @param conn 连接资源
     * @param stmt statement资源
     * @param rs 结果集资源
     */
    public static void release(Connection conn, Statement stmt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            rs = null;
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            stmt = null;
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            conn = null;
        }
    }

    /**
     * 释放资源
     * @param conn 连接资源
     * @param stmt statement资源
     */
    public static void release(Connection conn, Statement stmt) {
        // 这里需要调用上面的方法，最后一个参数给null即可。
        release(conn, stmt, null);
    }
}
