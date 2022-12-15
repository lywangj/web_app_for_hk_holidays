package com.interviewcodingtest.holidays;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;

import java.util.List;

import com.interviewcodingtest.holidays.model.Holiday;

@Controller
public class HomeController {

    private DBHandler dbHandler;

//    getdata?StartDate=20210101&EndDate=20210102
    @GetMapping(path="/filterdata")
    public String filterData(@RequestParam(name = "StartDate") String startDate,
                             @RequestParam(name = "EndDate") String endDate, Model model) {

        System.out.println("Received a get request with Filtering the data from " + startDate + " to " + endDate);

        String sql = "select * from `holidays` where dtstart>='" + startDate + "' and dtend<='" + endDate + "';";

        List<Holiday> outputHolidays = dbHandler.extractHolidaysFromDB(sql);
        System.out.println("Select filtered Holidays size : " + outputHolidays.size());

        // Set models to the filtered data
        model.addAttribute("holidays", outputHolidays);
        return "home";
    }

    @GetMapping(path="/getdata")
    public String getData(Model model) {

        List<Holiday> holidaysFromJson;
        dbHandler = new DBHandler();
        String dbName = "hk_holidays";

        try {
            // Read json
            JsonHandler jsonHandler = new JsonHandler();
            holidaysFromJson = jsonHandler.getHolidayList();

            // Connect and store data to database
            dbHandler.connectToDB(dbName);
            dbHandler.createHolidayTable();
            dbHandler.updateHolidaysToDB(holidaysFromJson);

            // Select all data from database
            String sql = "select * from holidays;";
            List<Holiday> outputHolidays = dbHandler.extractHolidaysFromDB(sql);
            System.out.println("Select output Holidays size : " + outputHolidays.size());

            // Set models to the selected data
            model.addAttribute("holidays", outputHolidays);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "home";
    }

    @GetMapping("/")
    public String homepage(ModelMap model) {

        return "home";
    }


}
