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


            String errorString = "";
            Syllabus syllabus = new Syllabus();
            errorString += syllabus.setSyllabusId(syllabusId);
            errorString += syllabus.setSyllabusName(syllabusName);
            errorString += syllabus.setEnglishName(englishName);
            errorString += syllabus.setClassRoom(classroom);
            errorString += syllabus.setSemester(semester);
            errorString += syllabus.setWeek(week);
            errorString += syllabus.setTime(time);
            try {
                dividendGrade = Integer.parseInt(replaceString.repairRequest(request.getParameter("dividend_grade")));
            } catch (java.lang.NumberFormatException e) {
                dividendGrade = -1;
                if (!request.getParameter("dividend_grade").equals("")) {
                    errorString += "配当学年に数字以外の文字を入力しないでください。";
                }
            }
            errorString += syllabus.setDividendGrade(dividendGrade);

            try {
                year = Integer.parseInt(replaceString.repairRequest(request.getParameter("year")));
            } catch (java.lang.NumberFormatException e) {
                year = -1;
                if (!request.getParameter("year").equals("")) {
                    errorString += "開講年度に数字以外の文字を入力しないでください。";
                }
            }
            errorString += syllabus.setYear(year);

            try {
                unit = Integer.parseInt(replaceString.repairRequest(request.getParameter("unit")));
            } catch (java.lang.NumberFormatException e) {
                unit = -1;
                if (!request.getParameter("unit").equals("")) {
                    errorString += "単位数に数字以外の文字を入力しないでください。";
                }
            }
            errorString += syllabus.setUnit(unit);

            try {
                capacity = Integer.parseInt(replaceString.repairRequest(request.getParameter("capacity")));
            } catch (java.lang.NumberFormatException e) {
                capacity = -1;
                if (!request.getParameter("capacity").equals("")) {
                    errorString += "定員に数字以外の文字を入力しないでください。";
                }
            }
            errorString += syllabus.setCapacity(capacity);

            errorString = errorString.replace("。", "。<br/>");

            session.setAttribute("searchSyllabus", syllabus);
            if (!errorString.equals("")) {
                request.setAttribute("errorString", errorString);
                request.setAttribute("Number", 13);
                dispatch.forward(request, response);
                return;
            }

            List<Syllabus> syllabusList = modelManager.syllabusSearch(syllabus, 0);
            Integer resultCount = modelManager.syllabusCount();
            paging = new Paging(resultCount);
            paging.changePage(1);

            request.setAttribute("paging", paging);
            request.setAttribute("syllabusList", syllabusList);
            request.setAttribute("Number", 14);
            dispatch.forward(request, response);
            return;
        } else if (action.equals("change_page")) {
            Integer page = Integer.parseInt(replaceString.repairRequest(request.getParameter("page")));
            paging.changePage(page);
            Syllabus syllabus = (Syllabus) session.getAttribute("searchSyllabus");
            List<Syllabus> syllabusList = modelManager.syllabusSearch(syllabus, page - 1);
            request.setAttribute("paging", paging);
            request.setAttribute("syllabusList", syllabusList);
            request.setAttribute("Number", 14);
            dispatch.forward(request, response);
            return;
        } else if (action.equals("return") || action.equals("delete")) {
            Syllabus syllabus = (Syllabus) session.getAttribute("searchSyllabus");
            List<Syllabus> syllabusList = modelManager.syllabusSearch(syllabus, paging.getNowPage() - 1);
            request.setAttribute("paging", paging);
            request.setAttribute("syllabusList", syllabusList);
            request.setAttribute("Number", 14);
            dispatch.forward(request, response);
            return;
        }

    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
