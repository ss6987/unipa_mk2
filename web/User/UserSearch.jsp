<%--
  Created by IntelliJ IDEA.
  User: satone
  Date: 2018/07/20
  Time: 19:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="searchUser" class="Entity.User" scope="session"/>
<jsp:useBean id="errorString" class="java.lang.String" scope="request"/>
<html lang="ja">
<head>
    <title>ユーザー検索</title>
    <link rel="stylesheet" type="text/css" href="Design.css">
</head>

<body>
<table id="window">
    <tr>
        <td class="a-box">
            <form action="/Main" method="post">
                <button type="submit" name="action" value="Top" class="btn_1"
                        style="position: absolute; left: 0px; top: 0px">トップ
                </button>
            </form>
        </td>
        <td class="c-box">
            <h1>ユーザー検索</h1>

            <br>
            <%
                if (!errorString.equals("")) {
            %>
            <span style="background-color:#ffcc99">
            <%=errorString%>
        </span>
            <%
                }
            %>

            <form action="/Main" method="post">
                <table BORDER="1" class="part">
                    <tr>
                        <th>学籍番号</th>
                        <td>
                            <input type="text" name="userId" pattern="^[0-9A-Za-z]+$"
                                   value="<jsp:getProperty name="searchUser" property="userId"/>"/>
                        </td>
                    </tr>

                    <tr>
                        <th>氏名</th>
                        <td>
                            <input type="text" name="name"
                                   value="<jsp:getProperty name="searchUser" property="name"/>"/>
                        </td>
                    </tr>
                    <tr>
                        <th>フリガナ</th>
                        <td>
                            <input type="text" name="phonetic" pattern="^[ァ-ヶー　 ]+$"
                                   value="<jsp:getProperty name="searchUser" property="phonetic"/>"/>
                        </td>
                    </tr>
                    <tr>
                        <th>性別</th>
                        <td>
                            <input type="text" name="gender"
                                   value="<jsp:getProperty name="searchUser" property="genderString"/>"/>
                        </td>
                    </tr>
                    <tr>
                        <th>生年月日</th>
                        <td>
                            西暦<input type="text" name="year" pattern="^[0-9]+$"
                                     value="<jsp:getProperty name="searchUser" property="birthYear"/>"/>年<br/>
                            <input type="text" name="month" pattern="^[0-9]+$"
                                   value="<jsp:getProperty name="searchUser" property="birthMonth"/>"/>月<br/>
                            <input type="text" name="day" pattern="^[0-9]+$"
                                   value="<jsp:getProperty name="searchUser" property="birthDay"/>"/>日<br/>
                        </td>
                    </tr>
                    <tr>
                        <th>郵便番号</th>
                        <td>
                            <input type="text" name="postal_code" pattern="^[0-9]{7}"
                                   value="<jsp:getProperty name="searchUser" property="postalCode"/>"/>
                        </td>
                    </tr>
                    <tr>
                        <th>住所</th>
                        <td>
                            <input type="text" name="address"
                                   value="<jsp:getProperty name="searchUser" property="address"/>"/>
                        </td>
                    </tr>
                    <tr>
                        <th>電話番号</th>
                        <td>
                            <input type="text" name="tel" pattern="^[0-9]{10,11}"
                                   value="<jsp:getProperty name="searchUser" property="tel"/>"/>
                        </td>
                    </tr>
                    <tr>
                        <th>ユーザー分類</th>
                        <td>
                            <input type="text" name="user_classification"
                                   value="<jsp:getProperty name="searchUser" property="userClassification"/>"/>
                        </td>
                    </tr>
                    <tr>
                        <th>学部・学科</th>
                        <td>

                        </td>
                    </tr>
                </table>
                <button type="submit" name="action" class="btn_4" value="UserSearchFirst">検索</button>
            </form>
        </td>
    </tr>
</table>
</body>
</html>
