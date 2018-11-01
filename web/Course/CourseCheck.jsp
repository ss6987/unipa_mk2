<%@ page import="Entity.Syllabus" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: satone
  Date: 2018/07/27
  Time: 18:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="nowTable" class="etc.timetable.TimeTable" scope="session"/>
<jsp:useBean id="errorString" class="java.lang.String" scope="request"/>
<html lang="ja">
<head>
    <title>履修登録確認</title>
    <link rel="stylesheet" type="text/css" href="Design.css">
</head>
<body>
<form action="/Main" method="post">
    <button type="submit" name="action" value="Top" class="btn_1" style="position: absolute; left: 0px; top: 0px">トップ</button>
</form>
<br>
<h1>履修登録</h1>

<%
    if (!errorString.equals("")) {
%>
<span style="background-color:#ffcc99"><%=errorString%></span>
<%
    }
%>

<table BORDER="1" align="center">
    <tr align="center">
        <th></th>
        <th>月</th>
        <th>火</th>
        <th>水</th>
        <th>木</th>
        <th>金</th>
        <th>土</th>
    </tr>
    <%
        for (int time = 1; time < 9; time++) {
    %>
    <tr>
        <th><%=time%>
        </th>
        <%
            for (int week = 1; week < 7; week++) {
        %>
        <td>
            <%
                List<Syllabus> tableSyllabusList = nowTable.getSyllabusList(week, time);
                for (Syllabus syllabus : tableSyllabusList) {
            %>
            <%=syllabus.getSyllabusName()%>
            <%
                }
            %>
        </td>
        <%
            }
        %>
    </tr>
    <%
        }
    %>
</table>

<%=nowTable.getTotalUnit()%>

<form action="/CourseRegistration" method="post">
    <button type="submit" name="action" class="btn_1" value="back">戻る</button>
    <button type="submit" name="action" class="btn_4" value="registration">登録</button>
</form>



</body>
</html>
