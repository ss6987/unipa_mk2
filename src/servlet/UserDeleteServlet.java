package servlet;

import Entity.User;
import etc.ModelManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserDeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userId = request.getParameter("user_id");

        ModelManager modelManager=new ModelManager();
        User user = modelManager.userFindById(userId);
        if(modelManager.userDelete(user)){
            System.out.println("成功");
        }else{
            System.out.printf("失敗");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
