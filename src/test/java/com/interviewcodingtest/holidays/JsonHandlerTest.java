package com.interviewcodingtest.holidays;

import static org.junit.jupiter.api.Assertions.*;

import org.json.JSONArray;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;

class JsonHandlerTest {

    JsonHandler jsonHandler;

    @Test
    void testSeparateToList() throws IOException, JSONException {
        String url = "https://www.1823.gov.hk/common/ical/en.json";
        InputStream is = new URL(url).openStream();
//        System.out.println(json.toString());
        BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
        String jsonText = JsonHandler.readAll(rd);
//        System.out.println(jsonText);
        JSONObject json = new JSONObject(jsonText.substring(1));
        JsonHandler.readAllJson(json);
//        System.out.println(json);
//        JSONArray arr = json.getJSONArray("vcalendar");
//        JSONArray events = arr.getJSONObject(0).getJSONArray("vevent");
//        int num = events.length();
//        for (int i=0; i<num; ++i) {
//            System.out.println(events.getJSONObject(i).getString("uid"));
//        }
    }

}