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
<table id="window">
    <tr>
        <td class="ta-box">
            <form action="/Main" method="post">
                <button type="submit" name="action" value="Top" class="btn_1">トップ</button>
            </form>
        </td>
        <td class="tb-box"></td>
    </tr>
    <tr>
        <td class="c-box" colspan="2">
            <h1>シラバス検索</h1>
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
                        <th>授業ID</th>
                        <td>
                            <input type="text" name="syllabus_id" pattern="^[0-9A-Za-z]+$" tabindex="1"
                                   value="<jsp:getProperty name="searchSyllabus" property="syllabusId"/>"/>
                        </td>

                    </tr>

                    <tr>
                        <th>授業名</th>
                        <td>
                            <input type="text" name="syllabus_name" tabindex="2"
                                   value="<jsp:getProperty name="searchSyllabus" property="syllabusName"/>"/>
                        </td>
                    </tr>

                    <tr>
                        <th>英語名</th>
                        <td>
                            <input type="text" name="english_name" tabindex="3"
                                   value="<jsp:getProperty name="searchSyllabus" property="englishName"/>"/>
                        </td>
                    </tr>

                    <tr>
                        <th>配当学年</th>
                        <td>
                            <select name="dividend_grade" tabindex="4">
                                <option value="-1">全学年</option>
                                <option value="1" <%=searchSyllabus.getDividendGradeSelected(1)%>>1</option>
                                <option value="2" <%=searchSyllabus.getDividendGradeSelected(2)%>>2</option>
                                <option value="3" <%=searchSyllabus.getDividendGradeSelected(3)%>>3</option>
                                <option value="4" <%=searchSyllabus.getDividendGradeSelected(4)%>>4</option>
                            </select>
                        </td>
                    </tr>

                    <tr>
                        <th>開講年度</th>
                        <td>
                            <input type="text" name="year" pattern="^20[0-9]{2}" tabindex="5"
                                   value="<jsp:getProperty name="searchSyllabus" property="yearString"/>"/>
                        </td>
                    </tr>

                    <tr>
                        <th>学期</th>
                        <td>
                            <select name="semester" tabindex="6">
                                <option value="">---</option>
                                <option value="前期" <%=searchSyllabus.getSemesterSelected("前期")%>>前期</option>
                                <option value="後期" <%=searchSyllabus.getSemesterSelected("後期")%>>後期</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th>曜日</th>
                        <td>
                            <select name="week" tabindex="7">
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
                            <select name="time" tabindex="8">
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
                    <tr>
                        <th>単位数</th>
                        <td>
                            <input type="text" name="unit" pattern="^[0-9]+$" tabindex="9"
                                   value="<jsp:getProperty name="searchSyllabus" property="unitString"/>"/>
                        </td>
                    </tr>

                    <tr>
                        <th>定員</th>
                        <td>
                            <input type="text" name="capacity" pattern="^[0-9]+$" tabindex="10"
                                   value="<jsp:getProperty name="searchSyllabus" property="capacityString"/>"/>
                        </td>
                    </tr>

                    <tr>
                        <th>教室</th>
                        <td>
                            <input type="text" name="class_room" tabindex="11"
                                   value="<jsp:getProperty name="searchSyllabus" property="classRoom"/>"/>
                        </td>
                    </tr>
                    <tr>
                        <th>ソート</th>
                        <td>
                            <select name="sort_key">
                                <option value="0">授業ID</option>
                                <option value="1">授業名</option>
                                <option value="2">英語名</option>
                                <option value="3">開講年度</option>
                                <option value="4">学期</option>
                                <option value="5">曜日</option>
                                <option value="6">時限</option>
                                <option value="7">単位数</option>
                                <option value="8">定員</option>
                                <option value="9">教室</option>
                            </select>
                            <input type="radio" name="asc_desc" value="0" checked>昇順
                            <input type="radio" name="asc_desc" value="1">降順
                        </td>
                    </tr>
                </table>
                <button type="submit" name="action" class="btn_5" value="SyllabusSearchFirst" tabindex="12">検索</button>
            </form>
        </td>
    </tr>
</table>
</body>
</html>
