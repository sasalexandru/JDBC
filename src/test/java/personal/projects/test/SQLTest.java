package personal.projects.test;

import org.junit.*;
import personal.projects.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SQLTest {
    DBTest dbTest;

    SQLAccommodation sqlAccommodation ;
    SQLRoomFair sqlRoomFair ;
    SQLAccommodationRoomFair sqlAccommodationRoomFair;

    @BeforeClass
    public static void initTests() throws  SQLException {
        DBTest.setUpTestDB();
    }

    @AfterClass
    public static void discardTests() throws  SQLException {
        DBTest.dropTestDB();
    }

    @Before
    public void setUp(){
        dbTest = new DBTest();
        sqlAccommodation = new SQLAccommodation(dbTest);
        sqlRoomFair = new SQLRoomFair(dbTest);
        sqlAccommodationRoomFair = new SQLAccommodationRoomFair(dbTest);
    }

    @After
    public void tearDown() throws SQLException {
        dbTest.dropDataFromTables();
    }

    @Test
    public void insert_accommodations_into_db() throws  SQLException{
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

        List<Accommodation> list = sqlAccommodation.getAll();

        Assert.assertArrayEquals(new Accommodation[]{a1,a2,a3,a4,a5,a6}, list.toArray());
    }

    @Test
    public void delete_accommodations_from_db() throws  SQLException{
        Accommodation a1 = new Accommodation("Single","King Size",1,"-");
        Accommodation a2 = new Accommodation("Queen","Queen Size",2,"-");
        sqlAccommodation.add(a1);
        sqlAccommodation.add(a2);

        sqlAccommodation.delete(a1);
        sqlAccommodation.delete(a2);

        List<Accommodation> list = sqlAccommodation.getAll();

        Assert.assertEquals(0,list.size());
    }

    @Test
    public void update_accommodation_into_db() throws  SQLException{
        Accommodation a1 = new Accommodation("Single","King Size",1,"-");
        Accommodation a2 = new Accommodation("Queen","Queen Size",2,"-");
        sqlAccommodation.add(a1);
        sqlAccommodation.add(a2);

        a1.setType(a2.getType());
        a1.setBedType(a2.getBedType());
        a1.setMaxGuests(a2.getMaxGuests());
        a1.setDescription(a2.getDescription());

        sqlAccommodation.update(a1);

        List<Accommodation> list = sqlAccommodation.getAll();

        Assert.assertEquals(a2,list.get(0));
    }

    @Test
    public void insert_room_fair_into_db() throws  SQLException{
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

        List<RoomFair> list = sqlRoomFair.getAll();

        Assert.assertArrayEquals(new RoomFair[]{r1,r2,r3,r4,r5,r6}, list.toArray());
    }

    @Test
    public void delete_room_fairs_from_db() throws  SQLException{
        RoomFair r1 = new RoomFair(100,"Summer");
        RoomFair r2 = new RoomFair(200,"Winter");
        sqlRoomFair.add(r1);
        sqlRoomFair.add(r2);

        sqlRoomFair.delete(r1);

        List<RoomFair> list = sqlRoomFair.getAll();

        Assert.assertEquals(1,list.size());
    }

    @Test
    public void update_room_fair_into_db() throws  SQLException{
        RoomFair r1 = new RoomFair(100,"Summer");
        RoomFair r2 = new RoomFair(200,"Winter");
        sqlRoomFair.add(r1);
        sqlRoomFair.add(r2);

        r1.setValue(r2.getValue());
        r1.setSeason(r2.getSeason());


        sqlRoomFair.update(r1);

        List<RoomFair> list = sqlRoomFair.getAll();

        Assert.assertEquals(r2,list.get(0));
    }

    @Test
    public void set_relation_table() throws  SQLException{
        Accommodation a1 = new Accommodation("Single","King Size",1,"-");
        Accommodation a2 = new Accommodation("Queen","Queen Size",2,"-");
        Accommodation a3 = new Accommodation("Double","Twin",3,"-");
        Accommodation a4 = new Accommodation("Double","King Size",4,"-");
        sqlAccommodation.add(a1);
        sqlAccommodation.add(a2);
        sqlAccommodation.add(a3);
        sqlAccommodation.add(a4);
        RoomFair r1 = new RoomFair(100,"Summer");
        RoomFair r2 = new RoomFair(200,"Winter");
        sqlRoomFair.add(r1);
        sqlRoomFair.add(r2);

        sqlAccommodationRoomFair.setAll();
        List list = sqlAccommodationRoomFair.getAll();
        AccommodationFairRelation o1 = new AccommodationFairRelation(1,1);
        o1.setId(1);
        AccommodationFairRelation o2 = new AccommodationFairRelation(2,2);
        o2.setId(2);

        Assert.assertArrayEquals(new AccommodationFairRelation[]{o1,o2},list.toArray());
    }

    @Test
    public void room_value_interogation() throws  SQLException {
        Accommodation a4 = new Accommodation("Single","King Size",1,"-");
        Accommodation a5 = new Accommodation("Queen","Queen Size",2,"-");
        Accommodation a6 = new Accommodation("Double","Twin",3,"-");
        sqlAccommodation.add(a4);
        sqlAccommodation.add(a5);
        sqlAccommodation.add(a6);
        RoomFair r4 = new RoomFair(100,"Summer");
        RoomFair r5 = new RoomFair(200,"Winter");
        RoomFair r6 = new RoomFair(300,"Spring");
        sqlRoomFair.add(r4);
        sqlRoomFair.add(r5);
        sqlRoomFair.add(r6);

        List list = new ArrayList();
        list.add(a4.getId());list.add(a4.getType());list.add(a4.getBedType());list.add(a4.getMaxGuests());list.add(a4.getDescription());list.add(r4.getValue());
        list.add(a5.getId());list.add(a5.getType());list.add(a5.getBedType());list.add(a5.getMaxGuests());list.add(a5.getDescription());list.add(r5.getValue());
        list.add(a6.getId());list.add(a6.getType());list.add(a6.getBedType());list.add(a6.getMaxGuests());list.add(a6.getDescription());list.add(r6.getValue());

        List rs = new ArrayList();

        try(Connection connection = dbTest.connect()){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT accommodation.id,accommodation.type,accommodation.bed_type,accommodation.max_guests,accommodation.description,room_fair.value FROM accommodation INNER JOIN room_fair ON accommodation.id=room_fair.id;");

            while(resultSet.next()){
                rs.add(resultSet.getInt(1));
                rs.add(resultSet.getString(2));
                rs.add(resultSet.getString(3));
                rs.add(resultSet.getInt(4));
                rs.add(resultSet.getString(5));
                rs.add(resultSet.getDouble(6));
            }
        }
        Assert.assertArrayEquals(list.toArray(),rs.toArray());
    }
}
