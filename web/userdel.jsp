<%--
  Created by IntelliJ IDEA.
  User: ASAMI
  Date: 2018/07/13
  Time: 20:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ユーザー削除確認</title>
</head>
<body>
<%
    //InputCheckServletでセットした時のキーで値を取り出す。
    String name = (String) session.getAttribute("name");
    String say = (String) session.getAttribute("say");
    String may = (String) session.getAttribute("may");
    String saykana = (String) session.getAttribute("saykana");
    String maykana = (String) session.getAttribute("maykana");
    String sex = request.getParameter("sex");
    String bunrui1 = request.getParameter("bunrui1");
    String bunrui2 = request.getParameter("bunrui2");
    String nen = (String) session.getAttribute("nen");
    String tsuki = (String) session.getAttribute("tsuki");
    String hi = (String) session.getAttribute("hi");
    String yuubin = (String) session.getAttribute("yuubin");
    String jusho = (String) session.getAttribute("jusho");
    String denwa = (String) session.getAttribute("denwa");



%>


<h1>  <button type="submit" name="check"> トップ </button></h1>
<button type="submit" name="login" style="position: absolute; right:  0px; top: 0px">もどる</button>
<h1>ユーザー削除</h1>
<br>

以下の学生情報を完全に削除します。

<br>


<table BORDER="1" align="center">
<tr align="center">
    <td>学籍番号</td>
    <td><%=name%></td>
</tr>

<tr align="center">
    <td>氏名<br>フリガナ</td>
    <td>姓<%=say%>名<%=may%>
        <br>セイ<%=saykana%>メイ<%=maykana%></td>
</tr>

    <tr align="center">
        <td>性別</td>
        <td><%=sex%></td>
    </tr>

    <tr align="center">
        <td>ユーザー分類</td>
        <td><%=bunrui1%></td>
    </tr>


    <tr align="center">
        <td>学部・学科</td>
        <td><%=bunrui2%></td>
    </tr>




<tr align="center">
    <td>生年月日</td>
    <td>西暦<%=nen%>年<%=tsuki%>月<%=hi%>日</td>
</tr>

<tr align="center">
    <td>郵便番号</td>
    <td><%=yuubin%></td>
</tr>

<tr align="center">
    <td>住所</td>
    <td><%=jusho%></td>
</tr>

<tr align="center">
    <td>電話番号</td>
    <td><%=denwa%></td>
</tr>

</table>




<button type="submit" name="check" align="center"> 削除 </button>

</body>
</html>
