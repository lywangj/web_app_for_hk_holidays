package com.interviewcodingtest.holidays;

import com.interviewcodingtest.holidays.model.Holiday;

import java.util.Properties;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBHandler {

    private Connection connection;
    private String dbName;

    public DBHandler() {}

    public void connectToDB(String requestedDbName){

        Properties properties = new Properties( );
        properties.put( "user", "root" );              // input your username
        properties.put( "password", "og21893" );       // input your password

        dbName = requestedDbName;
        String url = "jdbc:mysql://localhost:3306/" + dbName;
        connection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
//            connection = DriverManager.getConnection( url, "root", "og21893");
            connection = DriverManager.getConnection( url, properties);

            System.out.println("Connecting to database ... ");
        }
        catch (Exception exception) {
            System.out.println(exception);
        }
    }

    public boolean tableExistsSQL(Connection connection, String tableName) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT count(*) "
                + "FROM information_schema.tables "
                + "WHERE TABLE_SCHEMA='" + dbName + "' AND TABLE_NAME= ? "
                + "LIMIT 1;");
        preparedStatement.setString(1, tableName);

        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getInt(1) != 0;
    }

    public void createHolidayTable() {
        try {

            Statement statement;
            statement = connection.createStatement();

            // if the table already exists in database, then drop this table
            if(tableExistsSQL(connection, "holidays")) {
                String sql_dropTable = "drop table `holidays`;";
                statement.executeUpdate(sql_dropTable);
                System.out.println("Successfully Dropped the old table ... ");
            }

            String sql_createTable =
                    "CREATE TABLE `holidays` (\n" +
                            "  `holiday_id` int(11) NOT NULL AUTO_INCREMENT,\n" +
                            "  `uid` varchar(45) NOT NULL,\n" +
                            "  `dtstart` varchar(45) NOT NULL,\n" +
                            "  `dtend` varchar(45) NOT NULL,\n" +
                            "  `summary` varchar(80) NOT NULL,\n" +
                            "  PRIMARY KEY (`holiday_id`)\n" +
                            ");";
            statement.executeUpdate(sql_createTable);
            System.out.println("Successfully Created the holidays table ... ");
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

            int i = 1;
            while (resultSet.next()) {
                String uid, dtstart, dtend, summary;
                uid = resultSet.getString("uid");
                dtstart = resultSet.getString("dtstart");
                dtend = resultSet.getString("dtend");
                summary = resultSet.getString("summary");
                output_holidays.add(new Holiday(i++, uid, dtstart, dtend, summary));
//                i++;
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

        // insert multiple data rows to table
        StringBuffer mySql = new StringBuffer(
                "insert into `holidays` (uid, dtstart, dtend, summary) values (?, ?, ?, ?)");
        mySql.append(", (?, ?, ?, ?)".repeat(Math.max(0, holidays.size() - 1)));
        mySql.append(";");

        try {
            PreparedStatement statement = connection.prepareStatement(mySql.toString());

            for (int i = 0; i < holidays.size(); i++) {
                statement.setString((4 * i) + 1, holidays.get(i).getUid());
                statement.setString((4 * i) + 2, holidays.get(i).getDtstart());
                statement.setString((4 * i) + 3, holidays.get(i).getDtend());
                statement.setString((4 * i) + 4, holidays.get(i).getSummary());
            }
            statement.executeUpdate();

//            resultSet.close();
            statement.close();
//            connection.close();
        }
        catch (Exception exception) {
            System.out.println(exception);
        }

    }

//    public String getDbName() {
//        return dbName;
//    }

}
