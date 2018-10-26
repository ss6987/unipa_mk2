package DAO;

import Entity.User;
import etc.CreateSalt;
import etc.Sha256;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserDAO {
    private List<DateSet> list = new ArrayList<DateSet>();
    private String tableName = "user";
    private List<String> columns = Arrays.asList("user_id", "name", "phonetic", "password", "slat", "gender", "birthday", "postal_code", "address", "tel", "user_classification");
    private List<String> mold = Arrays.asList("string", "string", "string", "string", "string", "integer", "string", "string", "string", "string", "string");
    private List<Boolean> blankSetting = Arrays.asList(false, false, true, true, true, false, false, true, true, true, false);

    private SessionManager sessionManager = new SessionManager();
    private SQLCreater sqlCreater = new SQLCreater();

    public UserDAO() {
        for (int i = 0; i < columns.size(); i++) {
            this.list.add(new DateSet(columns.get(i), mold.get(i), ""));
        }

    }

    public List<User> select(User user, Integer page) throws SQLException {
        setList(user);
        String sql = sqlCreater.select(tableName, list, page);
        ResultSet resultSet = this.sessionManager.executeQuery(sql);
        List<User> results = new ArrayList<User>();
        while (resultSet.next()) {
            results.add(new User(resultSet));
        }
        return results;
    }

    public boolean insert(User user) {
        setList(user);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getValue().equals("") && !blankSetting.get(i)) {
                return false;
            }
        }
        String sql = sqlCreater.insert(tableName, list);
        return sessionManager.execute(sql);
    }


    public boolean update(User user) {
        setList(user);
        String sql = sqlCreater.update(tableName, list);
        return sessionManager.execute(sql);

    }

    public boolean delete(User user) {
        setList(user);
        String sql = sqlCreater.delete(tableName, list);
        return sessionManager.execute(sql);
    }


    private void setList(User user) {
        list.get(0).setValue(user.getUserId());
        list.get(1).setValue(user.getName());
        list.get(2).setValue(user.getPhonetic());
        list.get(3).setValue("");
        list.get(4).setValue("");
        list.get(5).setValue(user.getGender().toString());
        list.get(6).setValue(user.getBirthday().toString());
        list.get(7).setValue(user.getPostalCode());
        list.get(8).setValue(user.getAddress());
        list.get(9).setValue(user.getTel());
        list.get(10).setValue(user.getUserClassification());
    }

    private void setList(User user, String password) {
        Sha256 sha256 = new Sha256();
        String salt = new CreateSalt().getrSalt();
        list.get(0).setValue(user.getUserId());
        list.get(1).setValue(user.getName());
        list.get(2).setValue(user.getPhonetic());
        list.get(3).setValue(sha256.sha256(password + salt));
        list.get(4).setValue(salt);
        list.get(5).setValue(user.getGender().toString());
        list.get(6).setValue(user.getBirthday().toString());
        list.get(7).setValue(user.getPostalCode());
        list.get(8).setValue(user.getAddress());
        list.get(9).setValue(user.getTel());
        list.get(10).setValue(user.getUserClassification());
    }

    public User findById(String userId) {
        setValue("user_id", userId);
        String sql = sqlCreater.selectEqual(tableName, list, 0);
        ResultSet resultSet = this.sessionManager.executeQuery(sql);
        try {
            resultSet.next();
            return new User(resultSet);
        } catch (SQLException e) {
            return new User();
        }
    }


    public void setValue(String column, String value) {
        list.get(columns.indexOf(column)).setValue(value);
    }

    public void clearValue() {
        list = new ArrayList<DateSet>();
        for (int i = 0; i < columns.size(); i++) {
            this.list.add(new DateSet(columns.get(i), mold.get(i), ""));
        }
    }

    public User login(String userId, String password) throws SQLException {
        clearValue();
        setValue("user_id", userId);
        String sql = sqlCreater.select(tableName, list, 0);
        ResultSet resultSet = sessionManager.executeQuery(sql);
        if (resultSet.next()) {
            String salt = resultSet.getString("slat");
            String resultString = resultSet.getString("password");
            Sha256 sha256 = new Sha256();
            if (resultString.equals(sha256.sha256(password + salt))) {
                return new User(resultSet);
            }
        }
        return null;
    }

    public boolean updatePassword(User user, String password) {
        setList(user, password);
        String sql = sqlCreater.update(tableName, list);
        return sessionManager.execute(sql);
    }

    public Integer getCount() throws SQLException {
        String sql = new SQLCreater().getCount(tableName, list);
        ResultSet resultSet = this.sessionManager.executeQuery(sql);
        resultSet.next();
        return resultSet.getInt("C1");
    }
}
