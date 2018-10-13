package servlet;

import etc.ModelManager;
import etc.ReplaceString;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.spi.http.HttpContext;
import java.io.IOException;

/*requestのactionを見て、各種MainServletに振り分けるサーブレット*/
public class MainServlet extends HttpServlet {
    private ReplaceString replaceString = new ReplaceString();
    private ModelManager modelManager = new ModelManager();

    @Override
    public void init() throws ServletException {
        super.init();
        getServletContext().setAttribute("modelManager",modelManager);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);

        String url = "Login.jsp";
        if (session.getAttribute("user") == null) {
            request.getRequestDispatcher(url).forward(request, response);
            return;
        }

        String action = (String) request.getAttribute("action");
        if(action == null){
            action = replaceString.repairRequest(request.getParameter("action"));
            request.setAttribute("action",action);
        }

        if (action.indexOf("Login") != -1 || action.indexOf("Top") != -1) {
            url = "/Top";
        } else if (action.indexOf("Achievement") != -1) {
            url = "/AchievementMain";
        } else if (action.indexOf("Course") != -1) {
            url = "/CourseMain";
        } else if (action.indexOf("Syllabus") != -1) {
            url = "/SyllabusMain";
        } else if (action.indexOf("Timetable") != -1) {
            url = "/TimeTableMain";
        } else if (action.indexOf("User") != -1) {
            url = "/UserMain";
        }
        request.getRequestDispatcher(url).forward(request, response);
        return;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


}
