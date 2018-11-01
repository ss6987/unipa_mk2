package servlet.user;

import Entity.User;
import etc.ModelManager;
import etc.Paging;
import etc.ReplaceString;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
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
                actionSearch(request,response);
                return;
            case "UserSearchFirst":
                actionSearchFirst(request,response);
                return;
            case "UserSearchChangePage":
                actionChangePage(request,response);
                return;
            case "UserSearchBack":
                actionBack(request, response);
                return;
        }
        return;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void actionSearch(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher(url).forward(request, response);
        return;
    }

    private void actionSearchFirst(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
        User searchUser = new User(request,"");
        List<User> userList = modelManager.userSearch(searchUser,0);

        HttpSession session = request.getSession(true);
        session.setAttribute("searchUser",searchUser);
        session.setAttribute("paging",new Paging(modelManager.userCount()));
        request.setAttribute("userList",userList);
        request.setAttribute("action","UserResult");
        request.getRequestDispatcher("/Main").forward(request,response);
        return;
    }

    private void actionChangePage(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
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

    private void actionBack(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        Paging paging = (Paging) session.getAttribute("paging");
        User searchUser = (User) session.getAttribute("searchUser");

        List<User> userList = modelManager.userSearch(searchUser,paging.getNowPage() -1);
        paging.setCount(modelManager.userCount());

        request.setAttribute("userList",userList);
        request.setAttribute("action","UserResult");
        request.getRequestDispatcher("/Main").forward(request,response);
        return;
    }
}
