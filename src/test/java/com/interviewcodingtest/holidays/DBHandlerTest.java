package com.interviewcodingtest.holidays;

import com.interviewcodingtest.holidays.model.Holiday;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.Connection;
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
        connection = dbHandler.getConnection();
        try {
            Statement statement;
            statement = connection.createStatement();

            dbHandler.createHolidayTable();
            assertTrue(dbHandler.tableExistsSQL(connection, "holidays"));

            dbHandler.updateHolidaysToDB(ts_holidays);

            String sql_selectAllHolidays = "select * from `holidays`;";
            List<Holiday> outputHolidays = dbHandler.extractHolidaysFromDB(sql_selectAllHolidays);
            assertEquals(outputHolidays.size(), 2, "ERROR: update table");
            assertEquals(outputHolidays.get(1).getUid(), "uid1", "ERROR: select content in table");


            String sql_dropTable = "drop table `holidays`;";

            int successfulDroppingTable = statement.executeUpdate(sql_dropTable);
            assertEquals(successfulDroppingTable, 0, "ERROR: drop table `holidays`");

            statement.close();
        }
        catch (Exception exception) {
            System.out.println(exception);
        }
        dbHandler.closeConnection();
    }

}