package servlet.timetable;


import Entity.Syllabus;

import java.util.ArrayList;
import java.util.List;

public class Week {
    private List<Time> times = new ArrayList<>();

    public Week(){
        for(int i = 0;i<8;i++){
            times.add(new Time());
        }
    }

    public void addSyllabus(Syllabus syllabus,String time){
        times.get(Integer.parseInt(time) -1).addSyllabus(syllabus);
    }

    public List<Syllabus> getSyllabusList(Integer time){
        return this.times.get(time - 1).getSyllabusLists();
    }

    public void deleteSyllabus(String syllabusId){
        for(Time time:times){
            time.deleteSyllabus(syllabusId);
        }
    }
}
