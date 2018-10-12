package servlet;

import etc.ModelManager;
import Entity.User;
import etc.ReplaceString;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class LoginCheckServlet extends HttpServlet {
    private ModelManager modelManager = new ModelManager();
    private RequestDispatcher dispatch;
    private HttpSession session;
    private ReplaceString replaceString = new ReplaceString();


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (session != null) {
            try {
                session.invalidate();
            } catch (java.lang.IllegalStateException e) {
                ;
            }
        }
        session = request.getSession(true);

        String id = replaceString.repairRequest(request.getParameter("id"));
        String password = replaceString.repairRequest(request.getParameter("password"));

        User user = modelManager.login(id, password);
        if (user != null) {
            dispatch = request.getRequestDispatcher("/Main");
        } else {
            user = new User();
            user.setUserId(id);
            Integer url = 1;
            dispatch = request.getRequestDispatcher("/Login.jsp");
            request.setAttribute("Number", url);
        }

        session.setAttribute("user", user);
        dispatch.forward(request, response);
        return;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        dispatch = request.getRequestDispatcher("/TopServlet");
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
