<%@ page import="Entity.User" %>
<%@ page import="java.util.List" %>
<%@ page import="Entity.Course" %><%--
  Created by IntelliJ IDEA.
  User: satone
  Date: 2018/07/20
  Time: 18:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Course> courseList = (List<Course>) request.getAttribute("courseList");
    List<User> studentList = (List<User>) request.getAttribute("studentList");
%>
<html lang="ja">
<head>
    <title>履修者一覧</title>
    <link rel="stylesheet" type="text/css" href="Design.css">
</head>
<body>
<form action="/Top" method="get">
    <button type="submit" class="btn_1" name="top">トップへ</button>
</form>
<button type="submit" name="action" class="btn_1" value="back" onclick="history.back()">戻る</button>

<%--エラー（仮）--%>
<h1>履修者一覧</h1>
<span style="background-color:#ffcc99">※正しく入力されていません</span>
<br>

<table BORDER="1" align="center">
    <tr align="center">
        <th>学籍番号</th>
        <th>氏名</th>
        <th>成績</th>
        <th>受講年度</th>
        <th>主担当教員</th>
    </tr>

    <%
        for (int i = 0; i < courseList.size(); i++) {
            Course course = courseList.get(i);
            User user = studentList.get(i);
    %>
    <tr>
        <td>
            <%=user.getUserId()%>
        </td>
        <td>
            <%=user.getName()%>
        </td>
        <td>
            <%=course.getAchievementString()%>
        </td>
        <td>
            <%=course.getYear()%>
        </td>
        <td>
            <%=course.getMainTeacherName()%>
        </td>
    </tr>
    <%
        }
    %>
</table>
</body>
</html>
