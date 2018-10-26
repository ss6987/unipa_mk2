package DAO;

import Entity.Course;
import Entity.Student;
import Entity.Syllabus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CourseDAO {
    private List<DateSet> list = new ArrayList<DateSet>();
    private String tableName = "course";
    private List<String> columns = Arrays.asList("user_id","syllabus_id","achievement","main_teacher_name","year");
    private List<String> mold = Arrays.asList("string", "string", "integer","string","integer");
    private SessionManager sessionManager = new SessionManager();
    private SQLCreater sqlCreater = new SQLCreater();

    public CourseDAO() {
        for (int i = 0; i < columns.size(); i++) {
            this.list.add(new DateSet(columns.get(i), mold.get(i), ""));
        }
    }

    public boolean insert(Course course) throws SQLException {
        setList(course);
        list.get(2).setValue("-3");
        String sql = sqlCreater.insert(tableName, list);
        return sessionManager.execute(sql);
    }

    public boolean delete(Course course) throws SQLException {
        setList(course);
        String sql = sqlCreater.deleteAnd(tableName, list);
        return sessionManager.execute(sql);
    }

    public boolean update(Course course) throws SQLException {
        setList(course);
        String sql = sqlCreater.update(tableName,list);
        sql += " AND syllabus_id = '" + list.get(1).getValue() + "'";
        return sessionManager.execute(sql);
    }

    public List<Course> findByStudent(Student student, Integer achievement) throws SQLException {
        String sql = "SELECT * FROM COURSE WHERE user_id = '" + student.getUserId() + "'";
        if (achievement != -1) {
            sql = sql + " AND achievement = " + achievement;
        }
        ResultSet resultSet = sessionManager.executeQuery(sql);
        List<Course> courseList = new ArrayList<Course>();
        while (resultSet.next()) {
            courseList.add(new Course(resultSet));
        }
        return courseList;
    }

    public List<Course> findBySyllabus(Syllabus syllabus, Integer achievement) throws SQLException {
        String sql = "SELECT * FROM COURSE WHERE syllabus_id = '" + syllabus.getSyllabusId() + "'";
        if (achievement != -1) {
            sql = sql + " AND achievement = " + achievement;
        }
        ResultSet resultSet = sessionManager.executeQuery(sql);
        List<Course> courseList = new ArrayList<Course>();
        while (resultSet.next()) {
            courseList.add(new Course(resultSet));
        }
        return courseList;
    }

    private void setList(Course course) throws SQLException {
        list.get(0).setValue(course.getUserId());
        list.get(1).setValue(course.getSyllabusId());
        list.get(2).setValue(course.getAchievement().toString());
        list.get(3).setValue(course.getMainTeacherName());
        list.get(4).setValue(course.getYear().toString());
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
}