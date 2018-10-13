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
import java.io.UnsupportedEncodingException;
import java.util.List;

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
        HttpSession session = request.getSession(true);


        String action = (String) request.getAttribute("action");
        String targetUserId = (String) session.getAttribute("targetUserId");
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
        User targetUser = readUserData(request, targetUserId);
        modelManager.userUpdate(targetUser);

        if (targetUser.getUserClassification().equals("学生")) {
            Student targetStudent = readStudentData(request, targetUserId);
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

    private User readUserData(HttpServletRequest request, String targetUserId) throws UnsupportedEncodingException {
        String name = replaceString.repairRequest(request.getParameter("name"));
        String phonetic = replaceString.repairRequest(request.getParameter("phonetic"));
        String genderString = replaceString.repairRequest(request.getParameter("gender"));
        String year = replaceString.repairRequest(request.getParameter("year"));
        String month = replaceString.repairRequest(request.getParameter("month"));
        String day = replaceString.replace(request.getParameter("day"));
        String birthday = year + "-" + month + "-" + day;
        String postalCode = replaceString.repairRequest(request.getParameter("postal_code"));
        String address = replaceString.repairRequest(request.getParameter("address"));
        String tel = replaceString.repairRequest(request.getParameter("tel"));
        String userClassification = replaceString.repairRequest(request.getParameter("user_classification"));

        Integer gender;
        try {
            gender = Integer.parseInt(genderString);
        } catch (java.lang.NumberFormatException e) {
            gender = -1;
        }

        String errorString = "";
        User user = new User();
        errorString += user.setUserId(targetUserId);
        errorString += user.setName(name);
        errorString += user.setPhonetic(phonetic);
        errorString += user.setGender(gender);
        errorString += user.setBirthday(birthday);
        errorString += user.setPostalCode(postalCode);
        errorString += user.setAddress(address);
        errorString += user.setTel(tel);
        errorString += user.setUserClassification(userClassification);
        errorString = errorString.replace("。", "。<br/>");

        if (!errorString.equals("")) {
            request.setAttribute("errorString", errorString);
            return null;
        }
        return user;
    }

    private Student readStudentData(HttpServletRequest request, String targetUserId) throws UnsupportedEncodingException {
        String facultyDepartmentIdString = replaceString.repairRequest(request.getParameter("facultyDepartmentId"));
        String gradeString = replaceString.repairRequest(request.getParameter("grade"));
        Integer facultyDepartmentId;
        try {
            facultyDepartmentId = Integer.parseInt(facultyDepartmentIdString);
        } catch (java.lang.NumberFormatException e) {
            facultyDepartmentId = -1;
        }

        Integer grade;
        try {
            grade = Integer.parseInt(gradeString);
        } catch (java.lang.NumberFormatException e) {
            grade = -1;
        }

        return new Student(targetUserId, facultyDepartmentId, grade);
    }
}
