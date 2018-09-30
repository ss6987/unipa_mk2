package servlet.timetable;

import Entity.Syllabus;

import java.util.ArrayList;
import java.util.List;

public class TimeTable {
    private String[] weekName = {"月","火","水","木","金","土","日"};
    private Week[] weeks = new Week[7];

    public TimeTable(){
        for(int i = 0;i < weeks.length;i++){
            weeks[i] = new Week();
        }
    }

    public void addSyllabus(Syllabus syllabus){
        String[] week = syllabus.getWeek().split("&#44;");
        String[] time = syllabus.getTime().split("&#44;");
        String[] weekTime = new String[week.length];
        for(int i = 0;i<week.length;i++){
            weekTime[i] = week[i] + time[i];
            System.out.println(weekTime[i]);
        }
    }
}
