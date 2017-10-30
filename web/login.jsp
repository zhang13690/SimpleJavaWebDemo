<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8" />
    <title>首页</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/loginServlet" method="POST">
        <%-- 约定优于配置的开发原则：表单的name属性值和实体的属性名称一致 --%>
        <c:if test="${!empty loginError}">
            <p>用户名或密码错误</p>
        </c:if>
        <p>用户名： <input type="text" name="name" /></p>
        <p>密码：<input type="password" name="pwd" /></p>
        <p><input type="submit" /> </p>
    </form>

</body>
</html>
