package DAO;

import Entity.Syllabus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RelationDAO {
    private List<DateSet> list = new ArrayList<DateSet>();
    private String tableName = "relation";
    private List<String> columns = Arrays.asList("main_syllabus_id", "sub_syllabus_id", "relation_type");
    private List<String> mold = Arrays.asList("string", "string", "string");
    private SessionManager sessionManager = new SessionManager();
    private SQLCreater sqlCreater = new SQLCreater();

    public RelationDAO() {
        for (int i = 0; i < columns.size(); i++) {
            this.list.add(new DateSet(columns.get(i), mold.get(i), ""));
        }
    }

    public boolean insert(Syllabus mainSyllabus, Syllabus subSyllabus, String relation) {
        setList(mainSyllabus, subSyllabus, relation);
        String sql = sqlCreater.insert(tableName, list);
        return sessionManager.execute(sql);
    }

    public boolean delete(Syllabus mainSyllabus, Syllabus subSyllabus, String relation) {
        setList(mainSyllabus, subSyllabus, relation);
        String sql = sqlCreater.deleteAnd(tableName, list);
        return sessionManager.execute(sql);
    }

    public List<Syllabus> findByMainSyllabusId(String syllabusId, String relation) throws SQLException {
        String sql = "SELECT * FROM relation as r,syllabus as s WHERE r.sub_syllabus_id = s.syllabus_id AND main_syllabus_id = '" + syllabusId + "'";
        if(!relation.isEmpty()){
            sql = sql + " AND relation_type = '" + relation + "'";
        }
        ResultSet resultSet = sessionManager.executeQuery(sql);
        List<Syllabus> syllabusList = new ArrayList<Syllabus>();
        System.out.println(sql);
        while(resultSet.next()){
            syllabusList.add(new Syllabus(resultSet));
        }
        return syllabusList;
    }

    private void setList(Syllabus mainSyllabus, Syllabus subSyllabus, String relation) {
        list.get(0).setValue(mainSyllabus.getSyllabusId());
        list.get(1).setValue(subSyllabus.getSyllabusId());
        list.get(2).setValue(relation);
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
