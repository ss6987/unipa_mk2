package servlet;

import etc.ModelManager;
import Entity.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginCheckServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String disp = "/MainForward";
        RequestDispatcher dispatch = request.getRequestDispatcher(disp);
        HttpSession session = request.getSession(true);

        String id = request.getParameter("id");
        String password = request.getParameter("password");

        ModelManager modelManager = new ModelManager();

        User user = modelManager.login(id, password);
        Integer url = 1;
        if(user != null){
            System.out.println("login");
            session.setAttribute("user",user);
            url = 2;
        }else{
            System.out.println("error");
            user = new User();
            user.setUserId(id);
            session.setAttribute("user",user);
            url = 1;
        }
        request.setAttribute("Number",url);
        dispatch.forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
