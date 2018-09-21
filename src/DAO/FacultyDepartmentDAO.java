package DAO;

import Entity.FacultyDepartment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FacultyDepartmentDAO {
    private List<DateSet> list = new ArrayList<DateSet>();
    private String tableName = "faculty_department";
    private List<String> columns = Arrays.asList("faculty_department_id", "faculty", "department");
    private List<String> mold = Arrays.asList("integer", "string", "string");
    private SessionManager sessionManager = new SessionManager();
    private SQLCreater sqlCreater = new SQLCreater();

    public FacultyDepartmentDAO() {
        for (int i = 0; i < columns.size(); i++) {
            this.list.add(new DateSet(columns.get(i), mold.get(i), ""));
        }

    }

    public List<FacultyDepartment> select(FacultyDepartment facultyDepartment,Integer page) throws SQLException {
        setList(facultyDepartment);
        String sql = sqlCreater.select(tableName, list,page);
        ResultSet resultSet = this.sessionManager.executeQuery(sql);
        List<FacultyDepartment> results = new ArrayList<FacultyDepartment>();
        while (resultSet.next()) {
            results.add(new FacultyDepartment(resultSet));
        }
        return results;
    }


    public boolean insert(FacultyDepartment facultyDepartment) {
        setList(facultyDepartment);
        String sql = sqlCreater.insert(tableName, list);
        return sessionManager.execute(sql);
    }


    public boolean update(FacultyDepartment facultyDepartment) {
        setList(facultyDepartment);
        String sql = sqlCreater.update(tableName, list);
        System.out.println(sql);
        return sessionManager.execute(sql);
    }


    public boolean delete(FacultyDepartment facultyDepartment) {
        setList(facultyDepartment);
        String sql = sqlCreater.delete(tableName, list);
        return sessionManager.execute(sql);
    }

    private void setList(FacultyDepartment facultyDepartment) {
        list.get(0).setValue(facultyDepartment.getFacultyDepartmentId().toString());
        list.get(1).setValue(facultyDepartment.getFaculty());
        list.get(2).setValue(facultyDepartment.getDepartment());
    }

    public FacultyDepartment findById(String facultyDepartmentId) throws SQLException {
        setValue("faculty_department_id", facultyDepartmentId);
        String sql = sqlCreater.select(tableName, list,0);
        ResultSet resultSet = this.sessionManager.executeQuery(sql);
        resultSet.next();
        return new FacultyDepartment(resultSet);
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
