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

/**/
public class UserMainServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = (String) request.getAttribute("action");
        String url = "/Top";
        if (action.indexOf("Detail") != -1) {
            url = "/UserDetail";
        } else if (action.indexOf("Update") != -1 || action.indexOf("Registration") != -1) {
            url = "/UserInsertOrUpdate";
        } else if (action.indexOf("Delete") != -1) {
            url = "/UserDelete";
        }

        request.getRequestDispatcher(url).forward(request, response);
        return;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
