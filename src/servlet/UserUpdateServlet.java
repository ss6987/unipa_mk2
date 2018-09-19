package servlet;

import Entity.User;
import etc.ModelManager;
import etc.ReplaceString;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UserUpdateServlet extends HttpServlet {
    private String disp = "/MainForward";
    private ModelManager modelManager = new ModelManager();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatch = request.getRequestDispatcher(disp);

        String action = request.getParameter("action");
        String userId = request.getParameter("user_id");
        User targetUser = modelManager.userFindById(userId);

        if (action.equals("update")) {
            String name = new ReplaceString().repairRequest(request.getParameter("name"));
            String phonetic = new ReplaceString().repairRequest(request.getParameter("phonetic"));
            Integer gender = Integer.parseInt(request.getParameter("gender"));
            String year = request.getParameter("year");
            String month = request.getParameter("month");
            String day = request.getParameter("day");
            String birthday = year + "-" + month + "-" + day;
            String postalCode = request.getParameter("postal_code");
            String address = new ReplaceString().repairRequest(request.getParameter("address"));
            String tel = request.getParameter("tel");
            String userClassification = request.getParameter("user_classification");
            if (name.equals("管理者")) {
                System.out.println(name);
                System.out.println(phonetic);
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

                ModelManager modelManager = new ModelManager();
//                boolean update = modelManager.userUpdate(user);
//
//                if (update != true) errorString += ("更新に失敗しました");

                request.setAttribute("Number", 4);
                dispatch.forward(request, response);
            }

        } else if (action.equals("delete")) {
            request.setAttribute("targetUser", targetUser);
            request.setAttribute("Number", 5);
            dispatch.forward(request, response);
        } else {
            request.setAttribute("Number", 2);
            dispatch.forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
