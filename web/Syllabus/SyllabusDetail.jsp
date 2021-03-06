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
<jsp:useBean id="backPage" scope="session" class="java.lang.String"/>

<html lang="ja">
<head>
    <title>
        <jsp:getProperty name="targetSyllabus" property="syllabusName"/>
    </title>
    <link rel="stylesheet" type="text/css" href="Design.css">
</head>
<body>
<table id="window">
    <tr>
        <td class="ta-box">
            <form action="/Main" method="post">
                <button type="submit" name="action" value="Top" class="btn_1"
                        style="position: absolute; left: 0px; top: 0px">トップ
                </button>
            </form>

        </td>
        <td class="tb-box">
            <%
                if (backPage.equals("searchResult")) {
            %>
            <form action="/Main" method="post">
                <button type="submit" name="action" class="btn_1" value="SyllabusSearchBack">戻る</button>
            </form>
            <%
            } else if (backPage.equals("timetable")) {
            %>
            <form action="/Main" method="post">
                <button type="submit" name="action" class="btn_1" value="TimeTableCheck">戻る</button>
            </form>
            <%
                }
            %>
        </td>
    </tr>
</table>
<table id="subwindow">
    <tr>
        <td class="a-box">
            <h1>シラバス<br>詳細</h1>

            <%
                boolean registrationPeriodFlag = (boolean) session.getAttribute("registrationPeriodFlag");
                String semesterString = (String) request.getAttribute("semesterString");
                if (user.getUserClassification().equals("管理者")) {
            %>

            <form action="/Main" method="post">
                <button type="submit" name="action" class="btn_5" value="SyllabusUpdate">更新ページへ</button>
                <button type="submit" name="action" class="btn_3" value="SyllabusDelete">シラバス削除</button>
                <button type="submit" name="action" class="btn_5" value="CourseList">履修登録者一覧</button>
                <button type="submit" name="action" class="btn_5" value="AchieveRegistration">成績登録</button>
            </form>
            <%
            } else if ((boolean) request.getAttribute("inChargeFlag")) {
            %>
            <form action="/Main" method="post">
                <button type="submit" name="action" class="btn_5" value="CourseList">履修登録者一覧</button>
            </form>
            <%
            } else if (user.getUserClassification().equals("学生") && registrationPeriodFlag && targetSyllabus.getSemester().equals(semesterString)) {
            %>
            <form action="/Main" method="post">
                <input type="hidden" name="targetSyllabusId"
                       value="<jsp:getProperty name="targetSyllabus" property="syllabusId"/>">
                <button type="submit" name="action" class="btn_5" value="CourseRegistrationAdd">履修登録に追加</button>
            </form>
            <%
                }
            %>


        </td>
        <td class="c-box">
            <table border="3" class="part">
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
                    <th width="30%">主担当教員</th>
                    <td>
                        <jsp:getProperty name="targetSyllabus" property="mainTeacher"/>
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
                    <th width="30%">
                        第<%=syllabusContents.getClassNumber()%>回
                    </th>
                    <td>
                        <%=syllabusContents.getCourseContent()%>
                    </td>
                </tr>
                <%
                    }
                %>
            </table>
        </td>
    </tr>
</table>
</body>
</html>
