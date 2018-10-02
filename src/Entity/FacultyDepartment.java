package Entity;

import etc.ReplaceString;
import etc.StringCheck;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FacultyDepartment {
    private Integer facultyDepartmentId;
    private String faculty;
    private String department;

    public FacultyDepartment(){
        facultyDepartmentId = -1;
        faculty = "";
        department = "";
    }

    public FacultyDepartment(ResultSet resultSet) throws SQLException {
        this.facultyDepartmentId = resultSet.getInt("faculty_department_id");
        this.faculty = resultSet.getString("faculty");
        this.department = resultSet.getString("department");
    }

    public FacultyDepartment(Integer id, String faculty, String department) {
        setFacultyDepartmentId(id);
        setFaculty(faculty);
        setDepartment(department);
    }

    public String setFacultyDepartmentId(Integer facultyDepartmentId) {
        if(facultyDepartmentId >= -1){
            this.facultyDepartmentId = facultyDepartmentId;
            return "";
        }
        return "に使用できない値が入力されました。";
    }

    public String setFaculty(String faculty) {
        faculty = new ReplaceString().replace(faculty);
        if(new StringCheck().checkNotSymbols(faculty)){
            this.faculty = faculty;
            return "";
        }
        return "に使用できない文字が存在します。";
    }

    public String setDepartment(String department) {
        department = new ReplaceString().replace(department);
        if(new StringCheck().checkNotSymbols(department)){
            this.department = department;
            return "";
        }
        return "に使用できない文字が存在します。";
    }

    public Integer getFacultyDepartmentId() {
        return facultyDepartmentId;
    }

    public String getFaculty() {
        return faculty;
    }

    public String getDepartment() {
        return department;
    }
}
