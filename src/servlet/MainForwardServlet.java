package servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*requestにページ番号を突っ込むとそのページへforwardしてくれるサーブレット*/
public class MainForwardServlet extends HttpServlet {
    //ページリストの定義
    private String[] pageList = {"/Login.jsp", "/Top.jsp", "/User/UserRegistration.jsp", "/User/UserDetail.jsp", "/User/UserDelete.jsp",
            "/Course/CourseList.jsp", "/Course/CourseDelete.jsp", "/User/UserSearch.jsp", "/User/UserResult.jsp", "/Syllabus/SyllabusRegistration.jsp",
            "/Syllabus/SyllabusDetail.jsp", "/Syllabus/SyllabusDelete.jsp", "/Syllabus/SyllabusSearch.jsp", "/Syllabus/SyllabusResult.jsp",
            "/Course/CourseRegistration.jsp", "/Course/CourseCheck.jsp", "/TimeTable.jsp", "/Course/CourseCheck.jsp", "/Achieve/AchieveRegistrationServlet.jsp",
            "/Achieve/AchieveCheck.jsp", "/condition.jsp","/Syllabus/SyllabusUpdate.jsp"};

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int nextNumber;
        try {
            nextNumber = (Integer) request.getAttribute("Number");
        } catch (java.lang.NullPointerException e) {
            nextNumber = Integer.parseInt(request.getParameter("Number"));
        }

        //ページリストから画面NoのURLへ遷移する
        request.getRequestDispatcher(pageList[nextNumber - 1]).forward(request, response);
        return;
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
        return;
    }
}
