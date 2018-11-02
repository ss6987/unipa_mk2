package Entity;

import DAO.SyllabusDAO;
import DAO.TeacherInChargeDAO;
import etc.ReplaceString;
import etc.StringCheck;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Syllabus {
    protected String syllabusId;
    protected String syllabusName;
    protected String englishName;
    protected Integer dividendGrade;
    protected Integer year;
    protected String classRoom;
    protected String semester;
    protected String week;
    protected String time;
    protected Integer unit;
    protected Integer capacity;
    protected String mainTeacher;
    protected Integer sortKey = 0;
    protected Integer ascDesc = 0;

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
        this.mainTeacher = "";
    }

    public Syllabus(HttpServletRequest request, String syllabusId) throws UnsupportedEncodingException {
        ReplaceString replaceString = new ReplaceString();
        if (syllabusId.equals("")) {
            syllabusId = replaceString.repairRequest(request.getParameter("syllabus_id"));
        }

        String syllabusName = replaceString.repairRequest(request.getParameter("syllabus_name"));
        String englishName = replaceString.repairRequest(request.getParameter("english_name"));
        String classroom = replaceString.repairRequest(request.getParameter("class_room"));
        String semester = replaceString.repairRequest(request.getParameter("semester"));
        String week = replaceString.repairRequest(request.getParameter("week"));
        String time = replaceString.repairRequest(request.getParameter("time"));
        int dividendGrade;
        int year;
        int unit;
        int capacity;

        String errorString = "";
        try {
            dividendGrade = Integer.parseInt(replaceString.repairRequest(request.getParameter("dividend_grade")));
        } catch (java.lang.NumberFormatException e) {
            if (!request.getParameter("dividendGrade").equals("")) {
                errorString += "配当学年に使用できない文字が存在します。";
            }
            dividendGrade = -1;
        }

        try {
            year = Integer.parseInt(replaceString.repairRequest(request.getParameter("year")));
        } catch (java.lang.NumberFormatException e) {
            if (!request.getParameter("year").equals("")) {
                errorString += "開講年度に使用できない文字が存在します。";
            }
            year = -1;
        }

        try {
            unit = Integer.parseInt(replaceString.repairRequest(request.getParameter("unit")));
        } catch (java.lang.NumberFormatException e) {
            if (!request.getParameter("unit").equals("")) {
                errorString += "単位数に使用できない文字が存在します。";
            }
            unit = -1;
        }

        try {
            capacity = Integer.parseInt(replaceString.repairRequest(request.getParameter("capacity")));
        } catch (java.lang.NumberFormatException e) {
            if (!request.getParameter("capacity").equals("")) {
                errorString += "定員に使用できない文字が存在します。";
            }
            capacity = -1;
        }

        errorString += this.setSyllabusId(syllabusId);
        errorString += this.setSyllabusName(syllabusName);
        errorString += this.setEnglishName(englishName);
        errorString += this.setDividendGrade(dividendGrade);
        errorString += this.setYear(year);
        errorString += this.setClassRoom(classroom);
        errorString += this.setSemester(semester);
        errorString += this.setWeek(week);
        errorString += this.setTime(time);
        errorString += this.setUnit(unit);
        errorString += this.setCapacity(capacity);
        errorString = errorString.replace("。", "。<br/>");
        request.setAttribute("errorString", errorString);
    }

    public Syllabus(ResultSet resultSet) throws SQLException {
        this.syllabusId = resultSet.getString("syllabus_id");
        this.syllabusName = resultSet.getString("syllabus_name");
        this.englishName = resultSet.getString("english_name");
        this.dividendGrade = resultSet.getInt("dividend_grade");
        this.year = resultSet.getInt("year");
        this.classRoom = resultSet.getString("class");
        this.semester = resultSet.getString("semester");
        this.week = resultSet.getString("Week");
        this.time = resultSet.getString("Time");
        this.unit = resultSet.getInt("unit");
        this.capacity = resultSet.getInt("capacity");
        try {
            this.mainTeacher = resultSet.getString("name");
        } catch (java.sql.SQLException e) {
            this.mainTeacher = new TeacherInChargeDAO().findMainTeacherBySyllabus(this).getName();
        }
    }

    public Syllabus(String syllabusId, String syllabusName, String englishName, Integer dividendGrade, Integer year, String classRoom, String semester, String week, String time, Integer unit, Integer capacity, String mainTeacher) {
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
        setMainTeacher(mainTeacher);
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

    public String getDividendGradeString() {
        if (this.getDividendGrade() == -1) {
            return "";
        }
        return this.getDividendGrade().toString();
    }

    public String getDividendGradeSelected(Integer value) {
        if (value == this.getDividendGrade()) {
            return "selected";
        }
        return "";
    }

    public String getSemesterSelected(String value) {
        if (this.getSemester().equals(value)) {
            return "selected";
        }
        return "";
    }

    public String getWeekSelected(String value) {
        if (this.getWeek().equals(value)) {
            return "selected";
        }
        return "";
    }

    public String getTimeSelected(String value) {
        if (this.getTime().equals(value)) {
            return "selected";
        }
        return "";
    }

    public String getYearString() {
        if (this.getYear() == -1) {
            return "";
        }
        return this.getYear().toString();
    }

    public String getUnitString() {
        if (this.getUnit() == -1) {
            return "";
        }
        return this.getUnit().toString();
    }

    public String getCapacityString() {
        if (this.getCapacity() == -1) {
            return "";
        }
        return this.getCapacity().toString();
    }

    public String getMainTeacher() {
        return this.mainTeacher;
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
        if (new StringCheck().checkNotSymbols(englishName)) {
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

    public String setWeek(Integer week) {
        String[] weekList = {"月", "火", "水", "木", "金", "土"};
        this.week = weekList[week - 1];
        return "";
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

    public String setMainTeacher(String mainTeacher) {
        mainTeacher = new ReplaceString().replace(mainTeacher);
        if (new StringCheck().checkNotSymbols(mainTeacher)) {
            this.mainTeacher = mainTeacher;
            return "";
        }
        return "主担当名に使用できない文字が存在します。";
    }

    public String getMainTeacherId() throws SQLException {
        if (!this.getSyllabusId().equals("")) {
            TeacherInChargeDAO teacherInChargeDAO = new TeacherInChargeDAO();
            return teacherInChargeDAO.findMainTeacherBySyllabus(this).getUserId();
        } else {
            return "";
        }

    }

    public SyllabusDetail convertSyllabusToSyllabusDetail() {
        SyllabusDAO syllabusDAO = new SyllabusDAO();
        SyllabusDetail syllabusDetail = syllabusDAO.findBySyllabusDetailId(this.getSyllabusId());
        if(syllabusDetail == null){
            return new SyllabusDetail();
        }
        return syllabusDetail;
    }

    public Integer getSortKey() {
        return sortKey;
    }

    public void setSortKey(Integer sortKey) {
        this.sortKey = sortKey;
    }

    public Integer getAscDesc() {
        return ascDesc;
    }

    public void setAscDesc(Integer ascDesc) {
        this.ascDesc = ascDesc;
    }

    public String getWeekTimeString(){
        String[] weekList = this.getWeek().split("&#44;");
        String[] timeList = this.getTime().split("&#44;");
        String string = weekList[0] + "曜" + timeList[0] + "限";

        if(weekList.length == 1){
            return string;
        }

        for(int i =1;i < weekList.length;i++){
            string += ","+ weekList[i] + "曜" + timeList[i] + "限";
        }
        return string;
    }
}
