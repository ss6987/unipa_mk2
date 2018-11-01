package servlet.user;

import Entity.User;
import etc.ModelManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserDeleteServlet extends HttpServlet {
    private ModelManager modelManager;
    private String url = "/User/UserDelete.jsp";

    @Override
    public void init() throws ServletException {
        super.init();
        modelManager = (ModelManager) getServletContext().getAttribute("modelManager");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = (String) request.getAttribute("action");
        String targetUserId = (String) request.getSession(true).getAttribute("targetUserId");
        User targetUser = modelManager.userFindById(targetUserId);

        switch (action){
            case "UserDelete":
                actionDelete(request, response,targetUser);
                return;
            case "UserDeleteDone":
                actionDeleteDone(request, response,targetUser);
                return;
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void actionDelete(HttpServletRequest request, HttpServletResponse response,User targetUser) throws ServletException, IOException {
        request.setAttribute("targetUser",targetUser);
        request.getRequestDispatcher(url).forward(request,response);
        return;
    }

    private void actionDeleteDone(HttpServletRequest request, HttpServletResponse response,User targetUser) throws ServletException, IOException {
        if(!modelManager.userDelete(targetUser)){
            request.setAttribute("errorString","削除失敗");
            actionDelete(request, response, targetUser);
            return;
        }

        request.setAttribute("errorString","削除成功");
        request.setAttribute("action","UserSearchBack");
        request.getRequestDispatcher("/Main").forward(request,response);
        return;
    }
}
