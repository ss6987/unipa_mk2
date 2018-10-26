package servlet.syllabus;

import etc.ModelManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SyllabusResultServlet extends HttpServlet {
    private ModelManager modelManager;
    private String url = "/Syllabus/SyllabusResult.jsp";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = (String) request.getAttribute("action");
        switch (action){
            case "SyllabusResult":
                actionResult(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void actionResult(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(url).forward(request,response);
        return;
    }
}
