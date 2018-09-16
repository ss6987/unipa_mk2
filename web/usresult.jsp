<%--
  Created by IntelliJ IDEA.
  User: ASAMI
  Date: 2018/07/20
  Time: 20:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ユーザー検索結果</title>
</head>
<body>

<%
    //InputCheckServletでセットした時のキーで値を取り出す。
    String name = (String) session.getAttribute("name");
    String gakusekibangou = (String) session.getAttribute("gakusekibangou");
    String say = (String) session.getAttribute("say");
    String may = (String) session.getAttribute("may");
    String bunrui = request.getParameter("bunrui");
    String sansyo = request.getParameter("sansyo");

%>

<h1>  <button type="submit" name="check"> トップ </button></h1>
<button type="submit" name="login" style="position: absolute; right:  0px; top: 0px">もどる</button>
<br>

検索結果

<br>

<table BORDER="1" align="center">


    <tr align="center">
        <td>学籍番号</td>
        <td>氏名</td>
        <td>ユーザ種類</td>
        <td>参照</td>

    </tr>

    <tr align="center">
        <td><%=gakusekibangou%></td>
        <td><%=say%><%=may%></td>
        <td><%=bunrui%></td>
        <td><%=sansyo%></td>
    </tr>

</table>


<button type="submit" name="ue" ><a href="#top" >上へ戻る</a></button>
<button type="submit" name="check" align="center"> 再検索 </button>
</body>
</html>
