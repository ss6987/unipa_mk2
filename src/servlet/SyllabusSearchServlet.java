package servlet;

import Entity.SyllabusDetail;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SyllabusSearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String syllabusId = request.getParameter("syllabus_id");
        String syllabusName = request.getParameter("syllbus_name");
        String englishName = request.getParameter("english_name");
        int dividendGrde = Integer.parseInt("dividend_grde");
        int year = Integer.parseInt("year");
        String dass = request.getParameter("dass");
        String semester = request.getParameter("semester");
        String week = request.getParameter("week");
        String time = request.getParameter("time");
        int unit = Integer.parseInt("unit");
        int capacity = Integer.parseInt("capacity");
        String objeciveSummary = request.getParameter("objecive_Summar");
        String goal = request.getParameter("goal");
        String textbook = request.getParameter("textbook");
        String referenceBook = request.getParameter("reference_Book");
        String educationalObject = request.getParameter("educational_Object ");
        String dp = request.getParameter("dp");
        String selfStudy = request.getParameter("self_Study");
        String freeText = request.getParameter("freeText");
        String mailAddress = request.getParameter("mail_addres");
        String officeHour= request.getParameter("office_Hour");
        String classifcation = request.getParameter("classifcation");
        String guidance = request.getParameter("guidance");
        String advice = request.getParameter("advice");

        String errorString = "";
        SyllabusDetail syllabusDetail = new SyllabusDetail();
        errorString = errorString + syllabusDetail.setsyllabusId(syllabusId);
        errorString = errorString + syllabusDetail.setsyllabusName(syllabusName);
        errorString = errorString + syllabusDetail.setenglishName(englishName);
        errorString = errorString + syllabusDetail.setdividendGrde(dividendGrde);
        errorString = errorString + syllabusDetail.setyear(year);
        errorString = errorString + syllabusDetail.setdass(dass);
        errorString = errorString + syllabusDetail.setsemeter(semester);
        errorString = errorString + syllabusDetail.setweek(week);
        errorString = errorString + syllabusDetail.settime(time);
        errorString = errorString + syllabusDetail.setunit(unit);
        errorString = errorString + syllabusDetail.setcapacity(capacity);
        errorString = errorString + syllabusDetail.setobjeciveSummary(objeciveSummary);
        errorString = errorString + syllabusDetail.setgoal(goal);
        errorString = errorString + syllabusDetail.settextbook(textbook);
        errorString = errorString + syllabusDetail.setreferenceBook(referenceBook);
        errorString = errorString + syllabusDetail.seteducationalObject(educationalObject);
        errorString = errorString + syllabusDetail.setdp(dp);
        errorString = errorString + syllabusDetail.setselfStudy (selfStudy);
        errorString = errorString + syllabusDetail.setfreeText(freeText);
        errorString = errorString + syllabusDetail.setmailAddress(mailAddress);
        errorString = errorString + syllabusDetail.setofficeHour(officeHour);
        errorString = errorString + syllabusDetail.setclassifcation (classifcation);
        errorString = errorString + syllabusDetail.setguidance(guidance);
        errorString = errorString + syllabusDetail.setadvice(advice);

    }



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
