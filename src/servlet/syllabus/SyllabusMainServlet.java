package servlet.syllabus;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SyllabusMainServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = (String) request.getAttribute("action");
        String url = "Top.jsp";
        if (action.indexOf("Search") != -1) {
            url = "/SyllabusSearch";
        } else if (action.indexOf("Result") != -1) {
            url = "/SyllabusResult";
        } else if (action.indexOf("Detail") != -1) {
            url = "/SyllabusDetail";
        } else if (action.indexOf("Registration") != -1 || action.indexOf("Update") != -1) {
            url = "/SyllabusInsertOrUpdate";
        } else if(action.indexOf("Delete") != -1){
            url = "/SyllabusDelete";
        }

        request.getRequestDispatcher(url).forward(request, response);
        return;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
