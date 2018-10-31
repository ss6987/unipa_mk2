<%--
  Created by IntelliJ IDEA.
  User: ASAMI
  Date: 2018/07/13
  Time: 20:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="user" class="Entity.User" scope="session"/>
<jsp:useBean id="targetUserId" class="java.lang.String" scope="session"/>
<jsp:useBean id="targetUser" class="Entity.User" scope="request"/>
<jsp:useBean id="student" class="Entity.Student" scope="request"/>
<jsp:useBean id="errorString" class="java.lang.String" scope="request"/>
<html lang="ja">
<head>
    <title>ユーザー管理</title>
    <link rel="stylesheet" type="text/css" href="Design.css">
</head>
<body>
<table id="window">
    <tr>
        <td class="ta-box">
            <form action="/Main" method="post">
                <button type="submit" name="action" value="Top" class="btn_1">トップ</button>
            </form>
        </td>
        <td class="tb-box">
            <%
                if (!targetUserId.equals(user.getUserId())) {
            %>
            <form action="/Main" method="post">
                <button type="submit" name="action" class="btn_1" value="UserSearchBack">戻る</button>
            </form>
            <%
                }
            %>
        </td>
    </tr>
    <tr>
        <td class="c-box" colspan="2">
            <h1 align="center">ユーザー管理</h1>

            <%
                if (!errorString.equals("")) {
            %>
            <%=errorString%>
            <%
                }
            %>

            <table BORDER="1" class="part">
                <tr>
                    <th>学籍番号</th>
                    <td>
                        <%=targetUser.getUserId()%>
                    </td>
                </tr>

                <tr>
                    <th>氏名</th>
                    <td>
                        <jsp:getProperty name="targetUser" property="name"/>
                    </td>
                </tr>
                <tr>
                    <th>フリガナ</th>
                    <td>
                        <jsp:getProperty name="targetUser" property="phonetic"/>
                    </td>
                </tr>

                <tr>
                    <th>性別</th>
                    <td>
                        <jsp:getProperty name="targetUser" property="genderString"/>
                    </td>
                </tr>

                <tr>
                    <th>ユーザー分類</th>
                    <td>
                        <jsp:getProperty name="targetUser" property="userClassification"/>
                    </td>
                </tr>

                <%
                    if (targetUser.getUserClassification().equals("学生")) {
                %>
                <tr align="center" class="studentStatus">
                    <th>学部学科</th>
                    <td>
                        <jsp:getProperty name="student" property="facultyDepartment"/>
                    </td>
                </tr>
                <tr>
                    <th>学年</th>
                    <td>
                        <jsp:getProperty name="student" property="gradeString"/>年
                    </td>
                </tr>
                <%
                    }
                %>


                <tr>
                    <th>生年月日</th>
                    <td>
                        <jsp:getProperty name="targetUser" property="birthday"/>
                    </td>
                </tr>

                <tr>
                    <th>郵便番号</th>
                    <td>
                        <jsp:getProperty name="targetUser" property="postalCode"/>
                    </td>
                </tr>

                <tr>
                    <th>住所</th>
                    <td>
                        <jsp:getProperty name="targetUser" property="address"/>
                    </td>
                </tr>

                <tr>
                    <th>電話番号</th>
                    <td>
                        <jsp:getProperty name="targetUser" property="tel"/>
                    </td>
                </tr>
            </table>
            <span>
            <%
                if (user.getUserClassification().equals("管理者")) {
            %>
                <form action="/Main" method="post">
                    <button type="submit" name="action" class="btn_4" value="UserUpdate">更新</button>
                </form>
            <%
                }
                if (user.getUserId() != targetUserId) {
            %>
                <form action="/Main" method="post">
                    <button type="submit" name="action" class="btn_4" value="UserDelete">削除</button>
                </form>
            <%
                }
            %>
            </span>
            <%
                if (user.getUserId() == targetUser.getUserId()) {
            %>
            <h2 align="center">パスワード設定</h2>
            <form action="/Main" method="post">
                <table BORDER="1" class="part">
                    <tr>
                        <th>更新前パスワード</th>
                        <td>
                            <input type="password" name="before_password" required>
                        </td>
                    </tr>
                    <tr>
                        <th>更新後パスワード</th>
                        <td>
                            <input type="password" name="after_password" required>
                        </td>
                    </tr>
                </table>
                <button type="submit" name="action" class="btn_4" value="UserUpdatePassword">パスワード更新</button>
            </form>
            <%
                }
            %>

            <%
                if (user.getUserClassification().equals("学生")) {
            %>
            <h2 align="center">保護者用パスワード設定</h2>
            <table BORDER="1" class="part">
                <tr>
                    <th>パスワード<br>確認用パスワード</th>
                    <td><input type="password" name="pass"><br><input type="password" name="kakuninpass"></td>
                </tr>
            </table>
            <%
                }
            %>
        </td>
    </tr>
</table>
</body>
</html>
