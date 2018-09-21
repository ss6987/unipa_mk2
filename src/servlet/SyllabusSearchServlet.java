package servlet;

import Entity.Syllabus;
import etc.ModelManager;
import etc.ReplaceString;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class SyllabusSearchServlet extends HttpServlet {
    private String disp = "/MainForward";
    private ModelManager modelManager = new ModelManager();
    private RequestDispatcher dispatch;
    private HttpSession session;


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        dispatch = request.getRequestDispatcher(disp);
        session = request.getSession(true);

        String syllabusId = new ReplaceString().repairRequest(request.getParameter("syllabus_id"));
        String syllabusName = new ReplaceString().repairRequest(request.getParameter("syllabus_name"));
        String englishName = new ReplaceString().repairRequest(request.getParameter("english_name"));
        String classroom = new ReplaceString().repairRequest(request.getParameter("classroom"));
        String semester = new ReplaceString().repairRequest(request.getParameter("semester"));
        String week = new ReplaceString().repairRequest(request.getParameter("week"));
        String time = new ReplaceString().repairRequest(request.getParameter("time"));
        int dividendGrade;
        int year;
        int unit;
        int capacity;

        try {
            dividendGrade = Integer.parseInt(new ReplaceString().repairRequest("dividend_grade"));
        } catch (java.lang.NumberFormatException e) {
            dividendGrade = -1;
        }

        try {
            year = Integer.parseInt(new ReplaceString().repairRequest("year"));
        } catch (java.lang.NumberFormatException e) {
            year = -1;
        }

        try {
            unit = Integer.parseInt(new ReplaceString().repairRequest("unit"));
        } catch (java.lang.NumberFormatException e) {
            unit = -1;
        }

        try {
            capacity = Integer.parseInt(new ReplaceString().repairRequest("capacity"));
        } catch (java.lang.NumberFormatException e) {
            capacity = -1;
        }


        String errorString = "";
        Syllabus syllabus = new Syllabus();
        errorString += syllabus.setSyllabusId(syllabusId);
        errorString += syllabus.setSyllabusName(syllabusName);
        errorString += syllabus.setEnglishName(englishName);
        errorString += syllabus.setDividendGrade(dividendGrade);
        errorString += syllabus.setYear(year);
        errorString += syllabus.setClassRoom(classroom);
        errorString += syllabus.setSemester(semester);
        errorString += syllabus.setWeek(week);
        errorString += syllabus.setTime(time);
        errorString += syllabus.setUnit(unit);
        errorString += syllabus.setCapacity(capacity);
        errorString = errorString.replace("。","。<br/>");

        if (!errorString.equals("")) {
            request.setAttribute("errorString", errorString);
            request.setAttribute("Number", 13);
            dispatch.forward(request, response);
        }

        session.setAttribute("searchSyllabus",syllabus);
        List<Syllabus> syllabusList = modelManager.syllabusSearch(syllabus,0);
        request.setAttribute("syllabusList",syllabusList);
        request.setAttribute("Number",14);
        dispatch.forward(request,response);
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
