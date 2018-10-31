package servlet.timetable;

import Entity.Syllabus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class TimeTable {
    private List<String> weekName = Arrays.asList("月", "火", "水", "木", "金", "土", "日");
    private List<Week> weeks = new ArrayList<>();
    private Integer totalUnit = 0;
    private String semester = "前期";
    private LocalDateTime now = LocalDateTime.now();

    public TimeTable() {
        for (int i = 0; i < weekName.size(); i++) {
            weeks.add(new Week());
        }
    }

    public TimeTable(String semester, LocalDateTime now) {
        for (int i = 0; i < weekName.size(); i++) {
            weeks.add(new Week());
        }
        this.semester = semester;
        this.now = now;
    }

    public void addSyllabus(Syllabus syllabus) {
        String[] week = syllabus.getWeek().split("&#44;");
        String[] time = syllabus.getTime().split("&#44;");
        String semester = syllabus.getSemester();

        for (int i = 0; i < week.length; i++) {
            if (semester.equals(this.semester) && syllabus.getYear().equals(now.getYear())) {
                this.weeks.get(this.weekName.indexOf(week[i])).addSyllabus(syllabus, time[i]);
                this.totalUnit += syllabus.getUnit();
            }
        }
    }

    public void addSyllabusList(List<Syllabus> syllabusList) {
        for (Syllabus syllabus : syllabusList) {
            this.addSyllabus(syllabus);
        }
    }

    public List<Syllabus> getSyllabusList(Integer week, Integer time) {
        return this.weeks.get(week - 1).getSyllabusList(time);
    }

    public List<String> getAllSyllabusList() {
        List<Syllabus> syllabusList = new ArrayList<>();
        for (Week week : weeks) {
            syllabusList.addAll(week.getAllSyllabusList());
        }
        List<String> stringList = new ArrayList<>();
        for (Syllabus syllabus : syllabusList) {
            stringList.add(syllabus.getSyllabusId());
        }
        return new ArrayList<>(new HashSet<>(stringList));
    }


    public void deleteSyllabus(String syllabusId) {
        for (Week week : weeks) {
            week.deleteSyllabus(syllabusId);
        }
    }

    public boolean checkOverlap() {
        for (Week week : weeks) {
            if (!week.checkOverlap()) {
                return false;
            }
        }
        return true;
    }

    public Integer getTotalUnit() {
        return totalUnit;
    }
}
