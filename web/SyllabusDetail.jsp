<%@ page import="Entity.SyllabusDetail" %>
<%@ page import="Entity.SyllabusContents" %><%--
  Created by IntelliJ IDEA.
  User: SS
  Date: 2018/09/19
  Time: 11:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    SyllabusDetail syllabusDetail = (SyllabusDetail) request.getAttribute("targetSyllabus");
%>
<html lang="ja">
<head>
    <title><%=syllabusDetail.getSyllabusName()%>
    </title>
</head>
<body>
<h1>シラバス詳細</h1>
<table border="3">
    <tr>
        <th width="30%">シラバスID</th>
        <td><%=syllabusDetail.getSyllabusId()%></td>
    </tr>
    <tr>
        <th width="30%">授業名</th>
        <td><%=syllabusDetail.getSyllabusName()%></td>
    </tr>
    <tr>
        <th width="30%">英語名</th>
        <td><%=syllabusDetail.getEnglishName()%></td>
    </tr>
    <tr>
        <th width="30%">配当学年</th>
        <td><%=syllabusDetail.getDividendGrade()%>年以上</td>
    </tr>
    <tr>
        <th width="30%">開講年度</th>
        <td><%=syllabusDetail.getYear()%>年度</td>
    </tr>
    <tr>
        <th width="30%">教室</th>
        <td><%=syllabusDetail.getClassRoom()%></td>
    </tr>
    <tr>
        <th width="30%">学期</th>
        <td><%=syllabusDetail.getSemester()%></td>
    </tr>
    <tr>
        <th width="30%">曜日</th>
        <td><%=syllabusDetail.getWeek()%></td>
    </tr>
    <tr>
        <th width="30%">時限</th>
        <td><%=syllabusDetail.getTime()%></td>
    </tr>
    <tr>
        <th width="30%">単位数</th>
        <td><%=syllabusDetail.getUnit()%></td>
    </tr>
    <tr>
        <th width="30%">定員</th>
        <td><%=syllabusDetail.getCapacity()%>人</td>
    </tr>
    <tr>
        <th width="30%">目的概要</th>
        <td><%=syllabusDetail.getObjectiveSummary()%></td>
    </tr>
    <tr>
        <th width="30%">達成目標</th>
        <td><%=syllabusDetail.getGoal()%></td>
    </tr>
    <tr>
        <th width="30%">教科書</th>
        <td><%=syllabusDetail.getTextbook()%></td>
    </tr>
    <tr>
        <th width="30%">参考書</th>
        <td><%=syllabusDetail.getReferenceBook()%></td>
    </tr>
    <tr>
        <th width="30%">学習・教育目標との対応</th>
        <td><%=syllabusDetail.getEducationalObject()%></td>
    </tr>
    <tr>
        <th width="30%">DPとの対応</th>
        <td><%=syllabusDetail.getDp()%></td>
    </tr>
    <tr>
        <th width="30%">事前・事後学習</th>
        <td></td>
    </tr>
    <tr>
        <th width="30%">自由記載欄</th>
        <td><%=syllabusDetail.getFreeText()%></td>
    </tr>
    <tr>
        <th width="30%">メールアドレス</th>
        <td><%=syllabusDetail.getMailAddress()%></td>
    </tr>
    <tr>
        <th width="30%">オフィスアワー等</th>
        <td><%=syllabusDetail.getOfficeHour()%></td>
    </tr>
    <tr>
        <th width="30%">クラス分け情報</th>
        <td><%=syllabusDetail.getClassification()%></td>
    </tr>
    <tr>
        <th width="30%">ガイダンス情報</th>
        <td><%=syllabusDetail.getGuidance()%></td>
    </tr>
    <tr>
        <th width="30%">学習上の助言</th>
        <td><%=syllabusDetail.getAdvice()%></td>
    </tr>
    <%
        for (SyllabusContents syllabusContents : syllabusDetail.getSyllabusContents()) {
    %>
    <tr>
        <th width="30%">第<%=syllabusContents.getClassNumber()%>回</th>
        <td><%=syllabusContents.getCourseContent()%></td>
    </tr>
    <%
        }
    %>
</table>
</body>
</html>
