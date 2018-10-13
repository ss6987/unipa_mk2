<%--
  Created by IntelliJ IDEA.
  User: ASAMI
  Date: 2018/07/27
  Time: 3:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="searchSyllabus" class="Entity.Syllabus" scope="session"/>
<jsp:useBean id="errorString" class="java.lang.String" scope="request"/>
<html lang="ja">
<head>
    <title>シラバス検索</title>
    <link rel="stylesheet" type="text/css" href="Design.css">
</head>
<body>
<form action="/Main" method="get">
    <button type="submit" name="action" value="Top" class="btn_1">トップ</button>
</form>
<br>
<h1>シラバス検索</h1>
<br>
<%
    if (!errorString.equals("")) {
%>
<span style="background-color:#ffcc99">
    <%=errorString%>
</span><br>
<%
    }
%>
<form action="/SyllabusSearch" method="post">
    <table BORDER="1" align="center">
        <tr>
            <th>授業ID</th>
            <td>
                <input type="text" name="syllabus_id"
                       value="<jsp:getProperty name="searchSyllabus" property="syllabusId"/>"/>
            </td>

        </tr>

        <tr align="center">
            <th>科授業名</th>
            <td>
                <input type="text" name="syllabus_name"
                       value="<jsp:getProperty name="searchSyllabus" property="syllabusName"/>"/>
            </td>
        </tr>

        <tr>
            <th>英語名</th>
            <td>
                <input type="text" name="english_name"
                       value="<jsp:getProperty name="searchSyllabus" property="englishName"/>"/>
            </td>
        </tr>

        <tr align="center">
            <th>配当学年</th>
            <td>
                <select name="dividend_grade">
                    <option value="-1">全学年</option>
                    <option value="1" <%=searchSyllabus.getDividendGradeSelected(1)%>>1</option>
                    <option value="2" <%=searchSyllabus.getDividendGradeSelected(2)%>>2</option>
                    <option value="3" <%=searchSyllabus.getDividendGradeSelected(3)%>>3</option>
                    <option value="4" <%=searchSyllabus.getDividendGradeSelected(4)%>>4</option>
                </select>
            </td>
        </tr>

        <tr align="center">
            <th>開講年度</th>
            <td>
                <input type="text" name="year"
                       value="<jsp:getProperty name="searchSyllabus" property="yearString"/>"/>
            </td>
        </tr>

        <tr align="center">
            <th>学期</th>
            <td>
                <select name="semester">
                    <option value="">---</option>
                    <option value="前期" <%=searchSyllabus.getSemesterSelected("前期")%>>前期</option>
                    <option value="後期" <%=searchSyllabus.getSemesterSelected("後期")%>>後期</option>
                </select>
            </td>
        </tr>
        <tr>
            <th>曜日</th>
            <td>
                <select name="week">
                    <option value="">---</option>
                    <option value="月" <%=searchSyllabus.getWeekSelected("月")%>>月</option>
                    <option value="火" <%=searchSyllabus.getWeekSelected("火")%>>火</option>
                    <option value="水" <%=searchSyllabus.getWeekSelected("水")%>>水</option>
                    <option value="木" <%=searchSyllabus.getWeekSelected("木")%>>木</option>
                    <option value="金" <%=searchSyllabus.getWeekSelected("金")%>>金</option>
                    <option value="土" <%=searchSyllabus.getWeekSelected("土")%>>土</option>
                </select>
            </td>
        </tr>
        <tr>
            <th>時限</th>
            <td>
                <select name="time">
                    <option value="">---</option>
                    <option value="1" <%=searchSyllabus.getTimeSelected("1")%>>1</option>
                    <option value="2" <%=searchSyllabus.getTimeSelected("2")%>>2</option>
                    <option value="3" <%=searchSyllabus.getTimeSelected("3")%>>3</option>
                    <option value="4" <%=searchSyllabus.getTimeSelected("4")%>>4</option>
                    <option value="5" <%=searchSyllabus.getTimeSelected("5")%>>5</option>
                    <option value="6" <%=searchSyllabus.getTimeSelected("6")%>>6</option>
                    <option value="7" <%=searchSyllabus.getTimeSelected("7")%>>7</option>
                    <option value="8" <%=searchSyllabus.getTimeSelected("8")%>>8</option>
                </select>
            </td>
        </tr>

        <tr align="center">
            <th>科目分類</th>
            <td>
            </td>
        </tr>

        <tr align="center">
            <th>担当教員</th>
            <td>
            </td>
        </tr>

        <tr align="center">
            <th>単位数</th>
            <td>
                <input type="text" name="unit"
                       value="<jsp:getProperty name="searchSyllabus" property="unitString"/>"/>
            </td>
        </tr>

        <tr align="center">
            <th>定員</th>
            <td>
                <input type="text" name="capacity"
                       value="<jsp:getProperty name="searchSyllabus" property="capacityString"/>"/>
            </td>
        </tr>

        <tr align="center">
            <th>関連科目</th>
            <td>
            </td>
        </tr>

        <tr align="center">
            <th>履修条件</th>
            <td>
            </td>
        </tr>

        <tr align="center">
            <th>教室</th>
            <td>
                <input type="text" name="classroom"
                       value="<jsp:getProperty name="searchSyllabus" property="classRoom"/>"/>
            </td>
        </tr>

    </table>
    <input type="hidden" name="action" value="first_search"/>
    <button type="submit" name="action" class="btn_5" value="search">検索</button>
</form>
</body>
</html>
