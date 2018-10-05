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
    private String disp = "/MainForward";
    private ModelManager modelManager = new ModelManager();
    private RequestDispatcher dispatch;
    private HttpSession session;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        dispatch = request.getRequestDispatcher(disp);
        session = request.getSession(true);

        ReplaceString replaceString = new ReplaceString();
        String action = replaceString.repairRequest(request.getParameter("action"));
        String targetUserId = (String) session.getAttribute("targetUserId");
        if (targetUserId.equals("")) {
            targetUserId = replaceString.repairRequest(request.getParameter("targetUserId"));
        }
        User targetUser = modelManager.userFindById(targetUserId);
        String errorString = "";


        if (action.equals("update") || action.equals("insert")) {
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
            String facultyDepartmentIdString = replaceString.repairRequest(request.getParameter("facultyDepartmentId"));
            String gradeString = replaceString.repairRequest(request.getParameter("grade"));
            Integer gender;
            try {
                gender = Integer.parseInt(genderString);
            } catch (java.lang.NumberFormatException e) {
                gender = -1;
            }

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
                request.setAttribute("facultyDepartmentList", modelManager.getFacultyDepartmentList());
                request.setAttribute("targetUser", user);
                request.setAttribute("errorString", errorString);
                request.setAttribute("Number", 3);
                dispatch.forward(request, response);
                return;
            }

            boolean update = false;
            if (action.equals("insert")) {
                update = modelManager.userRegistration(user, "password");
            } else if (action.equals("update")) {
                update = modelManager.userUpdate(user);
            }

            if (user.getUserClassification().equals("学生") && update) {
                Student student = new Student(user.getUserId(), facultyDepartmentId, grade);
                update = modelManager.studentRegistrationOrUpdate(student);
            }

            if (!update) {
                errorString += "更新に失敗しました";
                request.setAttribute("facultyDepartmentList", modelManager.getFacultyDepartmentList());
                request.setAttribute("user", user);
                request.setAttribute("Number", 3);
            } else {
                errorString += "更新完了";


                User loginUser = (User) session.getAttribute("user");
                user = modelManager.userFindById(targetUserId);
                if (loginUser.getUserId() == targetUserId) {
                    session.setAttribute("user", user);
                }
                List<FacultyDepartment> facultyDepartmentList = modelManager.getFacultyDepartmentList();
                request.setAttribute("facultyDepartmentList", facultyDepartmentList);
                request.setAttribute("targetUser", user);
                session.setAttribute("targetUserId", targetUserId);
                request.setAttribute("Number", 4);

            }
            request.setAttribute("errorString", errorString);

            dispatch.forward(request, response);
            return;

        } else if (action.equals("delete")) {
            request.setAttribute("targetUser", targetUser);
            request.setAttribute("Number", 5);
            dispatch.forward(request, response);
            return;
        } else if (action.equals("update_password")) {
            String beforePassword = request.getParameter("before_password");
            String afterPassword = request.getParameter("after_password");

            User user = modelManager.login(targetUserId, beforePassword);
            if (user != null) {
                modelManager.userUpdatePassword(user, afterPassword);
                session.invalidate();
                request.setAttribute("Number", 1);
                dispatch.forward(request, response);
                return;

            } else {
                errorString += "パスワードが違います。";
                request.setAttribute("errorString", errorString);
                request.setAttribute("targetUser", targetUser);

                request.setAttribute("Number", 4);
                dispatch.forward(request, response);
                return;
            }

        } else {
            request.setAttribute("Number", 2);
            dispatch.forward(request, response);
            return;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
