<%--
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
    //InputCheckServletでセットした時のキーで値を取り出す。
    String kamokuID = (String) session.getAttribute("kamokuID");
    String kamokumei = (String) session.getAttribute("kamokumei");
    String kyouinID = (String) session.getAttribute("=kyouinID");
    String may = (String) session.getAttribute("may");
    String bunrui = request.getParameter("bunrui");
    String sansyo = request.getParameter("sansyo");
    String gakki = request.getParameter("gakki");
    String youbi = request.getParameter("youbi");
    String jigen = request.getParameter("jigen");
%>

<h1>  <button type="submit" name="check"> 再検索 </button></h1>

<br>

検索結果

<br>

<table BORDER="1" align="center">


    <tr align="center">
        <td>科目ID</td>
        <td>科目名</td>
        <td>担当教員</td>
        <td>科目分類</td>
        <td>学期</td>
        <td>曜日</td>
        <td>時限</td>
        <td>参照</td>
    </tr>

    <tr align="center">
        <td><%=kamokuID%></td>
        <td><%=kamokumei%></td>
        <td><%=kyouinID%></td>
        <td><%=bunrui%></td>
        <td><%=gakki%></td>
        <td><%=youbi%></td>
        <td><%=jigen%></td>
        <td><%=sansyo%></td>
    </tr>

</table>


<button type="submit" name="ue" ><a href="#top" >上へ戻る</a></button>
<button type="submit" name="check" align="center"> 戻る </button>
</body>
</html>
