package Entity;

import DAO.FacultyDepartmentDAO;
import DAO.UserDAO;
import etc.ReplaceString;
import etc.StringCheck;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

public class Student {
    private String userId;
    private Integer facultyDepartmentId;
    private Integer grade;


    public Student(){
        userId = "";
        facultyDepartmentId = -1;
        grade = -1;
    }

    public Student(String userId, Integer facultyDepartmentId, Integer grade) {
        setUserId(userId);
        setFacultyDepartmentId(facultyDepartmentId);
        setGrade(grade);
    }

    public Student(HttpServletRequest request,String targetUserId) throws UnsupportedEncodingException {
        ReplaceString replaceString = new ReplaceString();
        String facultyDepartmentIdString = replaceString.repairRequest(request.getParameter("facultyDepartmentId"));
        String gradeString = replaceString.repairRequest(request.getParameter("grade"));
        Integer facultyDepartmentId;
        try {
            facultyDepartmentId = Integer.parseInt(facultyDepartmentIdString);
        } catch (java.lang.NumberFormatException e) {
            facultyDepartmentId = -1;
        }

        Integer grade;
        try {
            grade = Integer.parseInt(gradeString);
        } catch (java.lang.NumberFormatException e) {
            grade = -1;
        }

        setUserId(targetUserId);
        setFacultyDepartmentId(facultyDepartmentId);
        setGrade(grade);
    }

    public String setUserId(String userId)  {
        userId = new ReplaceString().replace(userId);
        if (new StringCheck().checkNotSymbols(userId)) {
            if(new UserDAO().findById(userId).getUserId() != ""){
                this.userId = userId;
                return "";
            }
            return "ユーザーIDが存在しません。";
        }
        return "ユーザーIDに使用できない文字が存在します。";
    }

    public String setFacultyDepartmentId(Integer facultyDepartmentId) {
        if (facultyDepartmentId >= -1) {
            this.facultyDepartmentId = facultyDepartmentId;
            return "";
        }
        return "学部学科IDに使用できない文字が存在します。";
    }

    public String setGrade(Integer grade) {
        if (grade >= -1 && grade <= 4) {
            this.grade = grade;
            return "";
        }
        return "学年に使用できない値が存在します。";

    }

    public String getUserId() {
        return userId;
    }

    public Integer getFacultyDepartmentId() {

        return facultyDepartmentId;
    }

    public Integer getGrade() {
        return grade;
    }

    public String getFacultyDepartment() throws SQLException {
        FacultyDepartmentDAO facultyDepartmentDAO = new FacultyDepartmentDAO();
        FacultyDepartment facultyDepartment = facultyDepartmentDAO.findById(this.facultyDepartmentId.toString());
        return facultyDepartment.getFaculty() + facultyDepartment.getDepartment();
    }

    public User convertStudentToUser() throws SQLException {
        UserDAO userDAO = new UserDAO();
        return userDAO.findById(this.userId);
    }

    public String getFacultyDepartmentSelected(Integer facultyDepartmentId){
        if(this.getFacultyDepartmentId() == facultyDepartmentId){
            return "selected";
        }
        return "";
    }

    public String getGradeString(){
        if(this.grade != -1){
            return String.valueOf(this.grade);
        }else{
            return "";
        }
    }
}
