package com.webdemo.web.controller;

import com.webdemo.domain.User;
import com.webdemo.service.UserService;
import com.webdemo.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/loginServlet")
public class LoginServlet extends HttpServlet {

    UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        // 获得用户名和密码
        String name = req.getParameter("name");
        String pwd = req.getParameter("pwd");
        // 查询用户
        User user = userService.login(name, pwd);
        if (user != null) {
            req.getSession().setAttribute("currentUser", user);
            // 返回登录页面
            resp.sendRedirect(req.getContextPath() + "/index.jsp"); // 重定向要使用应用路径。而下面的转发不需要。
        } else {
            req.setAttribute("loginError", "用户名或密码错误");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }
}
