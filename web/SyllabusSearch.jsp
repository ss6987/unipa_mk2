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
<span style="background-color:#ffcc99">※正しく入力されていません</span><br>
<form action="/SyllabusSearch" method="post">
    <table BORDER="1" align="center">
        <tr align="center">
            <th>科目ID</th>
            <td><input type="text" ></td>

        </tr>

        <tr align="center">
            <th>科目名</th>
            <td><input type="text"></td>
        </tr>

        <tr align="center">
            <th>学年</th>
            <td><input type="text"></td>
        </tr>
        <tr align="center">
            <th>学期</th>
            <td><input type="text"></td>
        </tr>
        <tr>
            <th>曜日</th>
            <td><input type="text"></td>
        </tr>
        <tr>
            <th>時限</th>
            <td><input type="text"></td>
        </tr>

        <tr align="center">
            <th>科目分類</th>
            <td><input type="text"></td>
        </tr>

        <tr align="center">
            <th>担当教員</th>
            <td><input type="text"></td>
        </tr>

        <tr align="center">
            <th>単位数</th>
            <td><input type="text"></td>
        </tr>
        <tr align="center">
            <th>関連科目</th>
            <td><input type="text"></td>
        </tr>

        <tr align="center">
            <th>履修条件</th>
            <td><input type="text"></td>
        </tr>

    </table>
    <button type="submit" name="kensakubutton">検索</button>
</form>
</body>
</html>
