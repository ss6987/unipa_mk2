package servlet.course;

import Entity.Syllabus;
import etc.ModelManager;
import etc.ReplaceString;
import servlet.timetable.Time;
import servlet.timetable.TimeTable;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CourseRegistrationServlet extends HttpServlet {
    private String disp = "/MainForward";
    private ModelManager modelManager = new ModelManager();
    private RequestDispatcher dispatch;
    private HttpSession session;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        dispatch = request.getRequestDispatcher(disp);
        session = request.getSession(true);

        ReplaceString replaceString = new ReplaceString();
        String action = replaceString.repairRequest(request.getParameter("action"));

        if (action.equals("registration")) {

        } else if (action.equals("firstSearch")) {
            String time = replaceString.repairRequest(request.getParameter("time"));
            String week = replaceString.repairRequest(request.getParameter("week"));
            Syllabus searchSyllabus = new Syllabus();
            searchSyllabus.setTime(time);
            searchSyllabus.setWeek(Integer.parseInt(week));
            session.setAttribute("searchSyllabus", searchSyllabus);

            request.setAttribute("errorString", "");
            request.setAttribute("Number", 13);
            dispatch.forward(request, response);
            return;
        } else if (action.equals("add")) {
            String targetSyllabusId = replaceString.repairRequest(request.getParameter("targetSyllabusId"));
            TimeTable timeTable = (TimeTable)session.getAttribute("timeTable");

            Syllabus syllabus = modelManager.syllabusFindById(targetSyllabusId);
            timeTable.addSyllabus(syllabus);
            request.setAttribute("errorString", "");
            request.setAttribute("Number", 15);
            dispatch.forward(request, response);
            return;

        } else if (action.equals("delete")) {
            String targetSyllabusId = replaceString.repairRequest(request.getParameter("targetSyllabusId"));
            TimeTable timeTable = (TimeTable) session.getAttribute("timeTable");
            timeTable.deleteSyllabus(targetSyllabusId);

            session.setAttribute("timeTable",timeTable);
            request.setAttribute("errorString", "");
            request.setAttribute("Number", 15);
            dispatch.forward(request, response);
            return;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
