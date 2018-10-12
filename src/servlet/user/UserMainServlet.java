package servlet.user;

import etc.ModelManager;
import etc.ReplaceString;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UserMainServlet extends HttpServlet {
    private ModelManager modelManager = new ModelManager();
    private RequestDispatcher dispatch;
    private HttpSession session;
    private ReplaceString replaceString = new ReplaceString();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        session = request.getSession(true);
        String action = replaceString.repairRequest(request.getParameter("action"));
        String url = "/Top";
        switch (action) {
            case "myUserDetail":
                url = "/UserDetail";
                break;
            case "UserUpdate":
                url = "/UserUpdate";
                break;
        }
        dispatch = request.getRequestDispatcher(url);
        dispatch.forward(request, response);
        return;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
