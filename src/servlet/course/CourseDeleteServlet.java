package servlet.course;

import Entity.Course;
import etc.ModelManager;
import etc.ReplaceString;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CourseDeleteServlet extends HttpServlet {
    private ModelManager modelManager;
    private ReplaceString replaceString = new ReplaceString();

    @Override
    public void init() throws ServletException {
        super.init();
        modelManager = (ModelManager) getServletContext().getAttribute("modelManager");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = (String) request.getAttribute("action");
        switch (action){
            case "CourseDelete":
                actionDelete(request, response);
                return;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/Main").forward(request,response);
        return;
    }

    private void actionDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String targetSyllabusId = (String) request.getSession(true).getAttribute("targetSyllabusId");
        List<Course> courseList = modelManager.courseSelect("", targetSyllabusId, -1);
        List<String> targetUserIdList = new ArrayList<>();

        for (int i = 0; i < courseList.size(); i++) {
            String userId = replaceString.repairRequest(request.getParameter("check_" + i));
            if (!userId.equals("")) {
                targetUserIdList.add(userId);
            }
        }

        if (targetUserIdList.size() == 0) {
            errorReturn(request,response,"チェックが一つもついていません。");
            return;
        }
        if(!modelManager.courseDelete(targetSyllabusId,targetUserIdList)){
            errorReturn(request,response,"更新に失敗しました。");
            return;
        }
        errorReturn(request,response,"更新成功");
        return;
    }

    private void errorReturn(HttpServletRequest request, HttpServletResponse response, String errorString) throws ServletException, IOException {
        request.setAttribute("errorString", errorString);
        request.setAttribute("action", "CourseList");
        request.getRequestDispatcher("/Main").forward(request, response);
        return;
    }
}
