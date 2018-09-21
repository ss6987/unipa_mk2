package DAO;

import Entity.RegistrationPeriod;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RegistrationPeriodDAO {
    private List<DateSet> list = new ArrayList<DateSet>();
    private String tableName = "registration_period";
    private List<String> columns = Arrays.asList("id", "start_date", "end_date");
    private List<String> mold = Arrays.asList("integer", "string", "string");
    private SessionManager sessionManager = new SessionManager();
    private SQLCreater sqlCreater = new SQLCreater();

    public RegistrationPeriodDAO() {
        for (int i = 0; i < columns.size(); i++) {
            this.list.add(new DateSet(columns.get(i), mold.get(i), ""));
        }

    }

    public RegistrationPeriod select() throws SQLException {
        clearValue();
        setValue("id","0");
        String sql = sqlCreater.select(tableName,list,0);
        ResultSet resultSet = sessionManager.executeQuery(sql);
        resultSet.next();
        return new RegistrationPeriod(resultSet);
    }

    public boolean insert(RegistrationPeriod registrationPeriod) {
        setList(registrationPeriod);
        String sql = sqlCreater.insert(tableName, list);
        return sessionManager.execute(sql);
    }


    public boolean update(RegistrationPeriod registrationPeriod) {
        setList(registrationPeriod);
        String sql = sqlCreater.update(tableName, list);
        System.out.println(sql);
        return sessionManager.execute(sql);
    }


    public boolean delete() {
        clearValue();
        setValue("id","0");
        String sql = sqlCreater.delete(tableName, list);
        return sessionManager.execute(sql);
    }

    private void setList(RegistrationPeriod registrationPeriod) {
        list.get(0).setValue(registrationPeriod.getId().toString());
        list.get(1).setValue(registrationPeriod.getStartDate());
        list.get(2).setValue(registrationPeriod.getEndDate());
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
}
