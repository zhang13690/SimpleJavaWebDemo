package com.webdemo.web.controller;

import com.webdemo.domain.User;
import com.webdemo.exception.UserAlreadyExistsException;
import com.webdemo.service.UserService;
import com.webdemo.service.impl.UserServiceImpl;
import com.webdemo.util.SimpleDateConverter;
import com.webdemo.web.bean.UserFormBean;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

// 注册时编码的重点
@WebServlet(urlPatterns = "/registerServlet")
public class RegisterServlet extends HttpServlet {

    UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String encoding = "UTF-8";
        req.setCharacterEncoding(encoding);
        resp.setContentType("text/html;charset=" + encoding);

        // 获取表单数据，封装到UserFormBean这个表单对象中。
        // 由于约定，表单提交的属性名和User属性名一致，因此可以使用BeanUtils将表单的提交参数封装到UserFormBean中。
        UserFormBean userFormBean = new UserFormBean();
        try {
            BeanUtils.populate(userFormBean, req.getParameterMap());
            // 验证数据
            if (!userFormBean.validate()) {
                // 不通过则回显数据
                req.setAttribute("userFormBean", userFormBean);
                req.getRequestDispatcher("/register.jsp").forward(req, resp);
                return ; // 显式地指明方法结束了。程序员一看就知道执行到这里该分支结束了
            } else {
                // 可以保存User数据。
                User user = new User();
                // 先将userFormBean对象的属性数据拷贝到真正的User对象中。
                // 我们还是使用BeanUtils。
                // 但有一点，UserFormBean里的数据都是String类型的，
                // 对于基本类型，BeanUtils自动帮我们解决，但其中有一个birthday时间类型，这需要我们注册一个转换器。
                ConvertUtils.register(new SimpleDateConverter(), Date.class);
                // 拷贝对象
                BeanUtils.copyProperties(user, userFormBean); // 参数1是拷贝的目标对象，参数2是拷贝的原始对象数据
                // 调用service保存数据
                try {
                    userService.register(user);
                    // 保存成功
                    resp.getWriter().write("保存成功！2秒后跳转到首页");
                    resp.setHeader("Refresh", "2;URL=" + req.getContextPath() + "/index.jsp");
                } catch (UserAlreadyExistsException e) {
                    // 说明用户已经存在。进行数据回显和提示
                    userFormBean.getErrorMap().put("name", "用户名已存在");
                    req.setAttribute("userFormBean", userFormBean);
                    req.getRequestDispatcher("/register.jsp").forward(req, resp);
                    return;
                }
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
