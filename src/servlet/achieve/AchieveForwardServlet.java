package servlet.achieve;

import Entity.Course;
import Entity.Syllabus;
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

public class AchieveForwardServlet extends HttpServlet {
    private String disp = "/MainForward";
    private ModelManager modelManager = new ModelManager();
    private RequestDispatcher dispatch;
    private HttpSession session;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        dispatch = request.getRequestDispatcher(disp);
        session = request.getSession(true);

        ReplaceString replaceString = new ReplaceString();
        String action = replaceString.repairRequest(request.getParameter("action"));
        if(action.equals("Achieve")){
            User user = (User) session.getAttribute("user");
            List<Course> courseList = modelManager.courseSelect(user.getUserId(),"",-1);
            List<Syllabus> syllabusList = new ArrayList<>();
            for(Course course:courseList){
                syllabusList.add(modelManager.syllabusFindById(course.getSyllabusId()));
            }

            request.setAttribute("courseList",courseList);
            request.setAttribute("syllabusList",syllabusList);
            request.setAttribute("Number", 20);
            request.setAttribute("errorString", "");
            dispatch.forward(request, response);
            return;
        }else if(action.equals("AchieveCheck")){
            request.setAttribute("Number", 19);
            request.setAttribute("errorString", "");
            dispatch.forward(request, response);
            return;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
