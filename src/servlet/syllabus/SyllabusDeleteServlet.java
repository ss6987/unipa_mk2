package servlet.syllabus;

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

public class SyllabusDeleteServlet extends HttpServlet {
    private String disp = "/MainForward";
    private ModelManager modelManager = new ModelManager();
    private RequestDispatcher dispatch;
    private HttpSession session;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        dispatch = request.getRequestDispatcher(disp);
        session = request.getSession(true);

        String action = request.getParameter("action");
        String syllabusId = (String) session.getAttribute("targetSyllabusId");

        if (action.equals("delete")) {
            SyllabusDetail syllabusDetail = modelManager.syllabusDetailFindById(syllabusId);

            boolean flag = modelManager.syllabusDelete(syllabusDetail);
            if (flag) {
                request.getRequestDispatcher("/SyllabusSearch").forward(request, response);
            } else {
                request.setAttribute("Number", 11);
                dispatch.forward(request, response);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
