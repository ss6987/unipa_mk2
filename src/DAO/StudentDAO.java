package DAO;

import Entity.Student;
import Entity.User;
import etc.CreateSalt;
import etc.Sha256;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class StudentDAO {
    private List<DateSet> list = new ArrayList<DateSet>();
    private String tableName = "student";
    private List<String> columns = Arrays.asList("user_id", "faculty_department_id", "grade", "guardian_password", "guardian_slat");
    private List<String> mold = Arrays.asList("string", "integer", "integer", "string", "string");

    private SessionManager sessionManager = new SessionManager();
    private SQLCreater sqlCreater = new SQLCreater();

    public StudentDAO() {
        for (int i = 0; i < columns.size(); i++) {
            this.list.add(new DateSet(columns.get(i), mold.get(i), ""));
        }

    }

    public List<Student> select(Student student,Integer page) throws SQLException {
        setList(student);
        String sql = sqlCreater.select(tableName, list,page);
        ResultSet resultSet = this.sessionManager.executeQuery(sql);
        List<Student> results = new ArrayList<Student>();
        while (resultSet.next()) {
            results.add(createStudent(resultSet));
        }
        return results;
    }

    public boolean insert(Student student) {
        setList(student);
        String sql = sqlCreater.insert(tableName, list);
        return sessionManager.execute(sql);
    }

    public boolean update(Student student) {
        setList(student);
        String sql = sqlCreater.update(tableName, list);
        System.out.println(sql);
        return sessionManager.execute(sql);
    }

    public boolean delete(Student student) {
        setList(student);
        String sql = sqlCreater.delete(tableName, list);
        return sessionManager.execute(sql);
    }

    public boolean delete(User user){
        Student student;
        try {
            student = user.convertUserToStudent();
        } catch (SQLException e) {
            return false;
        }

        if(student == null){
            return true;
        }

        return this.delete(student);
    }


    private void setList(Student student) {
        list.get(0).setValue(student.getUserId());
        list.get(1).setValue(student.getFacultyDepartmentId().toString());
        list.get(2).setValue(student.getGrade().toString());
        list.get(3).setValue("password");
        list.get(4).setValue("");
    }

    public Student findByStudent(String StudentId) throws SQLException {
        setValue("user_id", StudentId);
        String sql = sqlCreater.select(tableName, list,0);
        ResultSet resultSet = this.sessionManager.executeQuery(sql);
        resultSet.next();
        return createStudent(resultSet);
    }


    private Student createStudent(ResultSet resultSet) throws SQLException {
        String userId = resultSet.getString("user_id");
        Integer facultyDepartmentId = resultSet.getInt("faculty_department_id");
        Integer grade = resultSet.getInt("grade");
        return new Student(userId, facultyDepartmentId, grade);
    }

    public void setValue(String column, String value) {
        list.get(columns.indexOf(column)).setValue(value);
    }

    public void clearValue() {
        list = new ArrayList<DateSet>();
        for (int i = 0; i < columns.size(); i++) {
            this.list.add(new DateSet(columns.get(i), mold.get(i), ""));
        }
    }

    public boolean updateGuardianPassword(Student student,String password){
        setList(student);
        String salt = new CreateSalt().getSalt();
        String hash = new Sha256().sha256(password + salt);
        setValue("guardian_password",hash);
        setValue("guardian_slat",salt);
        String sql = sqlCreater.update(tableName,list);
        return sessionManager.execute(sql);
    }
}
