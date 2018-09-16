package servlet;

import Entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserUpdateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("user_id");
        String name = request.getParameter("name");
        String phonetic =request.getParameter("phonetic");
        String password =request.getParameter("password");
        String salt = request.getParameter("salt");
        Integer gender = Integer.parseInt(request.getParameter("gender"));
        String birthday =  request.getParameter("birthday");
        String postalCode = request.getParameter("postal_code");
        String address = request.getParameter("address");
        String tel = request.getParameter("tel");
        String userClassification = request.getParameter("user_classification");

        String errorString = "";
        User user = new User();
        errorString = errorString + user.setUserId(userId);
        errorString = errorString + user.setName(name);
        errorString = errorString + user.setPhonetic(phonetic);
        errorString = errorString + user.setGender(gender);
        errorString = errorString + user.setBirthday(birthday);
        errorString = errorString + user.setPostalCode(postalCode);
        errorString = errorString + user.setAddress(address);
        errorString = errorString + user.setTel(tel);
        errorString = errorString + user.setUserClassification(userClassification);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
