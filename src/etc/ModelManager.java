package etc;

import DAO.CourseDAO;
import DAO.RegistrationPeriodDAO;
import DAO.SyllabusDAO;
import DAO.UserDAO;
import Entity.*;


import java.sql.SQLException;
import java.util.List;

public class ModelManager {

    public ModelManager() {
        System.out.println("OK");
    }

    public User login(String userId, String password) {
        UserDAO userDAO = new UserDAO();
        try {
            return userDAO.login(userId, password);
        } catch (SQLException e) {
            return null;
        }
    }

    public boolean userRegistration(User user, String password) {
        UserDAO userDAO = new UserDAO();
        if (userDAO.insert(user)) {
            userDAO.updatePassword(user, password);
            return true;
        } else {
            return false;
        }
    }

    public boolean userDelete(User user) {
        UserDAO userDAO = new UserDAO();
        if (userDAO.delete(user)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean userUpdate(User user) {
        UserDAO userDAO = new UserDAO();
        return userDAO.update(user);
    }

    public boolean userUpdatePassword(User user, String password) {
        UserDAO userDAO = new UserDAO();
        return userDAO.updatePassword(user, password);
    }

    public User userFindById(String userId) {
        UserDAO userDAO = new UserDAO();
        try {
            return userDAO.findById(userId);
        } catch (SQLException e) {
            return null;
        }
    }

    public List<User> userSearch(User user) {
        UserDAO userDAO = new UserDAO();
        try {
            return userDAO.select(user);
        } catch (SQLException e) {
            return null;
        }
    }

    public boolean userExist(String userId) {
        UserDAO userDAO = new UserDAO();
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

    public boolean syllabusRegistration(SyllabusDetail syllabusDetail) {
        SyllabusDAO syllabusDAO = new SyllabusDAO();
        return syllabusDAO.insert(syllabusDetail);
    }

    public boolean syllabusUpdate(SyllabusDetail syllabusDetail) {
        SyllabusDAO syllabusDAO = new SyllabusDAO();
        return syllabusDAO.update(syllabusDetail);
    }

    public boolean syllabusDelete(Syllabus syllabus) {
        SyllabusDAO syllabusDAO = new SyllabusDAO();
        try {
            return syllabusDAO.delete(syllabus.convertSyllabusToSyllabusDetail());
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean syllabusDelete(SyllabusDetail syllabus) {
        SyllabusDAO syllabusDAO = new SyllabusDAO();
        return syllabusDAO.delete(syllabus);
    }

    public Syllabus syllabusFindById(String syllabusId) {
        SyllabusDAO syllabusDAO = new SyllabusDAO();
        try {
            return syllabusDAO.findBySyllabusId(syllabusId);
        } catch (SQLException e) {
            return null;
        }
    }

    public List<Syllabus> syllabusSearch(Syllabus syllabus) {
        SyllabusDAO syllabusDAO = new SyllabusDAO();
        try {
            return syllabusDAO.select(syllabus);
        } catch (SQLException e) {
            return null;
        }
    }

    public boolean syllabusExsist(String syllabusId) {
        SyllabusDAO syllabusDAO = new SyllabusDAO();
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

    public boolean courseRegistration(Student student, List<String> syllabusIdList) {
        CourseDAO courseDAO = new CourseDAO();
        SyllabusDAO syllabusDAO = new SyllabusDAO();
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
        CourseDAO courseDAO = new CourseDAO();
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
        RegistrationPeriodDAO registrationPeriodDAO = new RegistrationPeriodDAO();
        RegistrationPeriod registrationPeriod = null;
        try {
            registrationPeriod = registrationPeriodDAO.select();
            return registrationPeriod.getPeriod();
        } catch (SQLException e) {
            return "";
        }
    }
}
