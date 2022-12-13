package com.interviewcodingtest.holidays;

import com.interviewcodingtest.holidays.model.Holiday;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBHandler {

    private JsonHandler jsonHandler;
    private List<Holiday> holidays;
    private Connection connection;

    public DBHandler() {
    }

    public void connectToDB(String dbName){

        String url = "jdbc:mysql://localhost:3306/" + dbName;
        connection = null;
        try {
            // below two lines are used for connectivity.
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    url,"root", "og21893");

            System.out.println("Connecting to database ... ");
        }
        catch (Exception exception) {
            System.out.println(exception);
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        try {
            // below two lines are used for connectivity.
            connection.close();
            System.out.println("Close connection to database ... ");
        }
        catch (Exception exception) {
            System.out.println(exception);
        }
    }

    public List<Holiday> extractHolidaysFromDB(String sql) {

        // Example. sql = "select * from holidays WHERE holiday_id=0;"
        // Example. sql = "select * from holidays;"

        List<Holiday> output_holidays = new ArrayList<>();

        try {
            Statement statement;
            statement = connection.createStatement();
            ResultSet resultSet;
            resultSet = statement.executeQuery(sql);
            System.out.println(resultSet.toString());
            String uid;
            String dtstart;
            String dtend;
            String summary;
            int i = 0;
            while (resultSet.next()) {
                uid = resultSet.getString("uid");
                dtstart = resultSet.getString("dtstart");
                dtend = resultSet.getString("dtend");
                summary = resultSet.getString("summary");
                System.out.println("Uid : " + uid + " Summary : " + summary);
                output_holidays.add(new Holiday(i, uid, dtstart, dtend, summary));
                i++;
            }
            resultSet.close();
            statement.close();
//            connection.close();
        }
        catch (Exception exception) {
            System.out.println(exception);
        }

        return output_holidays;

    }

    public void updateHolidaysToDB(List<Holiday> holidays) {

        StringBuffer mySql = new StringBuffer(
                "insert into `holidays` (uid, dtstart, dtend, summary) values (?, ?, ?, ?)");
        for (int i = 0; i < holidays.size()-1; i++) {
            mySql.append(", (?, ?, ?, ?)");
        }
        mySql.append(";");
        System.out.println(mySql.toString());
//        Statement statement;

        try {

            PreparedStatement statement = connection.prepareStatement(mySql.toString());

            for (int i = 0; i < holidays.size(); i++) {
                statement.setString((4 * i) + 1, holidays.get(i).getUid());
                statement.setString((4 * i) + 2, holidays.get(i).getDtstart());
                statement.setString((4 * i) + 3, holidays.get(i).getDtend());
                statement.setString((4 * i) + 4, holidays.get(i).getSummary());
            }
            System.out.println(statement.toString());
            statement.executeUpdate();

//            resultSet.close();
            statement.close();
//            connection.close();
        }
        catch (Exception exception) {
            System.out.println(exception);
        }

    }
}
//        // read json
//        try {
//            jsonHandler = new JsonHandler();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        holidays = jsonHandler.getHolidayList();

        // connect to database
//        connection = null;
//        try {
//            // below two lines are used for connectivity.
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            connection = DriverManager.getConnection(
//                    "jdbc:mysql://localhost:3306/hk_holidays",
//                    "root", "og21893");
//
//            System.out.println("Connecting to database ... ");

//            connectToDB();
//
//            // mydb is database
//            // mydbuser is name of database
//            // mydbuser is password of database
//
//            Statement statement;
//            statement = connection.createStatement();
//            ResultSet resultSet;
//            resultSet = statement.executeQuery(
//                    "select * from WHERE holiday_id=0");
//            System.out.println(resultSet.toString());
//            String uid;
//            String summary;
//            while (resultSet.next()) {
//                uid = resultSet.getString("uid");
//                summary = resultSet.getString("summary").trim();
//                System.out.println("Uid : " + uid
//                        + " Summary : " + summary);
//            }
//            resultSet.close();
//            statement.close();
////            connection.close();
//        }
//        catch (Exception exception) {
//            System.out.println(exception);
//        }


//
//
//        // store holiday list in database
//        for (Holiday newHoliday : holidays) {
//            holidayDAO.saveOrUpdate(newHoliday);
//        }
//
//        // retrieve holiday list from database
//        List<Holiday> listContact = holidayDAO.list();


//    }



