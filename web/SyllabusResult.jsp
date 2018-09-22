<%@ page import="Entity.Syllabus" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: ASAMI
  Date: 2018/07/27
  Time: 4:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ja">
<head>
    <title>シラバス検索結果</title>
</head>
<body>
<%
    long start = System.currentTimeMillis();
    List<Syllabus> syllabusList = (List<Syllabus>) request.getAttribute("syllabusList");
%>

<form action="/Top" method="get">
    <button type="submit" name="check"> 再検索</button>
</form>

<h1>
    検索結果
</h1>
<%=(Integer)request.getAttribute("result_count")%>件
<br>


<br>

<table BORDER="1" align="center">
    <tr>
        <th>科目ID</th>
        <th>科目名</th>
        <th>担当教員</th>
        <th>学期</th>
        <th>曜日</th>
        <th>時限</th>
        <th>教室</th>
    </tr>
    <%
        for (int i = 0; i < syllabusList.size(); i++) {
            Syllabus syllabus = syllabusList.get(i);
    %>
    <tr align="center">
        <td>
            <%=syllabus.getSyllabusId()%>
        </td>
        <td>
            <form action="/SyllabusDetail" method="post" name="form<%=i%>">
                <input type="hidden" name="targetSyllabusId" value="<%=syllabus.getSyllabusId()%>"/>
                <a href="javascript:form<%=i%>.submit()"><%=syllabus.getSyllabusName()%>
                </a>
            </form>
        </td>
        <td>
            <%=syllabus.getMainTeacher()%>
        </td>
        <td>
            <%=syllabus.getSemester()%>
        </td>
        <td>
            <%=syllabus.getWeek()%>
        </td>
        <td>
            <%=syllabus.getTime()%>
        </td>
        <td>
            <%=syllabus.getClassRoom()%>
        </td>
    </tr>
    <%
        }
    %>

</table>
<form action="/SyllabusSearch" method="post">

</form>
</body>
</html>
