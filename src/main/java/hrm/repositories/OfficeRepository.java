package hrm.repositories;

import hrm.db.DbContext;
import hrm.entities.Office;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OfficeRepository {

    private Office MapResultSetToEntity(ResultSet resultSet) throws SQLException {
        Office office = new Office();

        office.setId(resultSet.getInt("id"));
        office.setStreetAddress(resultSet.getString("streetAddress"));
        office.setPostalCode(resultSet.getString("postalCode"));
        office.setInternalName(resultSet.getString("internalName"));
        office.setCountry(resultSet.getString("country"));
        office.setCity(resultSet.getString("city"));

        return office;
    }

    public  Office GetOfficeById(int id) throws SQLException, ClassNotFoundException {
        Connection connection = DbContext.openConnection();
        String query = "SELECT * FROM Office WHERE Id = ?";
        PreparedStatement sqlStatement = connection.prepareStatement(query);

        sqlStatement.setString(1, Integer.toString(id));

        ResultSet results = sqlStatement.executeQuery();

        if(results.next()) {
            return MapResultSetToEntity(results);
        }
        return null;
    }

    public List<Office> GetOffices() throws SQLException, ClassNotFoundException {
        Connection connection = DbContext.openConnection();
        String query = "SELECT * FROM Office";
        PreparedStatement sqlStatement = connection.prepareStatement(query);

        ResultSet results = sqlStatement.executeQuery();

        List<Office> resultList = new ArrayList<>();
        while(results.next()) {
            resultList.add(MapResultSetToEntity(results));
        }

        return resultList;
    }

    public void InsertOffice(Office office) throws SQLException, ClassNotFoundException{
        Connection connection = DbContext.openConnection();
        String query = "INSERT `office` (internalName, streetAddress, postalCode, country, city) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement sqlStatement = connection.prepareStatement(query);

        sqlStatement.setString(1, office.getInternalName());
        sqlStatement.setString(2, office.getStreetAddress());
        sqlStatement.setString(3, office.getPostalCode());
        sqlStatement.setString(4, office.getCountry());
        sqlStatement.setString(5, office.getCity());

        sqlStatement.executeUpdate();
    }

    public void DeleteOffice(int id) throws SQLException, ClassNotFoundException{
        Connection connection = DbContext.openConnection();

        String query = "DELETE FROM Office WHERE Id = ?";
        PreparedStatement sqlStatement = connection.prepareStatement(query);
        sqlStatement.setInt(1, id);

        sqlStatement.executeUpdate();
    }

    public void UpdateOffice(Office office) throws SQLException, ClassNotFoundException{
        Connection connection = DbContext.openConnection();
        String query = "UPDATE office SET internalName = ?, streetAddress = ?, postalCode = ?, country = ?, city = ? WHERE Id = ?";
        PreparedStatement sqlStatement = connection.prepareStatement(query);

        sqlStatement.setString(1, office.getInternalName());
        sqlStatement.setString(2, office.getStreetAddress());
        sqlStatement.setString(3, office.getPostalCode());
        sqlStatement.setString(4, office.getCountry());
        sqlStatement.setString(5, office.getCity());
        sqlStatement.setInt(6, office.getId());

        sqlStatement.executeUpdate();
    }
}
