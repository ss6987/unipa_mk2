<%@ page import="Entity.SyllabusContents" %>
<%--
  Created by IntelliJ IDEA.
  User: SS
  Date: 2018/09/24
  Time: 23:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="targetSyllabusId" scope="session" class="java.lang.String"/>
<jsp:useBean id="targetSyllabus" scope="request" class="Entity.SyllabusDetail"/>

<html lang="ja">
<head>
    <title><jsp:getProperty name="targetSyllabus" property="syllabusName"/></title>
    <link rel="stylesheet" type="text/css" href="Design.css">
</head>
<body>
<h1>シラバス更新</h1>
<form action="/SyllabusDetail" method="post">
    <button type="submit" name="action" class="btn_1" value="detail">戻る</button>
</form>
<form action="/SyllabusUpdate" method="post">
    <button type="submit" name="action" class="btn_4" value="update">更新</button>
    <table border="3">
        <tr>
            <th width="30%">シラバスID</th>
            <td>
                <jsp:getProperty name="targetSyllabus" property="syllabusId"/>
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
                       value="<jsp:getProperty name="targetSyllabus" property="dividendGrade"/>"/>
                年以上
            </td>
        </tr>
        <tr>
            <th width="30%">開講年度</th>
            <td>
                <input type="text" name="year" value="<jsp:getProperty name="targetSyllabus" property="year"/>"/>
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
                <input type="text" name="week" value="<jsp:getProperty name="targetSyllabus" property="week"/>"/>
            </td>
        </tr>
        <tr>
            <th width="30%">時限</th>
            <td>
                <input type="text" name="time" value="<jsp:getProperty name="targetSyllabus" property="time"/>"/>
            </td>
        </tr>
        <tr>
            <th width="30%">単位数</th>
            <td>
                <input type="text" name="unit" value="<jsp:getProperty name="targetSyllabus" property="unit"/>"/>
            </td>
        </tr>
        <tr>
            <th width="30%">定員</th>
            <td>
                <input type="text" name="capacity"
                       value="<jsp:getProperty name="targetSyllabus" property="capacity"/>"/>
                人
            </td>
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
                <input type="text" name="goal" value="<jsp:getProperty name="targetSyllabus" property="goal"/>"/>
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
                <input type="text" name="dp" value="<jsp:getProperty name="targetSyllabus" property="dp"/>"/>
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
                <input type="text" name="advice" value="<jsp:getProperty name="targetSyllabus" property="advice"/>"/>
            </td>
        </tr>
        <%
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
        %>
    </table>
</form>
</body>
</html>
