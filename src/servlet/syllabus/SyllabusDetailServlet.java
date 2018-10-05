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
    private String disp = "/MainForward";
    private ModelManager modelManager = new ModelManager();
    private RequestDispatcher dispatch;
    private HttpSession session;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        dispatch = request.getRequestDispatcher(disp);
        session = request.getSession(true);

        String action = request.getParameter("action");
        String syllabusId = request.getParameter("targetSyllabusId");
        if(syllabusId == null){
            syllabusId = (String) session.getAttribute("targetSyllabusId");
        }

        SyllabusDetail targetSyllabus = modelManager.syllabusDetailFindById(syllabusId);

        session.setAttribute("targetSyllabusId", syllabusId);
        request.setAttribute("targetSyllabus", targetSyllabus);
        if (action.equals("detail")) {
            String backPage = new ReplaceString().repairRequest(request.getParameter("backPage"));
            User user = (User) session.getAttribute("user");
            if(user.getUserClassification().equals("教職員") && modelManager.getInCharge(syllabusId,user.getUserId())){
                request.setAttribute("inChargeFlag",true);
            }else{
                request.setAttribute("inChargeFlag",false);
            }
            request.setAttribute("semesterFlag",modelManager.getSemester());
            request.setAttribute("backPage",backPage);
            request.setAttribute("Number", 11);
            dispatch.forward(request, response);
        }else if(action.equals("update")){
            request.setAttribute("Number",10);
            dispatch.forward(request, response);
        }else if(action.equals("delete")){
            request.setAttribute("Number",12);
            dispatch.forward(request, response);
        }
        return;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
