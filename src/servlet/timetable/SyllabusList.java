package servlet.timetable;

import Entity.Syllabus;
import java.util.ArrayList;
import java.util.List;

public class SyllabusList {
    private List<Syllabus> syllabusList = new ArrayList<>();

    public SyllabusList(List<Syllabus> syllabusList) {
        this.syllabusList = syllabusList;
    }

    public void add(Syllabus syllabus){
        this.syllabusList.add(syllabus);
    }
}
