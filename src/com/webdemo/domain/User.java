package com.webdemo.domain;

import java.io.Serializable;
import java.util.Date;

// 实现序列化接口
public class User implements Serializable {
    private String name; // 姓名
    private String email; // 邮箱
    private Date birthday; // 生日
    private String pwd; // 密码
    // 下面写好getter和setter方法、toString方法

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", birthday=" + birthday +
                ", pwd='" + pwd + '\'' +
                '}';
    }
}
