<%@ page import="Entity.User" %>
<%@ page import="java.util.List" %>
<%@ page import="Entity.Course" %><%--
  Created by IntelliJ IDEA.
  User: ASAMI
  Date: 2018/07/20
  Time: 18:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Course> courseList = (List<Course>) request.getAttribute("courseList");
    List<User> studentList = (List<User>) request.getAttribute("studentList");
    List<String> targetUserIdList = (List<String>) request.getAttribute("targetUserIdList");
%>
<html lang="ja">
<head>
    <title>履修情報削除確認</title>
    <link rel="stylesheet" type="text/css" href="Design.css">
</head>
<body>
<form action="/Top" method="get">
    <button type="submit" class="btn_1" name="check"> トップ</button>
</form>
<button type="submit" name="login" class="btn_1" style="position: absolute; right:  0px; top: 0px">もどる</button>

<h1>履修情報削除確認</h1>
<h2>履修情報一覧</h2>
以下の履修情報を削除しますか
<br>
<table BORDER="1" align="center">

    <tr align="center">
        <th>学籍番号</th>
        <th>氏名</th>
        <th>成績</th>

    </tr>
    <%
        for (int i = 0; i < courseList.size(); i++) {
            Course course = courseList.get(i);
            User user = studentList.get(i);
            if (targetUserIdList.indexOf(user.getUserId()) != -1) {
    %>
    <tr>
        <td><%=user.getUserId()%>
        </td>
        <td><%=user.getName()%>
        </td>
        <td><%=course.getAchievementString()%>
        </td>
    </tr>
    <%
            }
        }
    %>

</table>

<form action="/CourseCheck" method="post">
    <button type="submit" name="action" value="delete" class="btn_3" align="center"> 削除</button>
</form>
</body>
</html>
