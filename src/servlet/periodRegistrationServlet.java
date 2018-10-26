package servlet;

import Entity.RegistrationPeriod;
import etc.ModelManager;
import etc.ReplaceString;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class periodRegistrationServlet extends HttpServlet {
    private String disp = "/MainForward";
    private ModelManager modelManager = new ModelManager();
    private RequestDispatcher dispatch;
    private HttpSession session;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        dispatch = request.getRequestDispatcher(disp);
        session = request.getSession(true);

        ReplaceString replaceString = new ReplaceString();
        String action = replaceString.repairRequest(request.getParameter("action"));

        if (action.equals("periodRegistrationCheck")) {
            RegistrationPeriod registrationPeriod = modelManager.registrationPeriodSelect();

            request.setAttribute("registrationPeriod", registrationPeriod);
            request.setAttribute("Number", 23);
            request.setAttribute("errorString", "");
            dispatch.forward(request, response);
            return;
        } else if (action.equals("periodRegistration")) {
            String startYear = replaceString.repairRequest(request.getParameter("startYear"));
            String startMonth = replaceString.repairRequest(request.getParameter("startMonth"));
            String startDay = replaceString.repairRequest(request.getParameter("startDay"));
            String endYear = replaceString.repairRequest(request.getParameter("endYear"));
            String endMonth = replaceString.repairRequest(request.getParameter("endMonth"));
            String endDay = replaceString.repairRequest(request.getParameter("endDay"));

            startMonth = addZero(startMonth);
            startDay = addZero(startDay);
            endMonth = addZero(endMonth);
            endDay = addZero(endDay);

            String startDate = startYear + "-" + startMonth + "-" + startDay;
            String endDate = endYear + "-" + endMonth + "-" + endDay;

            RegistrationPeriod registrationPeriod = new RegistrationPeriod(startDate, endDate);

            if (!registrationPeriod.checkOrder()) {
                request.setAttribute("errorString", "登録開始日が登録終了日を超えています。");
                registrationPeriod = modelManager.registrationPeriodSelect();
                request.setAttribute("registrationPeriod", registrationPeriod);
                request.setAttribute("Number", 23);
                dispatch.forward(request, response);
                return;
            }

            if (modelManager.registrationPeriodUpdate(registrationPeriod)) {
                request.setAttribute("errorString", "");
                request.setAttribute("registrationPeriod", registrationPeriod);
            } else {
                request.setAttribute("errorString", "登録に失敗しました。");
                registrationPeriod = modelManager.registrationPeriodSelect();
                request.setAttribute("registrationPeriod", registrationPeriod);
            }

            modelManager.updateRegistrationPeriodFlag();

            request.setAttribute("Number", 23);
            dispatch.forward(request, response);
            return;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private String addZero(String string) {
        if (string.length() == 1) {
            return "0" + string;
        }
        return string;
    }
}
