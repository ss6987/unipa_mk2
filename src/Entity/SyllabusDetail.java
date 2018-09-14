package Entity;

import DAO.SyllabusContentsDAO;
import etc.ReplaceString;
import etc.StringCheck;

import java.sql.SQLException;
import java.util.List;

public class SyllabusDetail extends Syllabus {
    private String objectiveSummary;
    private String goal;
    private String textbook;
    private String referenceBook;
    private String educationalObject;
    private String dp;
    private String selfStudy;
    private String freeText;
    private String mailAddress;
    private String officeHour;
    private String classification;
    private String guidance;
    private String advice;
    private List<SyllabusContents> syllabusContents;


    public SyllabusDetail(String syllabusId, String syllabusName, String englishName, Integer dividendGrade, Integer year, String classRoom, String semester, String week, String time, Integer unit, Integer capacity, String objectiveSummary, String goal, String textbook, String referenceBook, String educationalObject, String dp, String selfStudy, String freeText, String mailAddress, String officeHour, String classification, String guidance, String advice) throws SQLException {
        super(syllabusId, syllabusName, englishName, dividendGrade, year, classRoom, semester, week, time, unit, capacity);
        setObjectiveSummary(objectiveSummary);
        setGoal(goal);
        setTextbook(textbook);
        setReferenceBook(referenceBook);
        setEducationalObject(educationalObject);
        setDp(dp);
        setSelfStudy(selfStudy);
        setFreeText(freeText);
        setMailAddress(mailAddress);
        setOfficeHour(officeHour);
        setClassification(classification);
        setGuidance(guidance);
        setAdvice(advice);
        SyllabusContentsDAO syllabusContentsDAO = new SyllabusContentsDAO();
        setSyllabusContents(syllabusContentsDAO.findBySyllabus(convertSyllabusDetailToSyllabus()));
    }

    public String setObjectiveSummary(String objectiveSummary) {
        objectiveSummary = new ReplaceString().replace(objectiveSummary);
        if (new StringCheck().checkFreeText(objectiveSummary)) {
            this.objectiveSummary = objectiveSummary;
            return "";
        }
        return "目的概要に使用できない文字が存在します。";
    }

    public String setGoal(String goal) {
        goal = new ReplaceString().replace(goal);
        if (new StringCheck().checkFreeText(goal)) {
            this.goal = goal;
            return "";
        }
        return "達成目標に使用できない文字が存在します。";

    }

    public String setTextbook(String textbook) {
        textbook = new ReplaceString().replace(textbook);
        if (new StringCheck().checkFreeText(textbook)) {
            this.textbook = textbook;
            return "";
        }
        return "教科書に使用できない文字が存在します。";

    }

    public String setReferenceBook(String referenceBook) {
        referenceBook = new ReplaceString().replace(referenceBook);
        if (new StringCheck().checkFreeText(referenceBook)) {
            this.referenceBook = referenceBook;
            return "";
        }
        return "参考書に使用できない文字が存在します。";

    }

    public String setEducationalObject(String educationalObject) {
        educationalObject = new ReplaceString().replace(educationalObject);
        if (new StringCheck().checkFreeText(educationalObject)) {
            this.educationalObject = educationalObject;
            return "";
        }
        return "学習・教育目標との対応に使用できない文字が存在します。";

    }

    public String setDp(String dp) {
        dp = new ReplaceString().replace(dp);
        if (new StringCheck().checkFreeText(dp)) {
            this.dp = dp;
            return "";
        }
        return "DPとの対応に使用できない文字が存在します。";

    }

    public String setSelfStudy(String selfStudy) {
        selfStudy = new ReplaceString().replace(selfStudy);
        if (new StringCheck().checkFreeText(selfStudy)) {
            this.selfStudy = selfStudy;
            return "";
        }
        return "事前・事後学習に使用できない文字が存在します。";

    }

    public String setFreeText(String freeText) {
        freeText = new ReplaceString().replace(freeText);
        if (new StringCheck().checkFreeText(freeText)) {
            this.freeText = freeText;
            return "";
        }
        return "自由記載欄に使用できない文字が存在します。";

    }

    public String setMailAddress(String mailAddress) {
        mailAddress = new ReplaceString().replace(mailAddress);
        if (new StringCheck().checkFreeText(mailAddress)) {
            this.mailAddress = mailAddress;
            return "";
        }
        return "メールアドレスに使用できない文字が存在します。";

    }

    public String setOfficeHour(String officeHour) {
        officeHour = new ReplaceString().replace(officeHour);
        if (new StringCheck().checkFreeText(officeHour)) {
            this.officeHour = officeHour;
            return "";
        }
        return "オフィスアワーに使用できない文字が存在します。";

    }

    public String setClassification(String classification) {
        classification = new ReplaceString().replace(classification);
        if (new StringCheck().checkFreeText(classification)) {
            this.classification = classification;
            return "";
        }
        return "クラス分け情報に使用できない文字が存在します。";

    }

    public String setGuidance(String guidance) {
        guidance = new ReplaceString().replace(guidance);
        if (new StringCheck().checkFreeText(guidance)) {
            this.guidance = guidance;
            return "";
        }
        return "ガイダンス情報に使用できない文字が存在します。";

    }

    public String setAdvice(String advice) {
        advice = new ReplaceString().replace(advice);
        if (new StringCheck().checkFreeText(advice)) {
            this.advice = advice;
            return "";
        }
        return "学習上の助言に使用できない文字が存在します。";

    }

    public void setSyllabusContents(List<SyllabusContents> list) {
        this.syllabusContents = list;
    }

    public void addSyllabusContents(SyllabusContents syllabusContents) {
        this.syllabusContents.add(syllabusContents);
    }

    public String getObjectiveSummary() {
        return objectiveSummary;
    }

    public String getGoal() {
        return goal;
    }

    public String getTextbook() {
        return textbook;
    }

    public String getReferenceBook() {
        return referenceBook;
    }

    public String getEducationalObject() {
        return educationalObject;
    }

    public String getDp() {
        return dp;
    }

    public String getSelfStudy() {
        return selfStudy;
    }

    public String getFreeText() {
        return freeText;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public String getOfficeHour() {
        return officeHour;
    }

    public String getClassification() {
        return classification;
    }

    public String getGuidance() {
        return guidance;
    }

    public String getAdvice() {
        return advice;
    }

    public List<SyllabusContents> getSyllabusContents() {
        return syllabusContents;
    }

    public Syllabus convertSyllabusDetailToSyllabus() {
        return new Syllabus(getSyllabusId(), getSyllabusName(), getEnglishName(), getDividendGrade(), getYear(), getClassRoom(), getSemester(), getWeek(), getTime(), getUnit(), getCapacity());
    }
}
