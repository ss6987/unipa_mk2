package servlet;

import Entity.RegistrationPeriod;
import etc.ModelManager;
import etc.ReplaceString;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class PeriodRegistrationServlet extends HttpServlet {
    private ModelManager modelManager;
    private ReplaceString replaceString = new ReplaceString();
    private String url = "PeriodRegistration.jsp";

    @Override
    public void init() throws ServletException {
        super.init();
        modelManager = (ModelManager) getServletContext().getAttribute("modelManager");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = (String) request.getAttribute("action");
        switch (action) {
            case "PeriodRegistrationCheck":
                actionCheck(request, response);
                return;
            case "PeriodRegistrationDone":
                actionDone(request, response);
                return;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void actionCheck(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RegistrationPeriod registrationPeriod = modelManager.registrationPeriodSelect();

        request.setAttribute("registrationPeriod", registrationPeriod);
        request.getRequestDispatcher(url).forward(request, response);
        return;
    }

    private void actionDone(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RegistrationPeriod registrationPeriod = createRegistrationPeriod(request);
        if (!registrationPeriod.checkOrder()) {
            errorReturn(request,response,"登録開始日が登録終了日を超えています。");
            return;
        }

        if (!modelManager.registrationPeriodUpdate(registrationPeriod)) {
            errorReturn(request,response,"登録に失敗しました。");
        }
        modelManager = new ModelManager();
        errorReturn(request,response,"更新成功");
        return;
    }

    private void errorReturn(HttpServletRequest request, HttpServletResponse response,String errorString) throws ServletException, IOException {
        request.setAttribute("action","PeriodRegistrationCheck");
        request.setAttribute("errorString",errorString);
        request.getRequestDispatcher("/Main").forward(request,response);
        return;
    }

    private RegistrationPeriod createRegistrationPeriod(HttpServletRequest request) throws UnsupportedEncodingException {
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

        return new RegistrationPeriod(startDate, endDate);
    }

    private String addZero(String string) {
        if (string.length() == 1) {
            return "0" + string;
        }
        return string;
    }
}
