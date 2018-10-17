package etc;

import DAO.*;
import Entity.*;


import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ModelManager {
    private UserDAO userDAO = new UserDAO();
    private SyllabusDAO syllabusDAO = new SyllabusDAO();
    private CourseDAO courseDAO = new CourseDAO();
    private RegistrationPeriodDAO registrationPeriodDAO = new RegistrationPeriodDAO();
    private TeacherInChargeDAO teacherInChargeDAO = new TeacherInChargeDAO();
    private FacultyDepartmentDAO facultyDepartmentDAO = new FacultyDepartmentDAO();
    private StudentDAO studentDAO = new StudentDAO();
    private boolean registrationPeriodFlag = false;
    private boolean semester = true;
    private LocalDateTime now;


    public ModelManager() {
        RegistrationPeriod registrationPeriod;
        try {
            registrationPeriod = registrationPeriodDAO.select();
        } catch (SQLException e) {
            registrationPeriod = new RegistrationPeriod("1900-01-01", "1900-01-01");
        }
//        LocalDateTime now = LocalDateTime.now();
        now = LocalDateTime.of(2018, 9, 27, 0, 0, 1);
        LocalDateTime startDate = registrationPeriod.getStartLocalDate();
        LocalDateTime endDate = registrationPeriod.getEndLocalDate();
        if (now.isAfter(startDate) && now.isBefore(endDate)) {
            this.registrationPeriodFlag = true;
        }
        LocalDateTime september = LocalDateTime.of(now.getYear(), 9, 1, 0, 0, 0);
        if (now.isAfter(september)) {
            semester = false;
        }
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
        if (!studentDAO.delete(user)) {
            return false;
        }

        if (!teacherInChargeDAO.deleteByUser(user)) {
            return false;
        }

        if (!userDAO.delete(user)) {
            return false;
        }
        return true;
    }

    public boolean userUpdate(User user) {
        if (!userDAO.update(user)) {
            return false;
        }

        if (!user.getUserClassification().equals("学生")) {
            try {
                studentDAO.delete(studentDAO.findByStudent(user.getUserId()));
            } catch (SQLException e) {
                return false;
            }
        }
        return true;
    }

    public boolean userUpdatePassword(User user, String password) {
        return userDAO.updatePassword(user, password);
    }

    public User userFindById(String userId) {
        return userDAO.findById(userId);
    }

    public List<User> userSearch(User user, Integer page) {
        try {
            return userDAO.select(user, page);
        } catch (SQLException e) {
            return null;
        }
    }

    public boolean userExist(String userId) {
        User user = userDAO.findById(userId);
        if (user.getUserId().equals("")) {
            return true;
        } else {
            return false;
        }
    }

    public Integer userCount() {
        try {
            return userDAO.getCount();
        } catch (SQLException e) {
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
        for (SyllabusContents syllabusContents : syllabus.getSyllabusContents()) {
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

    public SyllabusDetail syllabusDetailFindById(String syllabusId) {
        try {
            return syllabusDAO.findBySyllabusDetailId(syllabusId);
        } catch (SQLException e) {
            return null;
        }
    }

    public List<Syllabus> syllabusSearch(Syllabus syllabus, Integer page) {
        try {
            return syllabusDAO.select(syllabus, page);
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

    public boolean teacherInChargeRegistration(String syllabusId, String userId, Integer mainTeacher) {
        Syllabus syllabus = new Syllabus();
        User user = new User();
        syllabus.setSyllabusId(syllabusId);
        user.setUserId(userId);
        teacherInChargeDAO.deleteBySyllabus(syllabus);
        return teacherInChargeDAO.insert(user, syllabus, mainTeacher);
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

    public boolean courseUpdate(List<String> studentIdList, String syllabusId, Integer achievement) {
        for (String studentId : studentIdList) {
            if (!courseUpdate(studentId, syllabusId, achievement)) {
                return false;
            }
        }
        return true;
    }

    public boolean courseUpdate(String studentId, String syllabusId, Integer achievement) {
        Course course = new Course();
        try {
            course = new Course(studentId, syllabusId, achievement);
            if (!courseDAO.update(course)) {
                return false;
            }
        } catch (SQLException e) {
            return false;
        }
        return false;
    }

    public boolean courseDelete(String syllabusId, List<String> studentList) {
        Course course = new Course();
        try {
            course.setSyllabusId(syllabusId);
            for (String studentId : studentList) {
                course.setUserId(studentId);
                courseDAO.delete(course);
            }
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public boolean courseDelete(String studentId) {
        Course course = new Course();
        try {
            course.setUserId(studentId);
            courseDAO.delete(course);
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public List<Course> courseSelect(String studentId, String syllabusId, Integer achievement) {
        try {
            if (!studentId.equals("")) {
                Student student = studentDAO.findByStudent(studentId);
                return courseDAO.findByStudent(student, achievement);
            } else if (!syllabusId.equals("")) {
                Syllabus syllabus = syllabusDAO.findBySyllabusId(syllabusId);
                return courseDAO.findBySyllabus(syllabus, achievement);
            } else {
                return new ArrayList<>();
            }
        } catch (SQLException e) {
            return new ArrayList<>();
        }
    }

    public List<Syllabus> courseSelectSyllabus(String studentId) {
        try {
            Student student = studentDAO.findByStudent(studentId);
            List<Course> courseList = courseDAO.findByStudent(student, -1);
            List<Syllabus> syllabusList = new ArrayList<>();
            for (Course course : courseList) {
                Syllabus syllabus = syllabusDAO.findBySyllabusId(course.getSyllabusId());
                syllabusList.add(syllabus);
            }
            return syllabusList;
        } catch (SQLException e) {
            return new ArrayList<>();
        }

    }

    public List<User> courseSelectUser(String syllabusId) {
        try {
            Syllabus syllabus = syllabusDAO.findBySyllabusId(syllabusId);
            List<Course> courseList = courseDAO.findBySyllabus(syllabus, -1);
            List<User> userList = new ArrayList<>();
            for (Course course : courseList) {
                User user = userDAO.findById(course.getUserId());
                userList.add(user);
            }
            return userList;

        } catch (SQLException e) {
            return new ArrayList<>();
        }

    }

    public String getRegistrationPeriod() {
        RegistrationPeriod registrationPeriod = null;
        try {
            registrationPeriod = registrationPeriodDAO.select();
            return registrationPeriod.getPeriod();
        } catch (SQLException e) {
            return "";
        }
    }

    public RegistrationPeriod registrationPeriodSelect() {
        try {
            return registrationPeriodDAO.select();
        } catch (SQLException e) {
            return new RegistrationPeriod();
        }
    }

    public List<FacultyDepartment> getFacultyDepartmentList() {
        try {
            return facultyDepartmentDAO.select(new FacultyDepartment(), -1);
        } catch (SQLException e) {
            return null;
        }

    }

    public boolean studentRegistrationOrUpdate(Student student) {
        try {
            studentDAO.findByStudent(student.getUserId());
            return studentDAO.update(student);
        } catch (SQLException e) {
            return studentDAO.insert(student);
        }
    }

    public boolean getRegistrationPeriodFlag() {
        return registrationPeriodFlag;
    }

    public List<Syllabus> teacherInChargeSearch(String userId) {
        User user = userDAO.findById(userId);
        try {
            return teacherInChargeDAO.findByUser(user, -1);
        } catch (SQLException e) {
            return new ArrayList<>();
        }
    }

    public boolean getSemester() {
        return semester;
    }

    public String getSemesterString() {
        if (this.semester) {
            return "前期";
        } else {
            return "後期";
        }
    }

    public boolean getInCharge(String syllabusId, String teacherId) {
        return teacherInChargeDAO.getInCharge(syllabusId, teacherId);
    }

    public LocalDateTime getNow() {
        return now;
    }

    public boolean registrationPeriodUpdate(RegistrationPeriod registrationPeriod) {
        return registrationPeriodDAO.update(registrationPeriod);
    }
}
