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
import java.util.List;

public class AchieveRegistServlet extends HttpServlet {
    private String disp = "/MainForward";
    private ModelManager modelManager = new ModelManager();
    private RequestDispatcher dispatch;
    private HttpSession session;
    private List<Course> courseList;
    private List<User> userList;
    private Syllabus syllabus;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        dispatch = request.getRequestDispatcher(disp);
        session = request.getSession(true);

        ReplaceString replaceString = new ReplaceString();
        String action = replaceString.repairRequest(request.getParameter("action"));

        if (action.equals("achieveRegistration")) {
            String targetSyllabusId = (String) session.getAttribute("targetSyllabusId");

            syllabus = modelManager.syllabusFindById(targetSyllabusId);
            courseList = modelManager.courseSelect("", targetSyllabusId, -1);
            for (int i = 0; i < courseList.size(); i++) {
                if (courseList.get(i).getAchievement() == -3) {
                    courseList.remove(i);
                }
            }
            userList = modelManager.courseSelectUser(targetSyllabusId);

            request.setAttribute("syllabus", syllabus);
            request.setAttribute("courseList", courseList);
            request.setAttribute("userList", userList);
            request.setAttribute("Number", 19);
            request.setAttribute("errorString", "");
            dispatch.forward(request, response);
            return;
        } else if (action.equals("update")) {
            for (int i = 0; i < courseList.size(); i++) {
                String achievementString = request.getParameter("select_" + i);
                Integer achievement = Integer.parseInt(achievementString);
                Course course = courseList.get(i);
                if (course.getAchievement() != achievement) {
                    modelManager.courseUpdate(course.getUserId(), course.getSyllabusId(), achievement);
                }
            }

            courseList = modelManager.courseSelect("", syllabus.getSyllabusId(), -1);
            for (int i = 0; i < courseList.size(); i++) {
                if (courseList.get(i).getAchievement() == -3) {
                    courseList.remove(i);
                }
            }
            userList = modelManager.courseSelectUser(syllabus.getSyllabusId());

            request.setAttribute("syllabus", syllabus);
            request.setAttribute("courseList", courseList);
            request.setAttribute("userList", userList);
            request.setAttribute("Number", 19);
            request.setAttribute("errorString", "");
            dispatch.forward(request, response);
            return;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
