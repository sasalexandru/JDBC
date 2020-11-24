package personal.projects;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SQLAccommodation {
    private ConnectDB connectDB = new ConnectDB();

    public SQLAccommodation(ConnectDB connectDB) {
        this.connectDB = connectDB;
    }

    public void add(Accommodation accommodation) throws SQLException {
        try (Connection connection = connectDB.connect()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO accommodation(type,bed_type,max_guests,description) values('" +
                    accommodation.getType() + "','" + accommodation.getBedType() + "','" + accommodation.getMaxGuests() + "','" +
                    accommodation.getDescription() + "');");
            ResultSet resultSet = statement.executeQuery("SELECT CURRVAL('accommodation_ids');");
            resultSet.next();
            accommodation.setId(resultSet.getInt(1));
        }
    }
    public List getAll() throws  SQLException {
        try(Connection connection= connectDB.connect()){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * from accommodation;");
            ArrayList<Accommodation> accommodations = new ArrayList<>();
            while (resultSet.next()){
                accommodations.add(mapResultSetToAccommodation(resultSet)) ;
            }
            return accommodations;
        }
    }
    private Accommodation mapResultSetToAccommodation(ResultSet resultSet) throws SQLException{
        Accommodation accommodation = new Accommodation(resultSet.getString("type"),
                resultSet.getString("bed_type"),
                resultSet.getInt("max_guests"),
                resultSet.getString("description"));
        accommodation.setId(resultSet.getInt("id"));
        return accommodation;
    }
    public void update(Accommodation accommodation) throws  SQLException {
        try(Connection connection= connectDB.connect()){
            Statement statement = connection.createStatement();
            statement.executeUpdate("UPDATE accommodation SET type = '"+accommodation.getType()+"'," +
                    " bed_type = '"+accommodation.getBedType()+"', max_guests = '"+accommodation.getMaxGuests()+"',"+
                    " description = '"+accommodation.getDescription()+"' WHERE id = "+ accommodation.getId()+";");
        }
    }


    public void delete(Accommodation accommodation) throws  SQLException {
        try(Connection connection = connectDB.connect()){
            Statement statement = connection.createStatement();
            statement.execute("DELETE FROM accommodation WHERE id="+ accommodation.getId()+";");
        }
    }
}
