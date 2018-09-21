<%--
  Created by IntelliJ IDEA.
  User: ASAMI
  Date: 2018/07/27
  Time: 3:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>シラバス検索</title>
</head>
<body>
<form action="/Top" method="get">
    <button type="submit">トップ</button>
</form>
</button>
<br>
<h1>シラバス検索</h1>
<br>
<%
    if (!request.getAttribute("errorString").equals("")) {
%>
<span style="background-color:#ffcc99">
    <%=request.getAttribute("errorString")%>
</span><br>
<%
    }
%>
<form action="/SyllabusSearch" method="post">
    <table BORDER="1" align="center">
        <tr>
            <th>科目ID</th>
            <td>
                <input type="text" name="syllabus_id">
            </td>

        </tr>

        <tr align="center">
            <th>科目名</th>
            <td>
                <input type="text" name="syllabus_name">
            </td>
        </tr>

        <tr>
            <th>英語名</th>
            <td>
                <input type="text" name="english_name">
            </td>
        </tr>

        <tr align="center">
            <th>配当学年</th>
            <td>
                <input type="text" name="dividend_grade">
            </td>
        </tr>

        <tr align="center">
            <th>開講年度</th>
            <td>
                <input type="text" name="year">
            </td>
        </tr>

        <tr align="center">
            <th>学期</th>
            <td>
                <input type="text" name="semester">
            </td>
        </tr>
        <tr>
            <th>曜日</th>
            <td>
                <input type="text" name="week">
            </td>
        </tr>
        <tr>
            <th>時限</th>
            <td>
                <input type="text" name="time">
            </td>
        </tr>

        <tr align="center">
            <th>科目分類</th>
            <td>
            </td>
        </tr>

        <tr align="center">
            <th>担当教員</th>
            <td>
            </td>
        </tr>

        <tr align="center">
            <th>単位数</th>
            <td>
                <input type="text" name="unit">
            </td>
        </tr>

        <tr align="center">
            <th>定員</th>
            <td>
                <input type="text" name="capacity">
            </td>
        </tr>

        <tr align="center">
            <th>関連科目</th>
            <td>
            </td>
        </tr>

        <tr align="center">
            <th>履修条件</th>
            <td>
            </td>
        </tr>

        <tr align="center">
            <th>教室</th>
            <td>
                <input type="text" name="classroom"/>
            </td>
        </tr>

    </table>
    <button type="submit" name="action" value="search">検索</button>
</form>
</body>
</html>
