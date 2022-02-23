package hrm.repositories;

import hrm.db.DbContext;
import hrm.entities.PositionHistory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PositionHistoryRepository {

    private PositionHistory MapResultSetToEntity(ResultSet resultSet) throws SQLException {
        PositionHistory positionHistory = new PositionHistory();

        positionHistory.setId(resultSet.getInt("id"));
        positionHistory.setStartDate(resultSet.getDate("startDate"));
        positionHistory.setEndDate(resultSet.getDate("endDate"));
        positionHistory.setEmployeeId(resultSet.getInt("employeeId"));
        positionHistory.setPositionId(resultSet.getInt("positionId"));
        positionHistory.setDepartmentId(resultSet.getInt("departmentId"));

        return positionHistory;
    }

    public PositionHistory GetPositionHistoryById(int id) throws SQLException, ClassNotFoundException {
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

    public List<PositionHistory> GetPositionHistories() throws SQLException, ClassNotFoundException{
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

    public PositionHistory GetLastPositionHistory(int employeeId, int departmentId, int positionId) throws SQLException, ClassNotFoundException {
        Connection connection = DbContext.openConnection();
        String query = "SELECT * FROM PositionHistory WHERE employeeId = ? && departmentId = ? && positionId = ? ORDER BY Id DESC";
        PreparedStatement sqlStatement = connection.prepareStatement(query);

        sqlStatement.setString(1, Integer.toString(employeeId));
        sqlStatement.setString(2, Integer.toString(departmentId));
        sqlStatement.setString(3, Integer.toString(positionId));

        ResultSet results = sqlStatement.executeQuery();

        if(results.next()) {
            return MapResultSetToEntity(results);
        }
        return null;
    }


    public void InsertPositionHistory(PositionHistory positionHistory) throws SQLException, ClassNotFoundException {
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

    public void DeletePositionHistory(int id) throws SQLException, ClassNotFoundException {
        Connection connection = DbContext.openConnection();

        String query = "DELETE FROM PositionHistory WHERE Id = ?";
        PreparedStatement sqlStatement = connection.prepareStatement(query);
        sqlStatement.setInt(1, id);

        sqlStatement.executeUpdate();
    }

    public void UpdatePositionHistory(PositionHistory positionHistory) throws SQLException, ClassNotFoundException {
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
