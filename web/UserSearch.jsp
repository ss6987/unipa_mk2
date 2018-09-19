<%--
  Created by IntelliJ IDEA.
  User: satone
  Date: 2018/07/20
  Time: 19:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ユーサー検索</title>


</head>

<body>
<button type="submit" name="top" style="position: absolute; left: 0px; top: 0px">トップへ</button>
<button type="submit" name="modoru" style="position: absolute; right: 0px; top: 0px" onclick="history.back()">戻る</button>
<br>
<h1>ユーザー検索</h1>

<br>
<span style="background-color:#ffcc99">※正しく入力されていません</span>

<table BORDER="1" align="center">
    <tr align="center">
        <td>学籍番号</td>
        <td><input type ="text" name ="gakusekibangou"/></td>
    </tr>

    <tr align="center">
        <td>氏名</td>
        <td>姓<input type ="text" name ="say"/>名<input type ="text" name ="may"/></td>
    </tr>


    <tr>
        <td>学部・学科</td>
        <td><select name="bunrui" size="1">
            <option value="ek">工学部  機械工学科</option>
            <option value="ej">工学部  電気電子工学科</option>
            <option value="ec">工学部  情報通信工学科</option>
            <option value="eh">工学部  電子システム工学科</option>
            <option value="es">工学部  環境化学科</option>
            <option value="es">工学部  応用化学科</option>
            <option value="ef">工学部  先端機械工学科</option>
            <option value="nm">工学部第二部  機械工学科</option>
            <option value="ne">工学部第二部  電気電子工学科</option>
            <option value="nc">工学部第二部  情報通信工学科</option>
            <option value="fa">未来科学部  建築学科</option>
            <option value="fi">未来科学部  情報メディア学科</option>
            <option value="fr">未来科学部  ロボット・メカトロニクス学科</option>
            <option value="aj">システムデザイン工学部  情報システム工学科</option>
            <option value="ad">システムデザイン工学部  デザイン工学科</option>
            <option value="ru">理工学部  理学系</option>
            <option value="rb">理工学部  生命科学系</option>
            <option value="rd">理工学部  情報システムデザイン学系</option>
            <option value="rm">理工学部  機械工学系</option>
            <option value="re">理工学部  電子工学系</option>
            <option value="rg">理工学部  建築・都市環境学系</option>
            <option value="jn">情報環境学部  情報環境学科  ネットワーク・コンピュータ工学コース</option>
            <option value="jd">情報環境学部  情報環境学科  デジタル情報工学コース</option>
            <option value="js">情報環境学部  情報環境学科  建築デザインコース</option>
            <option value="jc">情報環境学部  情報環境学科  コミュニケーション工学コース</option></select></td>
    </tr>



    <tr >
        <td>住所</td>
        <td>都道府県<br>
            <select name="bunrui" size="1">
                <option value="hokkaido">北海道</option>
                <option value="aomori">青森県</option>
                <option value="iwate">岩手県</option>
                <option value="miyagi">宮城県</option>
                <option value="akita">秋田県</option>
                <option value="yamagata">山形県</option>
                <option value="fukushima">福島県</option>
                <option value="ibaraki">茨城県</option>
                <option value="tochigi">栃木県</option>
                <option value="gunma">群馬県</option>
                <option value="saitama">埼玉県</option>
                <option value="chiba">千葉県</option>
                <option value="tokyo">東京都</option>
                <option value="kanagawa">神奈川県</option>
                <option value="niigata">新潟県</option>
                <option value="toyama">富山県</option>
                <option value="ishikawa">石川県</option>
                <option value="fukui">福井県</option>
                <option value="yamanashi">山梨県</option>
                <option value="nagano">長野県</option>
                <option value="gifu">岐阜県</option>
                <option value="shizuoka">静岡県</option>
                <option value="aichi">愛知県</option>
                <option value="mie">三重県</option>
                <option value="shiga">滋賀県</option>
                <option value="kyoto">京都府</option>
                <option value="osaka">大阪府</option>
                <option value="hyogo">兵庫県</option>
                <option value="nara">奈良県</option>
                <option value="wakayama">和歌山県</option>
                <option value="tottori">鳥取県</option>
                <option value="shimane">島根県</option>
                <option value="okayama">岡山県</option>
                <option value="hiroshima">広島県</option>
                <option value="yamaguchi">山口県</option>
                <option value="tokushima">徳島県</option>
                <option value="kagawa">香川県</option>
                <option value="ehime">愛媛県</option>
                <option value="kochi">高知県</option>
                <option value="fukuoka">福岡県</option>
                <option value="saga">佐賀県</option>
                <option value="nagasaki">長崎県</option>
                <option value="kumamoto">熊本県</option>
                <option value="oita">大分県</option>
                <option value="miyazaki">宮崎県</option>
                <option value="kagoshima">鹿児島県</option>
                <option value="okinawa">沖縄県</option>
            </select>
            <br>
            市区町村以下の住所
            <br>
            <input type ="text" name ="shikuchouson"/></td>
    </tr>


</table>

<br>
<button type="submit" name="kensakubutton">検索</button>
<br>
</body>
</html>
