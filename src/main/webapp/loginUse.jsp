<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script type="text/javascript" src="static/js/jquery-1.7.2.js"></script>
    <script type="text/javascript">
        alert("请先登录");
        window.top.location.href="${pageContext.request.contextPath}/login.jsp";
    </script>
    <title>Title</title>
</head>
<body>

</body>
</html>
