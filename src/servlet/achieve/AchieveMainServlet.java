package servlet.achieve;

import etc.ModelManager;
import etc.ReplaceString;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AchieveMainServlet extends HttpServlet {
    private ReplaceString replaceString = new ReplaceString();
    private ModelManager modelManager;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = (String) request.getAttribute("action");
        String url = "Top.jsp";
        if(action.indexOf("Check") != -1){
            url = "/AchieveCheck";
        }else if(action.indexOf("Registration") != -1){
            url = "/AchieveRegistration";
        }

        request.getRequestDispatcher(url).forward(request,response);
        return;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
