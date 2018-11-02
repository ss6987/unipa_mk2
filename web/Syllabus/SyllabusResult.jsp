<%@ page import="Entity.Syllabus" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: ASAMI
  Date: 2018/07/27
  Time: 4:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="paging" class="etc.Paging" scope="session"/>
<html lang="ja">
<head>
    <title>シラバス検索結果</title>
    <link rel="stylesheet" type="text/css" href="Design.css">
</head>
<body>
<table id="window">
    <tr>
        <td class="ta-box">
            <%
                List<Syllabus> syllabusList = (List<Syllabus>) request.getAttribute("syllabusList");
                Integer nowPage = paging.getNowPage();
            %>

            <form action="/Main" method="post">
                <button type="submit" name="action" class="btn_5" value="SyllabusSearch"> 再検索</button>
            </form>
        </td>
        <td class="tb-box"></td>
    </tr>
    <tr>
        <td class="c-box" colspan="2">
            <h1>検索結果</h1>
            <%=paging.getCount()%>件
            <table BORDER="1" class="part">
                <tr>
                    <th>授業ID</th>
                    <th>授業名</th>
                    <th>担当教員</th>
                    <th>学期</th>
                    <th>曜日・時限</th>
                    <th>学年</th>
                    <th>教室</th>
                </tr>
                <%
                    for (int i = 0; i < syllabusList.size(); i++) {
                        Syllabus syllabus = syllabusList.get(i);
                %>
                <tr>
                    <td>
                        <%=syllabus.getSyllabusId()%>
                    </td>
                    <td>
                        <form action="/Main" method="post" name="form<%=i%>">
                            <input type="hidden" name="action" value="SyllabusDetail">
                            <input type="hidden" name="targetSyllabusId" value="<%=syllabus.getSyllabusId()%>"/>
                            <input type="hidden" name="backPage" value="searchResult"/>
                            <a href="javascript:form<%=i%>.submit()"><%=syllabus.getSyllabusName()%>
                            </a>
                        </form>
                    </td>
                    <td>
                        <%=syllabus.getMainTeacher()%>
                    </td>
                    <td>
                        <%=syllabus.getSemester()%>
                    </td>
                    <td>
                        <%=syllabus.getWeekTimeString()%>
                    </td>
                    <td>
                        <%=syllabus.getDividendGrade()%>年
                    </td>
                    <td>
                        <%=syllabus.getClassRoom()%>
                    </td>
                </tr>
                <%
                    }
                %>

            </table>
            <%
                if (nowPage != 1) {
            %>
            <form action="/Main" method="post">
                <input type="hidden" name="page" value="<%=paging.getStartPage()%>"/>
                <button name="action" type="submit" class="btn_6" value="SyllabusSearchChangePage">最初</button>
            </form>
            <%
            } else {
            %>
            <div>最初</div>
            <%
                }
            %>

            <%
                for (int i = nowPage - 2; i < nowPage + 3; i++) {
                    if (i != nowPage && i >= 1 && i <= paging.getLastPage()) {
            %>
            <form action="/Main" method="post">
                <input type="hidden" name="page" value="<%=i%>"/>
                <button name="action" type="submit" class="btn_6" value="SyllabusSearchChangePage"><%=i%>
                </button>
            </form>
            <%
            } else if (i == nowPage && i != paging.getStartPage() && i != paging.getLastPage() && i >= 1 && i <= paging.getLastPage()) {
            %>
            <div>
                <%=i%>
            </div>
            <%
                    }
                }
            %>

            <%
                if (nowPage != paging.getLastPage()) {
            %>
            <form action="/Main" method="post">
                <input type="hidden" name="page" value="<%=paging.getLastPage()%>"/>
                <button name="action" type="submit" class="btn_6" value="SyllabusSearchChangePage">最後</button>
            </form>
            <%
            } else {
            %>
            <div>最後</div>
            <%
                }
            %>
        </td>
    </tr>
</table>
</body>
</html>
