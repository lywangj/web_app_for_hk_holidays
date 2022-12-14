package com.interviewcodingtest.holidays;

import com.interviewcodingtest.holidays.model.Holiday;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DBHandlerTest {

    List<Holiday> ts_holidays;
    DBHandler dbHandler;
    Connection connection;

    @BeforeEach
    void setup() {

        ts_holidays = new ArrayList<>();
        ts_holidays.add(new Holiday(0, "uid0", "dtstart0", "dtend0", "summary0"));
        ts_holidays.add(new Holiday(1, "uid1", "dtstart1", "dtend1", "summary1"));
    }


    @Test
    void testConnectToMySQL() throws IOException, JSONException {

        dbHandler = new DBHandler();
        dbHandler.connectToDB("hk_holidays");
//        dbHandler.connectToDB("ts_db");
        connection = dbHandler.getConnection();
        try {
            Statement statement;
            statement = connection.createStatement();

//            assertFalse(DBHandler.tableExistsSQL(connection, "holidays"));

//            ResultSet resultSet;
            dbHandler.createHolidayTable();
//            String sql_createTable =
//                    "CREATE TABLE `holidays` (\n" +
//                    "  `holiday_id` int(11) NOT NULL AUTO_INCREMENT,\n" +
//                    "  `uid` varchar(45) NOT NULL,\n" +
//                    "  `dtstart` varchar(45) NOT NULL,\n" +
//                    "  `dtend` varchar(45) NOT NULL,\n" +
//                    "  `summary` varchar(80) NOT NULL,\n" +
//                    "  PRIMARY KEY (`holiday_id`)\n" +
//                    ");";
//            int successfulCreatingTable = statement.executeUpdate(sql_createTable);
//            assertEquals(successfulCreatingTable, 0, "ERROR: create table `holidays`");

            assertTrue(dbHandler.tableExistsSQL(connection, "holidays"));

            dbHandler.updateHolidaysToDB(ts_holidays);


            String sql_selectAllHolidays = "select * from `holidays`;";

            List<Holiday> outputHolidays = dbHandler.extractHolidaysFromDB(sql_selectAllHolidays);

            assertEquals(outputHolidays.size(), 2, "ERROR: update table");
            assertEquals(outputHolidays.get(1).getUid(), "uid1", "ERROR: select content in table");


            String sql_dropTable = "drop table `holidays`;";

            int successfulDroppingTable = statement.executeUpdate(sql_dropTable);
            assertEquals(successfulDroppingTable, 0, "ERROR: drop table `holidays`");

//            resultSet.close();
            statement.close();
//            connection.close();
        }
        catch (Exception exception) {
            System.out.println(exception);
        }

        dbHandler.closeConnection();

    }

}