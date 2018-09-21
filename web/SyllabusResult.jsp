<%@ page import="Entity.Syllabus" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: ASAMI
  Date: 2018/07/27
  Time: 4:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>シラバス検索結果</title>
</head>
<body>
<%
    List<Syllabus> syllabusList = (List<Syllabus>) request.getAttribute("syllabusList");

%>

<h1>
    <button type="submit" name="check"> 再検索</button>
</h1>

<br>

検索結果

<br>

<table BORDER="1" align="center">


    <tr align="center">
        <th>科目ID</th>
        <th>科目名</th>
        <th>担当教員</th>
        <th>科目分類</th>
        <th>学期</th>
        <th>曜日</th>
        <th>時限</th>
        <th>参照</th>
    </tr>

    <tr align="center">
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
    </tr>

</table>


<button type="submit" name="ue"><a href="#top">上へ戻る</a></button>
<button type="submit" name="check" align="center"> 戻る</button>
</body>
</html>
