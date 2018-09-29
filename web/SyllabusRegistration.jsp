<%@ page import="Entity.SyllabusContents" %><%--
  Created by IntelliJ IDEA.
  User: satone
  Date: 2018/07/20
  Time: 19:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="targetSyllabus" class="Entity.SyllabusDetail" scope="request"/>
<jsp:useBean id="targetSyllabusId" class="java.lang.String" scope="session"/>
<jsp:useBean id="errorString" class="java.lang.String" scope="request"/>

<html lang="ja">
<head>
    <title>シラバス登録</title>
</head>
<body>
<form action="/SyllabusDetail" method="post">
    <button type="submit" name="action" value="detail">戻る</button>
</form>
<form action="/Top" method="get">
    <button type="submit">トップ</button>
</form>

<%
    if (!targetSyllabusId.equals("")) {
%>
<h1>シラバス更新</h1>
<%
} else {
%>
<h1>シラバス登録</h1>
<%
    }
%>

<br>
<%
    if (!errorString.equals("")) {
%>
<%=errorString%>
<%
    }
%>
<form action="/SyllabusUpdate" method="post">
    <table border="3">
        <tr>
            <th width="30%">シラバスID</th>
            <td>
                <%
                    if (targetSyllabusId.equals("")) {
                %>
                <input type="text" name="syllabusId">
                <%
                } else {
                %>
                <jsp:getProperty name="targetSyllabus" property="syllabusId"/>
                <%
                    }
                %>
            </td>
        </tr>
        <tr>
            <th width="30%">授業名</th>
            <td>
                <input type="text" name="syllabusName"
                       value="<jsp:getProperty name="targetSyllabus" property="syllabusName"/>"/>
            </td>
        </tr>
        <tr>
            <th width="30%">英語名</th>
            <td>
                <input type="text" name="englishName"
                       value="<jsp:getProperty name="targetSyllabus" property="englishName"/>"/>
            </td>
        </tr>
        <tr>
            <th width="30%">配当学年</th>
            <td>
                <input type="text" name="dividendGrade"
                       value="<jsp:getProperty name="targetSyllabus" property="dividendGradeString"/>"/>
                年以上
            </td>
        </tr>
        <tr>
            <th width="30%">開講年度</th>
            <td>
                <input type="text" name="year"
                       value="<jsp:getProperty name="targetSyllabus" property="yearString"/>"/>
                年度
            </td>
        </tr>
        <tr>
            <th width="30%">教室</th>
            <td>
                <input type="text" name="classRoom"
                       value="<jsp:getProperty name="targetSyllabus" property="classRoom"/>"/>
            </td>
        </tr>
        <tr>
            <th width="30%">学期</th>
            <td>
                <input type="text" name="semester"
                       value="<jsp:getProperty name="targetSyllabus" property="semester"/>"/>
            </td>
        </tr>
        <tr>
            <th width="30%">曜日</th>
            <td>
                <input type="text" name="week"
                       value="<jsp:getProperty name="targetSyllabus" property="week"/>"/>
            </td>
        </tr>
        <tr>
            <th width="30%">時限</th>
            <td>
                <input type="text" name="time"
                       value="<jsp:getProperty name="targetSyllabus" property="time"/>"/>
            </td>
        </tr>
        <tr>
            <th width="30%">単位数</th>
            <td>
                <input type="text" name="unit"
                       value="<jsp:getProperty name="targetSyllabus" property="unitString"/>"/>
            </td>
        </tr>
        <tr>
            <th width="30%">定員</th>
            <td>
                <input type="text" name="capacity"
                       value="<jsp:getProperty name="targetSyllabus" property="capacityString"/>"/>
                人
            </td>
        </tr>
        <tr>
            <th>主担当教員</th>
            <td><input value="root" type="text" name="mainTeacherId"></td>
        </tr>
        <tr>
            <th width="30%">目的概要</th>
            <td>
                <input type="text" name="objectiveSummary"
                       value="<jsp:getProperty name="targetSyllabus" property="objectiveSummary"/>"/>
            </td>
        </tr>
        <tr>
            <th width="30%">達成目標</th>
            <td>
                <input type="text" name="goal"
                       value="<jsp:getProperty name="targetSyllabus" property="goal"/>"/>
            </td>
        </tr>
        <tr>
            <th width="30%">教科書</th>
            <td>
                <input type="text" name="textbook"
                       value="<jsp:getProperty name="targetSyllabus" property="textbook"/>"/>
            </td>
        </tr>
        <tr>
            <th width="30%">参考書</th>
            <td>
                <input type="text" name="referenceBook"
                       value="<jsp:getProperty name="targetSyllabus" property="referenceBook"/>"/>
            </td>
        </tr>
        <tr>
            <th width="30%">学習・教育目標との対応</th>
            <td>
                <input type="text" name="educationalObject"
                       value="<jsp:getProperty name="targetSyllabus" property="educationalObject"/>"/>
            </td>
        </tr>
        <tr>
            <th width="30%">DPとの対応</th>
            <td>
                <input type="text" name="dp"
                       value="<jsp:getProperty name="targetSyllabus" property="dp"/>"/>
            </td>
        </tr>
        <tr>
            <th width="30%">事前・事後学習</th>
            <td>
                <input type="text" name="selfStudy"
                       value="<jsp:getProperty name="targetSyllabus" property="selfStudy"/>"/>
            </td>
        </tr>
        <tr>
            <th width="30%">自由記載欄</th>
            <td>
                <input type="text" name="freeText"
                       value="<jsp:getProperty name="targetSyllabus" property="freeText"/>"/>
            </td>
        </tr>
        <tr>
            <th width="30%">メールアドレス</th>
            <td>
                <input type="text" name="mailAddress"
                       value="<jsp:getProperty name="targetSyllabus" property="mailAddress"/>"/>
            </td>
        </tr>
        <tr>
            <th width="30%">オフィスアワー等</th>
            <td>
                <input type="text" name="officeHour"
                       value="<jsp:getProperty name="targetSyllabus" property="officeHour"/>"/>
            </td>
        </tr>
        <tr>
            <th width="30%">クラス分け情報</th>
            <td>
                <input type="text" name="classification"
                       value="<jsp:getProperty name="targetSyllabus" property="classification"/>"/>
            </td>
        </tr>
        <tr>
            <th width="30%">ガイダンス情報</th>
            <td>
                <input type="text" name="guidance"
                       value="<jsp:getProperty name="targetSyllabus" property="guidance"/>"/>
            </td>
        </tr>
        <tr>
            <th width="30%">学習上の助言</th>
            <td>
                <input type="text" name="advice"
                       value="<jsp:getProperty name="targetSyllabus" property="advice"/>"/>
            </td>
        </tr>
        <%
            if (!targetSyllabusId.equals("")) {
                for (SyllabusContents syllabusContents : targetSyllabus.getSyllabusContents()) {
        %>
        <tr>
            <th width="30%">第<%=syllabusContents.getClassNumber()%>回</th>
            <td>
                <input type="text" name="syllabusContents<%=syllabusContents.getClassNumber()%>"
                       value="<%=syllabusContents.getCourseContent()%>"/>
            </td>
        </tr>
        <%
            }
        } else {
            for (int i = 1; i <= 16; i++) {
        %>
        <tr>
            <th width="30%">第<%=i%>回</th>
            <td>
                <input type="text" name="syllabusContents<%=i%>"/>
            </td>
        </tr>

        <%
                }
            }
        %>
    </table>
    <%
        if (!targetSyllabusId.equals("")) {
    %>
    <button type="submit" name="action" value="update">更新</button>
    <button type="submit" name="action" value="delete">削除</button>
    <%
    } else {
    %>
    <button type="submit" name="action" value="insert">登録</button>
    <%
        }
    %>
</form>
</body>
</html>
