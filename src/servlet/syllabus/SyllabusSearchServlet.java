package servlet.syllabus;

import Entity.Syllabus;
import etc.ModelManager;
import etc.Paging;
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
import java.util.List;

public class SyllabusSearchServlet extends HttpServlet {
    private ModelManager modelManager;
    private String url = "/Syllabus/SyllabusSearch.jsp";

    @Override
    public void init() throws ServletException {
        super.init();
        modelManager = (ModelManager) getServletContext().getAttribute("modelManager");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = (String) request.getAttribute("action");
        switch (action){
            case "SyllabusSearch":
                actionSearch(request, response);
                return;
            case "SyllabusSearchFirst":
                actionSearchFirst(request, response);
                return;
            case "SyllabusSearchChangePage":
                actionChangePage(request, response);
                return;
            case "SyllabusSearchBack":
                actionBack(request, response);
                return;
        }

    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void actionSearch(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(url).forward(request,response);
        return;
    }

    private void actionSearchFirst(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
        Syllabus searchSyllabus = new Syllabus(request,"");
        if(!request.getAttribute("errorString").equals("")){
            request.getRequestDispatcher(url).forward(request,response);
            return;
        }

        List<Syllabus> syllabusList = modelManager.syllabusSearch(searchSyllabus,0);

        HttpSession session = request.getSession(true);
        session.setAttribute("searchSyllabus",searchSyllabus);
        session.setAttribute("paging",new Paging(modelManager.syllabusCount()));
        request.setAttribute("syllabusList",syllabusList);
        request.setAttribute("action","SyllabusResult");
        request.getRequestDispatcher("/Main").forward(request,response);
        return;
    }

    private void actionChangePage(HttpServletRequest request,HttpServletResponse response){

    }

    private void actionBack(HttpServletRequest request,HttpServletResponse response){

    }
}
