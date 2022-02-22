package hrm.repositories;

import hrm.db.DbContext;
import hrm.models.PositionHistory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PositionHistoryRepository {

    private static PositionHistory MapResultSetToEntity(ResultSet resultSet) throws SQLException {
        PositionHistory positionHistory = new PositionHistory();

        positionHistory.setId(resultSet.getInt("id"));
        positionHistory.setStartDate(resultSet.getDate("startDate"));
        positionHistory.setEndDate(resultSet.getDate("endDate"));
        positionHistory.setEmployeeId(resultSet.getInt("employeeId"));
        positionHistory.setPositionId(resultSet.getInt("positionId"));
        positionHistory.setDepartmentId(resultSet.getInt("departmentId"));

        return positionHistory;
    }

    public static PositionHistory GetPositionHistoryById(int id) throws SQLException, ClassNotFoundException {
        Connection connection = DbContext.openConnection();
        String query = "SELECT * FROM PositionHistory WHERE Id = ?";
        PreparedStatement sqlStatement = connection.prepareStatement(query);

        sqlStatement.setString(1, Integer.toString(id));

        ResultSet results = sqlStatement.executeQuery();

        if(results.next()) {
            return MapResultSetToEntity(results);
        }
        return null;
    }

    public static List<PositionHistory> GetPositionHistories() throws SQLException, ClassNotFoundException{
        Connection connection = DbContext.openConnection();
        String query = "SELECT * FROM PositionHistory";
        PreparedStatement sqlStatement = connection.prepareStatement(query);

        ResultSet results = sqlStatement.executeQuery();

        List<PositionHistory> resultList = new ArrayList<>();
        while(results.next()) {
            resultList.add(MapResultSetToEntity(results));
        }

        return resultList;
    }

    public static void InsertPositionHistory(PositionHistory positionHistory) throws SQLException, ClassNotFoundException {
        Connection connection = DbContext.openConnection();
        String query = "INSERT `positionHistory` (startDate, endDate, employeeId, positionId, departmentId) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement sqlStatement = connection.prepareStatement(query);

        sqlStatement.setDate(1, positionHistory.getStartDate());
        sqlStatement.setDate(2, positionHistory.getEndDate());
        sqlStatement.setInt(3, positionHistory.getEmployeeId());
        sqlStatement.setInt(4, positionHistory.getPositionId());
        sqlStatement.setInt(5, positionHistory.getDepartmentId());

        sqlStatement.executeUpdate();
    }

    public static void DeletePositionHistory(int id) throws SQLException, ClassNotFoundException {
        Connection connection = DbContext.openConnection();

        String query = "DELETE FROM PositionHistory WHERE Id = ?";
        PreparedStatement sqlStatement = connection.prepareStatement(query);
        sqlStatement.setInt(1, id);

        sqlStatement.executeUpdate();
    }

    public static void UpdatePositionHistory(PositionHistory positionHistory) throws SQLException, ClassNotFoundException {
        Connection connection = DbContext.openConnection();
        String query = "UPDATE PositionHistory SET startDate = ?, endDate = ?, employeeId = ?, positionId = ?, departmentId = ? WHERE Id = ?";
        PreparedStatement sqlStatement = connection.prepareStatement(query);

        sqlStatement.setDate(1, positionHistory.getStartDate());
        sqlStatement.setDate(2, positionHistory.getEndDate());
        sqlStatement.setInt(3, positionHistory.getEmployeeId());
        sqlStatement.setInt(4, positionHistory.getPositionId());
        sqlStatement.setInt(5, positionHistory.getDepartmentId());
        sqlStatement.setInt(6, positionHistory.getId());

        sqlStatement.executeUpdate();
    }
}
