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
<button type="submit" name="top" style="position: absolute; left: 0px; top: 0px">トップへ</button>
<button type="submit" name="modoru" style="position: absolute; right: 0px; top: 0px" onclick="history.back()">戻る</button>
<br>
<h1>シラバス検索</h1>
<br>
<span style="background-color:#ffcc99">※正しく入力されていません</span><br>
条件指定
<table BORDER="1" align="center">
    <tr align="center">
        <td>科目ID</td>
        <td><input type ="text" name ="kamokuID"/></td>
    </tr>

    <tr align="center">
        <td>科目名</td>
        <td><input type ="text" name ="kamokumei"/></td>
    </tr>


    <tr>
        <td>授業実施日</td>
        <td><input type ="text" name ="zyugyoubi"/></td>
    <tr align="center">
        <td>学年</td>
        <td><select name="gakunen" size="1">
            <option value="all">全学年</option>
            <option value="one">1</option>
            <option value="two">2</option>
            <option value="three">3</option>
            <option value="four">4</option> </select>
        </td>
    </tr>
    <tr align="center">
    <td>学期</td>
        <td><select name="gakki" size="1">
        <option value="tunen">通年</option>
        <option value="shuchu">集中</option>
        <option value="zenki">前期</option>
        <option value="kouki">後期</option> </select>
        </td>
    <td>曜日</td>
        <td><select name="youbi" size="1">
        <option value="getsu">月</option>
        <option value="ka">火</option>
        <option value="sui">水</option>
        <option value="moku">木</option>
        <option value="kin">金</option>
        <option value="do">土</option> </select>
        </td>
    <td>時限</td>
        <td><select name="jigen" size="1">
            <option value="one">1</option>
            <option value="two">2</option>
            <option value="three">3</option>
            <option value="four">4</option>
            <option value="five">5</option>
            <option value="six">6</option>
            <option value="seven">7</option></select>
    </td>
    </tr>

    <tr align="center">
        <td>科目分類</td>
        <td><select name="bunrui" size="1">
            <option value="jinka">人間科学科目</option>
            <option value="eigo">英語科目</option>
            <option value="zenki">専門基礎科目</option>
            <option value="kouki">専門科目</option>
            <option value="kouki">実践知重点科目</option>
            <option value="kouki">教職科目</option>
        </select></td>
    </tr>

    <tr align="center">
        <td>担当教員</td>
        <td><input type ="text" name ="tantou"/></td>
    </tr>

    <tr align="center">
        <td>単位数</td>
        <td><input type ="text" name ="tani"/></td>
    </tr>
    <tr align="center">
        <td>関連科目</td>
        <td><input type ="text" name ="kanrenkamoku"/></td>
    </tr>

    <tr align="center">
        <td>履修条件</td>
        <td><input type ="text" name ="jouken"/></td>
    </tr>

</table>

<br>
<button type="submit" name="kensakubutton">検索</button>
<br>
</body>
</html>
