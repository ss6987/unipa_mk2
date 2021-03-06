<%@ page import="Entity.User" %>
<%@ page import="java.util.List" %>
<%@ page import="Entity.User" %>
<%--
  Created by IntelliJ IDEA.
  User: ASAMI
  Date: 2018/07/20
  Time: 20:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="paging" class="etc.Paging" scope="session"/>
<%
    List<User> userList = (List<User>) request.getAttribute("userList");
    Integer nowPage = paging.getNowPage();
%>

<html lang="ja">
<head>
    <title>ユーザー検索結果</title>
    <link rel="stylesheet" type="text/css" href="Design.css">
</head>
<body>
<table id="window">
<tr><td class="ta-box">
        <form action="/Main" method="post">
            <button type="submit" name="action" value="Top" class="btn_1"> トップ</button>
        </form>
</td><td class="tb-box">
        <form action="/Main" method="post">
            <button type="submit" name="action" class="btn_1" value="UserSearch">もどる</button>
        </form>
</td></tr>
<tr><td colspan="2" class="c-box">
        <h1>検索結果</h1>
        <jsp:getProperty name="paging" property="count"/>
        件
        <br>

        <table BORDER="1" class="part">
            <tr>
                <th>学籍番号</th>
                <th>名前</th>
                <th>フリガナ</th>
                <th>性別</th>
                <th>生年月日</th>
                <th>郵便番号</th>
                <th>住所</th>
                <th>電話番号</th>
                <th>ユーザー分類</th>
            </tr>
            <%
                for (int i = 0; i < userList.size(); i++) {
                    User user = userList.get(i);
            %>
            <tr>
                <td>
                    <%=user.getUserId()%>
                </td>
                <td>
                    <form action="/Main" method="post" name="form<%=i%>">
                        <input type="hidden" name="action" value="UserDetail"/>
                        <input type="hidden" name="targetUserId" value="<%=user.getUserId()%>"/>
                        <a href="javascript:form<%=i%>.submit()"><%=user.getName()%>
                        </a>
                    </form>
                </td>
                <td>
                    <%=user.getPhonetic()%>
                </td>
                <td>
                    <%=user.getGenderString()%>
                </td>
                <td><%=user.getBirthYear()%>年
                    <%=user.getBirthMonth()%>月
                    <%=user.getBirthDay()%>日
                </td>
                <td>
                    <%=user.getPostalCode()%>
                </td>
                <td>
                    <%=user.getAddress()%>
                </td>
                <td>
                    <%=user.getTel()%>
                </td>
                <td>
                    <%=user.getUserClassification()%>
                </td>
            </tr>
            <%
                }
            %>
        </table>
        <%
            if (nowPage != 1) {
        %>
        <form action="/Main" method="post">
            <input type="hidden" name="page" value="<%=paging.getStartPage()%>"/>
            <button name="action" type="submit" class="btn_6" value="UserSearchChangePage">最初</button>
        </form>
        <%
        } else {
        %>
        <div>最初</div>
        <%
            }
        %>

        <%
            for (int i = nowPage - 2; i < nowPage + 3; i++) {
                if (i != nowPage && i >= 1 && i <= paging.getLastPage()) {
        %>
        <form action="/Main" method="post">
            <input type="hidden" name="page" value="<%=i%>"/>
            <button name="action" type="submit" class="btn_6" value="UserSearchChangePage"><%=i%>
            </button>
        </form>
        <%
        } else if (i == nowPage && i != paging.getStartPage() && i != paging.getLastPage() && i >= 1 && i <= paging.getLastPage()) {
        %>
        <div>
            <%=i%>
        </div>
        <%
                }
            }
        %>

        <%
            if (nowPage != paging.getLastPage()) {
        %>
        <form action="/Main" method="post">
            <input type="hidden" name="page" value="<%=paging.getLastPage()%>"/>
            <button name="action" type="submit" class="btn_6" value="UserSearchChangePage">最後</button>
        </form>
        <%
        } else {
        %>
        <div>最後</div>
        <%
            }
        %>
</td></tr></table>
</body>
</html>
