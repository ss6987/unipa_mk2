package servlet.user;

import Entity.User;
import etc.ModelManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;


public class UserRegistServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String disp = "/MainForward";
        RequestDispatcher dispatch = request.getRequestDispatcher(disp);
        int num;

        String id = request.getParameter("id");
        String userId = request.getParameter("user_id");
        String name = request.getParameter("name");
        String phonetic =request.getParameter("phonetic");
        String salt = request.getParameter("salt");
        Integer gender = Integer.parseInt(request.getParameter("gender"));
        String birthday =  request.getParameter("birthday");
        String postalCode = request.getParameter("postal_code");
        String address = request.getParameter("address");
        String tel = request.getParameter("tel");
        String userClassification = request.getParameter("user_classification");
        
        String errorString = "";
        User user = new User();
        errorString += user.setUserId(userId);
        errorString += user.setName(name);
        errorString += user.setPhonetic(phonetic);
        errorString += user.setGender(gender);
        errorString += user.setBirthday(birthday);
        errorString += user.setPostalCode(postalCode);
        errorString += user.setAddress(address);
        errorString += user.setTel(tel);
        errorString += user.setUserClassification(userClassification);

        ModelManager mm = new ModelManager();
        errorString += mm.userRegistration(user,"");

        if(errorString == null){
            num = 4;
        }
        else{
            num = 3;
        }

        request.setAttribute("Number",num);
        dispatch.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
