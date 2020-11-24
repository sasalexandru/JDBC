package personal.projects;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ManagementDB {
    private ConnectDB connectDB ;

    public ManagementDB(ConnectDB connectDB){
        this.connectDB = connectDB;

    }
    public void createBookingDb() throws SQLException {

        try(Connection connection = connectDB.connect()){
            StringBuilder builder = new StringBuilder();

            //Accommodation Table
            builder.append("CREATE SEQUENCE accommodation_ids;");
            builder.append("CREATE TABLE accommodation(id INT PRIMARY KEY DEFAULT NEXTVAL('accommodation_ids'), " +
                    "type VARCHAR(32), bed_type VARCHAR(32), max_guests INT, description VARCHAR(512));");

            //Room Fair Table
            builder.append("CREATE SEQUENCE room_fair_ids;");
            builder.append("CREATE TABLE room_fair(id INT PRIMARY KEY DEFAULT NEXTVAL('room_fair_ids'), " +
                    "value DOUBLE PRECISION, season VARCHAR(32));");

            //Accommodation Fair Relation Table
            builder.append("CREATE SEQUENCE accommodation_fair_relation_ids;");
            builder.append("CREATE TABLE accommodation_fair_relation(id INT PRIMARY KEY DEFAULT NEXTVAL " +
                    "('accommodation_fair_relation_ids'), id_accommodation INT REFERENCES accommodation(id), " +
                    "id_room_fair INT REFERENCES room_fair(id));");


            Statement statement = connection.createStatement();
            statement.execute(builder.toString());
        }

    }
    public  void resetDb() throws  SQLException {
        try (Connection connection = connectDB.connect()) {
            Statement statement = connection.createStatement();
            statement.execute("DROP TABLE accommodation_fair_relation , accommodation , room_fair;");
            statement.execute("DROP SEQUENCE accommodation_ids,room_fair_ids,accommodation_fair_relation_ids;");
        }
    }
}
