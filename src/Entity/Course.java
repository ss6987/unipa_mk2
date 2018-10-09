package Entity;

import DAO.SyllabusDAO;
import DAO.TeacherInChargeDAO;
import DAO.UserDAO;
import etc.ReplaceString;
import etc.StringCheck;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Course {
    private String userId;
    private String syllabusId;
    private Integer achievement;
    private String mainTeacherName;
    private Integer year;

    public Course() {
        this.userId = "";
        this.syllabusId = "";
        this.achievement = -1;
        this.mainTeacherName = "";
        this.year = -1;
    }

    public Course(ResultSet resultSet) throws SQLException {
        this.userId = resultSet.getString("user_id");
        this.syllabusId = resultSet.getString("syllabus_id");
        this.achievement = resultSet.getInt("achievement");
        this.mainTeacherName = resultSet.getString("main_teacher_name");
        this.year = resultSet.getInt("year");
    }

    public Course(String studentId,String syllabusId,Integer achievement) throws SQLException {
        setUserId(studentId);
        setSyllabusId(syllabusId);
        setAchievement(achievement);
        this.mainTeacherName = "";
        this.year = -1;
    }

    public Course(Student student, Syllabus syllabus, Integer achievement) throws SQLException {
        setUserId(student.getUserId());
        setSyllabusId(syllabus.getSyllabusId());
        setAchievement(achievement);
        TeacherInChargeDAO teacherInChargeDAO = new TeacherInChargeDAO();
        setMainTeacherName(teacherInChargeDAO.findMainTeacherBySyllabus(syllabus).getName());
        setYear(syllabus.getYear());
    }

    public String setUserId(String userId) throws SQLException {
        userId = new ReplaceString().replace(userId);
        if (new StringCheck().checkNotSymbols(userId)) {
            if (new UserDAO().findById(userId).getUserId() != "") {
                this.userId = userId;
                return "";
            }
            return "ユーザーIDが存在しません。";
        }
        return "ユーザーIDに使用できない文字が存在します。";
    }

    public String setSyllabusId(String syllabusId) throws SQLException {
        syllabusId = new ReplaceString().replace(syllabusId);
        if (new StringCheck().checkNotSymbols(syllabusId)) {
            if (new SyllabusDAO().findBySyllabusId(syllabusId).getSyllabusId() != "") {
                this.syllabusId = syllabusId;
                return "";
            }
            return "シラバスIDが存在しません。";
        }
        return "シラバスIDに使用できない文字が存在します。";
    }

    public String setAchievement(Integer achievement) {
        if (achievement >= -3 && achievement <= 4) {
            this.achievement = achievement;
            return "";
        }
        return "成績に使用できない値が入力されました。";
    }

    public String setMainTeacherName(String mainTeacherName) {
        mainTeacherName = new ReplaceString().replace(mainTeacherName);
        if (new StringCheck().checkNotSymbols(mainTeacherName)) {
            this.mainTeacherName = mainTeacherName;
            return "";
        }
        return "主担当教員に使用できない文字が存在します。";
    }

    public String setYear(Integer year) {
        if (year >= -1) {
            this.year = year;
            return "";
        }
        return "年度に使用できない値が入力されました。";
    }

    public String getUserId() {
        return userId;
    }

    public String getSyllabusId() {
        return syllabusId;
    }

    public Integer getAchievement() {
        return achievement;
    }

    public String getMainTeacherName() {
        return mainTeacherName;
    }

    public Integer getYear() {
        return year;
    }

    public String getAchievementString() {
        switch (this.achievement) {
            case -3:
                return "承認前";
            case -2:
                return "履修中";
            case 0:
                return "D";
            case 1:
                return "C";
            case 2:
                return "B";
            case 3:
                return "A";
            case 4:
                return "S";
            default:
                return "--";
        }
    }
}
