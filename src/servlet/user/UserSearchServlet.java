package servlet.user;

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
import java.io.UnsupportedEncodingException;
import java.util.List;

public class UserSearchServlet extends HttpServlet {
    private ModelManager modelManager;
    private ReplaceString replaceString = new ReplaceString();
    private String url = "User/UserSearch.jsp";

    @Override
    public void init() throws ServletException {
        super.init();
        modelManager = (ModelManager) getServletContext().getAttribute("modelManager");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = (String) request.getAttribute("action");
        switch (action){
            case "UserSearch":
                search(request,response);
                return;
            case "UserSearchFirst":
                searchFirst(request,response);
                return;
            case "UserSearchChangePage":
                changePage(request,response);
                return;
            case "UserSearchBack":
                back(request, response);
                return;
        }
        return;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void search(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(url).forward(request, response);
        return;
    }

    private void searchFirst(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
        User searchUser = new User(request,"");
        List<User> userList = modelManager.userSearch(searchUser,0);
        Paging paging = new Paging(modelManager.userCount());

        HttpSession session = request.getSession(true);
        session.setAttribute("searchUser",searchUser);
        session.setAttribute("paging",paging);
        request.setAttribute("userList",userList);
        request.setAttribute("action","UserResult");
        request.getRequestDispatcher("/Main").forward(request,response);
        return;
    }

    private void changePage(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        Integer nextPage = Integer.parseInt(replaceString.repairRequest(request.getParameter("page")));
        Paging paging = (Paging) session.getAttribute("paging");
        User searchUser = (User) session.getAttribute("searchUser");

        paging.changePage(nextPage);
        List<User> userList = modelManager.userSearch(searchUser,paging.getNowPage() -1);

        session.setAttribute("paging",paging);
        request.setAttribute("userList",userList);
        request.setAttribute("action","UserResult");
        request.getRequestDispatcher("/Main").forward(request,response);
        return;
    }

    private void back(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        Paging paging = (Paging) session.getAttribute("paging");
        User searchUser = (User) session.getAttribute("searchUser");

        List<User> userList = modelManager.userSearch(searchUser,paging.getNowPage() -1);

        request.setAttribute("userList",userList);
        request.setAttribute("action","UserResult");
        request.getRequestDispatcher("/Main").forward(request,response);
        return;
    }
}
