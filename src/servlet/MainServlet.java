package servlet;

import etc.ModelManager;
import etc.ReplaceString;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
        if (session.getAttribute("user") == null || session.getAttribute("user").equals("")) {
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
        } else if (action.indexOf("Achieve") != -1) {
            url = "/AchieveMain";
        } else if (action.indexOf("Course") != -1) {
            url = "/CourseMain";
        } else if (action.indexOf("Syllabus") != -1) {
            url = "/SyllabusMain";
        } else if (action.indexOf("TimeTable") != -1) {
            url = "/TimeTableMain";
        } else if (action.indexOf("User") != -1) {
            url = "/UserMain";
        }else if(action.indexOf("PeriodRegistration") != -1){
            url = "/PeriodRegistration";
        }else if(action.indexOf("Logout") != -1){
            actionLogout(request, response);
            return;
        }else{
            request.setAttribute("action","Top");
            url = "/Top";
        }
        request.getRequestDispatcher(url).forward(request, response);
        return;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);

        if(session.getAttribute("user") == null || session.getAttribute("user").equals("")){
            request.getRequestDispatcher("Login.jsp").forward(request,response);
        }

        doPost(request, response);
        return;
    }

    private void actionLogout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        session.invalidate();
        request.getRequestDispatcher("Login.jsp").forward(request,response);
        return;
    }
}
