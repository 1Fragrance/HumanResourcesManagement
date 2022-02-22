package hrm.repositories;

import hrm.db.DbContext;
import hrm.models.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class EmployeeRepository {

    private static Employee MapResultSetToEntity(ResultSet resultSet) throws SQLException {
        Employee position = new Employee();

        position.setId(resultSet.getInt("id"));
        position.setFirstName(resultSet.getString("firstName"));
        position.setLastName(resultSet.getString("lastName"));
        position.setPatronymic(resultSet.getString("patronymic"));
        position.setPhoneNumber(resultSet.getString("phoneNumber"));
        position.setHireDate(resultSet.getDate("hireDate"));
        position.setSalary(resultSet.getFloat("salary"));
        position.setEmail(resultSet.getString("email"));
        position.setUserName(resultSet.getString("userName"));
        position.setPassword(resultSet.getString("password"));
        position.setAdmin(resultSet.getBoolean("isAdmin"));
        position.setStatus(resultSet.getInt("status"));
        position.setPositionId(resultSet.getInt("positionId"));
        position.setDepartmentId(resultSet.getInt("departmentId"));

        return position;
    }

    public static Employee GetEmployeeByCredentials(String username, String password) throws SQLException, ClassNotFoundException{
        Connection connection = DbContext.openConnection();
        String query = "SELECT * FROM Employee WHERE userName = ? && password = ?" ;
        PreparedStatement sqlStatement = connection.prepareStatement(query);

        sqlStatement.setString(1, username);
        sqlStatement.setString(2, password);

        ResultSet results = sqlStatement.executeQuery();

        if(results.next()) {
            return MapResultSetToEntity(results);
        }
        return null;
    }

    public static Employee GetEmployeeByCredentials(String username) throws SQLException, ClassNotFoundException{
        Connection connection = DbContext.openConnection();
        String query = "SELECT * FROM Employee WHERE userName = ?" ;
        PreparedStatement sqlStatement = connection.prepareStatement(query);

        sqlStatement.setString(1, username);

        ResultSet results = sqlStatement.executeQuery();

        if(results.next()) {
            return MapResultSetToEntity(results);
        }
        return null;
    }

    public static Employee GetEmployeeById(int id) throws SQLException, ClassNotFoundException{
        Connection connection = DbContext.openConnection();
        String query = "SELECT * FROM Employee WHERE Id = ?";
        PreparedStatement sqlStatement = connection.prepareStatement(query);

        sqlStatement.setInt(1, id);

        ResultSet results = sqlStatement.executeQuery();

        if(results.next()) {
            return MapResultSetToEntity(results);
        }
        return null;
    }

    public static List<Employee> GetEmployees() throws SQLException, ClassNotFoundException{
        Connection connection = DbContext.openConnection();
        String query = "SELECT * FROM Employee";
        PreparedStatement sqlStatement = connection.prepareStatement(query);

        ResultSet results = sqlStatement.executeQuery();

        List<Employee> resultList = new ArrayList<>();
        while(results.next()) {
            resultList.add(MapResultSetToEntity(results));
        }

        return resultList;
    }

    public static void InsertEmployee(Employee employee) throws SQLException, ClassNotFoundException{
        Connection connection = DbContext.openConnection();
        String query = "INSERT `employee` (firstname, lastname, patronymic, phonenumber, hireDate, salary, email, username, password, isAdmin, status, positionId, departmentId) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement sqlStatement = connection.prepareStatement(query);

        sqlStatement.setString(1, employee.getFirstName());
        sqlStatement.setString(2, employee.getLastName());
        sqlStatement.setString(3, employee.getPatronymic());
        sqlStatement.setString(4, employee.getPhoneNumber());
        sqlStatement.setDate(5, employee.getHireDate());
        sqlStatement.setFloat(6, employee.getSalary());
        sqlStatement.setString(7, employee.getEmail());
        sqlStatement.setString(8, employee.getUserName());
        sqlStatement.setString(9, employee.getPassword());
        sqlStatement.setBoolean(10, employee.getAdmin());
        sqlStatement.setInt(11, employee.getStatus());
        sqlStatement.setInt(12, employee.getPositionId());
        sqlStatement.setInt(13, employee.getDepartmentId());


        sqlStatement.executeUpdate();
    }

    public static void DeleteEmployee(int id) throws SQLException, ClassNotFoundException{
        Connection connection = DbContext.openConnection();

        String query = "DELETE FROM Employee WHERE Id = ?";
        PreparedStatement sqlStatement = connection.prepareStatement(query);
        sqlStatement.setInt(1, id);

        sqlStatement.executeUpdate();
    }

    public static void UpdateEmployee(Employee employee) throws SQLException, ClassNotFoundException{
        Connection connection = DbContext.openConnection();

        String query = "UPDATE Employee SET firstname = ?, lastname = ?, patronymic = ?, phonenumber = ?, hireDate = ?, salary = ?, email = ?, username = ?, password = ?, isAdmin = ?, status = ?, positionId = ?, departmentId = ? WHERE Id = ?";
        PreparedStatement sqlStatement = connection.prepareStatement(query);

        sqlStatement.setString(1, employee.getFirstName());
        sqlStatement.setString(2, employee.getLastName());
        sqlStatement.setString(3, employee.getPatronymic());
        sqlStatement.setString(4, employee.getPhoneNumber());
        sqlStatement.setDate(5, employee.getHireDate());
        sqlStatement.setFloat(6, employee.getSalary());
        sqlStatement.setString(7, employee.getEmail());
        sqlStatement.setString(8, employee.getUserName());
        sqlStatement.setString(9, employee.getPassword());
        sqlStatement.setBoolean(10, employee.getAdmin());
        sqlStatement.setInt(11, employee.getStatus());
        sqlStatement.setInt(12, employee.getPositionId());
        sqlStatement.setInt(13, employee.getDepartmentId());
        sqlStatement.setInt(14, employee.getId());

        sqlStatement.executeUpdate();
    }
}
