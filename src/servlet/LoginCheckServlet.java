package servlet;

import etc.ModelManager;
import Entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginCheckServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String password = request.getParameter("password");

        ModelManager modelManager = new ModelManager();
        HttpSession session = request.getSession(true);

        User user = modelManager.login(id, password);
        String url = "";
        if(user != null){
            System.out.println("login");
            session.setAttribute("user",user);
            url = "/ok.jsp";
        }else{
            System.out.println("error");
            user = new User();
            user.setUserId(id);
            session.setAttribute("user",user);
            url = "/login.jsp";
        }
        getServletConfig().getServletContext().getRequestDispatcher(url).forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
