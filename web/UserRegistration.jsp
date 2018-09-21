<%--
  Created by IntelliJ IDEA.
  User: satone
  Date: 2018/07/13
  Time: 19:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="user" class="Entity.User" scope="session"/>
<html lang="ja">
<head>
    <title>ユーザー登録</title>
</head>
<body>
<button type="submit" name="login" style="position: absolute; left: 0px; top: 0px">トップへ</button>
<button type="submit" name="login" style="position: absolute; right: 0px; top: 0px">戻る</button>

<br>
<h1>ユーザー登録でザンス</h1>

<br>

以下のフォームに登録する学生の情報を入力してくださいなんでもしますから

<br>

<table border="1" align="center">
    <tr align="center">
        <th>学籍番号</th>
        <td><input type="text" name="id"/></td>
    </tr>

    <tr align="center">
        <th>氏名<br>フリガナ</th>
        <td>姓<input type="text" name="say"/>
            名<input type="text" name="may"/>
            <br></td>
    </tr>
    <tr>
        <th>フリガナ</th>
        <td>
            セイ<input type="text" name="saykana"/>
            メイ<input type="text" name="maykana"/>
        </td>
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
        <td><select name="bunrui" size="1">
            <option value="ek">工学部 機械工学科</option>
            <option value="ej">工学部 電気電子工学科</option>
            <option value="ec">工学部 情報通信工学科</option>
            <option value="eh">工学部 電子システム工学科</option>
            <option value="es">工学部 環境化学科</option>
            <option value="es">工学部 応用化学科</option>
            <option value="ef">工学部 先端機械工学科</option>
            <option value="nm">工学部第二部 機械工学科</option>
            <option value="ne">工学部第二部 電気電子工学科</option>
            <option value="nc">工学部第二部 情報通信工学科</option>
            <option value="fa">未来科学部 建築学科</option>
            <option value="fi">未来科学部 情報メディア学科</option>
            <option value="fr">未来科学部 ロボット・メカトロニクス学科</option>
            <option value="aj">システムデザイン工学部 情報システム工学科</option>
            <option value="ad">システムデザイン工学部 デザイン工学科</option>
            <option value="ru">理工学部 理学系</option>
            <option value="rb">理工学部 生命科学系</option>
            <option value="rd">理工学部 情報システムデザイン学系</option>
            <option value="rm">理工学部 機械工学系</option>
            <option value="re">理工学部 電子工学系</option>
            <option value="rg">理工学部 建築・都市環境学系</option>
            <option value="jn">情報環境学部 情報環境学科 ネットワーク・コンピュータ工学コース</option>
            <option value="jd">情報環境学部 情報環境学科 デジタル情報工学コース</option>
            <option value="js">情報環境学部 情報環境学科 建築デザインコース</option>
            <option value="jc">情報環境学部 情報環境学科 コミュニケーション工学コース</option>
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

    <tr align="center">
        <td>パスワード<br>確認用パスワード</td>
        <td><input type="password" name="pass"><br><input type="password" name="kakuninpass"></td>
    </tr>
</table>

<button type="submit" name="next" align="center">次へ</button>
</body>
</html>
