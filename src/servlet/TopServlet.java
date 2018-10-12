package servlet;

import Entity.Syllabus;
import Entity.User;
import etc.ModelManager;
import servlet.timetable.TimeTable;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class TopServlet extends HttpServlet {
    private String disp = "/MainForward";
    private RequestDispatcher dispatch;
    private HttpSession session;
    private ModelManager modelManager = new ModelManager();
    private Integer url = 2;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        dispatch = request.getRequestDispatcher(disp);
        session = request.getSession(true);

        request.setAttribute("period",modelManager.getRegistrationPeriod());
        request.setAttribute("Number",url);
        dispatch.forward(request,response);
        return;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {


    }

}
