package servlet;

import Entity.User;
import etc.ModelManager;
import etc.Paging;
import etc.ReplaceString;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class UserSearchServlet extends HttpServlet {
    private String disp = "/MainForward";
    private ModelManager modelManager = new ModelManager();
    private RequestDispatcher dispatch;
    private HttpSession session;
    private Paging paging;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        dispatch = request.getRequestDispatcher(disp);
        session = request.getSession(true);
        ReplaceString replaceString = new ReplaceString();
        String action = replaceString.repairRequest(request.getParameter("action"));
        List<User> userList = null;
        if (action.equals("firstSearch")) {
            String userId = replaceString.repairRequest(request.getParameter("userId"));
            String name = replaceString.repairRequest(request.getParameter("name"));
            String phonetic = replaceString.repairRequest(request.getParameter("phonetic"));
            String genderString = replaceString.repairRequest(request.getParameter("gender"));
            String birthday = replaceString.repairRequest(request.getParameter("birthday"));
            String postalCode = replaceString.repairRequest(request.getParameter("postalCode"));
            String address = replaceString.repairRequest(request.getParameter("address"));
            String tel = replaceString.repairRequest(request.getParameter("tel"));
            String userClassification = replaceString.repairRequest(request.getParameter("userClassification"));
            Integer gender;

            try {
                gender = Integer.parseInt(genderString);
            } catch (java.lang.NumberFormatException e) {
                gender = -1;
            }

            String errorString = "";
            User searchUser = new User();
            errorString += searchUser.setUserId(userId);
            errorString += searchUser.setName(name);
            errorString += searchUser.setPhonetic(phonetic);
            errorString += searchUser.setGender(gender);
            errorString += searchUser.setBirthday(birthday);
            errorString += searchUser.setPostalCode(postalCode);
            errorString += searchUser.setAddress(address);
            errorString += searchUser.setTel(tel);
            errorString += searchUser.setUserClassification(userClassification);
            session.setAttribute("searchUser", searchUser);
            if (!errorString.equals("")) {
                request.setAttribute("errorString", errorString);
                request.setAttribute("Number", 8);
                dispatch.forward(request, response);
            }
            userList = modelManager.userSearch(searchUser, 0);
            Integer resultCount = modelManager.userCount();
            paging = new Paging(resultCount);
            paging.changePage(1);

        } else if (action.equals("changePage")) {
            Integer page = Integer.parseInt(replaceString.repairRequest(request.getParameter("page")));
            paging.changePage(page);
            User searchUser = (User) session.getAttribute("searchUser");
            userList = modelManager.userSearch(searchUser, page);
        } else if (action.equals("return")) {
            User user = (User) session.getAttribute("searchUser");
            userList = modelManager.userSearch(user, paging.getNowPage() - 1);
        }
        request.setAttribute("userList", userList);
        request.setAttribute("paging", paging);
        request.setAttribute("Number", 9);
        dispatch.forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
