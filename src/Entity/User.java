package Entity;


import DAO.StudentDAO;
import etc.ReplaceString;
import etc.StringCheck;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    private String userId;
    private String name;
    private String phonetic;
    private Integer gender;
    private String birthday;
    private String postalCode;
    private String address;
    private String tel;
    private String userClassification;

    public User() {
        this.userId = "";
        this.name = "";
        this.phonetic = "";
        this.gender = -1;
        this.birthday = "";
        this.postalCode = "";
        this.address = "";
        this.tel = "";
        this.userClassification = "";
    }

    public User(HttpServletRequest request,String targetUserId) throws UnsupportedEncodingException {
        ReplaceString replaceString = new ReplaceString();
        String errorString = "";

        if(targetUserId.equals("")){
            targetUserId = replaceString.repairRequest(request.getParameter("userId"));
        }

        String name = replaceString.repairRequest(request.getParameter("name"));
        String phonetic = replaceString.repairRequest(request.getParameter("phonetic"));
        String genderString = replaceString.repairRequest(request.getParameter("gender"));
        String year = replaceString.repairRequest(request.getParameter("year"));
        String month = replaceString.repairRequest(request.getParameter("month"));
        if(month.length() == 1){
            month = "0" + month;
        }
        String day = replaceString.replace(request.getParameter("day"));
        if(day.length() == 1){
            day = "0" + day;
        }
        String birthday = year + "-" + month + "-" + day;
        String postalCode = replaceString.repairRequest(request.getParameter("postal_code"));
        String address = replaceString.repairRequest(request.getParameter("address"));
        String tel = replaceString.repairRequest(request.getParameter("tel"));
        String userClassification = replaceString.repairRequest(request.getParameter("user_classification"));

        Integer gender;
        try {
            gender = Integer.parseInt(genderString);
        } catch (java.lang.NumberFormatException e) {
            gender = -1;
        }

        errorString += this.setUserId(targetUserId);
        errorString += this.setName(name);
        errorString += this.setPhonetic(phonetic);
        errorString += this.setGender(gender);
        errorString += this.setBirthday(birthday);
        errorString += this.setPostalCode(postalCode);
        errorString += this.setAddress(address);
        errorString += this.setTel(tel);
        errorString += this.setUserClassification(userClassification);
        errorString = errorString.replace("。", "。<br/>");

        if (!errorString.equals("")) {
            request.setAttribute("errorString", errorString);
        }
    }

    public User(ResultSet resultSet) throws SQLException {
        this.userId = resultSet.getString("user_id");
        this.name = resultSet.getString("name");
        this.phonetic = resultSet.getString("phonetic");
        this.gender = resultSet.getInt("gender");
        this.birthday = resultSet.getDate("birthday").toString();
        this.postalCode = resultSet.getString("postal_code");
        this.address = resultSet.getString("address");
        this.tel = resultSet.getString("tel");
        this.userClassification = resultSet.getString("user_classification");
    }

    public User(String userId, String name, String phonetic, Integer gender, String birthday, String postalCode, String address, String tel, String userClassification) {
        setUserId(userId);
        setName(name);
        setPhonetic(phonetic);
        setGender(gender);
        setBirthday(birthday);
        setPostalCode(postalCode);
        setAddress(address);
        setTel(tel);
        setUserClassification(userClassification);
    }

    public String setUserId(String userId) {
        if (new StringCheck().checkW(userId)) {
            this.userId = userId;
            return "";
        }
        return "ユーザーIDに使用できない文字が存在します。";
    }

    public String setName(String name) {
        name = new ReplaceString().replace(name);
        if (new StringCheck().checkNotSymbols(name)) {
            this.name = name;
            return "";
        }
        return "名前に使用できない文字が存在しています。";
    }

    public String setPhonetic(String phonetic) {
        phonetic = new ReplaceString().replace(phonetic);
        if (new StringCheck().checkPhonetic(phonetic)) {
            this.phonetic = phonetic;
            return "";
        }
        return "フリガナに使用できない文字が存在します。";
    }

    public String setGender(Integer gender) {
        if (gender >= -1 && gender <= 1) {
            this.gender = gender;
            return "";
        }
        return "性別で問題が発生しました。";
    }

    public String setBirthday(String birthday) {
        birthday = new ReplaceString().replace(birthday);
        birthday = birthday.replace("&#45;","-");
        if (birthday.matches("[-\\d]+$") && birthday.matches("^(\\d{4}-(0[0-9]|1[0-2])-(0[0-9]|[12][0-9]|3[01]))$")) {
            this.birthday = birthday;
            return "";
        }else if(birthday.equals("")||birthday.equals("--")){
            this.birthday = "";
            return "";
        }
        return "生年月日に使用できない文字が存在します。";
    }

    public String setPostalCode(String postalCode) {
        postalCode = new ReplaceString().replace(postalCode);
        if (new StringCheck().checkD(postalCode) && (postalCode.length() == 7|| postalCode.length() == 0)) {
            this.postalCode = postalCode;
            return "";
        }
        return "郵便番号に使用できない文字が存在します。";
    }

    public String setAddress(String address) {
        address = new ReplaceString().replace(address);
        if (new StringCheck().checkFreeText(address)) {
            this.address = address;
            return "";
        }
        return "住所に使用できない文字が存在します。";
    }

    public String setTel(String tel) {
        if (new StringCheck().checkD(tel) && (tel.length() == 11) || tel.length() == 10 || tel.length() == 0) {
            this.tel = tel;
            return "";
        }
        return "電話番号に使用できない文字が存在します。";
    }

    public String setUserClassification(String userClassification) {
        if (new StringCheck().checkNotSymbols(userClassification)) {
            this.userClassification = userClassification;
            return "";
        }
        return "ユーザー分類に使用できない文字が存在します。";
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getPhonetic() {
        return phonetic;
    }

    public Integer getGender() {
        return gender;
    }

    public String getGenderString() {
        if (this.gender == 0) {
            return "男性";
        } else if (this.gender == 1) {
            return "女性";
        } else {
            return "";
        }
    }

    public String getBirthday() {
        return birthday;
    }

    public String getPostalCode() {
        return postalCode.replace(" ","");
    }

    public String getAddress() {
        return address;
    }

    public String getTel() {
        return tel.replace(" ", "");
    }

    public String getUserClassification() {
        return userClassification.replace(" ", "");
    }

    public String getUserClassificationSelected(String string) {
        if (this.getUserClassification().equals(string)) {
            return "selected";
        } else {
            return "";
        }
    }

    public String getGenderSelected(Integer number) {
        if (this.getGender() == number) {
            return "checked";
        } else if(this.getGender() == -1 && number == 0) {
            return "checked";
        }else{
            return "";
        }
    }

    public String getBirthYear() {
        String[] birth = this.getBirthday().split("-");
        try{
            return birth[0];
        }catch (java.lang.ArrayIndexOutOfBoundsException e){
            return "";
        }
    }

    public String getBirthMonth() {
        String[] birth = this.getBirthday().split("-");
        try{
            return birth[1];
        }catch (java.lang.ArrayIndexOutOfBoundsException e){
            return "";
        }
    }

    public String getBirthDay() {
        String[] birth = this.getBirthday().split("-");
        try{
            return birth[2];
        }catch (java.lang.ArrayIndexOutOfBoundsException e){
            return "";
        }
    }

    public Student convertUserToStudent() throws SQLException {
        String tmpString = this.getUserClassification().replace(" ", "");
        if (tmpString.equals("学生")) {
            StudentDAO studentDAO = new StudentDAO();
            return studentDAO.findByStudent(this.getUserId());
        } else {
            return null;
        }
    }
}