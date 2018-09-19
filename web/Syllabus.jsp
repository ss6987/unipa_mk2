<%--
  Created by IntelliJ IDEA.
  User: satone
  Date: 2018/07/20
  Time: 19:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>シラバス登録</title>
</head>
<body>

    <h1>シラバス登録</h1>
<br>

    <span style="background-color:#ffcc99">※正しく入力されていません</span>
    <table BORDER="1" align="center">
        <tr align="center">
            <td>科目ID</td>
            <td><input type ="text" name ="kamokuID"/></td>
        </tr>

        <tr align="center">
            <td>科目名</td>
            <td><input type ="text" name ="kamokumei"/></td>
        </tr>

        <tr align="center">
            <td>科目名英名</td>
            <td><input type ="text" name ="kamokuenglish"/></td>
        </tr>

        <tr align="center">
            <td>配当学年</td>
            <td><select name="gakunen" size="1">
            <option value="all">全学年</option>
            <option value="one">1</option>
            <option value="two">2</option>
            <option value="three">3</option>
        <option value="four">4</option> </select>
        </td>
        </tr>

        <tr align="center">
            <td>学期・曜日・時限</td>
            <td><select name="gakki" size="1">
                <option value="tunen">通年</option>
                <option value="shuchu">集中</option>
                <option value="zenki">前期</option>
                <option value="kouki">後期</option> </select>
                <select name="youbi" size="1">
                    <option value="getsu">月</option>
                    <option value="ka">火</option>
                    <option value="sui">水</option>
                    <option value="moku">木</option>
                    <option value="kin">金</option>
                    <option value="do">土</option> </select>
                <input type ="text" name ="jigen"/>
            </td>
        </tr>



        <tr align="center">
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
            <td>単位数</td>
            <td><input type ="text" name ="tani"/></td>
        </tr>

        <tr align="center">
            <td>担当教員ID</td>
            <td><input type ="text" name ="kyouinID"/></td>
        </tr>

        <tr align="center">
            <td>担当副教員ID</td>
            <td><input type ="text" name ="fukukyouinID"/></td>
        </tr>

        <tr align="center">
            <td>関連科目</td>
            <td><input type ="text" name ="kanrenkamoku"/></td>
        </tr>

        <tr align="center">
            <td>履修条件</td>
            <td><input type ="text" name ="jouken"/></td>
        </tr>

        <tr align="center">
            <td>概要説明</td>
            <td><input type ="text" name ="setsumei"/></td>
        </tr>

        <tr align="center">
            <td>定員数</td>
            <td><input type ="text" name ="ninzu"/></td>
        </tr>

    </table>


    <br>
    <button type="submit" name="next">次へ</button>
    <br>

</body>
</html>
