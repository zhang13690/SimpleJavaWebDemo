package com.webdemo.dao.impl;

import com.webdemo.dao.UserDao;
import com.webdemo.domain.User;
import com.webdemo.util.DOM4JUtil;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class UserDaoXMLImpl implements UserDao {
    @Override
    public User findUser(String name) {
        // 这里是根据name属性找到user节点，并封装成User对象返回。
        // 由于要处理异常，把下面的代码用try...catch包含起来处理。
        try {
            Document document = DOM4JUtil.getDocument();
            Node node = document.selectSingleNode("//user[@name='" + name + "']"); // 使用XPath语法
            if (node == null) {
                return null; // 找不到直接返回null
            } else {
                // 封装对象
                User user = new User();
                user.setName(node.valueOf("@name"));
                user.setPwd(node.valueOf("@pwd"));
                user.setEmail(node.valueOf("@email"));
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                user.setBirthday( sdf.parse(node.valueOf("@birthday")) );; // 转成Date对象存储
                return user;
            }
        } catch (ParseException | DocumentException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User findUser(String name, String pwd) {
        // 实现和上述方法类似
        try {
            Document document = DOM4JUtil.getDocument();
            Node node = document.selectSingleNode("//user[@name='" + name + "' and @pwd='" + pwd + "']");
            if (node == null) {
                return null; // 找不到直接返回null
            } else {
                // 封装对象
                User user = new User();
                user.setName(node.valueOf("@name"));
                user.setPwd(node.valueOf("@pwd"));
                user.setEmail(node.valueOf("@email"));
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                user.setBirthday( sdf.parse(node.valueOf("@birthday")) );; // 转成Date对象存储
                return user;
            }
        } catch (ParseException | DocumentException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveUser(User user) {
        // 保存user对象
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Document document = DOM4JUtil.getDocument();
            Element root = document.getRootElement();
            root.addElement("user")
                    .addAttribute("name", user.getName())
                    .addAttribute("pwd", user.getPwd())
                    .addAttribute("email", user.getEmail())
                    .addAttribute("birthday", sdf.format( user.getBirthday() ));
            DOM4JUtil.write2XML(document);
        } catch (IOException | DocumentException e) {
            throw new RuntimeException(e);
        }
    }
}
