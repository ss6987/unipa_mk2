package servlet;

import etc.ModelManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class TopServlet extends HttpServlet {
    private String disp = "/MainForward";
    private ModelManager modelManager = new ModelManager();
    private RequestDispatcher dispatch;
    private HttpSession session;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        dispatch = request.getRequestDispatcher(disp);
        session = request.getSession(true);

        String action = request.getParameter("action");
        Integer url;

        switch (action) {
            case "MyUser":
                url = 0;
                break;
            case "SyllabusSearch":
                url = 0;
                break;
            case "CheckTimeTable":
                url = 0;
                break;
            case "Achieve":
                url = 0;
                break;
            case "CourseRegistration":
                url = 0;
                break;
            case "CourseCheck":
                url = 0;
                break;
            case "UserSearch":
                url = 0;
                break;
            case "AchieveRegistration":
                url = 0;
                break;
            case "UserRegistration":
                url = 0;
                break;
            case "SyllabusRegistration":
                url = 0;
                break;
            case "AchieveCheck":
                url = 0;
                break;
            default:
                url = 0;
        }

        request.setAttribute("Number", url);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        dispatch = request.getRequestDispatcher(disp);
        session = request.getSession(true);

        if (session.getAttribute("user") != null) {
            request.setAttribute("period", modelManager.getRegistrationPeriod());
            request.setAttribute("Number", 2);
            dispatch.forward(request, response);
        }

    }
}
