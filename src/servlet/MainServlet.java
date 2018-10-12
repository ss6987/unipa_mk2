package servlet;

import Entity.Syllabus;
import Entity.User;
import etc.ModelManager;
import etc.ReplaceString;
import servlet.timetable.TimeTable;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class MainServlet extends HttpServlet {
    private RequestDispatcher dispatch;
    private HttpSession session;
    private ModelManager modelManager = new ModelManager();
    private ReplaceString replaceString = new ReplaceString();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        session = request.getSession(true);

        String url = "Login.jsp";
        if (session.getAttribute("user") == null) {
            dispatch = request.getRequestDispatcher(url);
            dispatch.forward(request, response);
            return;
        }

        String action = replaceString.repairRequest(request.getParameter("action"));

        if (action.indexOf("Login") != -1) {
            setTimeTable();
            session.setAttribute("registrationPeriodFlag", modelManager.getRegistrationPeriodFlag());
            url = "/Top";
        } else if (action.indexOf("Top") != -1) {
            url = "/Top";
        } else if (action.indexOf("Achievement") != -1) {
            url = "/AchievementMain";
        } else if (action.indexOf("Course") != -1) {
            url = "/CourseMain";
        } else if (action.indexOf("Syllabus") != -1) {
            url = "/SyllabusMain";
        } else if (action.indexOf("Timetable") != -1) {
            url = "/TimeTableMain";
        } else if (action.indexOf("User") != -1) {
            url = "/UserMain";
        }
        dispatch = request.getRequestDispatcher(url);
        dispatch.forward(request, response);
        return;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void setTimeTable() {
        User loginUser = (User) session.getAttribute("user");
        List<Syllabus> syllabusList = null;

        if (loginUser.getUserClassification().equals("学生")) {
            syllabusList = modelManager.courseSelectSyllabus(loginUser.getUserId());
        } else if (loginUser.getUserClassification().equals("教職員")) {
            syllabusList = modelManager.teacherInChargeSearch(loginUser.getUserId());
        }

        if (syllabusList != null) {
            TimeTable timeTable = new TimeTable(modelManager.getSemesterString(), modelManager.getNow());
            TimeTable nowTable = new TimeTable(modelManager.getSemesterString(), modelManager.getNow());

            timeTable.addSyllabusList(syllabusList);
            nowTable.addSyllabusList(syllabusList);
            session.setAttribute("timeTable", timeTable);
            session.setAttribute("nowTable", nowTable);
        }
    }
}
