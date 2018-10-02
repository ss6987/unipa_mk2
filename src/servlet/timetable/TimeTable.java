package servlet.timetable;

import Entity.Syllabus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TimeTable {
    private List<String> weekName = Arrays.asList("月", "火", "水", "木", "金", "土", "日");
    private List<Week> weeks = new ArrayList<>();

    public TimeTable() {
        for (int i = 0; i < weekName.size(); i++) {
            weeks.add(new Week());
        }
    }

    public void addSyllabus(Syllabus syllabus) {
        String[] week = syllabus.getWeek().split("&#44;");
        String[] time = syllabus.getTime().split("&#44;");
        for (int i = 0; i < week.length; i++) {
            this.weeks.get(this.weekName.indexOf(week[i])).addSyllabus(syllabus, time[i]);
        }
    }

    public void addSyllabusList(List<Syllabus> syllabusList) {
        for (Syllabus syllabus : syllabusList) {
            String[] week = syllabus.getWeek().split("&#44;");
            String[] time = syllabus.getTime().split("&#44;");
            for (int i = 0; i < week.length; i++) {
                this.weeks.get(this.weekName.indexOf(week[i])).addSyllabus(syllabus, time[i]);
            }
        }
    }

    public List<Syllabus> getSyllabusList(Integer week,Integer time){
        return this.weeks.get(week - 1).getSyllabusList(time);
    }

    public void deleteSyllabus(String syllabusId){
        for(Week week:weeks){
            week.deleteSyllabus(syllabusId);
        }
    }

    public boolean checkOverlap(){
        for(Week week:weeks){
            if(!week.checkOverlap()){
                return false;
            }
        }
        return true;
    }
}
