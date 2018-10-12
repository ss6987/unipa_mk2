<%@ page import="Entity.FacultyDepartment" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: satone
  Date: 2018/07/13
  Time: 19:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="user" class="Entity.User" scope="session"/>
<jsp:useBean id="targetUser" class="Entity.User" scope="request"/>
<jsp:useBean id="targetUserId" class="java.lang.String" scope="session"/>
<jsp:useBean id="targetStudent" class="Entity.Student" scope="request"/>
<jsp:useBean id="errorString" class="java.lang.String" scope="request"/>

<%
    List<FacultyDepartment> facultyDepartmentList = (List<FacultyDepartment>) request.getAttribute("facultyDepartment");
%>

<html lang="ja">
<head>
    <title>ユーザー登録</title>
    <link rel="stylesheet" type="text/css" href="Design.css">
</head>
<body>

<form action="/Top" method="get">
    <button type="submit" name="action" class="btn_1" value="Top">トップ</button>
</form>
<br>
<%
    if (!targetUserId.equals("")) {
%>
<h1>ユーザー更新</h1>
<%
} else {
%>
<h1>ユーザー登録</h1>
<%
    }
%>
<br>

以下のフォームに登録する学生の情報を入力してくださいなんでもしますから

<br>
<%
    if (!errorString.equals("")) {
%>
<%=errorString%>
<%
    }
%>

<form action="/Main" method="post" name="userRegistration">
    <table BORDER="1" align="center">
        <tbody>
        <tr align="center">
            <th>学籍番号</th>
            <td>
                <%
                    if (targetUserId.equals("")) {
                %>
                <input type="text" name="targetUserId" value="<jsp:getProperty name="targetUser" property="userId" />"required pattern="^[0-9A-Za-z]+$"tabindex="1"/>
                <%
                } else {
                %>
                <jsp:getProperty name="targetUser" property="userId"/>
                <%
                    }
                %>
            </td>
        </tr>

        <tr align="center">
            <th>氏名</th>
            <td>
                <input type="text" name="name" value="<jsp:getProperty name="targetUser" property="name"/>"tabindex="2"required/>
            </td>
        </tr>

        <tr align="center">
            <th>フリガナ</th>
            <td>
                <input type="text" name="phonetic" value="<jsp:getProperty name="targetUser" property="phonetic"/>"required pattern="^[ァ-ヶ]+$"tabindex="3"/>
            </td>
        </tr>

        <tr align="center">
            <th>ユーザー分類</th>
            <td>
                <select name="user_classification" size="1" onchange="classificationSelect()"tabindex="4">
                    <option value="学生" <%=targetUser.getUserClassificationSelected("学生")%>>学生</option>
                    <option value="教職員" <%=targetUser.getUserClassificationSelected("教職員")%>>教職員</option>
                    <option value="管理者" <%=targetUser.getUserClassificationSelected("管理者")%>>管理者</option>
                </select>
            </td>
        </tr>

        <tr align="center">
            <th>性別</th>
            <td>
                <input type="radio" name="gender" value="0" tabindex="5"<%=targetUser.getGenderSelected(0)%>>男性
                <input type="radio" name="gender" value="1" tabindex="6" <%=targetUser.getGenderSelected(1)%>>女性
            </td>
        </tr>

        <tr align="center">
            <th>生年月日</th>
            <td>
                西暦<input type="text" name="year" value="<jsp:getProperty name="targetUser" property="birthYear"/>"required pattern="^[0-9]+$"tabindex="7"/>年
                <input type="text" name="month" value="<jsp:getProperty name="targetUser" property="birthMonth"/>"required pattern="^[0-9]+$"tabindex="8"/>月
                <input type="text" name="day" value="<jsp:getProperty name="targetUser" property="birthDay"/>"required pattern="^[0-9]+$"tabindex="9"/>日
            </td>
        </tr>

        <tr align="center">
            <th>郵便番号</th>
            <td>
                <input type="text" name="postal_code"
                       value="<jsp:getProperty name="targetUser" property="postalCode"/>"required pattern="^[0-9]+$"tabindex="10"/><br>※ハイフンなしで入力
            </td>
        </tr>

        <tr align="center">
            <th>住所</th>
            <td>
                <input type="text" name="address" value="<jsp:getProperty name="targetUser" property="address"/>"tabindex="11"required/>
            </td>
        </tr>

        <tr align="center">
            <th>電話番号</th>
            <td>
                <input type="text" name="tel" value="<jsp:getProperty name="targetUser" property="tel"/>"required pattern="^[0-9]+$"tabindex="12"/><br>※ハイフンなしで入力
            </td>
        </tr>
        <tr align="center" class="studentStatus">
            <th>学部学科</th>
            <td>
                <select name="facultyDepartmentId" tabindex="13">
                    <%
                        for (FacultyDepartment facultyDepartment : facultyDepartmentList) {
                    %>
                    <option value="<%=facultyDepartment.getFacultyDepartmentId()%>"
                            <%=targetStudent.getFacultyDepartmentSelected(facultyDepartment.getFacultyDepartmentId())%>>

                        <%=facultyDepartment.getFaculty()%>
                        <%=facultyDepartment.getDepartment()%>
                    </option>
                    <%
                        }
                    %>
                </select>
            </td>
        </tr>
        <tr align="center" class="studentStatus">
            <th>学年</th>
            <td>
                <input type="text" name="grade"
                       value="<jsp:getProperty name="targetStudent" property="gradeString"/>" pattern="[1-4]" tabindex="14"/>
            </td>
        </tr>
        </tbody>
    </table>
    <%
        if (!targetUserId.equals("")) {
    %>
    <button type="submit" name="action" value="UserUpdateDone" class="btn_4" align="center"tabindex="15"> 更新</button>
    <button type="submit" name="action" value="UserDelete" class="btn_3" align="center"tabindex="16"> 削除</button>
    <%
    } else {
    %>
    <button type="submit" name="action" value="UserRegistration" class="btn_4" align="center"tabindex="15"> 登録</button>
    <%
        }
    %>
</form>

</body>
</html>
<script type="text/javascript">
    window.onload = function start() {
        classificationSelect()
    }

    function classificationSelect() {
        select = document.getElementsByTagName("select")
        if (select.user_classification.value == "学生") {
            studentStatus = document.getElementsByClassName("studentStatus")
            for (var i = 0; i < studentStatus.length; i++) {
                studentStatus[i].style.display = "";
            }
        } else {
            studentStatus = document.getElementsByClassName("studentStatus")
            for (var i = 0; i < studentStatus.length; i++) {
                studentStatus[i].style.display = "none";
            }
        }
    }
</script>
