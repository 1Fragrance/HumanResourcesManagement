package hrm.repositories;

import hrm.db.DbContext;
import hrm.models.Position;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PositionRepository {
    private Position MapResultSetToEntity(ResultSet resultSet) throws SQLException {
        Position position = new Position();

        position.setId(resultSet.getInt("id"));
        position.setTitle(resultSet.getString("title"));
        position.setMinSalary(resultSet.getFloat("minSalary"));
        position.setMaxSalary(resultSet.getFloat("maxSalary"));

        return position;
    }

    public Position GetPositionById(int id) throws SQLException, ClassNotFoundException {
        Connection connection = DbContext.openConnection();
        String query = "SELECT * FROM Position WHERE Id = ?";
        PreparedStatement sqlStatement = connection.prepareStatement(query);

        sqlStatement.setString(1, Integer.toString(id));

        ResultSet results = sqlStatement.executeQuery();

        if(results.next()) {
            return MapResultSetToEntity(results);
        }
        return null;
    }

    public List<Position> GetPositions() throws SQLException, ClassNotFoundException{
        Connection connection = DbContext.openConnection();
        String query = "SELECT * FROM Position";
        PreparedStatement sqlStatement = connection.prepareStatement(query);

        ResultSet results = sqlStatement.executeQuery();

        List<Position> resultList = new ArrayList<>();
        while(results.next()) {
            resultList.add(MapResultSetToEntity(results));
        }

        return resultList;
    }

    public void InsertPosition(Position position) throws SQLException, ClassNotFoundException {
        Connection connection = DbContext.openConnection();
        String query = "INSERT `position` (title, minSalary, maxSalary) VALUES (?, ?, ?)";
        PreparedStatement sqlStatement = connection.prepareStatement(query);

        sqlStatement.setString(1, position.getTitle());
        sqlStatement.setFloat(2, position.getMinSalary());
        sqlStatement.setFloat(3, position.getMaxSalary());

        sqlStatement.executeUpdate();
    }

    public void DeletePosition(int id) throws SQLException, ClassNotFoundException {
        Connection connection = DbContext.openConnection();

        String query = "DELETE FROM `position` WHERE Id = ?";
        PreparedStatement sqlStatement = connection.prepareStatement(query);
        sqlStatement.setInt(1, id);

        sqlStatement.executeUpdate();
    }

    public void UpdatePosition(Position position) throws SQLException, ClassNotFoundException {
        Connection connection = DbContext.openConnection();
        String query = "UPDATE `position` SET title = ?, minSalary = ?, maxSalary = ? WHERE Id = ?";
        PreparedStatement sqlStatement = connection.prepareStatement(query);

        sqlStatement.setString(1, position.getTitle());
        sqlStatement.setFloat(2, position.getMinSalary());
        sqlStatement.setFloat(3, position.getMaxSalary());
        sqlStatement.setInt(4, position.getId());

        sqlStatement.executeUpdate();
    }
}
