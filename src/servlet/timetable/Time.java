package servlet.timetable;

import Entity.Syllabus;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Time {
    private List<Syllabus> syllabusLists = new ArrayList<>();
    private List<String> syllabusIdList = new ArrayList<>();

    public Time() {
    }

    public void addSyllabus(Syllabus syllabus) {
        if(syllabusIdList.indexOf(syllabus.getSyllabusId()) == -1){
            syllabusLists.add(syllabus);
            syllabusIdList.add(syllabus.getSyllabusId());
        }
    }

    public List<Syllabus> getSyllabusLists() {
        return syllabusLists;
    }

    public void deleteSyllabus(String syllabusId){
        Integer index = syllabusIdList.indexOf(syllabusId);
        if(index != -1){
            this.syllabusLists.remove(index.intValue());
            this.syllabusIdList.remove(index.intValue());
        }
    }

    public boolean checkOverlap(){
        if(syllabusLists.size() == 1 || syllabusLists.size() == 0){
            return true;
        }
        return false;
    }
}
