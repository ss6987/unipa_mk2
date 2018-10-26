<%--
  Created by IntelliJ IDEA.
  User: satone
  Date: 2018/07/27
  Time: 19:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="Entity.Syllabus" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="timeTable" class="servlet.timetable.TimeTable" scope="session"/>
<jsp:useBean id="errorString" class="java.lang.String" scope="request"/>
<html lang="ja">
<head>
    <title>時間割表</title>
    <link rel="stylesheet" type="text/css" href="Design.css">
</head>
<body>
<table id="window"><tr><td class="ta-box">
        <form action="/Top" method="get">
            <button type="submit" name="top" class="btn_1">トップ</button>
        </form>
</td><td class="tb-box"></td></tr>
<tr><td class="c-box" colspan="2">
        <%
            if (!errorString.equals("")) {
        %>
        <%=errorString%>
        <%
            }
        %>

        <table BORDER="1" class="part">
            <tr>
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
                        List<Syllabus> tableSyllabusList = timeTable.getSyllabusList(week, time);
                        for (Syllabus syllabus : tableSyllabusList) {
                    %>
                    <form action="<%=request.getContextPath()%>/SyllabusDetail" method="post" name="form<%=week%><%=time%>_<%=syllabus.getSyllabusId()%>">
                        <input type="hidden" name="targetSyllabusId" value="<%=syllabus.getSyllabusId()%>"/>
                        <input type="hidden" name="action" value="detail"/>
                        <input type="hidden" name="backPage" value="timetable"/>
                        <a href="javascript:form<%=week%><%=time%>_<%=syllabus.getSyllabusId()%>.submit()">
                            <%=syllabus.getSyllabusName()%>,<%=syllabus.getMainTeacher()%>,<%=syllabus.getClassRoom()%>
                        </a>
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
</td></tr></table>
</body>
</html>