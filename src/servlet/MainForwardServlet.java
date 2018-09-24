package servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainForwardServlet extends HttpServlet {
    //ページリストの定義
    private String[] pageList = {"/Login.jsp", "/Top.jsp", "/UserRegistration.jsp", "/UserDetail.jsp", "/UserDelete.jsp",
            "/CourseList.jsp", "/CourseDelete.jsp", "/UserSearch.jsp", "/UserResult.jsp", "/SyllabusRegistration.jsp",
            "/SyllabusDetail.jsp", "/SyllabusDelete.jsp", "/SyllabusSearch.jsp", "/SyllabusResult.jsp",
            "/CourseRegistration.jsp", "/CourseCheck.jsp", "/TimeTable.jsp", "/CourseCheck.jsp", "/AchieveRegistration.jsp",
            "/AchieveCheck.jsp", "/condition.jsp","SyllabusUpdate.jsp"};

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int nextNumber;
        try {
            nextNumber = (Integer) request.getAttribute("Number");
        } catch (java.lang.NullPointerException e) {
            nextNumber = Integer.parseInt(request.getParameter("Number"));
        }

        //ページリストから画面NoのURLへ遷移する
        request.getRequestDispatcher(pageList[nextNumber - 1]).forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int nextNumber;
        try {
            nextNumber = (Integer) request.getAttribute("Number");
        }catch (java.lang.NullPointerException e){
            nextNumber = 1;
        }
        //ページリストから画面NoのURLへ遷移する
        request.getRequestDispatcher(pageList[nextNumber - 1]).forward(request, response);
    }
}
