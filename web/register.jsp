<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8" />
    <title>注册</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/registerServlet" method="POST">
        <%-- 每行中，value属性值中和最后面的EL表达式是为了显示可能的回显信息和错误信息 --%>
        <p> 姓名：<input type="text" name="name" value="${userFormBean.name}"/> ${userFormBean.errorMap.name} </p>
        <p> 生日：<input type="date" name="birthday" value="${userFormBean.birthday}"/> ${userFormBean.errorMap.birthday} </p>
        <p> 邮箱：<input type="email" name="email" value="${userFormBean.email}"/> ${userFormBean.errorMap.email} </p>
        <p> 密码：<input type="password" name="pwd" value="${userFormBean.pwd}" /> ${userFormBean.errorMap.pwd} </p>
        <p> 确定密码：<input type="password" name="rePwd" value="${userFormBean.rePwd}" /> ${userFormBean.errorMap.rePwd} </p
        <p> <input type="submit"> </p>>
    </form>
</body>
</html>
