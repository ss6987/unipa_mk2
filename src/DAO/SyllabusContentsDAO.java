package DAO;

import Entity.Syllabus;
import Entity.SyllabusContents;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SyllabusContentsDAO {

    private List<DateSet> list = new ArrayList<DateSet>();
    private String tableName = "syllabus_contents";
    private List<String> columns = Arrays.asList("subject_id", "class_number", "course_content");
    private List<String> mold = Arrays.asList("string", "integer", "string");
    private SessionManager sessionManager = new SessionManager();
    private SQLCreater sqlCreater = new SQLCreater();

    public SyllabusContentsDAO() {
        for (int i = 0; i < columns.size(); i++) {
            this.list.add(new DateSet(columns.get(i), mold.get(i), ""));
        }

    }

    public boolean insert(SyllabusContents syllabusContents) {
        setList(syllabusContents);
        String sql = sqlCreater.insert(tableName, list);
        return sessionManager.execute(sql);
    }

    public boolean delete(SyllabusContents syllabusContents) {
        setList(syllabusContents);
        String sql = sqlCreater.deleteAnd(tableName, list);
        return sessionManager.execute(sql);
    }

    public List<SyllabusContents> findBySyllabus(Syllabus syllabus) throws SQLException {
        String sql = "SELECT * FROM syllabus_contents WHERE subject_id = '" + syllabus.getSyllabusId() + "'";
        ResultSet resultSet = sessionManager.executeQuery(sql);
        List<SyllabusContents> syllabusContentsList = new ArrayList<SyllabusContents>();
        while(resultSet.next()){
            syllabusContentsList.add(new SyllabusContents(resultSet));
        }
        return syllabusContentsList;
    }

    private void setList(SyllabusContents syllabusContents) {
        list.get(0).setValue(syllabusContents.getSyllabusId());
        list.get(1).setValue(syllabusContents.getClassNumber().toString());
        list.get(2).setValue(syllabusContents.getCourseContent());
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
