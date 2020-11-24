package personal.projects;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
    public Connection connect() throws SQLException {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver").newInstance();
            DriverManager.setLoginTimeout(60);
            connection = DriverManager.getConnection(urlBuilder());
        } catch (IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    private String urlBuilder(){
        String url = new StringBuilder()
                .append("jdbc:")
                .append("postgresql")
                .append("://")
                .append("localhost")
                .append(":")
                .append(5432)
                .append("/")
                .append("BookingDB")
                .append("?user=")
                .append("postgres")
                .append("&password=")
                .append("postgres").toString();
        return url;
    }
}
