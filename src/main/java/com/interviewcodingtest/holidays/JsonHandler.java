package com.interviewcodingtest.holidays;

import com.interviewcodingtest.holidays.model.Holiday;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;


public class JsonHandler {

    private static List<Holiday> holidayList;

    public JsonHandler() throws IOException {

        System.out.println("Start reading HK Holiday Json file ... ");
        readJsonFromUrl("https://www.1823.gov.hk/common/ical/en.json");
        System.out.println("Extract " + holidayList.size() + " items in total from Holiday Json");

        if (holidayList.size() == 0) {
            throw new IOException("ERROR: No holiday item is found from Json");
        }else {
            System.out.println("Successfully pared the Json file");
        }
    }

    public List<Holiday> getHolidayList(){
        return holidayList;
    }

    public static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static void readAllJson(JSONObject json) {

        holidayList = new ArrayList<>();

        JSONArray arr = json.getJSONArray("vcalendar");
        JSONArray events = arr.getJSONObject(0).getJSONArray("vevent");

        int num = events.length(), id = 1;
        for (int i=0; i<num; ++i) {
            String currUid = events.getJSONObject(i).getString("uid");
            String currSummary = events.getJSONObject(i).getString("summary");
            JSONArray currDtstartArray = events.getJSONObject(i).getJSONArray("dtstart");
            String currDtstart = currDtstartArray.getString(0);
            JSONArray currDtendArray = events.getJSONObject(i).getJSONArray("dtend");
            String currDtend = currDtendArray.getString(0);

            // add a new Holiday object to list
            holidayList.add(new Holiday(id, currUid, currDtstart, currDtend, currSummary));
        }
    }

    public static void readJsonFromUrl(String url) throws IOException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText.substring(1));
            // parse all the Holiday item in Json
            readAllJson(json);
        } finally {
            is.close();
        }
    }
}
