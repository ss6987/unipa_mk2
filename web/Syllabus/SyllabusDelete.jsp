<%--
  Created by IntelliJ IDEA.
  User: ASAMI
  Date: 2018/07/27
  Time: 3:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="user" scope="session" class="Entity.User"/>
<jsp:useBean id="targetSyllabusId" scope="session" class="java.lang.String"/>
<jsp:useBean id="targetSyllabus" scope="request" class="Entity.SyllabusDetail"/>
<jsp:useBean id="errorString" scope="request" class="java.lang.String"/>
<html lang="ja">
<head>
    <title>シラバス削除確認</title>
    <link rel="stylesheet" type="text/css" href="Design.css">
</head>
<body>
<table id="window">
    <tr>
        <td class="ta-box">
            <form action="/SyllabusDetail" method="post">
                <button type="submit" name="action" class="btn_1 inline-link" value="detail">戻る</button>
            </form>
        </td>
        <td class="tb-box"></td>
    </tr>

    <tr>
        <td class="c-box" colspan="2">
            <h1>削除確認</h1>
            <span style="background-color:#ffcc99">以下のシラバス情報を完全に削除します。</span>
            <%
                if (!errorString.equals("")) {
            %>
            <div>
                <%=errorString%>
            </div>
            <%
                }
            %>
            <table BORDER="1" class="part">
                <tr>
                    <th>シラバスID</th>
                    <td>
                        <jsp:getProperty name="targetSyllabus" property="syllabusId"/>
                    </td>
                </tr>

                <tr>
                    <th>授業名</th>
                    <td>
                        <jsp:getProperty name="targetSyllabus" property="syllabusName"/>
                    </td>
                </tr>

                <tr>
                    <th>英語名</th>
                    <td>
                        <jsp:getProperty name="targetSyllabus" property="englishName"/>
                    </td>
                </tr>

                <tr>
                    <th>配当学年</th>
                    <td>
                        <jsp:getProperty name="targetSyllabus" property="dividendGrade"/>
                    </td>
                </tr>

                <tr>
                    <th>学期</th>
                    <td>
                        <jsp:getProperty name="targetSyllabus" property="semester"/>
                    </td>
                </tr>

                <tr>
                    <th>曜日</th>
                    <td>
                        <jsp:getProperty name="targetSyllabus" property="week"/>
                    </td>
                </tr>

                <tr>
                    <th>時限</th>
                    <td>
                        <jsp:getProperty name="targetSyllabus" property="time"/>
                    </td>
                </tr>

                <tr>
                    <th>単位数</th>
                    <td>
                        <jsp:getProperty name="targetSyllabus" property="unit"/>
                    </td>
                </tr>

                <tr>
                    <th>主担当教員名</th>
                    <td>
                        <jsp:getProperty name="targetSyllabus" property="mainTeacher"/>
                    </td>
                </tr>

                <%--<tr align="center">--%>
                <%--<th>関連科目</th>--%>
                <%--</tr>--%>

                <%--<tr align="center">--%>
                <%--<th>履修条件</th>--%>
                <%--</tr>--%>


                <tr>
                    <th>定員数</th>
                    <td>
                        <jsp:getProperty name="targetSyllabus" property="capacity"/>
                    </td>
                </tr>

            </table>


            <form action="/Main" method="post">
                <button type="submit" name="action" class="btn_3" value="SyllabusDeleteDone">削除確定</button>
            </form>

            <form action="/Main" method="post">
                <button type="submit" name="action" class="btn_1" value="SyllabusDetail">戻る</button>
            </form>
        </td>
    </tr>
</table>
</body>
</html>
