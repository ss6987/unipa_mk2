package servlet.course;

import Entity.Student;
import Entity.Syllabus;
import etc.ModelManager;
import etc.ReplaceString;
import Entity.User;
import servlet.timetable.Time;
import servlet.timetable.TimeTable;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CourseRegistrationServlet extends HttpServlet {
    private String disp = "/MainForward";
    private ModelManager modelManager = new ModelManager();
    private RequestDispatcher dispatch;
    private HttpSession session;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        dispatch = request.getRequestDispatcher(disp);
        session = request.getSession(true);

        ReplaceString replaceString = new ReplaceString();
        String action = replaceString.repairRequest(request.getParameter("action"));

        if(!(boolean)session.getAttribute("registrationPeriodFlag")){
            request.setAttribute("errorString", "履修登録期間外");
            request.setAttribute("Number", 16);
            dispatch.forward(request, response);
            return;
        }

        if (action.equals("registration")) {
            TimeTable timeTable = (TimeTable) session.getAttribute("nowTable");
            User user = (User) session.getAttribute("user");

            if (!timeTable.checkOverlap()) {
                session.setAttribute("nowTable", timeTable);
                request.setAttribute("errorString", "重複している時間があります。");
                request.setAttribute("Number", 15);
                dispatch.forward(request, response);
                return;
            }

            Student student;
            try {
                student = user.convertUserToStudent();
            } catch (SQLException e) {
                request.setAttribute("errorString", "エラーが発生しました。");
                request.setAttribute("Number", 15);
                dispatch.forward(request, response);
                return;
            }

            modelManager.courseDelete(student.getUserId());
            List<String> syllabusList = timeTable.getAllSyllabusList();
            boolean flag = modelManager.courseRegistration(student, syllabusList);

            if (!flag) {
                request.setAttribute("errorString", "エラーが発生しました。");
                request.setAttribute("Number", 16);
                dispatch.forward(request, response);
                return;
            }
            session.setAttribute("timeTable",timeTable);

            request.setAttribute("errorString", "更新成功");
            request.setAttribute("Number", 17);
            dispatch.forward(request, response);
            return;

        } else if (action.equals("firstSearch")) {
            String time = replaceString.repairRequest(request.getParameter("time"));
            String week = replaceString.repairRequest(request.getParameter("week"));
            Syllabus searchSyllabus = new Syllabus();
            searchSyllabus.setTime(time);
            searchSyllabus.setWeek(Integer.parseInt(week));
            searchSyllabus.setSemester(modelManager.getSemesterString());
            session.setAttribute("searchSyllabus", searchSyllabus);

            request.setAttribute("errorString", "");
            request.setAttribute("Number", 13);
            dispatch.forward(request, response);
            return;
        } else if (action.equals("add")) {
            String targetSyllabusId = replaceString.repairRequest(request.getParameter("targetSyllabusId"));
            TimeTable timeTable = (TimeTable) session.getAttribute("nowTable");

            Syllabus syllabus = modelManager.syllabusFindById(targetSyllabusId);
            timeTable.addSyllabus(syllabus);
            request.setAttribute("errorString", "");
            request.setAttribute("Number", 15);
            dispatch.forward(request, response);
            return;

        } else if (action.equals("delete")) {
            String targetSyllabusId = replaceString.repairRequest(request.getParameter("targetSyllabusId"));
            TimeTable nowTable = (TimeTable) session.getAttribute("nowTable");
            nowTable.deleteSyllabus(targetSyllabusId);

            session.setAttribute("nowTable", nowTable);
            request.setAttribute("errorString", "");
            request.setAttribute("Number", 15);
            dispatch.forward(request, response);
            return;
        }else if(action.equals("check")){
            request.setAttribute("errorString", "");
            request.setAttribute("Number", 16);
            dispatch.forward(request, response);
        }else if(action.equals("back")){
            request.setAttribute("errorString", "");
            request.setAttribute("Number", 15);
            dispatch.forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
