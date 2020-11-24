package personal.projects;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        ConnectDB connectDB = new ConnectDB();
        ManagementDB managementDB = new ManagementDB(connectDB);
        managementDB.resetDb();
        managementDB.createBookingDb();


        SQLAccommodation sqlAccommodation = new SQLAccommodation(connectDB);
        SQLRoomFair sqlRoomFair = new SQLRoomFair(connectDB);
        SQLAccommodationRoomFair accommodationRoomFair = new SQLAccommodationRoomFair(connectDB);

        Accommodation a1 = new Accommodation("Single","King Size",1,"-");
        Accommodation a2 = new Accommodation("Queen","Queen Size",2,"-");
        Accommodation a3 = new Accommodation("Double","Twin",3,"-");
        Accommodation a4 = new Accommodation("Double","King Size",4,"-");
        Accommodation a5 = new Accommodation("Queen","Queen Size",1,"-");
        Accommodation a6 = new Accommodation("King","King size",2,"-");
        sqlAccommodation.add(a1);
        sqlAccommodation.add(a2);
        sqlAccommodation.add(a3);
        sqlAccommodation.add(a4);
        sqlAccommodation.add(a5);
        sqlAccommodation.add(a6);


        RoomFair r1 = new RoomFair(100,"Summer");
        RoomFair r2 = new RoomFair(200,"Winter");
        RoomFair r3 = new RoomFair(300,"Spring");
        RoomFair r4 = new RoomFair(400,"Autumn");
        RoomFair r5 = new RoomFair(200,"Winter");
        RoomFair r6 = new RoomFair(200,"Summer");
        sqlRoomFair.add(r1);
        sqlRoomFair.add(r2);
        sqlRoomFair.add(r3);
        sqlRoomFair.add(r4);
        sqlRoomFair.add(r5);
        sqlRoomFair.add(r6);


        accommodationRoomFair.setAll();
    }
}
