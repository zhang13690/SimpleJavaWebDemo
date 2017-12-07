package com.webdemo.dao.impl;

import com.webdemo.dao.UserDao;
import com.webdemo.domain.User;
import com.webdemo.exception.DaoException;
import com.webdemo.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDaoDBImpl implements UserDao {
    @Override
    public User findUser(String name) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtil.getConnection();
            stmt = conn.prepareStatement("SELECT * FROM `user` WHERE name = ?;");
            stmt.setString(1, name);
            rs = stmt.executeQuery();
            User user = null;
            while (rs.next()) {
                user = new User();
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setBirthday(rs.getDate("birthday"));
                user.setPwd(rs.getString("pwd"));
            }
            return user;
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            JdbcUtil.release(conn, stmt, rs);
        }
    }

    @Override
    public User findUser(String name, String pwd) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtil.getConnection();
            stmt = conn.prepareStatement("SELECT * FROM `user` WHERE name = ? and pwd = ?;");
            stmt.setString(1, name);
            stmt.setString(2, pwd);
            rs = stmt.executeQuery();
            User user = null;
            while (rs.next()) {
                user = new User();
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setBirthday(rs.getDate("birthday"));
                user.setPwd(rs.getString("pwd"));
            }
            return user;
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            JdbcUtil.release(conn, stmt, rs);
        }
    }

    @Override
    public void saveUser(User user) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = JdbcUtil.getConnection();
            stmt = conn.prepareStatement("INSERT INTO `user` VALUES (?,?,?,?);");
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setDate(3, new java.sql.Date(user.getBirthday().getTime()));
            stmt.setString(4, user.getPwd());
            stmt.executeUpdate();
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            JdbcUtil.release(conn, stmt);
        }
    }
}
