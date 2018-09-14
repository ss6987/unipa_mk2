<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="user" scope="session" class="Entity.User"/>
<html>
<head>
    <title>
        <jsp:getProperty name="user" property="userId"/>
    </title>
</head>
<body>
<h1>
    成功<br>
</h1>
<img src="error/fuck.jpg" alt="中指" title="FUCK">
</body>
</html>
