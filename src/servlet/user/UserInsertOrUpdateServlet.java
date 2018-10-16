package servlet.user;

import Entity.Student;
import Entity.User;
import etc.ModelManager;
import etc.ReplaceString;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UserInsertOrUpdateServlet extends HttpServlet {
    private String url = "/User/UserRegistration.jsp";
    private ModelManager modelManager;
    private ReplaceString replaceString = new ReplaceString();

    @Override
    public void init() throws ServletException {
        super.init();
        modelManager = (ModelManager) getServletContext().getAttribute("modelManager");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String action = (String) request.getAttribute("action");
        String targetUserId = (String) request.getSession(true).getAttribute("targetUserId");
        if (targetUserId == null) {
            try {
                targetUserId = replaceString.repairRequest(request.getParameter("targetUserId"));
            } catch (java.lang.NullPointerException e) {
                targetUserId = "";
            }
        }

        switch (action) {
            case "UserUpdate":
                actionUpdate(request, response, targetUserId);
                return;
            case "UserRegistration":
                actionRegistration(request, response);
                return;
            case "UserUpdateDone":
                actionUpdateDone(request, response, targetUserId);
                return;
            case "UserRegistrationDone":
                actionRegistrationDone(request, response, targetUserId);
        }
        return;

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void actionUpdate(HttpServletRequest request, HttpServletResponse response, String targetUserId) throws ServletException, IOException {
        User targetUser = modelManager.userFindById(targetUserId);
        request.setAttribute("targetUser", targetUser);
        request.setAttribute("facultyDepartment", modelManager.getFacultyDepartmentList());
        request.getRequestDispatcher(url).forward(request, response);
        return;
    }

    private void actionRegistration(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("facultyDepartment", modelManager.getFacultyDepartmentList());
        request.getRequestDispatcher(url).forward(request, response);
        return;
    }

    private void actionUpdateDone(HttpServletRequest request, HttpServletResponse response, String targetUserId) throws ServletException, IOException {
        User targetUser = new User(request,targetUserId);

        if (targetUser == null) {
            request.setAttribute("action","UserUpdate");
            request.getRequestDispatcher("/Main").forward(request, response);
            return;
        }

        modelManager.userUpdate(targetUser);

        if (targetUser.getUserClassification().equals("学生")) {
            Student targetStudent = new Student(request, targetUserId);
            modelManager.studentRegistrationOrUpdate(targetStudent);
        }

        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute("user");
        if (user.getUserId().equals(targetUserId)) {
            session.setAttribute("user", targetUser);
            request.setAttribute("action", "myUserDetail");
        } else {
            request.setAttribute("action", "UserDetail");
        }

        request.getRequestDispatcher("/Main").forward(request, response);
        return;
    }

    private void actionRegistrationDone(HttpServletRequest request, HttpServletResponse response, String targetUserId) throws IOException, ServletException {
        User targetUser = new User(request,targetUserId);
        modelManager.userRegistration(targetUser, "password");

        if (targetUser.getUserClassification().equals("学生")) {
            Student targetStudent = new Student(request, targetUserId);
            modelManager.studentRegistrationOrUpdate(targetStudent);
        }

        request.setAttribute("action", "UserDetail");
        request.getRequestDispatcher("/Main").forward(request, response);
        return;
    }



}
