package etc;

import DAO.*;
import Entity.*;


import java.sql.SQLException;
import java.util.List;

public class ModelManager {
    private UserDAO userDAO = new UserDAO();
    private SyllabusDAO syllabusDAO = new SyllabusDAO();
    private CourseDAO courseDAO = new CourseDAO();
    private RegistrationPeriodDAO registrationPeriodDAO = new RegistrationPeriodDAO();
    private TeacherInChargeDAO teacherInChargeDAO = new TeacherInChargeDAO();


    public ModelManager() {
        System.out.println("OK");
    }

    public User login(String userId, String password) {
        try {
            return userDAO.login(userId, password);
        } catch (SQLException e) {
            return null;
        }
    }

    public boolean userRegistration(User user, String password) {
        if (userDAO.insert(user)) {
            userDAO.updatePassword(user, password);
            return true;
        } else {
            return false;
        }
    }

    public boolean userDelete(User user) {
        if (userDAO.delete(user)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean userUpdate(User user) {
        return userDAO.update(user);
    }

    public boolean userUpdatePassword(User user, String password) {
        return userDAO.updatePassword(user, password);
    }

    public User userFindById(String userId) {
        try {
            return userDAO.findById(userId);
        } catch (SQLException e) {
            return null;
        }
    }

    public List<User> userSearch(User user,Integer page) {
        try {
            return userDAO.select(user,page);
        } catch (SQLException e) {
            return null;
        }
    }

    public boolean userExist(String userId) {
        User user = null;
        try {
            user = userDAO.findById(userId);
        } catch (SQLException e) {
            return false;
        }
        if (user != null) {
            return true;
        } else {
            return false;
        }
    }

    public Integer userCount(){
        try{
            return userDAO.getCount();
        }catch (SQLException e){
            return 0;
        }
    }

    public boolean syllabusRegistration(SyllabusDetail syllabusDetail) {
        return syllabusDAO.insert(syllabusDetail);
    }

    public boolean syllabusUpdate(SyllabusDetail syllabusDetail) {
        try {
            return syllabusDAO.update(syllabusDetail);
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean syllabusDelete(Syllabus syllabus) {
        try {
            return syllabusDAO.delete(syllabus.convertSyllabusToSyllabusDetail());
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean syllabusDelete(SyllabusDetail syllabus) {
        for(SyllabusContents syllabusContents:syllabus.getSyllabusContents()){
            SyllabusContentsDAO syllabusContentsDAO = new SyllabusContentsDAO();
            syllabusContentsDAO.delete(syllabusContents);
        }
        TeacherInChargeDAO teacherInChargeDAO = new TeacherInChargeDAO();
        teacherInChargeDAO.deleteBySyllabus(syllabus.convertSyllabusDetailToSyllabus());
        return syllabusDAO.delete(syllabus);
    }

    public Syllabus syllabusFindById(String syllabusId) {
        try {
            return syllabusDAO.findBySyllabusId(syllabusId);
        } catch (SQLException e) {
            return null;
        }
    }

    public SyllabusDetail syllabusDetailFindById(String syllabusId){
        try {
            return syllabusDAO.findBySyllabusDetailId(syllabusId);
        } catch (SQLException e) {
            return null;
        }
    }

    public List<Syllabus> syllabusSearch(Syllabus syllabus,Integer page) {
        try {
            return syllabusDAO.select(syllabus,page);
        } catch (SQLException e) {
            return null;
        }
    }

    public boolean syllabusExsist(String syllabusId) {
        Syllabus syllabus;
        try {
            syllabus = syllabusDAO.findBySyllabusId(syllabusId);
        } catch (SQLException e) {
            syllabus = null;
        }
        if (syllabus == null) {
            return true;
        } else {
            return true;
        }
    }

    public Integer syllabusCount() {
        try {
            return syllabusDAO.getCount();
        } catch (SQLException e) {
            return 0;
        }
    }

    public boolean teacherInChargeRegistration(String syllabusId,String userId,Integer mainTeacher){
        Syllabus syllabus = new Syllabus();
        User user = new User();
        syllabus.setSyllabusId(syllabusId);
        user.setUserId(userId);
        return teacherInChargeDAO.insert(user,syllabus,mainTeacher);
    }

    public boolean courseRegistration(Student student, List<String> syllabusIdList) {
        boolean flag = true;
        for (int i = 0; i < syllabusIdList.size(); i++) {
            try {
                Syllabus tmpSyllabus = syllabusDAO.findBySyllabusId(syllabusIdList.get(i));
                Course course = new Course(student, tmpSyllabus, -1);
                courseDAO.insert(course);
            } catch (SQLException e) {
                flag = false;
            }
        }
        return flag;
    }

    public boolean courseUpdate(Student student, List<String> syllabusIdList, List<String> achievementList) {
        return true;
    }

    public boolean courseDelete(String syllabusId, List<String> studentList) throws SQLException {
        Course course = new Course();
        course.setSyllabusId(syllabusId);
        for (int i = 0; i < studentList.size(); i++) {
            course.setUserId(studentList.get(i));
            try {
                courseDAO.delete(course);
            } catch (SQLException e) {
                return false;
            }
        }
        return true;
    }

    public String getRegistrationPeriod(){
        RegistrationPeriod registrationPeriod = null;
        try {
            registrationPeriod = registrationPeriodDAO.select();
            return registrationPeriod.getPeriod();
        } catch (SQLException e) {
            return "";
        }
    }
}
