package personal.projects.test;

import org.junit.Test;
import personal.projects.ConnectDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class DBTest extends ConnectDB {

    private  Connection connectToPostgreSQL() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = null;
            DriverManager.setLoginTimeout(60);
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
            return DriverManager.getConnection(url);


        } catch (ClassNotFoundException e) {
           throw new SQLException("Cannot load DB");
        }
    }

    public Connection connect() throws  SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = null;
            DriverManager.setLoginTimeout(60);
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
            return DriverManager.getConnection(url);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Cannot load DB");
        }
    }

    public static void setUpTestDB() throws  SQLException {
        DBTest bdt = new DBTest();
        try(Connection connection = bdt.connectToPostgreSQL()) {
            Statement statement = connection.createStatement();
            statement.execute("CREATE DATABASE BookingDBTest;");
        }

        try(Connection connection = bdt.connect()) {
            StringBuilder builder = new StringBuilder();

            builder.append("CREATE SEQUENCE accommodation_ids;");
            builder.append("CREATE TABLE accommodation(id INT PRIMARY KEY DEFAULT NEXTVAL('accommodation_ids'), " +
                    "type VARCHAR(32), bed_type VARCHAR(32), max_guests INT, description VARCHAR(512));");


            builder.append("CREATE SEQUENCE room_fair_ids;");
            builder.append("CREATE TABLE room_fair(id INT PRIMARY KEY DEFAULT NEXTVAL('room_fair_ids'), " +
                    "value DOUBLE PRECISION, season VARCHAR(32));");


            builder.append("CREATE SEQUENCE accommodation_fair_relation_ids;");
            builder.append("CREATE TABLE accommodation_fair_relation(id INT PRIMARY KEY DEFAULT NEXTVAL " +
                    "('accommodation_fair_relation_ids'), id_accommodation INT REFERENCES accommodation(id), " +
                    "id_room_fair INT REFERENCES room_fair(id));");
            Statement statement = connection.createStatement();
            statement = connection.createStatement();
            statement.execute(builder.toString());
        }
    }

    public static void dropTestDB() throws  SQLException {
        DBTest bdt = new DBTest();
        try(Connection connection = bdt.connectToPostgreSQL()) {
            Statement statement = connection.createStatement();
            statement.execute("DROP DATABASE BookingDBTest;");
        }
    }

    public void dropDataFromTables() throws  SQLException {
        try(Connection connection = connect()){
            StringBuilder builder = new StringBuilder();
            builder.append("DELETE FROM accommodation_fair_relation;");
            builder.append("DELETE FROM accommodation;");
            builder.append("DELETE FROM room_fair;");
            Statement statement = connection.createStatement();
            statement = connection.createStatement();
            statement.execute(builder.toString());
        }
        try(Connection connection = connect()){
            StringBuilder builder = new StringBuilder();
            builder.append("ALTER SEQUENCE accommodation_ids RESTART WITH 1;");
            builder.append("ALTER SEQUENCE room_fair_ids RESTART WITH 1;");
            builder.append("ALTER SEQUENCE accommodation_fair_relation_ids RESTART WITH 1;");
            Statement statement = connection.createStatement();
            statement = connection.createStatement();
            statement.execute(builder.toString());
        }

    }
}
