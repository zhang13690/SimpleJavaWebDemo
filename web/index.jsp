<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <c:choose>
        <c:when test="${empty sessionScope.currentUser}">
            <%-- 显示登录和注册按钮 --%>
            <a href="${pageContext.request.contextPath}/login.jsp">登录</a>
            <a href="${pageContext.request.contextPath}/register.jsp">注册</a>
        </c:when>
        <c:otherwise>
            <%-- 显示登录信息和注销按钮 --%>
            欢迎你：${sessionScope.currentUser.name}
            <a href="${pageContext.request.contextPath}/logoutServlet">注销</a>
        </c:otherwise>
    </c:choose>
</body>
</html>
