package servlet;

import Entity.Syllabus;
import Entity.User;
import etc.ModelManager;
import etc.timetable.TimeTable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class TopServlet extends HttpServlet {
    private String url = "Top.jsp";
    private ModelManager modelManager;

    @Override
    public void init() throws ServletException {
        super.init();
        this.modelManager = (ModelManager) getServletContext().getAttribute("modelManager");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = (String) request.getAttribute("action");
        HttpSession session = request.getSession(true);

        if(!(action.equals("Login") || action.equals("Top"))){
            request.getRequestDispatcher("Login.jsp").forward(request,response);
            return;
        }

        if (action.equals("Login")) {
            setTimeTable(session);
        }

        clearSession(session);
        request.setAttribute("periodFlag",modelManager.getRegistrationPeriodFlag());
        request.setAttribute("period", modelManager.getRegistrationPeriod());
        request.getRequestDispatcher(url).forward(request, response);
        return;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
        return;
    }

    private void setTimeTable(HttpSession session) {
        User loginUser = (User) session.getAttribute("user");
        List<Syllabus> syllabusList = null;

        if (loginUser.getUserClassification().equals("学生") || loginUser.getUserClassification().equals("保護者")) {
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

    private void clearSession(HttpSession session) {
        session.removeAttribute("targetUser");
        session.removeAttribute("targetUserId");
        session.removeAttribute("targetSyllabus");
        session.removeAttribute("targetSyllabusId");
        session.removeAttribute("backPage");
        return;
    }

}
