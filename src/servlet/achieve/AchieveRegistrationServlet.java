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
import java.sql.SQLException;
import java.util.List;

public class AchieveRegistrationServlet extends HttpServlet {
    private ModelManager modelManager;
    private ReplaceString replaceString = new ReplaceString();
    private String url = "Achieve/AchieveRegistration.jsp";

    @Override
    public void init() throws ServletException {
        super.init();
        modelManager = (ModelManager) getServletContext().getAttribute("modelManager");
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = (String) request.getAttribute("action");
        switch (action) {
            case "AchieveRegistration":
                actionRegistration(request, response);
                return;
            case "AchieveRegistrationDone":
                actionRegistrationDone(request, response);
                return;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    private void actionRegistration(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String targetSyllabusId = (String) request.getSession(true).getAttribute("targetSyllabusId");
        Syllabus syllabus = modelManager.syllabusFindById(targetSyllabusId);
        List<Course> courseList = modelManager.courseSelect("", targetSyllabusId, -1);
        List<User> userList = modelManager.courseSelectUser(targetSyllabusId);

        request.setAttribute("syllabus", syllabus);
        request.setAttribute("courseList", courseList);
        request.setAttribute("userList", userList);
        request.getRequestDispatcher(url).forward(request, response);
        return;
    }

    private void actionRegistrationDone(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String targetSyllabusId = (String) request.getSession(true).getAttribute("targetSyllabusId");
        List<Course> courseList = modelManager.courseSelect("", targetSyllabusId, -1);

        for (int i = 0; i < courseList.size(); i++) {
            String achievementString = request.getParameter("select_" + i);
            Integer achievement = Integer.parseInt(achievementString);
            Course course = courseList.get(i);
            if (course.getAchievement() != achievement && course.getAchievement() != -3) {
                modelManager.courseUpdate(course.getUserId(), course.getSyllabusId(), achievement);
            }
        }

        request.setAttribute("action", "AchieveRegistration");
        request.getRequestDispatcher("/Main").forward(request, response);
        return;
    }
}
