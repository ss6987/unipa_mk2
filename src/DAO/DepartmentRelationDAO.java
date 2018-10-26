package DAO;

import Entity.FacultyDepartment;
import Entity.Syllabus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DepartmentRelationDAO {
    private List<DateSet> list = new ArrayList<DateSet>();
    private String tableName = "department_relation";
    private List<String> columns = Arrays.asList("faculty_department_id", "syllabus_id");
    private List<String> mold = Arrays.asList("integer", "string");
    private SessionManager sessionManager = new SessionManager();
    private SQLCreater sqlCreater = new SQLCreater();

    public DepartmentRelationDAO(){
        for (int i = 0; i < columns.size(); i++) {
            this.list.add(new DateSet(columns.get(i), mold.get(i), ""));
        }
    }

    public List<Syllabus> findByFacultyDepartmen(FacultyDepartment facultyDepartment) throws SQLException {
        String sql = "select * from department_relation as d,syllabus as s where d.syllabus_id = s.syllabus_id and d.faculty_department_id = " + facultyDepartment.getFacultyDepartmentId();
        ResultSet resultSet = sessionManager.executeQuery(sql);
        List<Syllabus> syllabusList = new ArrayList<Syllabus>();
        while (resultSet.next()) {
            syllabusList.add(new Syllabus(resultSet));
        }
        return syllabusList;
    }

    public List<FacultyDepartment> findBySyllabus(Syllabus syllabus) throws SQLException {
        String sql = "select * from department_relation as d,faculty_department as f where d.faculty_department_id = f.faculty_department_id and d.syllabus_id = '" + syllabus.getSyllabusId() + "'";
        ResultSet resultSet = sessionManager.executeQuery(sql);
        List<FacultyDepartment> facultyDepartmentList = new ArrayList<FacultyDepartment>();
        System.out.println(sql);
        while (resultSet.next()) {
            facultyDepartmentList.add(new FacultyDepartment(resultSet));
        }
        return facultyDepartmentList;
    }

    public boolean insert(FacultyDepartment facultyDepartment, Syllabus syllabus) {
        setList(facultyDepartment,syllabus);
        String sql = sqlCreater.insert(tableName, list);
        return sessionManager.execute(sql);
    }

    public boolean delete(FacultyDepartment facultyDepartment, Syllabus syllabus){
        setList(facultyDepartment,syllabus);
        String sql = sqlCreater.delete(tableName, list);
        return sessionManager.execute(sql);
    }

    private void setList(FacultyDepartment facultyDepartment, Syllabus syllabus) {
        list.get(0).setValue(facultyDepartment.getFacultyDepartmentId().toString());
        list.get(1).setValue(syllabus.getSyllabusId());
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
