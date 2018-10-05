package DAO;

import Entity.Syllabus;
import Entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TeacherInChargeDAO {
    private List<DateSet> list = new ArrayList<DateSet>();
    private String tableName = "teacher_in_charge";
    private List<String> columns = Arrays.asList("user_id", "syllabus_id", "main_teacher");
    private List<String> mold = Arrays.asList("string", "string", "integer");
    private SessionManager sessionManager = new SessionManager();
    private SQLCreater sqlCreater = new SQLCreater();

    public TeacherInChargeDAO() {
        for (int i = 0; i < columns.size(); i++) {
            this.list.add(new DateSet(columns.get(i), mold.get(i), ""));
        }
    }

    public boolean insert(User user, Syllabus syllabus, Integer mainTeacher) {
        setList(user, syllabus, mainTeacher);
        String sql = sqlCreater.insert(tableName, list);
        return sessionManager.execute(sql);
    }

    public boolean delete(User user, Syllabus syllabus, Integer mainTeacher) {
        setList(user, syllabus, mainTeacher);
        String sql = sqlCreater.deleteAnd(tableName, list);
        return sessionManager.execute(sql);
    }

    public boolean deleteBySyllabus(Syllabus syllabus){
        setList(new User(),syllabus,-1);
        String sql = sqlCreater.deleteAnd(tableName,list);
        return sessionManager.execute(sql);
    }

    public List<Syllabus> findByUser(User user, Integer mainTeacher) throws SQLException {
        String sql = "SELECT * FROM syllabus AS s,teacher_in_charge AS t WHERE t.syllabus_id = s.syllabus_id AND t.user_id = '" + user.getUserId() + "'";
        if (mainTeacher != -1) {
            sql = sql + " AND t.main_teacher = " + mainTeacher;
        }
        ResultSet resultSet = sessionManager.executeQuery(sql);
        List<Syllabus> syllabusList = new ArrayList<Syllabus>();
        while (resultSet.next()) {
            syllabusList.add(new Syllabus(resultSet));
        }
        return syllabusList;
    }

    public User findMainTeacherBySyllabus(Syllabus syllabus) throws SQLException {
        String sql = "SELECT * FROM user AS u,teacher_in_charge AS t WHERE t.user_id = u.user_id AND t.syllabus_id = '" + syllabus.getSyllabusId() + "'";
        sql = sql + " AND t.main_teacher = " + 0;

        System.out.println(sql);
        ResultSet resultSet = sessionManager.executeQuery(sql);
        List<User> userList = new ArrayList<User>();
        resultSet.next();
        return new User(resultSet);
    }


    public List<User> findBySyllabus(Syllabus syllabus, Integer mainTeacher) throws SQLException {
        String sql = "SELECT * FROM user AS u,teacher_in_charge AS t WHERE t.user_id = u.user_id AND t.syllabus_id = '" + syllabus.getSyllabusId() + "'";
        if (mainTeacher != -1) {
            sql = sql + " AND t.main_teacher = " + mainTeacher;
        }
        System.out.println(sql);
        ResultSet resultSet = sessionManager.executeQuery(sql);
        List<User> userList = new ArrayList<User>();
        while (resultSet.next()) {
            userList.add(new User(resultSet));
        }
        return userList;
    }

    public boolean getInCharge(String syllabusId,String userId){
        setList(userId,syllabusId,-1);
        String sql = sqlCreater.selectAnd(tableName,list,-1);
        ResultSet resultSet = sessionManager.executeQuery(sql);
        try {
            return resultSet.next();
        } catch (SQLException e) {
            return false;
        }
    }

    private void setList(User user, Syllabus syllabus, Integer mainTeacher) {
        list.get(0).setValue(user.getUserId());
        list.get(1).setValue(syllabus.getSyllabusId());
        list.get(2).setValue(mainTeacher.toString());
    }

    private void setList(String userId,String syllabusId,Integer mainTeacher){
        list.get(0).setValue(userId);
        list.get(1).setValue(syllabusId);
        list.get(2).setValue(mainTeacher.toString());
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
