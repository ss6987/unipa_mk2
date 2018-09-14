package Entity;

import DAO.FacultyDepartmentDAO;
import DAO.UserDAO;
import etc.ReplaceString;
import etc.StringCheck;

import java.sql.SQLException;

public class Student {
    private String userId;
    private Integer facultyDepartmentId;
    private Integer grade;

    public Student(String userId, Integer facultyDepartmentId, Integer grade) throws SQLException {
        setUserId(userId);
        setFacultyDepartmentId(facultyDepartmentId);
        setGrade(grade);
    }

    public String setUserId(String userId) throws SQLException {
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

    public FacultyDepartment getFacultyDepartment() throws SQLException {
        FacultyDepartmentDAO facultyDepartmentDAO = new FacultyDepartmentDAO();
        return facultyDepartmentDAO.findById(this.facultyDepartmentId.toString());
    }

    public User convertStudentToUser() throws SQLException {
        UserDAO userDAO = new UserDAO();
        return userDAO.findById(this.userId);
    }
}
