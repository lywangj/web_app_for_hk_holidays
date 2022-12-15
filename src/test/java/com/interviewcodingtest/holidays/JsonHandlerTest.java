package com.interviewcodingtest.holidays;

import static org.junit.jupiter.api.Assertions.*;

import com.interviewcodingtest.holidays.model.Holiday;

import org.junit.jupiter.api.Test;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;

class JsonHandlerTest {

    JsonHandler jsonHandler;
    List<Holiday> holidays;

    @Test
    void testSeparateToList() throws IOException, JSONException {
        jsonHandler = new JsonHandler();
        String url = "https://www.1823.gov.hk/common/ical/en.json";
        InputStream is = new URL(url).openStream();

        BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
        String jsonText = JsonHandler.readAll(rd);
        JSONObject json = new JSONObject(jsonText.substring(1));
        jsonHandler.readAllJson(json);

        // check the parsed holiday data from Json
        holidays = jsonHandler.getHolidayList();
        assertEquals(holidays.size(), 51, "Error: invalid holiday counts in Db");
        assertEquals(holidays.get(0).getId(), 1, "Error id in DB");
        assertEquals(holidays.get(0).getUid(), "20210101@1823.gov.hk", "Error uid of the tested holiday in DB");
        assertEquals(holidays.get(0).getDtstart(), "20210101", "Error start date of the tested holiday in DB");
        assertEquals(holidays.get(0).getDtend(), "20210102", "Error end date of the tested holiday in DB");
        assertEquals(holidays.get(0).getSummary(), "The first day of January", "Error summary of the tested holiday in DB");

    }

}