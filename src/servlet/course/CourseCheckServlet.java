package servlet.course;

import Entity.Course;
import etc.ModelManager;
import etc.ReplaceString;
import Entity.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CourseCheckServlet extends HttpServlet {
    private String disp = "/MainForward";
    private ModelManager modelManager = new ModelManager();
    private RequestDispatcher dispatch;
    private HttpSession session;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        dispatch = request.getRequestDispatcher(disp);
        session = request.getSession(true);

        ReplaceString replaceString = new ReplaceString();
        String action = replaceString.repairRequest(request.getParameter("action"));

        if (action.equals("courseCheck")) {
            String targetSyllabusId = (String) session.getAttribute("targetSyllabusId");
            List<Course> courseList = modelManager.courseSelect("", targetSyllabusId, -1);
            List<User> userList = new ArrayList<>();
            for (Course course : courseList) {
                userList.add(modelManager.userFindById(course.getUserId()));
            }
            request.setAttribute("courseList",courseList);
            request.setAttribute("studentList",userList);
            request.setAttribute("Number", 6);
            request.setAttribute("errorString", "");
            dispatch.forward(request, response);
            return;
        }else if(action.equals("back")){
            request.setAttribute("Number", 11);
            request.setAttribute("errorString", "");
            dispatch.forward(request, response);
            return;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
