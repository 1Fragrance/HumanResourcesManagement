package hrm.db;

import hrm.infrastructure.Constants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbContext {
    public static Connection openConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return  DriverManager.getConnection(Constants.SQLConstants.ConnectionString,
                Constants.SQLConstants.UserName,
                Constants.SQLConstants.Password);
    }

    public static void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
