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
    private ModelManager modelManager;
    private String url = "/User/UserDetail.jsp";

    @Override
    public void init() throws ServletException {
        super.init();
        modelManager = (ModelManager) getServletContext().getAttribute("modelManager");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);

        String action = (String) request.getAttribute("action");
        User targetUser;
        if (action.equals("myUserDetail")) {
            targetUser = (User) session.getAttribute("user");
            request.setAttribute("targetUser", targetUser);
        } else if (action.equals("UserDetail")) {
            String targetUserId = request.getParameter("targetUserId");
            if(targetUserId == null || targetUserId.equals("")){
                targetUserId = (String) session.getAttribute("targetUserId");
            }
            targetUser = modelManager.userFindById(targetUserId);
        } else {
            targetUser = new User();
        }

        if (targetUser.getUserClassification().equals("学生")) {
            try {
                Student student = targetUser.convertUserToStudent();
                request.setAttribute("student", student);
            } catch (SQLException e) {
                ;
            }
        }

        session.setAttribute("targetUserId", targetUser.getUserId());
        request.setAttribute("targetUser", targetUser);
        request.getRequestDispatcher(url).forward(request, response);
        return;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
