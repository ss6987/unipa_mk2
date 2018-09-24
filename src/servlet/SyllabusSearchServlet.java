package servlet;

import Entity.Syllabus;
import etc.ModelManager;
import etc.Paging;
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
    private Paging paging;


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        dispatch = request.getRequestDispatcher(disp);
        session = request.getSession(true);
        ReplaceString replaceString = new ReplaceString();
        String action = replaceString.repairRequest(request.getParameter("action"));
        if (action.equals("first_search")) {
            String syllabusId = replaceString.repairRequest(request.getParameter("syllabus_id"));
            String syllabusName = replaceString.repairRequest(request.getParameter("syllabus_name"));
            String englishName = replaceString.repairRequest(request.getParameter("english_name"));
            String classroom = replaceString.repairRequest(request.getParameter("classroom"));
            String semester = replaceString.repairRequest(request.getParameter("semester"));
            String week = replaceString.repairRequest(request.getParameter("week"));
            String time = replaceString.repairRequest(request.getParameter("time"));
            int dividendGrade;
            int year;
            int unit;
            int capacity;

            try {
                dividendGrade = Integer.parseInt(replaceString.repairRequest("dividend_grade"));
            } catch (java.lang.NumberFormatException e) {
                dividendGrade = -1;
            }

            try {
                year = Integer.parseInt(replaceString.repairRequest("year"));
            } catch (java.lang.NumberFormatException e) {
                year = -1;
            }

            try {
                unit = Integer.parseInt(replaceString.repairRequest("unit"));
            } catch (java.lang.NumberFormatException e) {
                unit = -1;
            }

            try {
                capacity = Integer.parseInt(replaceString.repairRequest("capacity"));
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
            errorString = errorString.replace("。", "。<br/>");

            if (!errorString.equals("")) {
                request.setAttribute("errorString", errorString);
                request.setAttribute("Number", 13);
                dispatch.forward(request, response);
            }

            session.setAttribute("searchSyllabus", syllabus);
            List<Syllabus> syllabusList = modelManager.syllabusSearch(syllabus, 0);
            Integer resultCount = modelManager.syllabusCount();
            paging = new Paging(resultCount);
            paging.changePage(0);

            request.setAttribute("paging", paging);
            request.setAttribute("syllabusList", syllabusList);
            request.setAttribute("Number", 14);
            dispatch.forward(request, response);
        } else if (action.equals("change_page")) {
            Integer page = Integer.parseInt(replaceString.repairRequest(request.getParameter("page")));
            paging.changePage(page);
            Syllabus syllabus = (Syllabus) session.getAttribute("searchSyllabus");
            List<Syllabus> syllabusList = modelManager.syllabusSearch(syllabus, page);
            request.setAttribute("paging", paging);
            request.setAttribute("syllabusList", syllabusList);
            request.setAttribute("Number", 14);
            dispatch.forward(request, response);
        }

    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
