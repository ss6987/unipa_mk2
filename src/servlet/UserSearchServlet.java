package servlet;

import Entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserSearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("user_id");
        String name = request.getParameter("name");
        String phonetic =request.getParameter("phonetic");
        Integer gender = Integer.parseInt(request.getParameter("gender"));
        String birthday =  request.getParameter("birthday");
        String postalCode = request.getParameter("postal_code");
        String address = request.getParameter("address");
        String tel = request.getParameter("tel");
        String urerClassification = request.getParameter("user_classification");


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
        errorString += user.setUserClassification(urerClassification);
        System.out.println("UserSearch");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
