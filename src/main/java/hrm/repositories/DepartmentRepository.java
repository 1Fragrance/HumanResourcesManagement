package hrm.repositories;

import hrm.db.DbContext;
import hrm.models.Department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentRepository {

    private static Department MapResultSetToEntity(ResultSet resultSet) throws SQLException {
        Department department = new Department();

        department.setId(resultSet.getInt("id"));
        department.setName(resultSet.getString("name"));
        department.setOfficeId(resultSet.getInt("officeId"));

        return department;
    }

    public static Department GetDepartmentById(int id) throws SQLException, ClassNotFoundException {
        Connection connection = DbContext.openConnection();
        String query = "SELECT * FROM Department WHERE Id = ?";
        PreparedStatement sqlStatement = connection.prepareStatement(query);

        sqlStatement.setString(1, Integer.toString(id));

        ResultSet results = sqlStatement.executeQuery();

        if(results.next()) {
            return MapResultSetToEntity(results);
        }
        return null;
    }

    public static List<Department> GetDepartments() throws SQLException, ClassNotFoundException {
        Connection connection = DbContext.openConnection();
        String query = "SELECT * FROM Department";
        PreparedStatement sqlStatement = connection.prepareStatement(query);

        ResultSet results = sqlStatement.executeQuery();

        List<Department> resultList = new ArrayList<>();
        while(results.next()) {
            resultList.add(MapResultSetToEntity(results));
        }

        return resultList;
    }

    public static void InsertDepartment(Department department) throws SQLException, ClassNotFoundException {
        Connection connection = DbContext.openConnection();
        String query = "INSERT Department (name, officeId) VALUES (?, ?)";
        PreparedStatement sqlStatement = connection.prepareStatement(query);
        sqlStatement.setString(1, department.getName());
        sqlStatement.setString(2, Integer.toString(department.getOfficeId()));

        sqlStatement.executeUpdate();
    }

    public static void DeleteDepartment(int id) throws SQLException, ClassNotFoundException {
        Connection connection = DbContext.openConnection();
        String query = "DELETE FROM Department WHERE Id = ?";
        PreparedStatement sqlStatement = connection.prepareStatement(query);
        sqlStatement.setInt(1, id);

        sqlStatement.executeUpdate();
    }

    public static void UpdateDepartment(Department department) throws SQLException, ClassNotFoundException {
        Connection connection = DbContext.openConnection();
        String query = "UPDATE Department SET name = ?, officeId = ? WHERE Id = ?";
        PreparedStatement sqlStatement = connection.prepareStatement(query);
        sqlStatement.setInt(3, department.getId());
        sqlStatement.setString(1, department.getName());
        sqlStatement.setInt(2, department.getOfficeId());

        sqlStatement.executeUpdate();
    }
}
