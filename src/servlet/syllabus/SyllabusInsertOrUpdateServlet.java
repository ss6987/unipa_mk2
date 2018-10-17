package servlet.syllabus;

import DAO.SyllabusDAO;
import Entity.Syllabus;
import Entity.SyllabusContents;
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
import java.sql.SQLException;

public class SyllabusInsertOrUpdateServlet extends HttpServlet {
    private String disp = "/MainForward";
    private ModelManager modelManager = new ModelManager();
    private RequestDispatcher dispatch;
    private HttpSession session;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = (String) request.getAttribute("action");
        switch (action){
            case "SyllabusInsert":
                actionInsert(request, response);
                return;
            case "SyllabusUpdate":
                actionUpdate(request, response);
                return;
            case "SyllabusInsertDone":
                actionInsertDone(request, response);
                return;
            case "SyllabusUpdateDone":
                actionUpdateDone(request, response);
                return;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void actionInsert(HttpServletRequest request, HttpServletResponse response){

    }

    private void actionUpdate(HttpServletRequest request, HttpServletResponse response){

    }

    private void actionInsertDone(HttpServletRequest request, HttpServletResponse response){

    }

    private void actionUpdateDone(HttpServletRequest request, HttpServletResponse response){

    }
}
