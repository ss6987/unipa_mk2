<%--
  Created by IntelliJ IDEA.
  User: ASAMI
  Date: 2018/07/20
  Time: 18:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ja">
<head>
    <title>履修者削除確認</title>
</head>
<body>

<%
    //InputCheckServletでセットした時のキーで値を取り出す。

    String sakujo = request.getParameter("sakujo");


%>

<h1>  <button type="submit" name="check"> トップ </button></h1>
<button type="submit" name="login" style="position: absolute; right:  0px; top: 0px">もどる</button>


以下の履修者を削除しますか?

<br>
<table BORDER="1" align="center">
    <tr align="center">
        <td>履修者一覧</td>

    </tr>

    <tr align="center">
        <td>学籍番号</td>
        <td>氏名</td>
        <td>成績</td>

    </tr>

    <tr align="center">
        <td><%=sakujo%></td>
        <td><%=sakujo%></td>
        <td><%=sakujo%></td>
    </tr>

</table>





<button type="submit" name="ue" ><a href="#top" >上へ戻る</a></button>
<button type="submit" name="check" align="center"> 削除 </button>
</body>
</html>
