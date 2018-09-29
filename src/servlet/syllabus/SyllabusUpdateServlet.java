package servlet.syllabus;

import DAO.SyllabusDAO;
import Entity.Syllabus;
import Entity.SyllabusContents;
import Entity.SyllabusDetail;
import etc.ModelManager;
import etc.ReplaceString;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class SyllabusUpdateServlet extends HttpServlet {
    private String disp = "/MainForward";
    private ModelManager modelManager = new ModelManager();
    private RequestDispatcher dispatch;
    private HttpSession session;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        dispatch = request.getRequestDispatcher(disp);
        session = request.getSession(true);
        ReplaceString replaceString = new ReplaceString();

        String action = request.getParameter("action");
        String syllabusId = (String) session.getAttribute("targetSyllabusId");
        if (syllabusId.equals("")) {
            syllabusId = replaceString.repairRequest(request.getParameter("syllabusId"));
        }

        if (action.equals("update") || action.equals("insert")) {

            String syllabusName = replaceString.repairRequest(request.getParameter("syllabusName"));
            String englishName = replaceString.repairRequest(request.getParameter("englishName"));
            String classroom = replaceString.repairRequest(request.getParameter("classRoom"));
            String semester = replaceString.repairRequest(request.getParameter("semester"));
            String week = replaceString.repairRequest(request.getParameter("week"));
            String time = replaceString.repairRequest(request.getParameter("time"));
            String objectiveSummary = replaceString.repairRequest(request.getParameter("objectiveSummary"));
            String goal = replaceString.repairRequest(request.getParameter("goal"));
            String textbook = replaceString.repairRequest(request.getParameter("textbook"));
            String referenceBook = replaceString.repairRequest(request.getParameter("referenceBook"));
            String educationalObject = replaceString.repairRequest(request.getParameter("educationalObject"));
            String dp = replaceString.repairRequest(request.getParameter("dp"));
            String selfStudy = replaceString.repairRequest(request.getParameter("selfStudy"));
            String freeText = replaceString.repairRequest(request.getParameter("freeText"));
            String mailAddress = replaceString.repairRequest(request.getParameter("mailAddress"));
            String officeHour = replaceString.repairRequest(request.getParameter("officeHour"));
            String classification = replaceString.repairRequest(request.getParameter("classification"));
            String guidance = replaceString.repairRequest(request.getParameter("guidance"));
            String advice = replaceString.repairRequest(request.getParameter("advice"));
            String mainTeacherId = replaceString.repairRequest(request.getParameter("mainTeacherId"));
            int dividendGrade;
            int year;
            int unit;
            int capacity;

            String errorString = "";
            try {
                dividendGrade = Integer.parseInt(replaceString.repairRequest(request.getParameter("dividendGrade")));
            } catch (java.lang.NumberFormatException e) {
                if (!request.getParameter("dividendGrade").equals("")) {
                    errorString += "配当学年に使用できない文字が存在します。";
                }
                dividendGrade = -1;
            }

            try {
                year = Integer.parseInt(replaceString.repairRequest(request.getParameter("year")));
            } catch (java.lang.NumberFormatException e) {
                if (!request.getParameter("year").equals("")) {
                    errorString += "開講年度に使用できない文字が存在します。";
                }
                year = -1;
            }

            try {
                unit = Integer.parseInt(replaceString.repairRequest(request.getParameter("unit")));
            } catch (java.lang.NumberFormatException e) {
                if (!request.getParameter("unit").equals("")) {
                    errorString += "単位数に使用できない文字が存在します。";
                }
                unit = -1;
            }

            try {
                capacity = Integer.parseInt(replaceString.repairRequest(request.getParameter("capacity")));
            } catch (java.lang.NumberFormatException e) {
                if (!request.getParameter("capacity").equals("")) {
                    errorString += "定員に使用できない文字が存在します。";
                }
                capacity = -1;
            }

            SyllabusDetail syllabusDetail = new SyllabusDetail();
            errorString += syllabusDetail.setSyllabusId(syllabusId);
            errorString += syllabusDetail.setSyllabusName(syllabusName);
            errorString += syllabusDetail.setEnglishName(englishName);
            errorString += syllabusDetail.setDividendGrade(dividendGrade);
            errorString += syllabusDetail.setYear(year);
            errorString += syllabusDetail.setClassRoom(classroom);
            errorString += syllabusDetail.setSemester(semester);
            errorString += syllabusDetail.setWeek(week);
            errorString += syllabusDetail.setTime(time);
            errorString += syllabusDetail.setUnit(unit);
            errorString += syllabusDetail.setCapacity(capacity);
            errorString += syllabusDetail.setObjectiveSummary(objectiveSummary);
            errorString += syllabusDetail.setGoal(goal);
            errorString += syllabusDetail.setTextbook(textbook);
            errorString += syllabusDetail.setReferenceBook(referenceBook);
            errorString += syllabusDetail.setEducationalObject(educationalObject);
            errorString += syllabusDetail.setDp(dp);
            errorString += syllabusDetail.setSelfStudy(selfStudy);
            errorString += syllabusDetail.setFreeText(freeText);
            errorString += syllabusDetail.setMailAddress(mailAddress);
            errorString += syllabusDetail.setOfficeHour(officeHour);
            errorString += syllabusDetail.setClassification(classification);
            errorString += syllabusDetail.setGuidance(guidance);
            errorString += syllabusDetail.setAdvice(advice);
            errorString = errorString.replace("。", "。<br/>");

            boolean flag = false;
            if (action.equals("insert")) {
                flag = modelManager.syllabusRegistration(syllabusDetail);
                if (!flag) {
                    errorString += "更新に失敗しました。";
                    request.setAttribute("targetSyllabus", syllabusDetail);
                    request.setAttribute("errorString", errorString);
                    request.setAttribute("Number", 10);
                    dispatch.forward(request, response);
                    return;
                }
            }

            flag = modelManager.teacherInChargeRegistration(syllabusId, mainTeacherId, 0);

            try {
                int i = 1;
                while (true) {
                    String contentsString = replaceString.repairRequest(request.getParameter("syllabusContents" + i));
                    SyllabusContents syllabusContents = new SyllabusContents(syllabusId, i, contentsString);
                    syllabusDetail.addSyllabusContents(syllabusContents);
                    i++;
                }
            } catch (java.lang.NullPointerException e) {
                ;
            } catch (SQLException e) {
                errorString += "シラバスの内容に問題が発生しました";
            }
            if (!errorString.equals("")) {
                request.setAttribute("errorString", errorString);
                request.setAttribute("targetSyllabus", syllabusDetail);
                request.setAttribute("Number", 10);
                dispatch.forward(request, response);
                return;
            }

            flag = modelManager.syllabusUpdate(syllabusDetail);

            if (!flag) {
                errorString += "更新に失敗しました。";
                request.setAttribute("targetSyllabus", syllabusDetail);
                request.setAttribute("Number", 10);
            } else {
                errorString += "更新完了";
                request.setAttribute("Number", 11);
                session.setAttribute("targetSyllabusId", syllabusId);
                request.setAttribute("targetSyllabus", modelManager.syllabusDetailFindById(syllabusId));
            }
            request.setAttribute("errorString", errorString);
            dispatch.forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
