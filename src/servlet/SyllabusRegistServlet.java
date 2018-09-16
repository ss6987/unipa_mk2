package servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import Entity.SyllabusDetail;

public class SyllabusRegistServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String syllabusId = request.getParameter("syllabus_id");
        String syllabusName = request.getParameter("syllabus_name");
        String englishName = request.getParameter("english_name");
        int dividendGrade = Integer.parseInt("dividend_grade");
        int year = Integer.parseInt("year");
        String classroom = request.getParameter("class");
        String semester = request.getParameter("semester");
        String week = request.getParameter("week");
        String time = request.getParameter("time");
        int unit = Integer.parseInt("unit");
        int capacity = Integer.parseInt("capacity");
        String objectiveSummary = request.getParameter("objectiveSummary");
        String goal = request.getParameter("goal");
        String textbook = request.getParameter("textbook");
        String referenceBook = request.getParameter("referenceBook");
        String educationalObject = request.getParameter("educationalObject ");
        String dp = request.getParameter("dp");
        String selfStudy = request.getParameter("selfStudy");
        String freeText = request.getParameter("freeText");
        String mailAddress = request.getParameter("mailAddress");
        String officeHour = request.getParameter("officeHour");
        String classification = request.getParameter("classification");
        String guidance = request.getParameter("guidance");
        String advice = request.getParameter("advice");

        String errorString = "";
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

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
