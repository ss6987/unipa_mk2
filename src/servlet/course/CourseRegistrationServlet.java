package servlet.course;

import Entity.Course;
import Entity.Student;
import Entity.Syllabus;
import Entity.User;
import etc.ModelManager;
import etc.ReplaceString;
import etc.timetable.TimeTable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CourseRegistrationServlet extends HttpServlet {
    private ModelManager modelManager;
    private ReplaceString replaceString = new ReplaceString();
    private String url = "/Course/CourseRegistration.jsp";

    @Override
    public void init() throws ServletException {
        super.init();
        modelManager = (ModelManager) getServletContext().getAttribute("modelManager");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = (String) request.getAttribute("action");
        switch (action) {
            case "CourseRegistration":
                actionRegistration(request, response);
                return;
            case "CourseRegistrationSearch":
                actionSearch(request, response);
                return;
            case "CourseRegistrationAdd":
                actionAdd(request, response);
                return;
            case "CourseRegistrationDelete":
                actionDelete(request, response);
                return;
            case "CourseRegistrationDone":
                actionRegistrationDone(request, response);
                return;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/Main").forward(request,response);
        return;
    }

    private void actionRegistration(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(url).forward(request, response);
        return;
    }

    private void actionSearch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String weekString = replaceString.repairRequest(request.getParameter("week"));
        Integer week = -1;
        try {
            week = Integer.parseInt(weekString);
        } catch (java.lang.NumberFormatException e) {
            ;
        }
        String time = replaceString.repairRequest(request.getParameter("time"));
        Syllabus syllabus = new Syllabus();
        syllabus.setWeek(week);
        syllabus.setTime(time);
        syllabus.setSemester(modelManager.getSemesterString());
        syllabus.setYear(modelManager.getNow().getYear());


        request.getSession(true).setAttribute("searchSyllabus", syllabus);
        request.setAttribute("action", "SyllabusSearch");
        request.getRequestDispatcher("/Main").forward(request, response);
        return;
    }

    private void actionAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        String targetSyllabusId = replaceString.repairRequest(request.getParameter("targetSyllabusId"));
        Syllabus targetSyllabus = modelManager.syllabusFindById(targetSyllabusId);
        User user = (User) session.getAttribute("user");
        List<Course> courseList = modelManager.courseSelect(user.getUserId(),"",-1);
        for(Course course:courseList){
            if(course.getSyllabusId().equals(targetSyllabusId)){
                request.setAttribute("errorString","その科目はすでに履修済みです。");
                request.getRequestDispatcher(url).forward(request,response);
                return;
            }
        }
        TimeTable nowTable = (TimeTable) session.getAttribute("nowTable");
        nowTable.addSyllabus(targetSyllabus);

        session.setAttribute("nowTable", nowTable);
        request.getRequestDispatcher(url).forward(request, response);
        return;
    }

    private void actionDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        String targetSyllabusId = replaceString.repairRequest(request.getParameter("targetSyllabusId"));
        TimeTable nowTable = (TimeTable) session.getAttribute("nowTable");
        nowTable.deleteSyllabus(targetSyllabusId);

        session.setAttribute("nowTable", nowTable);
        request.getRequestDispatcher(url).forward(request, response);
        return;
    }

    private void actionRegistrationDone(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        User loginUser = (User) session.getAttribute("user");
        Student student;
        try {
            student = loginUser.convertUserToStudent();
        } catch (SQLException e) {
            request.setAttribute("errorString", "対象学生が存在しません。");
            request.getRequestDispatcher(url).forward(request, response);
            return;
        }

        TimeTable nowTable = (TimeTable) session.getAttribute("nowTable");

        if(!nowTable.checkOverlap()){
            request.setAttribute("errorString", "重複している科目が存在します。");
            request.getRequestDispatcher(url).forward(request, response);
            return;
        }

        modelManager.courseDelete(loginUser.getUserId());
        List<String> syllabusList = nowTable.getAllSyllabusList();
        if (!modelManager.courseRegistration(student, syllabusList)) {
            request.setAttribute("errorString", "登録に失敗しました。");
            request.getRequestDispatcher(url).forward(request, response);
            return;
        }

        session.setAttribute("timeTable",nowTable);
        request.setAttribute("action", "TimeTableCheck");
        request.getRequestDispatcher("/Main").forward(request, response);
        return;
    }
}
