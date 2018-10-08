<%@ page import="Entity.Course" %>
<%@ page import="java.util.List" %>
<%@ page import="Entity.Syllabus" %><%--
  Created by IntelliJ IDEA.
  User: ASAMI
  Date: 2018/07/27
  Time: 20:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Course> courseList = (List<Course>) request.getAttribute("courseList");
    List<Syllabus> syllabusList = (List<Syllabus>) request.getAttribute("syllabusList");
%>
<html lang="ja">
<head>
    <title>成績参照</title>
    <link rel="stylesheet" type="text/css" href="Design.css">
</head>
<body>

<form action="/Top" method="get">
    <button type="submit" class="btn_1" name="check"> トップ</button>
</form>
<h1>
    成績照会
</h1>

<br>

<table BORDER="1" align="center">
    <tr align="center">
        <th>授業名</th>
        <th>単位</th>
        <th>評価</th>
        <th>年度</th>
        <th>学期</th>
        <th>教員名</th>
    </tr>
    <%
        for (int i = 0; i < courseList.size(); i++) {
            Course course = courseList.get(i);
            Syllabus syllabus = syllabusList.get(i);
    %>
    <tr>
        <td>
            <%=syllabus.getSyllabusName()%>
        </td>
        <td>
            <%=syllabus.getUnitString()%>
        </td>
        <td>
            <%=course.getAchievementString()%>
        </td>
        <td>
            <%=course.getYear()%>
        </td>
        <td>
            <%=syllabus.getSemester()%>
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
