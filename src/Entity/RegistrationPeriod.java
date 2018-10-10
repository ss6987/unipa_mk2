package Entity;

import etc.ReplaceString;
import etc.StringCheck;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class RegistrationPeriod {
    private Integer id = 0;
    private String startDate = "";
    private String endDate = "";

    public RegistrationPeriod(){
    }

    public RegistrationPeriod(ResultSet resultSet) throws SQLException {
        this.id = resultSet.getInt("id");
        this.startDate = resultSet.getDate("start_date").toString();
        this.endDate = resultSet.getDate("end_date").toString();
    }

    public RegistrationPeriod(String startDate, String endDate) {
        this.id = 0;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Integer getId() {
        return id;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getPeriod() {
        return getStartDate() + "~" + getEndDate();
    }

    public void setStartDate(String startDate) {
        startDate = new ReplaceString().replace(startDate);
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        endDate = new ReplaceString().replace(endDate);
        this.endDate = endDate;
    }

    public LocalDateTime getStartLocalDate() {
        String[] dateString = startDate.split("-");
        return createLocalDate(dateString);
    }

    public LocalDateTime getEndLocalDate() {
        String[] dateString = endDate.split("-");
        return createLocalDate(dateString);
    }

    private LocalDateTime createLocalDate(String[] dateString){
        Integer date = Integer.parseInt(dateString[0]);
        Integer month = Integer.parseInt(dateString[1]);
        Integer time = Integer.parseInt(dateString[2]);
        return LocalDateTime.of(date, month, time, 0, 0, 0);
    }
}
