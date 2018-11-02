package servlet.course;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CourseMainServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = (String) request.getAttribute("action");
        String url = "Top.jsp";
        if (action.indexOf("Registration") != -1 || action.indexOf("Check") != -1) {
            url = "/CourseRegistration";
        } else if (action.indexOf("List") != -1) {
            url = "/CourseList";
        } else if (action.indexOf("Allow") != -1) {
            url = "/CourseAllow";
        } else if (action.indexOf("Delete") != -1) {
            url = "/CourseDelete";
        }

        request.getRequestDispatcher(url).forward(request, response);
        return;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/Main").forward(request,response);
        return;
    }
}
