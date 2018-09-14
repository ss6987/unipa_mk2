package Entity;

import etc.ReplaceString;
import etc.StringCheck;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RegistrationPeriod {
    private Integer id;
    private String startDate;
    private String endDate;

    public RegistrationPeriod(ResultSet resultSet) throws SQLException {
        this.id = resultSet.getInt("id");
        this.startDate = resultSet.getDate("start_date").toString();
        this.endDate = resultSet.getDate("end_date").toString();
    }

    public RegistrationPeriod(String startDate,String endDate){
        this.id = 0;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setStartDate(String startDate) {
        startDate = new ReplaceString().replace(startDate);
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        endDate = new ReplaceString().replace(endDate);
        this.endDate = endDate;
    }
}
