package servlet;

import Entity.User;
import etc.ModelManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UserDeleteServlet extends HttpServlet {
    private String disp = "/MainForward";
    private ModelManager modelManager = new ModelManager();
    private RequestDispatcher dispatch;
    private HttpSession session;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        dispatch = request.getRequestDispatcher(disp);
        session = request.getSession(true);

        User user = (User) session.getAttribute("user");
        String targetUserId = (String) session.getAttribute("targetUserId");


        User targetUser = modelManager.userFindById(targetUserId);
        if (modelManager.userDelete(user)) {
            System.out.println("成功");
        } else {
            System.out.printf("失敗");
        }

        if (targetUserId == user.getUserId()) {
            session.invalidate();
            request.setAttribute("Number", 1);
            dispatch.forward(request, response);
        } else {
            request.setAttribute("Number", 2);
            dispatch.forward(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
