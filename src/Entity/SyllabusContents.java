package Entity;

import DAO.SyllabusDAO;
import etc.ReplaceString;
import etc.StringCheck;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SyllabusContents {
    private String syllabusId;
    private Integer classNumber;
    private String courseContent;

    public SyllabusContents(ResultSet resultSet) throws SQLException {
        this.syllabusId = resultSet.getString("subject_id");
        this.classNumber = resultSet.getInt("class_number");
        this.courseContent = resultSet.getString("course_content");
    }

    public SyllabusContents(String syllabusId, Integer classNumber, String courseContent) throws SQLException {
        setSyllabusId(syllabusId);
        setClassNumber(classNumber);
        setCourseContent(courseContent);
    }

    public String setSyllabusId(String syllabusId) throws SQLException {
        syllabusId = new ReplaceString().replace(syllabusId);
        if (new StringCheck().checkW(syllabusId)) {
            SyllabusDAO syllabusDAO = new SyllabusDAO();
            Syllabus syllabus = syllabusDAO.findBySyllabusId(syllabusId);
            if (syllabus != null) {
                this.syllabusId = syllabusId;
                return "";
            }else{
                this.syllabusId = syllabusId;
                return "シラバスIDが見つかりませんでした。";
            }
        }
        return "シラバスIDに使用できない文字が存在します。";
    }

    public String setClassNumber(Integer classNumber) {
        if(classNumber >= -1){
            this.classNumber = classNumber;
            return "";
        }
        return "授業回数に入力できない値が入っています。";
    }

    public String setCourseContent(String courseContent) {
        courseContent = new ReplaceString().replace(courseContent);
        if (new StringCheck().checkFreeText(courseContent)) {
            this.courseContent = courseContent;
            return "";
        }
        return "授業内容に入力できない値が存在します。";
    }

    public String getSyllabusId() {
        return syllabusId;
    }

    public Integer getClassNumber() {
        return classNumber;
    }

    public String getCourseContent() {
        return courseContent;
    }
}
