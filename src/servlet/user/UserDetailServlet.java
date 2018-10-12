package servlet.user;

import Entity.FacultyDepartment;
import Entity.Student;
import etc.ModelManager;
import Entity.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class UserDetailServlet extends HttpServlet {
    private ModelManager modelManager = new ModelManager();
    private RequestDispatcher dispatch;
    private HttpSession session;
    private Integer url = 4;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        session = request.getSession(true);

        String action = request.getParameter("action");
        User targetUser;
        if (action.equals("myUserDetail")) {
            targetUser = (User) session.getAttribute("user");
            request.setAttribute("targetUser", targetUser);
        } else if (action.equals("UserDetail")) {
            String targetUserId = request.getParameter("targetUserId");
            targetUser = modelManager.userFindById(targetUserId);
        } else {
            targetUser = new User();
        }

        if (targetUser.getUserClassification().equals("学生")) {
            try {
                Student student = targetUser.convertUserToStudent();
                request.setAttribute("facultyDepartment", student.getFacultyDepartment());
            } catch (SQLException e) {
                ;
            }
        }

        session.setAttribute("targetUserId", targetUser.getUserId());
        request.setAttribute("targetUser", targetUser);
        request.setAttribute("Number", url);
        dispatch = request.getRequestDispatcher("/MainForward");
        dispatch.forward(request, response);
        return;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
