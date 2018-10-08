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
    <link rel="stylesheet" type="text/css" href="Design.css">
</head>
<body>

<%
    //InputCheckServletでセットした時のキーで値を取り出す。

    String sakujo = request.getParameter("sakujo");


%>

<button type="submit" class="btn_1" name="check"> トップ </button>
<button type="submit" name="login" class="btn_1" style="position: absolute; right:  0px; top: 0px">もどる</button>


<h2>以下の履修者を削除しますか?</h2>

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





<button type="submit" class="btn_6" name="ue" ><a href="#top" >上へ戻る</a></button>
<button type="submit" name="check" class="btn_3" align="center"> 削除 </button>
</body>
</html>
