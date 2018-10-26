<%--
  Created by IntelliJ IDEA.
  User: ASAMI
  Date: 2018/07/27
  Time: 18:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ja">
<head>
    <title>履修登録期間設定</title>
    <link rel="stylesheet" type="text/css" href="Design.css">
</head>
<body>
<h1>  <button type="submit" class="btn_1" name="check"> トップ </button></h1>
<span style="background-color:#ffcc99">※正しく入力されていません</span>

<table BORDER="1" align="center">

    <td>登録期間</td>
<td>
        西暦
        <input type ="text" name ="nenst"/>
        年
        <input type ="text" name ="tsukist"/>
        月
        <input type ="text" name ="hist"/>
        日
    <br>
     ～
    <br>

        西暦
        <input type ="text" name ="nenend"/>
        年
        <input type ="text" name ="tsukiend"/>
        月
        <input type ="text" name ="hiend"/>
        日
    </tr>
</table>



<br>
<br>
<button type="submit" name="check" class="btn_1" align="center"> 戻る </button>
<button type="submit" class="btn_4" name="next">次へ</button>
<br>
</body>
</html>
