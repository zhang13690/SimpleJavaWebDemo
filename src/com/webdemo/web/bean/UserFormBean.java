package com.webdemo.web.bean;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class UserFormBean implements Serializable {
    // 这里就是和表单提交的参数对应了。这里要包含重复密码这个属性
    // 并且，由于是表单数据，这些数据类型都为设置为String类型(包括生日)。
    private String name; // 姓名
    private String email; // 邮箱
    private String birthday; // 生日
    private String pwd; // 密码
    private String rePwd; // 重复密码

    // 由于提交的User表单数据需要验证和回显错误信息等，因此这里封装一个error错误消息
    // 类型是Map<String, String>，key-value，一个字段就可以对应一个错误消息
    // 必须要创建出这个对象。否则这个成员是null。因为一开始的时候，并没有错误消息封装，故而如果不创建对象，会一致是null，使用时就出现空指针异常
    private Map<String, String> errorMap = new HashMap<>(); // 错误消息

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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getRePwd() {
        return rePwd;
    }

    public void setRePwd(String rePwd) {
        this.rePwd = rePwd;
    }

    public Map<String, String> getErrorMap() {
        return errorMap;
    }

    public void setErrorMap(Map<String, String> errorMap) {
        this.errorMap = errorMap;
    }

    @Override
    public String toString() {
        return "UserFormBean{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", birthday=" + birthday +
                ", pwd='" + pwd + '\'' +
                ", rePwd='" + rePwd + '\'' +
                ", errorMap=" + errorMap +
                '}';
    }

    // 在这个实体类中加入字段的验证，如果出现错误，就在errorMap中添加消息，以便回显
    public boolean validate() {
        // name验证
        if (name.isEmpty()) {
            errorMap.put("name", "请输入用户名");
        }
        // 密码验证
        if (pwd.isEmpty()) {
            errorMap.put("pwd", "请输入密码");
        } else if (!pwd.matches("\\d{3,8}")) {
            errorMap.put("pwd", "密码必须是3-8位数字");
        }
        // 重复密码验证
        if (!rePwd.equals(pwd)) {
            errorMap.put("rePwd", "两次密码不一致");
        }
        // 邮箱验证
        if (email.isEmpty()) {
            errorMap.put("email", "请输入邮箱");
        } else if (!email.matches("\\b^['_a-z0-9-\\+]+(\\.['_a-z0-9-\\+]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*\\.([a-z]{2}|aero|arpa|asia|biz|com|coop|edu|gov|info|int|jobs|mil|mobi|museum|name|nato|net|org|pro|tel|travel|xxx)$\\b")) {
            errorMap.put("email", "请输入正确的邮箱");
        }
        // 验证输入日期
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        if (birthday.isEmpty()) {
            errorMap.put("birthday", "请输入生日");
        } else {
            try {
                df.parse(birthday);
            } catch (ParseException e) {
                // 出现异常说明生日日期格式不符合要求
                errorMap.put("birthday", "生日日期格式必须满足yyyy-MM-dd的要求");
                throw new RuntimeException(e);
            }

        }
        // 最后返回值的时候，如何知道是否验证成功呢？实际上我们只要返回errorMap是否为空即可。
        // 因为所有验证情况都在上面考虑过了，如果验证不成功，errorMap中一定不是空的
        return errorMap.isEmpty();
    }

}
