package servlet;

import Entity.Syllabus;
import Entity.SyllabusDetail;
import etc.ModelManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class SyllabusDetailServlet extends HttpServlet {
    private String disp = "/MainForward";
    private ModelManager modelManager = new ModelManager();
    private RequestDispatcher dispatch;
    private HttpSession session;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        dispatch = request.getRequestDispatcher(disp);
        session = request.getSession(true);

        String syllabusId = request.getParameter("targetSyllabusId");

        SyllabusDetail targetSyllabus = null;
        try {
            targetSyllabus = modelManager.syllabusFindById(syllabusId).convertSyllabusToSyllabusDetail();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("targetSyllabus",targetSyllabus);

        request.setAttribute("Number", 11);
        dispatch.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
