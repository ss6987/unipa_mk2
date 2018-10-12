package servlet.user;

import Entity.FacultyDepartment;
import Entity.Student;
import Entity.User;
import etc.ModelManager;
import etc.ReplaceString;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class UserUpdateServlet extends HttpServlet {
    private ModelManager modelManager = new ModelManager();
    private RequestDispatcher dispatch;
    private HttpSession session;
    ReplaceString replaceString = new ReplaceString();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        session = request.getSession(true);


        String action = replaceString.repairRequest(request.getParameter("action"));
        String targetUserId = (String) session.getAttribute("targetUserId");
        if (targetUserId.equals("")) {
            targetUserId = replaceString.repairRequest(request.getParameter("targetUserId"));
        }
        User targetUser = modelManager.userFindById(targetUserId);

        switch (action) {
            case "UserUpdate":
                actionUpdate(request, response, targetUser);
                return;
            case "UserRegistration":
                return;
            case "UserUpdateDone":
                return;
            case "UserDelete":
                return;
        }

        return;

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void actionUpdate(HttpServletRequest request, HttpServletResponse response, User targetUser) throws ServletException, IOException {
        Integer pageNumber = 3;

        request.setAttribute("targetUser", targetUser);
        request.setAttribute("facultyDepartment", modelManager.getFacultyDepartmentList());
        request.setAttribute("Number", pageNumber);
        dispatch = request.getRequestDispatcher("MainForward");
        dispatch.forward(request, response);
        return;
    }

    private void actionRegistration(HttpServletRequest request, HttpServletResponse response) {
        Integer pageNumber = 4;
        return;
    }

    private void actionUpdateDone(HttpServletRequest request, HttpServletResponse response) {
        Integer pageNumber = 4;
        return;
    }

    private void actionDelete(HttpServletRequest request, HttpServletResponse response) {
        return;
    }
}
