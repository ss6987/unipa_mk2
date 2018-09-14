package Entity;

import DAO.SyllabusDAO;
import etc.ReplaceString;
import etc.StringCheck;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Syllabus {
    private String syllabusId;
    private String syllabusName;
    private String englishName;
    private Integer dividendGrade;
    private Integer year;
    private String classRoom;
    private String semester;
    private String week;
    private String time;
    private Integer unit;
    private Integer capacity;

    public Syllabus() {
        this.syllabusId = "";
        this.syllabusName = "";
        this.englishName = "";
        this.dividendGrade = -1;
        this.year = -1;
        this.classRoom = "";
        this.semester = "";
        this.week = "";
        this.time = "";
        this.unit = -1;
        this.capacity = -1;
    }

    public Syllabus(ResultSet resultSet) throws SQLException {
        this.syllabusId = resultSet.getString("syllabus_id");
        this.syllabusName = resultSet.getString("syllabus_name");
        this.englishName = resultSet.getString("english_name");
        this.dividendGrade = resultSet.getInt("dividend_grade");
        this.year = resultSet.getInt("year");
        this.classRoom = resultSet.getString("class");
        this.semester = resultSet.getString("semester");
        this.week = resultSet.getString("week");
        this.time = resultSet.getString("time");
        this.unit = resultSet.getInt("unit");
        this.capacity = resultSet.getInt("capacity");
    }

    public Syllabus(String syllabusId, String syllabusName, String englishName, Integer dividendGrade, Integer year, String classRoom, String semester, String week, String time, Integer unit, Integer capacity) {
        setSyllabusId(syllabusId);
        setSyllabusName(syllabusName);
        setEnglishName(englishName);
        setDividendGrade(dividendGrade);
        setYear(year);
        setClassRoom(classRoom);
        setSemester(semester);
        setWeek(week);
        setTime(time);
        setUnit(unit);
        setCapacity(capacity);
    }

    public String getSyllabusId() {
        return syllabusId;
    }

    public String getSyllabusName() {
        return syllabusName;
    }

    public String getEnglishName() {
        return englishName;
    }

    public Integer getDividendGrade() {
        return dividendGrade;
    }

    public Integer getYear() {
        return year;
    }

    public String getClassRoom() {
        return classRoom;
    }

    public String getSemester() {
        return semester;
    }

    public String getWeek() {
        return week;
    }

    public String getTime() {
        return time;
    }

    public Integer getUnit() {
        return unit;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public String setSyllabusId(String syllabusId) {
        syllabusId = new ReplaceString().replace(syllabusId);
        if (new StringCheck().checkW(syllabusId)) {
            this.syllabusId = syllabusId;
            return "";
        }
        return "シラバスIDに使用できない文字が存在します。";
    }

    public String setSyllabusName(String syllabusName) {
        syllabusName = new ReplaceString().replace(syllabusName);
        if (new StringCheck().checkNotSymbols(syllabusName)) {
            this.syllabusName = syllabusName;
            return "";
        }
        return "授業名に使用できない文字が存在します。";
    }

    public String setEnglishName(String englishName) {
        englishName = new ReplaceString().replace(englishName);
        if (new StringCheck().checkW(englishName)) {
            this.englishName = englishName;
            return "";
        }
        return "英語名に使用できない文字が存在します。";
    }

    public String setDividendGrade(Integer dividendGrade) {
        if (dividendGrade >= -1 && dividendGrade <= 4) {
            this.dividendGrade = dividendGrade;
            return "";
        }
        return "配当学年に使用できない値が入力されています。";
    }

    public String setYear(Integer year) {
        if (year >= -1) {
            this.year = year;
            return "";
        }
        return "年度に使用できない値が入力されています。";
    }

    public String setClassRoom(String classRoom) {
        classRoom = new ReplaceString().replace(classRoom);
        if (new StringCheck().checkNotSymbols(classRoom)) {
            this.classRoom = classRoom;
            return "";
        }
        return "教室に使用できない文字が存在します。";
    }

    public String setSemester(String semester) {
        semester = new ReplaceString().replace(semester);
        if (new StringCheck().checkNotSymbols(semester)) {
            this.semester = semester;
            return "";
        }
        return "学期に使用できない文字が存在します。";
    }

    public String setWeek(String week) {
        week = new ReplaceString().replace(week);
        if (new StringCheck().checkNotSymbols(week)) {
            this.week = week;
            return "";
        }
        return "曜日に使用できない文字が存在します。";
    }

    public String setTime(String time) {
        time = new ReplaceString().replace(time);
        if (new StringCheck().checkNotSymbols(time)) {
            this.time = time;
            return "";
        }
        return "時限に使用できない文字が存在します。";
    }

    public String setUnit(Integer unit) {
        if (unit >= -1 && unit <= 10) {
            this.unit = unit;
            return "";
        }
        return "単位数に使用できない値が入力されています。";
    }

    public String setCapacity(Integer capacity) {
        if (capacity >= -1 && capacity <= 500) {
            this.capacity = capacity;
            return "";
        }
        return "定員に使用できない値が入力されています。";
    }

    public SyllabusDetail convertSyllabusToSyllabusDetail() throws SQLException {
        SyllabusDAO syllabusDAO = new SyllabusDAO();
        try {
            return syllabusDAO.findBySyllabusDetailId(this.getSyllabusId());
        } catch (SQLException e) {
            return new SyllabusDetail(syllabusId, syllabusName, englishName, dividendGrade, year, classRoom, semester, week, time, unit, capacity, "", "", "", "", "", "", "", "", "", "", "", "", "");
        }
    }
}
