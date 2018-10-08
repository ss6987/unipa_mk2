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
    <link rel="stylesheet" type="text/css" href="Design.css">
</head>
<body>
<%
    if (!targetSyllabusId.equals("")) {
%>
<form action="/SyllabusDetail" method="post">
    <button type="submit" name="action" class="btn_1" value="detail">戻る</button>
</form>
<%
    }
%>
<form action="/Top" method="get">
    <button type="submit" class="btn_1">トップ</button>
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
                <input type="text" name="syllabusId" pattern="^[0-9A-Za-z]+$" tabindex="1" required autofocus>
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
                       value="<jsp:getProperty name="targetSyllabus" property="syllabusName"/>"
                       tabindex="2"
                       required autofocus/>
            </td>
        </tr>
        <tr>
            <th width="30%">英語名</th>
            <td>
                <input type="text" name="englishName"
                       value="<jsp:getProperty name="targetSyllabus" property="englishName"/>"

                       tabindex="3" />
            </td>
        </tr>
        <tr>
            <th width="30%">配当学年</th>
            <td>
                <input type="text" name="dividendGrade"
                       value="<jsp:getProperty name="targetSyllabus" property="dividendGradeString"/>"
                       required pattern="^[1-4]$"
                tabindex="4" />
                年以上
            </td>
        </tr>
        <tr>
            <th width="30%">開講年度</th>
            <td>
                <input type="text" name="year"
                       value="<jsp:getProperty name="targetSyllabus" property="yearString"/>"
                       required pattern="^[0-9]+$"
                       tabindex="5" />
                年度
            </td>
        </tr>
        <tr>
            <th width="30%">教室</th>
            <td>
                <input type="text" name="classRoom"
                       value="<jsp:getProperty name="targetSyllabus" property="classRoom"/>"tabindex="6" />
            </td>
        </tr>
        <tr>
            <th width="30%">学期</th>
            <td>
                <select name="semester"tabindex="7">
                    <option value="前期" <%=targetSyllabus.getSemesterSelected("前期")%>>前期</option>
                    <option value="後期" <%=targetSyllabus.getSemesterSelected("後期")%>>後期</option>
                </select>
            </td>
        </tr>
        <tr>
            <th width="30%">曜日</th>
            <td>
                <input type="text" name="week"
                       value="<jsp:getProperty name="targetSyllabus" property="week"/>"tabindex="8" />
            </td>
        </tr>
        <tr>
            <th width="30%">時限</th>
            <td>
                <input type="text" name="time"
                       value="<jsp:getProperty name="targetSyllabus" property="time"/>"
                       required pattern="^[0-9,]+$"
                       tabindex="9" />
            </td>
        </tr>
        <tr>
            <th width="30%">単位数</th>
            <td>
                <input type="text" name="unit"
                       value="<jsp:getProperty name="targetSyllabus" property="unitString"/>"
                       required pattern="^[0-9]+$"
                       tabindex="10" />
            </td>
        </tr>
        <tr>
            <th width="30%">定員</th>
            <td>
                <input type="text" name="capacity"
                       value="<jsp:getProperty name="targetSyllabus" property="capacityString"/>"
                       required pattern="^[0-9]+$"
                       tabindex="11" />
                人
            </td>
        </tr>
        <tr>
            <th>主担当教員ID</th>
            <td><input value="root" type="text" name="mainTeacherId"tabindex="12"required></td>
        </tr>
        <tr>
            <th width="30%">目的概要</th>
            <td>
                <input type="text" name="objectiveSummary"
                       value="<jsp:getProperty name="targetSyllabus" property="objectiveSummary"/>"tabindex="13" />
            </td>
        </tr>
        <tr>
            <th width="30%">達成目標</th>
            <td>
                <input type="text" name="goal"
                       value="<jsp:getProperty name="targetSyllabus" property="goal"/>"tabindex="14" />
            </td>
        </tr>
        <tr>
            <th width="30%">教科書</th>
            <td>
                <input type="text" name="textbook"
                       value="<jsp:getProperty name="targetSyllabus" property="textbook"/>"tabindex="15" />
            </td>
        </tr>
        <tr>
            <th width="30%">参考書</th>
            <td>
                <input type="text" name="referenceBook"
                       value="<jsp:getProperty name="targetSyllabus" property="referenceBook"/>"tabindex="16" />
            </td>
        </tr>
        <tr>
            <th width="30%">学習・教育目標との対応</th>
            <td>
                <input type="text" name="educationalObject"
                       value="<jsp:getProperty name="targetSyllabus" property="educationalObject"/>"tabindex="17" />
            </td>
        </tr>
        <tr>
            <th width="30%">DPとの対応</th>
            <td>
                <input type="text" name="dp"
                       value="<jsp:getProperty name="targetSyllabus" property="dp"/>"tabindex="18" />
            </td>
        </tr>
        <tr>
            <th width="30%">事前・事後学習</th>
            <td>
                <input type="text" name="selfStudy"
                       value="<jsp:getProperty name="targetSyllabus" property="selfStudy"/>"tabindex="19" />
            </td>
        </tr>
        <tr>
            <th width="30%">自由記載欄</th>
            <td>
                <input type="text" name="freeText"
                       value="<jsp:getProperty name="targetSyllabus" property="freeText"/>"tabindex="20" />
            </td>
        </tr>
        <tr>
            <th width="30%">メールアドレス</th>
            <td>
                <input type="text" name="mailAddress"
                       value="<jsp:getProperty name="targetSyllabus" property="mailAddress"/>"tabindex="21"/>
            </td>
        </tr>
        <tr>
            <th width="30%">オフィスアワー等</th>
            <td>
                <input type="text" name="officeHour"
                       value="<jsp:getProperty name="targetSyllabus" property="officeHour"/>"tabindex="22"/>
            </td>
        </tr>
        <tr>
            <th width="30%">クラス分け情報</th>
            <td>
                <input type="text" name="classification"
                       value="<jsp:getProperty name="targetSyllabus" property="classification"/>"tabindex="23"/>
            </td>
        </tr>
        <tr>
            <th width="30%">ガイダンス情報</th>
            <td>
                <input type="text" name="guidance"
                       value="<jsp:getProperty name="targetSyllabus" property="guidance"/>"tabindex="24"/>
            </td>
        </tr>
        <tr>
            <th width="30%">学習上の助言</th>
            <td>
                <input type="text" name="advice"
                       value="<jsp:getProperty name="targetSyllabus" property="advice"/>"tabindex="25"/>
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
                <input type="text" name="syllabusContents<%=i%>" tabindex="<%=25+i%>"/>
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
    <button type="submit" name="action" class="btn_4" value="update"tabindex="42">更新</button>
    <button type="submit" name="action" class="btn_3" value="delete"tabindex="43">削除</button>
    <%
    } else {
    %>
    <button type="submit" name="action" class="btn_4" value="insert"tabindex="42">登録</button>
    <%
        }
    %>
</form>
</body>
</html>
