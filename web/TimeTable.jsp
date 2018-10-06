<%--
  Created by IntelliJ IDEA.
  User: satone
  Date: 2018/07/27
  Time: 19:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ja">
<head>
    <title>時間割表</title>
    <link rel="stylesheet" type="text/css" href="Design.css">
</head>
<body>
<div>
<%
    //InputCheckServletでセットした時のキーで値を取り出す。
    String mon1 = request.getParameter("mon1");
    String mon2 = request.getParameter("mon2");
    String mon3 = request.getParameter("mon3");
    String mon4 = request.getParameter("mon4");
    String mon5 = request.getParameter("mon5");
    String mon6 = request.getParameter("mon6");
    String mon7 = request.getParameter("mon7");
    String tue1 = request.getParameter("tue1");
    String tue2 = request.getParameter("tue2");
    String tue3 = request.getParameter("tue3");
    String tue4 = request.getParameter("tue4");
    String tue5 = request.getParameter("tue5");
    String tue6 = request.getParameter("tue6");
    String tue7 = request.getParameter("tue7");
    String wed1 = request.getParameter("wed1");
    String wed2 = request.getParameter("wed2");
    String wed3 = request.getParameter("wed3");
    String wed4 = request.getParameter("wed4");
    String wed5 = request.getParameter("wed5");
    String wed6 = request.getParameter("wed6");
    String wed7 = request.getParameter("wed7");
    String thu1 = request.getParameter("thu1");
    String thu2 = request.getParameter("thu2");
    String thu3 = request.getParameter("thu3");
    String thu4 = request.getParameter("thu4");
    String thu5 = request.getParameter("thu5");
    String thu6 = request.getParameter("thu6");
    String thu7 = request.getParameter("thu7");
    String fri1 = request.getParameter("fri1");
    String fri2 = request.getParameter("fri2");
    String fri3 = request.getParameter("fri3");
    String fri4 = request.getParameter("fri4");
    String fri5 = request.getParameter("fri5");
    String fri6 = request.getParameter("fri6");
    String fri7 = request.getParameter("fri7");
    String sat1 = request.getParameter("sat1");
    String sat2 = request.getParameter("sat2");
    String sat3 = request.getParameter("sat3");
    String sat4 = request.getParameter("sat4");
    String sat5 = request.getParameter("sat5");
    String sat6 = request.getParameter("sat6");
    String sat7 = request.getParameter("sat7");

    String kamokuID = (String) session.getAttribute("kamokuID");
    String kamokumei = (String) session.getAttribute("kamokumei");
    String kamokuenglish = (String) session.getAttribute("kamokuenglish");
    String tani = (String) session.getAttribute("tani");
    String kyouinID = (String) session.getAttribute("kyouinID");
    String fukukyouinID = (String) session.getAttribute("fukukyouinID");
    String gakunen = request.getParameter("gakunen");
    String gakki = request.getParameter("gakki");
    String youbi = request.getParameter("youbi");
    String bunrui = request.getParameter("bunrui");
    String jigen = (String) session.getAttribute("jigen");
    String kanrenkamoku = (String) session.getAttribute("kanrenkamoku");
    String jouken = (String) session.getAttribute("jouken");
    String setsume = (String) session.getAttribute("setsume");
    String ninzu = (String) session.getAttribute("ninzu");
    String kyoushitsu =(String) session.getAttribute("kyoushitsu");
%>

<button type="submit" name="top" style="position: absolute; left: 0px; top: 0px"class="btn_1">トップへ</button>
<br>

<table BORDER="1"><tr><td>年度</td><td>　　　　　　　　　　　　</td></tr></table>

<table BORDER="1"align="center">
    <tr align="center">
        <td> </td><td>月</td><td>火</td><td>水</td><td>木</td><td>金</td><td>土</td>
    </tr>
    <tr align="center">
        <td>1</td>
        <td><%=mon1%></td>
        <td><%=tue1%></td>
        <td><%=wed1%></td>
        <td><%=thu1%></td>
        <td><%=fri1%></td>
        <td><%=sat1%></td>
    </tr>
    <tr align="center">
        <td>2</td>
        <td><%=mon2%></td>
        <td><%=tue2%></td>
        <td><%=wed2%></td>
        <td><%=thu2%></td>
        <td><%=fri2%></td>
        <td><%=sat2%></td>
    </tr>

    </tr>

    <tr align="center">
        <td>3</td>
        <td><%=mon3%></td>
        <td><%=tue3%></td>
        <td><%=wed3%></td>
        <td><%=thu3%></td>
        <td><%=fri3%></td>
        <td><%=sat3%></td>
    </tr>

    </tr>

    <tr align="center">
        <td>4</td>
        <td><%=mon4%></td>
        <td><%=tue4%></td>
        <td><%=wed4%></td>
        <td><%=thu4%></td>
        <td><%=fri4%></td>
        <td><%=sat4%></td>
    </tr>

    </tr>

    <tr align="center">
        <td>5</td>
        <td><%=mon5%></td>
        <td><%=tue5%></td>
        <td><%=wed5%></td>
        <td><%=thu5%></td>
        <td><%=fri5%></td>
        <td><%=sat5%></td>
    </tr>

    <tr align="center">
        <td>6</td>
        <td><%=mon6%></td>
        <td><%=tue6%></td>
        <td><%=wed6%></td>
        <td><%=thu6%></td>
        <td><%=fri6%></td>
        <td><%=sat6%></td>
    </tr>

    <tr align="center">
        <td>7</td>
        <td><%=mon7%></td>
        <td><%=tue7%></td>
        <td><%=wed7%></td>
        <td><%=thu7%></td>
        <td><%=fri7%></td>
        <td><%=sat7%></td>
    </tr>


</table>

<br>

<br>

集中講義・実習
<table BORDER="1"align="center">
    <tr><td>開講区分</td><td>科目ID</td><td>科目名</td><td>教員氏名</td><td>教室</td></tr>
    <tr><td><%=gakki%></td><td><%=kamokuID%></td><td><%=kamokumei%></td><td><%=kyouinID%></td><td><%=kyoushitsu%></td></tr>
</table>

</div>
</body>
</html>
