package servlet.course;

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

public class CourseListServlet extends HttpServlet {
    private ModelManager modelManager;
    private ReplaceString replaceString = new ReplaceString();
    private String url = "/Course/CourseList.jsp";

    @Override
    public void init() throws ServletException {
        super.init();
        modelManager = (ModelManager) getServletContext().getAttribute("modelManager");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = (String) request.getAttribute("action");
        switch (action){
            case "CourseList":
                actionList(request,response);
                return;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void actionList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String targetSyllabusId = (String) request.getSession(true).getAttribute("targetSyllabusId");
        List<Course> courseList = modelManager.courseSelect("",targetSyllabusId,-1);
        List<User> userList = modelManager.courseSelectUser(targetSyllabusId);

        request.setAttribute("courseList",courseList);
        request.setAttribute("studentList",userList);
        request.getRequestDispatcher(url).forward(request,response);
        return;
    }
}
