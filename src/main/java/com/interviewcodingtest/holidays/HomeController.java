package com.interviewcodingtest.holidays;

//import org.apache.tomcat.util.json.JSONParser;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.servlet.ModelAndView;
import java.io.IOException;
import java.sql.*;
//import java.io.FileReader;
//import java.io.IOException;
import java.util.List;

//import com.interviewcodingtest.holidays.dao.ContactDAO;
import com.interviewcodingtest.holidays.model.Holiday;

@Controller
public class HomeController {


//    @RequestMapping("/projects")
////    @ResponseStatus(value = HttpStatus.OK)
//    public void add()
//    {
//        System.out.println("Hello");
//    }


//    @GetMapping(path="/getdata")
//    public ModelAndView getData() {
//
//        ModelAndView modelAndView = new ModelAndView("")
//
//        return new ModelAndView;
//    }


//    private JsonHandler jsonHandler;
//    private List<Holiday> holidaysFrom;
    private DBHandler dbHandler;

//    @Autowired
//    private ContactDAO holidayDAO;
//    private DBHandler dbHandler;

    @GetMapping(path="/getdata")
    public String getData() {
        System.out.println("Hello");

        List<Holiday> holidaysFromJson;
        dbHandler = new DBHandler();
        String dbName = "hk_holidays";

        // read json
        try {

            JsonHandler jsonHandler = new JsonHandler();
            holidaysFromJson = jsonHandler.getHolidayList();

            dbHandler.connectToDB(dbName);

            dbHandler.updateHolidaysToDB(holidaysFromJson);

            String sql = "select * from holidays;";

            List<Holiday> outputHolidays = dbHandler.extractHolidaysFromDB(sql);
            System.out.println("outputHolidays size : " + outputHolidays.size());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "getdata";
    }

    @GetMapping("/")
    public String homepage(ModelMap model) {

//        Object obj = new JSONParser().parse(new FileReader("JSONExample.json"));


//        model.addAttribute("uid", "20210101@1823.gov.hk");
//        model.addAttribute("dtstart", "20210101");
//        model.addAttribute("dtend", "20210102");
//        model.addAttribute("summary", "The first day of January");
        return "home";
    }


}
