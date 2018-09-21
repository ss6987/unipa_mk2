<%--
  Created by IntelliJ IDEA.
  User: ASAMI
  Date: 2018/07/27
  Time: 20:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ja">
<head>
    <title>成績参照</title>
</head>
<body>
<%
    //InputCheckServletでセットした時のキーで値を取り出す。
    String kamokuID = (String) session.getAttribute("kamokuID");
    String tani = (String) session.getAttribute("tani");
    String kyouinID = (String) session.getAttribute("kyouinID");
    String bunrui1 = request.getParameter("bunrui1");
    String bunrui2 = request.getParameter("bunrui2");
    String nen = (String) session.getAttribute("nen");
    String gakki = (String) session.getAttribute("gakki");
    String hyouka = (String) session.getAttribute("hyouka");
%>

<h1>  <button type="submit" name="check"> トップ </button></h1>

<br>
<table  BORDER="1" align="left">
    <tr><td>表示パターン</td></tr>
    <tr>

        <td><input type=radio name="pattern" value="nomal">通常<br>
            <input type=radio name="pattern" value="nengak">年度学期</td>
    </tr>
    <tr><td>表示設定</td></tr>
    <tr><td>
<form action="cgi-bin/abc.cgi" method="post">
    <input type="checkbox" name="hyouzi" value="1">評価名称<br>
    <input type="checkbox" name="hyouzi" value="2">不合格科目<br>
    <input type="checkbox" name="hyouzi" value="3">履修中科目<br>
    <input type="checkbox" name="hyouzi" value="4">単位取得情報欄<br>

    </form>
    </td></tr>


</table>

<table BORDER="1" align="center">
    <tr align="center">
        <td>成績照会</td>

    </tr>
    <tr align="center">
        <td>科目名</td><td>単位</td><td>評価</td><td>年度</td><td>学期</td><td>教員名</td>
    </tr>
    <tr align="center">
        <td>＜人間科学科目＞</td>
    </tr>
    <tr align="center">
        <td><%=kamokuID%></td>
        <td><%=tani%></td>
        <td><%=hyouka%></td>
        <td><%=nen%></td>
        <td><%=gakki%></td>
        <td><%=kyouinID%></td>
    </tr>
    <tr align="center">
        <td>＜他部人間科学科目＞</td>
    </tr>
    <tr align="center">
        <td><%=kamokuID%></td>
        <td><%=tani%></td>
        <td><%=hyouka%></td>
        <td><%=nen%></td>
        <td><%=gakki%></td>
        <td><%=kyouinID%></td>
    </tr>
    <tr align="center">
        <td>＜英語科目＞</td>
    </tr>
    <tr align="center">
        <td><%=kamokuID%></td>
        <td><%=tani%></td>
        <td><%=hyouka%></td>
        <td><%=nen%></td>
        <td><%=gakki%></td>
        <td><%=kyouinID%></td>
    </tr>
    <tr align="center">
        <td>＜専門必修科目＞</td>
    </tr>
    <tr align="center">
        <td><%=kamokuID%></td>
        <td><%=tani%></td>
        <td><%=hyouka%></td>
        <td><%=nen%></td>
        <td><%=gakki%></td>
        <td><%=kyouinID%></td>
    </tr>
    <tr align="center">
        <td>＜専門選択科目＞</td>
    </tr>
    <tr align="center">
        <td><%=kamokuID%></td>
        <td><%=tani%></td>
        <td><%=hyouka%></td>
        <td><%=nen%></td>
        <td><%=gakki%></td>
        <td><%=kyouinID%></td>
    </tr>
    <tr align="center">
    <td>＜他部専門選択科目＞</td>
</tr>
    <tr align="center">
        <td><%=kamokuID%></td>
        <td><%=tani%></td>
        <td><%=hyouka%></td>
        <td><%=nen%></td>
        <td><%=gakki%></td>
        <td><%=kyouinID%></td>
    </tr>
</table>





</body>
</html>
