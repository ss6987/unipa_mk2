package servlet;

import etc.ModelManager;
import Entity.User;

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
        User user = (User) session.getAttribute("user");
        request.setAttribute("errorString","");
        if (user.getUserClassification().equals("学生")) {
            switch (action) {
                case "MyUser":
                    url = 4;
                    User targetUser = (User) session.getAttribute("user");
                    request.setAttribute("targetUser", targetUser);
                    session.setAttribute("targetUserId", targetUser.getUserId());
                    break;
                case "SyllabusSearch":
                    url = 13;
                    break;
                case "CheckTimeTable":
                    url = 17;
                    break;
                case "Achieve":
                    url = 20;
                    break;
                case "CourseRegistration":
                    url = 15;
                    break;
                default:
                    url = 2;
            }
        } else if (user.getUserClassification().equals("教職員")) {
            switch (action) {
                case "MyUser":
                    url = 4;
                    User targetUser = (User) session.getAttribute("user");
                    request.setAttribute("targetUser", targetUser);
                    session.setAttribute("targetUserId", targetUser.getUserId());
                    break;
                case "SyllabusSearch":
                    url = 13;
                    break;
                case "CourseCheck":
                    url = 18;
                    break;
                default:
                    url = 2;
            }
        } else if (user.getUserClassification().equals("管理者")) {
            switch (action) {
                case "MyUser":
                    url = 4;
                    User targetUser = (User) session.getAttribute("user");
                    request.setAttribute("targetUser", targetUser);
                    session.setAttribute("targetUserId", targetUser.getUserId());
                    break;
                case "SyllabusSearch":
                    url = 13;
                    break;
                case "UserSearch":
                    url = 8;
                    break;
                case "AchieveRegistration":
                    url = 19;
                    break;
                case "UserRegistration":
                    url = 3;
                    break;
                case "SyllabusRegistration":
                    url = 10;
                    break;
                case "AchieveCheck":
                    url = 20;
                    break;
                default:
                    url = 2;
            }
        }else{
            url = 2;
        }

        request.setAttribute("Number", url);
        dispatch.forward(request, response);
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
