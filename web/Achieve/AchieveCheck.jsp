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
</head>
<body>

<form action="/Top" method="get">
    <button type="submit" name="check"> トップ</button>
</form>
<h1>
    成績照会
</h1>

<br>
<%--<table BORDER="1" align="left">--%>
<%--<tr>--%>
<%--<td>表示パターン</td>--%>
<%--</tr>--%>
<%--<tr>--%>
<%--<td><input type=radio name="pattern" value="nomal">通常<br>--%>
<%--<input type=radio name="pattern" value="nengak">年度学期--%>
<%--</td>--%>
<%--</tr>--%>
<%--<tr>--%>
<%--<td>表示設定</td>--%>
<%--</tr>--%>
<%--<tr>--%>
<%--<td>--%>
<%--<form action="cgi-bin/abc.cgi" method="post">--%>
<%--<input type="checkbox" name="hyouzi" value="1">評価名称<br>--%>
<%--<input type="checkbox" name="hyouzi" value="2">不合格科目<br>--%>
<%--<input type="checkbox" name="hyouzi" value="3">履修中科目<br>--%>
<%--<input type="checkbox" name="hyouzi" value="4">単位取得情報欄<br>--%>

<%--</form>--%>
<%--</td>--%>
<%--</tr>--%>


<%--</table>--%>

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
