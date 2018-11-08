package servlet.achieve;

import Entity.Course;
import Entity.Syllabus;
import Entity.User;
import etc.ModelManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AchieveCheckServlet extends HttpServlet {
    private ModelManager modelManager;
    private String url = "/Achieve/AchieveCheck.jsp";


    @Override
    public void init() throws ServletException {
        super.init();
        modelManager = (ModelManager) getServletContext().getAttribute("modelManager");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = (String) request.getAttribute("action");
        switch (action){
            case "AchieveCheck":
                actionCheck(request,response);
                return;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/Main").forward(request,response);
        return;
    }

    private void actionCheck(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User loginUser = (User) request.getSession(true).getAttribute("user");
        List<Course> courseList = modelManager.courseSelect(loginUser.getUserId(),"",-1);
        List<Syllabus> syllabusList = modelManager.courseSelectSyllabus(loginUser.getUserId(),-1);

        request.setAttribute("courseList",courseList);
        request.setAttribute("syllabusList",syllabusList);
        request.getRequestDispatcher(url).forward(request,response);
        return;
    }
}
