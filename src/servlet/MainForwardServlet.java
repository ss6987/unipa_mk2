package servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainForwardServlet extends HttpServlet {
    //ページリストの定義
    private String[] pageList = {"/Login.jsp", "/Top.jsp", "/UserRegistration.jsp", "/UserUpdate.jsp", "/UserDelete.jsp",
            "/CourseList.jsp", "/CourseDelete.jsp", "/UserSearch.jsp", "/UserResult.jsp", "/SyllabusRegistration.jsp",
            "/SyllabusRegistration.jsp", "/SyllabusDelete.jsp", "/SyllabusSearch.jsp", "/SyllabusResult.jsp",
            "/CourseRegistration.jsp", "/CourseCheck.jsp", "/TimeTable.jsp", "/CourseCheck.jsp", "/AchieveRegistration.jsp",
            "/AchieveCheck.jsp", "/condition.jsp"};

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String errorMessage = (String) request.getAttribute("ErrorMessage");
        int nextNumber = 0;
        try {
            nextNumber = (Integer) request.getAttribute("Number");
        } catch (java.lang.NullPointerException e) {
            nextNumber = Integer.parseInt(request.getParameter("Number"));
        }

        //ページリストから画面NoのURLへ遷移する
        request.getRequestDispatcher(pageList[nextNumber - 1]).forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String errorMessage = (String) request.getAttribute("ErrorMessage");
        int nextNumber = (Integer) request.getAttribute("Number");

        //ページリストから画面NoのURLへ遷移する
        request.getRequestDispatcher(pageList[nextNumber - 1]).forward(request, response);
    }
}
