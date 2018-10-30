package servlet.syllabus;

import Entity.User;
import Entity.SyllabusDetail;
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
import java.sql.SQLException;

public class SyllabusInsertOrUpdateServlet extends HttpServlet {
    private ModelManager modelManager;
    private ReplaceString replaceString = new ReplaceString();
    private String url = "/Syllabus/SyllabusRegistration.jsp";

    @Override
    public void init() throws ServletException {
        super.init();
        modelManager = (ModelManager) getServletContext().getAttribute("modelManager");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = (String) request.getAttribute("action");
        switch (action) {
            case "SyllabusRegistration":
                actionInsert(request, response);
                return;
            case "SyllabusUpdate":
                actionUpdate(request, response);
                return;
            case "SyllabusRegistrationDone":
                actionInsertDone(request, response);
                return;
            case "SyllabusUpdateDone":
                actionUpdateDone(request, response);
                return;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void actionInsert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(url).forward(request, response);
        return;
    }

    private void actionUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String targetSyllabusId = (String) request.getSession(true).getAttribute("targetSyllabusId");
        SyllabusDetail targetSyllabus = modelManager.syllabusDetailFindById(targetSyllabusId);

        request.setAttribute("targetSyllabus", targetSyllabus);
        request.getRequestDispatcher(url).forward(request, response);
        return;
    }

    private void actionInsertDone(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        SyllabusDetail syllabusDetail = new SyllabusDetail(request, "");
        User targetMainTeacher = readMainTeacher(request);

        if (targetMainTeacher == null) {
            errorRegistrationReturn(request, response, "対象教職員が存在しません。");
            return;
        }

        if (!modelManager.syllabusRegistration(syllabusDetail)) {
            errorRegistrationReturn(request, response, "更新に失敗しました。");
            return;
        }

        if (!modelManager.teacherInChargeRegistration(syllabusDetail.getSyllabusId(), targetMainTeacher.getUserId(), 0)) {
            modelManager.syllabusDelete(syllabusDetail);
            errorRegistrationReturn(request, response, "更新に失敗しました。");
            return;
        }

        syllabusDetail.setSyllabusContents(request);

        if (!modelManager.syllabusUpdate(syllabusDetail)) {
            errorRegistrationReturn(request, response, "更新に失敗しました。");
            return;
        }

        request.getSession(true).setAttribute("targetSyllabusId", syllabusDetail.getSyllabusId());
        request.setAttribute("errorString", "更新成功");
        request.setAttribute("action", "SyllabusDetail");
        request.getRequestDispatcher("/Main").forward(request, response);
        return;
    }

    private void actionUpdateDone(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String targetSyllabusId = (String) request.getSession(true).getAttribute("targetSyllabusId");
        SyllabusDetail syllabusDetail = new SyllabusDetail(request, targetSyllabusId);
        User targetMainTeacher = readMainTeacher(request);
        if (targetMainTeacher == null) {
            request.setAttribute("targetSyllabus", syllabusDetail);
            errorUpdateReturn(request, response, "対象教職員が存在しません。");
            return;
        }

        if (!modelManager.syllabusUpdate(syllabusDetail)) {
            request.setAttribute("targetSyllabus", syllabusDetail);
            errorUpdateReturn(request, response, "更新に失敗しました。");
            return;
        }

        if (!modelManager.teacherInChargeRegistration(syllabusDetail.getSyllabusId(), targetMainTeacher.getUserId(), 0)) {
            errorUpdateReturn(request, response, "更新に失敗しました");
            return;
        }

        request.getSession(true).setAttribute("targetSyllabusId", targetSyllabusId);
        request.setAttribute("errorString", "更新成功");
        request.setAttribute("action", "SyllabusDetail");
        request.getRequestDispatcher("/Main").forward(request, response);
        return;
    }

    private User readMainTeacher(HttpServletRequest request) throws UnsupportedEncodingException {
        String targetMainTeacher = replaceString.repairRequest(request.getParameter("main_teacher_id"));
        User mainTeacher = modelManager.userFindById(targetMainTeacher);
        if (mainTeacher.getUserId().equals("")) {
            request.setAttribute("errorString", "該当ユーザーIDがありませんでした。");
            return null;
        } else if (!mainTeacher.getUserClassification().equals("教職員")) {
            request.setAttribute("errorString", "該当ユーザーが教職員ではありません。");
            return null;
        }
        return mainTeacher;
    }

    private void errorUpdateReturn(HttpServletRequest request, HttpServletResponse response, String errorString) throws ServletException, IOException {
        request.setAttribute("action", "SyllabusDetail");
        request.getRequestDispatcher("/Main").forward(request, response);
        return;
    }

    private void errorRegistrationReturn(HttpServletRequest request, HttpServletResponse response, String errorString) throws ServletException, IOException {
        request.setAttribute("action", "SyllabusRegistration");
        request.getRequestDispatcher("/Main").forward(request, response);
        return;
    }
}
