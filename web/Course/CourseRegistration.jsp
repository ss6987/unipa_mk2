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
<jsp:useBean id="nowTable" class="servlet.timetable.TimeTable" scope="session"/>
<jsp:useBean id="errorString" class="java.lang.String" scope="request"/>
<html lang="ja">
<head>
    <title>履修登録</title>
</head>
<body>
<form action="/Top" method="get">
    <button type="submit" name="top" style="position: absolute; left: 0px; top: 0px">トップへ</button>
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

<form action="/CourseRegistration" method="post">
    <button type="submit" name="action" value="check">登録</button>
</form>
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
            <form action="<%=request.getContextPath()%>/CourseRegistration" method="post">
                <div align="right">
                    <input type="hidden" name="week" value="<%=week%>">
                    <input type="hidden" name="time" value="<%=time%>">
                    <button type="submit" name="action" value="firstSearch">検索</button>
                </div>
            </form>
            <%
                List<Syllabus> tableSyllabusList = nowTable.getSyllabusList(week, time);
                for (Syllabus syllabus : tableSyllabusList) {
            %>
            <form action="<%=request.getContextPath()%>/CourseRegistration" method="post">
                <input type="hidden" name="targetSyllabusId" value="<%=syllabus.getSyllabusId()%>"/>
                <%=syllabus.getSyllabusName()%>,<%=syllabus.getMainTeacher()%>
                <button type="submit" name="action" value="delete">削除</button>
            </form>
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
<br>


</body>
</html>
