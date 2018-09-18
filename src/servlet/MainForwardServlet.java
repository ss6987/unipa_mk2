package servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainForwardServlet extends HttpServlet {
    //ページリストの定義
    private String[] pageList = {"/login.jsp", "/top.jsp", "/userreg.jsp", "/user.jsp", "/userdel.jsp",
            "/studentlist.jsp", "/studentdel.jsp", "/usersea.jsp", "/userresult.jsp", "/syllabusreg.jsp",
            "/syllabus.jsp", "/syllabusdel.jsp", "/syllabussea.jsp", "/ssresult.jsp",
            "/coursereg.jsp", "/course.jsp", "/timetable.jsp", "/courseperiod.jsp", "/achievereg.jsp",
            "/achieve.jsp", "/condition.jsp"};

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String errorMessage = (String) request.getAttribute("ErrorMessage");
        int nextNumber;
        try{
            nextNumber = (Integer) request.getAttribute("Number");
        }catch (java.lang.NullPointerException e){
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
