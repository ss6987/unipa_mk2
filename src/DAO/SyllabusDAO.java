package DAO;

import Entity.Syllabus;
import Entity.SyllabusContents;
import Entity.SyllabusDetail;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class SyllabusDAO {
    private List<DateSet> list = new ArrayList<DateSet>();
    private String tableName = "syllabus";
    private List<String> columns = Arrays.asList("syllabus_id", "syllabus_name", "english_name", "dividend_grade", "year", "class", "semester", "week", "time", "unit", "capacity", "objective_summary", "goal", "textbook", "reference_book", "educational_object", "dp", "self_study", "free_text", "mail_address", "office_hour", "classification", "guidance", "advice");
    private List<String> mold = Arrays.asList("string", "string", "string", "integer", "integer", "string", "string", "string", "string", "integer", "integer", "string", "string", "string", "string", "string", "string", "string", "string", "string", "string", "string", "string", "string");
    private SessionManager sessionManager = new SessionManager();
    private SQLCreater sqlCreater = new SQLCreater();

    public SyllabusDAO() {
        for (int i = 0; i < columns.size(); i++) {
            this.list.add(new DateSet(columns.get(i), mold.get(i), ""));
        }

    }

    public List<Syllabus> select(Syllabus syllabus) throws SQLException {
        clearValue();
        setList(syllabus);
        String sql = sqlCreater.select(tableName, list);
        ResultSet resultSet = this.sessionManager.executeQuery(sql);
        List<Syllabus> results = new ArrayList<Syllabus>();
        while (resultSet.next()) {
            results.add(new Syllabus(resultSet));
        }
        return results;
    }

    public boolean insert(SyllabusDetail syllabus) {
        setList(syllabus);
        String sql = sqlCreater.insert(tableName, list);
        return sessionManager.execute(sql);
    }

    public boolean update(SyllabusDetail syllabus) {
        setList(syllabus);
        String sql = sqlCreater.update(tableName, list);
        System.out.println(sql);
        return sessionManager.execute(sql);
    }

    public boolean delete(SyllabusDetail syllabus) {
        setList(syllabus);
        String sql = sqlCreater.delete(tableName, list);
        return sessionManager.execute(sql);
    }

    private void setList(Syllabus syllabus) {
        list.get(0).setValue(syllabus.getSyllabusId());
        list.get(1).setValue(syllabus.getSyllabusName());
        list.get(2).setValue(syllabus.getEnglishName());
        list.get(3).setValue(syllabus.getDividendGrade().toString());
        list.get(4).setValue(syllabus.getYear().toString());
        list.get(5).setValue(syllabus.getClassRoom());
        list.get(6).setValue(syllabus.getSemester());
        list.get(7).setValue(syllabus.getWeek());
        list.get(8).setValue(syllabus.getTime());
        list.get(9).setValue(syllabus.getUnit().toString());
        list.get(10).setValue(syllabus.getCapacity().toString());
    }

    private void setList(SyllabusDetail syllabus) {
        list.get(0).setValue(syllabus.getSyllabusId());
        list.get(1).setValue(syllabus.getSyllabusName());
        list.get(2).setValue(syllabus.getEnglishName());
        list.get(3).setValue(syllabus.getDividendGrade().toString());
        list.get(4).setValue(syllabus.getYear().toString());
        list.get(5).setValue(syllabus.getClassRoom());
        list.get(6).setValue(syllabus.getSemester());
        list.get(7).setValue(syllabus.getWeek());
        list.get(8).setValue(syllabus.getTime());
        list.get(9).setValue(syllabus.getUnit().toString());
        list.get(10).setValue(syllabus.getCapacity().toString());
        list.get(11).setValue(syllabus.getObjectiveSummary());
        list.get(12).setValue(syllabus.getGoal());
        list.get(13).setValue(syllabus.getTextbook());
        list.get(14).setValue(syllabus.getReferenceBook());
        list.get(15).setValue(syllabus.getEducationalObject());
        list.get(16).setValue(syllabus.getDp());
        list.get(17).setValue(syllabus.getSelfStudy());
        list.get(18).setValue(syllabus.getFreeText());
        list.get(19).setValue(syllabus.getMailAddress());
        list.get(20).setValue(syllabus.getOfficeHour());
        list.get(21).setValue(syllabus.getClassification());
        list.get(22).setValue(syllabus.getGuidance());
        list.get(23).setValue(syllabus.getAdvice());
    }

    public Syllabus findBySyllabusId(String syllabusId) throws SQLException {
        setValue("syllabus_id", syllabusId);
        String sql = sqlCreater.select(tableName, list);
        ResultSet resultSet = this.sessionManager.executeQuery(sql);
        resultSet.next();
        return new Syllabus(resultSet);
    }

    public SyllabusDetail findBySyllabusDetailId(String syllabusId) throws SQLException {
        setValue("syllabus_id", syllabusId);
        String sql = sqlCreater.select(tableName, list);
        ResultSet resultSet = this.sessionManager.executeQuery(sql);
        resultSet.next();
        return createSyllabusDetail(resultSet);
    }

    private SyllabusDetail createSyllabusDetail(ResultSet resultSet) throws SQLException {
        String syllabusId = resultSet.getString("syllabus_id");
        String syllabusName = resultSet.getString("syllabus_name");
        String englishName = resultSet.getString("english_name");
        Integer dividendGrade = resultSet.getInt("dividend_grade");
        Integer year = resultSet.getInt("year");
        String classroom = resultSet.getString("class");
        String semester = resultSet.getString("semester");
        String week = resultSet.getString("week");
        String time = resultSet.getString("time");
        Integer unit = resultSet.getInt("unit");
        Integer capacity = resultSet.getInt("capacity");
        String objectiveSummary = resultSet.getString("objective_summary");
        String goal = resultSet.getString("goal");
        String textbook = resultSet.getString("textbook");
        String referenceBook = resultSet.getString("reference_book");
        String educationalObject = resultSet.getString("educational_object");
        String dp = resultSet.getString("dp");
        String selfStudy = resultSet.getString("self_study");
        String freeText = resultSet.getString("free_text");
        String mailAddress = resultSet.getString("mail_address");
        String officeHour = resultSet.getString("office_hour");
        String classification = resultSet.getString("classification");
        String guidance = resultSet.getString("guidance");
        String advice = resultSet.getString("advice");
        return new SyllabusDetail(
                syllabusId,
                syllabusName,
                englishName,
                dividendGrade,
                year,
                classroom,
                semester,
                week,
                time,
                unit,
                capacity,
                objectiveSummary,
                goal,
                textbook,
                referenceBook,
                educationalObject,
                dp,
                selfStudy,
                freeText,
                mailAddress,
                officeHour,
                classification,
                guidance,
                advice
        );
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
