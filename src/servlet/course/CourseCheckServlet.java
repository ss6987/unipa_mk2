package servlet.course;

import Entity.Course;
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
import java.util.ArrayList;
import java.util.List;

public class CourseCheckServlet extends HttpServlet {
    private String disp = "/MainForward";
    private ModelManager modelManager = new ModelManager();
    private RequestDispatcher dispatch;
    private HttpSession session;
    private Integer listCount = 0;
    private List<Course> courseList;
    private List<User> userList;
    private List<String> targetUserIdList;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        dispatch = request.getRequestDispatcher(disp);
        session = request.getSession(true);

        ReplaceString replaceString = new ReplaceString();
        String action = replaceString.repairRequest(request.getParameter("action"));

        if (action.equals("courseCheck")) {
            String targetSyllabusId = (String) session.getAttribute("targetSyllabusId");
            courseList = modelManager.courseSelect("", targetSyllabusId, -1);
            userList = new ArrayList<>();
            for (Course course : courseList) {
                userList.add(modelManager.userFindById(course.getUserId()));
            }
            this.listCount = courseList.size();
            request.setAttribute("courseList", courseList);
            request.setAttribute("studentList", userList);
            request.setAttribute("Number", 6);
            request.setAttribute("errorString", "");
            dispatch.forward(request, response);
            return;
        } else if (action.equals("back")) {
            request.setAttribute("Number", 11);
            request.setAttribute("errorString", "");
            dispatch.forward(request, response);
            return;
        } else if (action.equals("deleteCheck")) {
            targetUserIdList = new ArrayList<>();
            for (int i = 0; i < this.listCount; i++) {
                try {
                    String targetUserId = replaceString.repairRequest(request.getParameter("check_" + i));
                    targetUserIdList.add(targetUserId);
                } catch (java.lang.NullPointerException e) {
                    ;
                }
            }
            if (targetUserIdList.size() == 0) {
                request.setAttribute("courseList", courseList);
                request.setAttribute("studentList", userList);
                request.setAttribute("Number", 6);
                request.setAttribute("errorString", "チェックが一つもついていません");
                dispatch.forward(request, response);
                return;
            }

            request.setAttribute("courseList", courseList);
            request.setAttribute("studentList", userList);
            request.setAttribute("targetUserIdList", targetUserIdList);
            request.setAttribute("Number", 7);
            request.setAttribute("errorString", "");
            dispatch.forward(request, response);
            return;
        } else if (action.equals("allow")) {
            String targetSyllabusId = (String) session.getAttribute("targetSyllabusId");
            targetUserIdList = new ArrayList<>();
            for (int i = 0; i < this.listCount; i++) {
                try {
                    String targetUserId = replaceString.repairRequest(request.getParameter("check_" + i));
                    targetUserIdList.add(targetUserId);
                } catch (java.lang.NullPointerException e) {
                    ;
                }
            }
            if (targetUserIdList.size() == 0) {
                request.setAttribute("courseList", courseList);
                request.setAttribute("studentList", userList);
                request.setAttribute("Number", 6);
                request.setAttribute("errorString", "チェックが一つもついていません");
                dispatch.forward(request, response);
                return;
            }

            try {
                modelManager.courseUpdate(targetUserIdList, targetSyllabusId, -2);
            } catch (SQLException e) {
                request.setAttribute("courseList", courseList);
                request.setAttribute("studentList", userList);
                request.setAttribute("Number", 6);
                request.setAttribute("errorString", "更新に失敗しました。");
                dispatch.forward(request, response);
                return;
            }

            courseList = modelManager.courseSelect("",targetSyllabusId,-1);
            userList = modelManager.courseSelectUser(targetSyllabusId);

            request.setAttribute("courseList", courseList);
            request.setAttribute("studentList", userList);
            request.setAttribute("Number", 6);
            request.setAttribute("errorString", "更新成功");
            dispatch.forward(request, response);
            return;
        } else if (action.equals("delete")) {
            String targetSyllabusId = (String) session.getAttribute("targetSyllabusId");
            if (modelManager.courseDelete(targetSyllabusId, targetUserIdList)) {
                request.setAttribute("targetSyllabus", modelManager.syllabusDetailFindById(targetSyllabusId));
                request.setAttribute("Number", 11);
                request.setAttribute("errorString", "");
                dispatch.forward(request, response);
            } else {
                request.setAttribute("courseList", courseList);
                request.setAttribute("studentList", userList);
                request.setAttribute("targetUserIdList", targetUserIdList);
                request.setAttribute("Number", 7);
                request.setAttribute("errorString", "削除に失敗しました。");
                dispatch.forward(request, response);
                return;
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
