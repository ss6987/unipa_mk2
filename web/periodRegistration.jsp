<%@ page import="java.time.LocalDateTime" %><%--
  Created by IntelliJ IDEA.
  User: SS
  Date: 2018/10/10
  Time: 15:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="registrationPeriod" class="Entity.RegistrationPeriod" scope="request"/>
<%
    LocalDateTime startDate = registrationPeriod.getStartLocalDate();
    LocalDateTime endDate = registrationPeriod.getEndLocalDate();
%>

<html>
<head>
    <title>履修登録期間変更</title>
</head>
<body>
<form action="/Top" method="get">
    <button type="submit" name="action" value="top">トップ</button>
</form>
<h1>
    履修登録期間変更
</h1>

<div>
    <form action="/periodRegistration" method="post">
        <table border="1">
            <tr>
                <th>登録期間</th>
                <td>
                    <div>
                        西暦<input type="text" name="startYear" value="<%=startDate.getYear()%>" required pattern="^20[0-9][0-9]$">年
                        <input type="text" name="startMonth" value="<%=startDate.getMonthValue()%>" required pattern="^(1[0-2]|[1-9])$">月
                        <input type="text" name="startDay" value="<%=startDate.getDayOfMonth()%>" required pattern="^([1-9]|1[0-9]|2[0-9]|3[01])$">日
                    </div>
                    <div>~</div>
                    <div>
                        西暦<input type="text" name="endYear" value="<%=endDate.getYear()%>" required pattern="^20[0-9][0-9]$">年
                        <input type="text" name="endMonth" value="<%=endDate.getMonthValue()%>" required pattern="^(1[0-2]|[1-9])$">月
                        <input type="text" name="endDay" value="<%=endDate.getDayOfMonth()%>" required pattern="^([1-9]|1[0-9]|2[0-9]|3[01])$">日
                    </div>
                </td>
            </tr>
        </table>
        <button type="submit" name="action" value="periodRegistration">登録</button>
    </form>
</div>
</body>
</html>
