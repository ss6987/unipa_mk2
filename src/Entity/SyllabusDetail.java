package Entity;

import DAO.SyllabusContentsDAO;
import com.sun.deploy.net.HttpRequest;
import etc.ReplaceString;
import etc.StringCheck;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
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

    public SyllabusDetail() {
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
        this.objectiveSummary = "";
        this.goal = "";
        this.textbook = "";
        this.referenceBook = "";
        this.educationalObject = "";
        this.dp = "";
        this.selfStudy = "";
        this.freeText = "";
        this.mailAddress = "";
        this.officeHour = "";
        this.classification = "";
        this.guidance = "";
        this.advice = "";
        this.syllabusContents = new ArrayList<>();
    }

    public SyllabusDetail(HttpServletRequest request, String syllabusId) throws UnsupportedEncodingException {
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
        String objectiveSummary = replaceString.repairRequest(request.getParameter("objective_summary"));
        String goal = replaceString.repairRequest(request.getParameter("goal"));
        String textbook = replaceString.repairRequest(request.getParameter("textbook"));
        String referenceBook = replaceString.repairRequest(request.getParameter("reference_book"));
        String educationalObject = replaceString.repairRequest(request.getParameter("educational_object"));
        String dp = replaceString.repairRequest(request.getParameter("dp"));
        String selfStudy = replaceString.repairRequest(request.getParameter("self_study"));
        String freeText = replaceString.repairRequest(request.getParameter("free_text"));
        String mailAddress = replaceString.repairRequest(request.getParameter("mail_address"));
        String officeHour = replaceString.repairRequest(request.getParameter("office_hour"));
        String classification = replaceString.repairRequest(request.getParameter("classification"));
        String guidance = replaceString.repairRequest(request.getParameter("guidance"));
        String advice = replaceString.repairRequest(request.getParameter("advice"));
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
        errorString += this.setObjectiveSummary(objectiveSummary);
        errorString += this.setGoal(goal);
        errorString += this.setTextbook(textbook);
        errorString += this.setReferenceBook(referenceBook);
        errorString += this.setEducationalObject(educationalObject);
        errorString += this.setDp(dp);
        errorString += this.setSelfStudy(selfStudy);
        errorString += this.setFreeText(freeText);
        errorString += this.setMailAddress(mailAddress);
        errorString += this.setOfficeHour(officeHour);
        errorString += this.setClassification(classification);
        errorString += this.setGuidance(guidance);
        errorString += this.setAdvice(advice);
        errorString = errorString.replace("。", "。<br/>");
        request.setAttribute("errorString", errorString);
    }

    public SyllabusDetail(String syllabusId, String syllabusName, String englishName, Integer dividendGrade, Integer year, String classRoom, String semester, String week, String time, Integer unit, Integer capacity, String mainTeacher, String objectiveSummary, String goal, String textbook, String referenceBook, String educationalObject, String dp, String selfStudy, String freeText, String mailAddress, String officeHour, String classification, String guidance, String advice) throws SQLException {
        super(syllabusId, syllabusName, englishName, dividendGrade, year, classRoom, semester, week, time, unit, capacity, mainTeacher);
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

    public void setSyllabusContents(HttpServletRequest request) {
        syllabusContents = new ArrayList<>();
        for(int i = 1;i < 16;i++) {
            try {
                String syllabus_contents = new ReplaceString().repairRequest(request.getParameter("syllabus_contents" + i));
                SyllabusContents contents = new SyllabusContents(this.syllabusId, i, syllabus_contents);
                this.addSyllabusContents(contents);
            } catch (SQLException e) {
                ;
            } catch (NullPointerException e) {
                ;
            } catch (UnsupportedEncodingException e) {
                ;
            }
        }
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
        return new Syllabus(getSyllabusId(), getSyllabusName(), getEnglishName(), getDividendGrade(), getYear(), getClassRoom(), getSemester(), getWeek(), getTime(), getUnit(), getCapacity(), getMainTeacher());
    }
}
