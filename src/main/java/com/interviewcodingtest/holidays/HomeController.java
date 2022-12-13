package com.interviewcodingtest.holidays;

import org.apache.tomcat.util.json.JSONParser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import java.io.FileReader;
import java.io.IOException;

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

    @GetMapping(path="/getdata")
    public String getData() {
        System.out.println("Hello");

        // read json
        try {
            JsonHandler jsonHandler = new JsonHandler();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // store in data
        // send back to html

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
