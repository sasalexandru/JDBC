package personal.projects;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SQLRoomFair {

    private ConnectDB connectDB = new ConnectDB();

    public SQLRoomFair(ConnectDB connectDB) {
        this.connectDB = connectDB;
    }

    public void add(RoomFair roomFair) throws SQLException {
        try (Connection connection = connectDB.connect()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO room_fair(value,season) values('" +
                    roomFair.getValue() + "','" + roomFair.getSeason() + "');");
            ResultSet resultSet = statement.executeQuery("SELECT CURRVAL('room_fair_ids');");
            resultSet.next();
            roomFair.setId(resultSet.getInt(1));
        }
    }


    public List<RoomFair> getAll() throws  SQLException {
        try (Connection connection = connectDB.connect()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * from room_fair;");
            ArrayList<RoomFair> roomFairs = new ArrayList<>();
            while (resultSet.next()) {
                roomFairs.add(mapResultSetToRoomFair(resultSet));
            }
            return roomFairs;
        }
    }


    private RoomFair mapResultSetToRoomFair(ResultSet resultSet) throws SQLException {
        RoomFair roomFair = new RoomFair(resultSet.getDouble("value"),
                resultSet.getString("season"));
        roomFair.setId(resultSet.getInt("id"));
        return roomFair;
    }


    public void update(RoomFair roomFair) throws  SQLException {
        try (Connection connection = connectDB.connect()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate("UPDATE room_fair SET value = '" + roomFair.getValue() + "'," +
                    " season = '" + roomFair.getSeason() + "' WHERE id = " + roomFair.getId() + ";");
        }
    }


    public void delete(RoomFair roomFair) throws  SQLException {
        try (Connection connection = connectDB.connect()) {
            Statement statement = connection.createStatement();
            statement.execute("DELETE FROM room_fair WHERE id=" + roomFair.getId() + ";");
        }
    }

}
