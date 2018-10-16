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

public class SyllabusDetailServlet extends HttpServlet {
    private ModelManager modelManager;
    private String url = "/Syllabus/SyllabusDetail.jsp";

    @Override
    public void init() throws ServletException {
        super.init();
        modelManager = (ModelManager) getServletContext().getAttribute("modelManager");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = (String) request.getAttribute("action");
        switch (action){
            case "SyllabusDetail":
                actionDetail(request, response);
                return;
        }
        return;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void actionDetail(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        String targetSyllabusId = request.getParameter("targetSyllabusId");
        SyllabusDetail syllabusDetail = modelManager.syllabusDetailFindById(targetSyllabusId);
        HttpSession session = request.getSession(true);
        session.setAttribute("targetSyllabusId",targetSyllabusId);
        request.setAttribute("targetSyllabus",syllabusDetail);
        request.getRequestDispatcher(url).forward(request,response);
        return;
    }
}
