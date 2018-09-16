<%--
  Created by IntelliJ IDEA.
  User: ASAMI
  Date: 2018/07/27
  Time: 3:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>シラバス削除</title>
</head>
<body>
<%
    //InputCheckServletでセットした時のキーで値を取り出す。
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



%>







<h1>  <button type="submit" name="check"> トップ </button></h1>

<br>
<span style="background-color:#ffcc99">以下の学生情報を完全に削除します。</span>

<br>

<table BORDER="1" align="center">
    <tr align="center">
        <td>科目ID</td>
        <td><%=kamokuID%></td>
    </tr>

    <tr align="center">
        <td>科目名</td>
        <td><%=kamokumei%></td>
    </tr>

    <tr align="center">
        <td>科目名英名</td>
        <td><%=kamokuenglish%></td>
    </tr>

    <tr align="center">
        <td>配当学年</td>
        <td><%=gakunen%>
        </td>
    </tr>

    <tr align="center">
        <td>学期・曜日・時限</td>
        <td><%=gakki%>
            <%=youbi%>
            <%=jigen%>
        </td>
    </tr>



    <tr align="center">
        <td>学部・学科</td>
        <td><%=bunrui%></td>
    </tr>

    <tr align="center">
        <td>単位数</td>
        <td><%=tani%></td>
    </tr>

    <tr align="center">
        <td>担当教員ID</td>
        <td><%=kyouinID%></td>
    </tr>

    <tr align="center">
        <td>担当副教員ID</td>
        <td><%=fukukyouinID%></td>
    </tr>

    <tr align="center">
        <td>関連科目</td>
        <td><%=kanrenkamoku%></td>
    </tr>

    <tr align="center">
        <td>履修条件</td>
        <td><%=jouken%></td>
    </tr>

    <tr align="center">
        <td>概要説明</td>
        <td><%=setsume%></td>
    </tr>

    <tr align="center">
        <td>定員数</td>
        <td><%=ninzu%></td>
    </tr>

</table>

<button type="submit" name="check" align="center"> 削除確定 </button>
<button type="submit" name="check" align="center"> 戻る </button>
</body>
</html>
