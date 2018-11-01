package servlet.syllabus;

import Entity.SyllabusDetail;
import etc.ModelManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SyllabusDeleteServlet extends HttpServlet {
    private ModelManager modelManager;
    private String url = "Syllabus/SyllabusDelete.jsp";

    @Override
    public void init() throws ServletException {
        super.init();
        modelManager = (ModelManager) getServletContext().getAttribute("modelManager");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = (String) request.getAttribute("action");
        switch (action){
            case "SyllabusDelete":
                actionDelete(request, response);
                return;
            case "SyllabusDeleteDone":
                actionDeleteDone(request, response);
                return;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void actionDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String targetSyllabusId = (String) request.getSession(true).getAttribute("targetSyllabusId");
        SyllabusDetail syllabusDetail = modelManager.syllabusDetailFindById(targetSyllabusId);

        request.setAttribute("targetSyllabus",syllabusDetail);
        request.getRequestDispatcher(url).forward(request,response);
        return;
    }

    private void actionDeleteDone(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String targetSyllabusId = (String) request.getSession(true).getAttribute("targetSyllabusId");
        SyllabusDetail syllabusDetail = modelManager.syllabusDetailFindById(targetSyllabusId);

        if(!modelManager.syllabusDelete(syllabusDetail)){
            request.setAttribute("errorString","削除失敗");
            request.setAttribute("action","SyllabusDelete");
            request.getRequestDispatcher("/Main").forward(request,response);
            return;
        }
        request.setAttribute("errorString","削除成功");
        request.setAttribute("action","SyllabusSearchBack");
        request.getRequestDispatcher("/Main").forward(request,response);
        return;
    }
}
