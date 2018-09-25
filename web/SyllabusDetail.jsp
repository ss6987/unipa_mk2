<%@ page import="Entity.SyllabusDetail" %>
<%@ page import="Entity.SyllabusContents" %>
<%--
  Created by IntelliJ IDEA.
  User: SS
  Date: 2018/09/19
  Time: 11:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="user" scope="session" class="Entity.User"/>
<jsp:useBean id="targetSyllabusId" scope="session" class="java.lang.String"/>
<jsp:useBean id="targetSyllabus" scope="request" class="Entity.SyllabusDetail"/>

<html lang="ja">
<head>
    <title>
        <jsp:getProperty name="targetSyllabus" property="syllabusName"/>
    </title>
</head>
<body>
<h1>シラバス詳細</h1>

<%
    if (user.getUserClassification().equals("管理者")) {
%>
<form action="/SyllabusDetail" method="post">
    <button type="submit" name="action" value="update">更新ページへ</button>
</form>
<form action="/SyllabusDetail" method="post">
    <button type="submit" name="action" value="delete">シラバス削除</button>
</form>
<form action="/SyllabusSearch" method="post">
    <button type="submit" name="action" value="return">戻る</button>
</form>
<%
    }
%>

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
            <jsp:getProperty name="targetSyllabus" property="syllabusName"/>
        </td>
    </tr>
    <tr>
        <th width="30%">英語名</th>
        <td>
            <jsp:getProperty name="targetSyllabus" property="englishName"/>
        </td>
    </tr>
    <tr>
        <th width="30%">配当学年</th>
        <td>
            <jsp:getProperty name="targetSyllabus" property="dividendGrade"/>
            年以上
        </td>
    </tr>
    <tr>
        <th width="30%">開講年度</th>
        <td>
            <jsp:getProperty name="targetSyllabus" property="year"/>
            年度
        </td>
    </tr>
    <tr>
        <th width="30%">教室</th>
        <td>
            <jsp:getProperty name="targetSyllabus" property="classRoom"/>
        </td>
    </tr>
    <tr>
        <th width="30%">学期</th>
        <td>
            <jsp:getProperty name="targetSyllabus" property="semester"/>
        </td>
    </tr>
    <tr>
        <th width="30%">曜日</th>
        <td>
            <jsp:getProperty name="targetSyllabus" property="week"/>
        </td>
    </tr>
    <tr>
        <th width="30%">時限</th>
        <td>
            <jsp:getProperty name="targetSyllabus" property="time"/>
        </td>
    </tr>
    <tr>
        <th width="30%">単位数</th>
        <td>
            <jsp:getProperty name="targetSyllabus" property="unit"/>
        </td>
    </tr>
    <tr>
        <th width="30%">定員</th>
        <td>
            <jsp:getProperty name="targetSyllabus" property="capacity"/>
            人
        </td>
    </tr>
    <tr>
        <th width="30%">目的概要</th>
        <td>
            <jsp:getProperty name="targetSyllabus" property="objectiveSummary"/>
        </td>
    </tr>
    <tr>
        <th width="30%">達成目標</th>
        <td>
            <jsp:getProperty name="targetSyllabus" property="goal"/>
        </td>
    </tr>
    <tr>
        <th width="30%">教科書</th>
        <td>
            <jsp:getProperty name="targetSyllabus" property="textbook"/>
        </td>
    </tr>
    <tr>
        <th width="30%">参考書</th>
        <td>
            <jsp:getProperty name="targetSyllabus" property="referenceBook"/>
        </td>
    </tr>
    <tr>
        <th width="30%">学習・教育目標との対応</th>
        <td>
            <jsp:getProperty name="targetSyllabus" property="educationalObject"/>
        </td>
    </tr>
    <tr>
        <th width="30%">DPとの対応</th>
        <td>
            <jsp:getProperty name="targetSyllabus" property="dp"/>
        </td>
    </tr>
    <tr>
        <th width="30%">事前・事後学習</th>
        <td>
            <jsp:getProperty name="targetSyllabus" property="selfStudy"/>
        </td>
    </tr>
    <tr>
        <th width="30%">自由記載欄</th>
        <td>
            <jsp:getProperty name="targetSyllabus" property="freeText"/>
        </td>
    </tr>
    <tr>
        <th width="30%">メールアドレス</th>
        <td>
            <jsp:getProperty name="targetSyllabus" property="mailAddress"/>
        </td>
    </tr>
    <tr>
        <th width="30%">オフィスアワー等</th>
        <td>
            <jsp:getProperty name="targetSyllabus" property="officeHour"/>
        </td>
    </tr>
    <tr>
        <th width="30%">クラス分け情報</th>
        <td>
            <jsp:getProperty name="targetSyllabus" property="classification"/>
        </td>
    </tr>
    <tr>
        <th width="30%">ガイダンス情報</th>
        <td>
            <jsp:getProperty name="targetSyllabus" property="guidance"/>
        </td>
    </tr>
    <tr>
        <th width="30%">学習上の助言</th>
        <td>
            <jsp:getProperty name="targetSyllabus" property="advice"/>
        </td>
    </tr>
    <%
        for (SyllabusContents syllabusContents : targetSyllabus.getSyllabusContents()) {
    %>
    <tr>
        <th width="30%">第<%=syllabusContents.getClassNumber()%>回</th>
        <td><%=syllabusContents.getCourseContent()%>
        </td>
    </tr>
    <%
        }
    %>
</table>

</body>
</html>
