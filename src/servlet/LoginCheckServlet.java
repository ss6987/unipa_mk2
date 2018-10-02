package servlet;

import Entity.Syllabus;
import etc.ModelManager;
import Entity.User;
import servlet.timetable.TimeTable;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class LoginCheckServlet extends HttpServlet {
    private String disp = "/MainForward";
    private ModelManager modelManager = new ModelManager();
    private RequestDispatcher dispatch;
    private HttpSession session;


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        dispatch = request.getRequestDispatcher(disp);
        session = request.getSession(true);

        String id = request.getParameter("id");
        String password = request.getParameter("password");


        User user = modelManager.login(id, password);
        Integer url;
        if (user != null) {
            if(user.getUserClassification().equals("学生")){
                List<Syllabus> syllabusList = modelManager.courseSelect(user.getUserId());
                TimeTable timeTable = new TimeTable();
                timeTable.addSyllabusList(syllabusList);
                session.setAttribute("timeTable",timeTable);
            }

            session.setAttribute("user", user);
            url = 2;
        } else {
            System.out.println("error");
            user = new User();
            user.setUserId(id);
            session.setAttribute("user", user);
            url = 1;
        }

        request.setAttribute("period", modelManager.getRegistrationPeriod());
        request.setAttribute("Number", url);
        dispatch.forward(request, response);
        return;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        dispatch = request.getRequestDispatcher(disp);
        session = request.getSession(true);

        User user = (User) session.getAttribute("user");
        if (!user.getUserId().equals("")) {
            request.setAttribute("period", modelManager.getRegistrationPeriod());
            request.setAttribute("Number", 2);
            dispatch.forward(request, response);
        } else {
            request.setAttribute("Number", 1);
            dispatch.forward(request, response);
        }

    }
}
