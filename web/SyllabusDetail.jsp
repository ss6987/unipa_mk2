<%@ page import="Entity.SyllabusDetail" %><%--
  Created by IntelliJ IDEA.
  User: SS
  Date: 2018/09/19
  Time: 11:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    SyllabusDetail syllabusDetail = (SyllabusDetail) request.getAttribute("targetSyllabus");
%>
<html lang="ja">
<head>
    <title><%=syllabusDetail.getSyllabusName()%></title>
</head>
<body>
<%=syllabusDetail.getSyllabusName()%>
<%=syllabusDetail.getSyllabusId()%>
</body>
</html>
