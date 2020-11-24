package personal.projects;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SQLAccommodationRoomFair {

    private ConnectDB connectDB = new ConnectDB();

    public SQLAccommodationRoomFair(ConnectDB connectDB) {
        this.connectDB = connectDB;
    }


    public List<AccommodationFairRelation> getAll() throws SQLException {
        try(Connection connection = connectDB.connect()){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * from accommodation_fair_relation;");
            ArrayList<AccommodationFairRelation> afr = new ArrayList<>();
            while (resultSet.next()){
                afr.add(mapResultSetToAccommodationFairRelation(resultSet)) ;
            }
            return afr;
        }
    }

    private AccommodationFairRelation mapResultSetToAccommodationFairRelation(ResultSet resultSet) throws SQLException{
        AccommodationFairRelation afr = new AccommodationFairRelation(resultSet.getInt(1),
                resultSet.getInt(2));
        afr.setId(resultSet.getInt("id"));
        return afr;
    }


    public void setAll() throws  SQLException {
        try(Connection connection = connectDB.connect()){
            StringBuilder builder = new StringBuilder();
            builder.append("DELETE FROM accommodation_fair_relation;");
            builder.append("ALTER SEQUENCE accommodation_ids RESTART WITH 1;");
            Statement statement = connection.createStatement();
            statement = connection.createStatement();
            statement.execute(builder.toString());
        }
        try(Connection connection = connectDB.connect()){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT accommodation.id,room_fair.id FROM accommodation JOIN room_fair ON accommodation.id=room_fair.id;");
            statement = connection.createStatement();

            while(resultSet.next()){
                statement.executeUpdate("INSERT INTO accommodation_fair_relation(id_accommodation,id_room_fair) values('"+
                        resultSet.getInt(1)+"','"+resultSet.getInt(2)+"');");
            }
        }
    }
}
