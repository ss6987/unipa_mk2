<%@ page import="Entity.Course" %>
<%@ page import="java.util.List" %>
<%@ page import="Entity.User" %><%--
  Created by IntelliJ IDEA.
  User: satone
  Date: 2018/07/27
  Time: 20:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="syllabus" class="Entity.Syllabus" scope="request"/>
<jsp:useBean id="errorString" class="java.lang.String" scope="request"/>
<%
    List<Course> courseList = (List<Course>) request.getAttribute("courseList");
    List<User> userList = (List<User>) request.getAttribute("userList");
%>
<html lang="ja">
<head>
    <title>成績情報更新</title>
    <link rel="stylesheet" type="text/css" href="Design.css">
</head>
<body>
<table id="window">
    <tr>
        <td class="ta-box">
            <form action="/Main" method="post">
                <button type="submit" name="action" value="Top" class="btn_1"
                        style="position: absolute; left: 0px; top: 0px">トップへ
                </button>
            </form>
        </td>
        <td class="tb-box">
            <form action="/Main" method="post">
                <button type="submit" name="action" value="SyllabusDetail" class="btn_1"> 戻る</button>
            </form>
        </td>
    </tr>
    <tr>
        <td class="c-box" colspan="2">
            <h1>成績情報更新</h1>
                <%
            if (!errorString.equals("")) {
        %>
            <span style="background-color:#ffcc99">※ファイルが違います</span>
                <%
            }
        %>
            <br>

        <%--<input type="file" name="seisekicsv" size="30">--%>

        <table BORDER="1" class="part">
            <tr>
                <td>授業名</td>
                <td>
                    <jsp:getProperty name="syllabus" property="syllabusName"/>
                </td>
            </tr>
        </table>

        <br>
        <form action="/Main" method="post">
            <table BORDER="1" class="part">
                <tr>
                    <th>学籍番号</th>
                    <th>名前</th>
                    <th>更新前評価</th>
                    <th>更新後評価</th>
                </tr>
                <%
                    for (int i = 0; i < courseList.size(); i++) {
                        Course course = courseList.get(i);
                        User user = userList.get(i);
                %>
                <tr>
                    <td><%=user.getUserId()%>
                    </td>
                    <td><%=user.getName()%>
                    </td>
                    <td><%=course.getAchievementString()%>
                    </td>
                    <td>
                        <select name="select_<%=i%>">
                            <option value="-2" <%=course.getAchievementSelected(-2)%>>履修中</option>
                            <option value="0" <%=course.getAchievementSelected(0)%>>D</option>
                            <option value="1" <%=course.getAchievementSelected(1)%>>C</option>
                            <option value="2" <%=course.getAchievementSelected(2)%>>B</option>
                            <option value="3" <%=course.getAchievementSelected(3)%>>A</option>
                            <option value="4" <%=course.getAchievementSelected(4)%>>S</option>
                        </select>
                    </td>
                </tr>
                <%
                    }
                %>
            </table>
            <button type="submit" name="action" class="btn_4" value="AchieveRegistrationDone">更新</button>
        </form>

</td></tr></table>
</body>
</html>
