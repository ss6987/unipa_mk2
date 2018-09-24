package DAO;

import java.sql.*;

public class SessionManager {
    private Connection con;

    public SessionManager() {
        try {
            Class.forName("org.hsqldb.jdbcDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            this.con = DriverManager.getConnection(
                    "jdbc:hsqldb:hsql://localhost",
                    "SA",
                    ""
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean execute(String sql) {
        try {
            Statement statement = con.createStatement();
            statement.execute(sql);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public ResultSet executeQuery(String sql) {
        Statement statement;
        try {
            statement = con.createStatement();
            return statement.executeQuery(sql);
        } catch (SQLException e) {
            return null;
        }
    }

    public void close() {
        try {
            this.con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
