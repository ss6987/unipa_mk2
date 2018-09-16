<%--
  Created by IntelliJ IDEA.
  User: ASAMI
  Date: 2018/07/13
  Time: 20:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ユーザー管理</title>
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


<h1>
    <button type="submit" name="check"> トップ</button>
</h1>

<h1 align="center">ユーザー更新</h1>


<table BORDER="1" align="center">
    <tr align="center">
        <td>学籍番号</td>
        <td><%=name%>
        </td>
    </tr>

    <tr align="center">
        <td>氏名<br>フリガナ</td>
        <td>姓<%=say%>名<%=may%>
            <br>セイ<%=saykana%>メイ<%=maykana%>
        </td>
    </tr>

    <tr align="center">
        <td>性別</td>
        <td><%=sex%>
        </td>
    </tr>


    <tr align="center">
        <td>ユーザー分類</td>
        <td><%=bunrui1%>
        </td>
    </tr>


    <tr align="center">
        <td>学部・学科</td>
        <td><%=bunrui2%>
        </td>
    </tr>


    <tr align="center">
        <td>生年月日</td>
        <td>西暦<%=nen%>年<%=tsuki%>月<%=hi%>日</td>
    </tr>

    <tr align="center">
        <td>郵便番号</td>
        <td><%=yuubin%>
        </td>
    </tr>

    <tr align="center">
        <td>住所</td>
        <td><%=jusho%>
        </td>
    </tr>

    <tr align="center">
        <td>電話番号</td>
        <td><%=denwa%>
        </td>
    </tr>

</table>
<br>
<br>
<h2 align="center">更新情報を入力してください</h2>

<table BORDER="1" align="center">
    <tr align="center">
        <td>学籍番号</td>
        <td><input type="text" name="gakusekibangou"/></td>
    </tr>

    <tr align="center">
        <td>氏名<br>フリガナ</td>
        <td>姓<input type="text" name="say"/>名<input type="text" name="may"/>
            <br>セイ<input type="text" name="saykana"/>メイ<input type="text" name="maykana"/></td>
    </tr>

    <tr align="center">
        <td>ユーザー分類</td>
        <td><select name="bunrui" size="1">
            <option value="student">学生</option>
            <option value="staff">教職員</option>
            <option value="admin">管理者</option>
        </select></td>
    </tr>

    <tr align="center">
        <td>学部・学科</td>
        <td><select name="bunrui2" size="1">
            <option value="nc">工学部第二部情報通信工学科</option>
            <option value="nm">工学部第二部機械工学科</option>
            <option value="ne">工学部第二部電気電子工学科</option>
        </select></td>
    </tr>

    <tr align="center">
        <td>性別</td>
        <td><input type=radio name="sex" value="man">男性<input type=radio name="sex" value="woman">女性</td>
    </tr>

    <tr align="center">
        <td>生年月日</td>
        <td>西暦<input type="text" name="nen"/>年<input type="text" name="tsuki"/>月<input type="text" name="hi"/>日</td>
    </tr>

    <tr align="center">
        <td>郵便番号</td>
        <td><input type="text" name="yuubin"/><br>※ハイフンなしで入力</td>
    </tr>

    <tr align="center">
        <td>住所</td>
        <td><input type="text" name="jusho"/></td>
    </tr>

    <tr align="center">
        <td>電話番号</td>
        <td><input type="text" name="denwa"/><br>※ハイフンなしで入力</td>
    </tr>
</table>

<table BORDER="1" align="center">
    <h2 align="center">パスワード設定</h2>
    <td>パスワード<br>確認用パスワード</td>
    <td><input type="password" name="pass"><br><input type="password" name="kakuninpass"></td>
    </tr>
</table>

<br>

<table BORDER="1" align="center">
    <h2 align="center">保護者用パスワード設定</h2>
    <td>パスワード<br>確認用パスワード</td>
    <td><input type="password" name="pass"><br><input type="password" name="kakuninpass"></td>
    </tr>
</table>

<button type="submit" name="update" align="center"> 更新</button>
<button type="submit" name="userdel" align="center"> 削除</button>
</body>
</html>
