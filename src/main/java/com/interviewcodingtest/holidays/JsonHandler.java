package com.interviewcodingtest.holidays;

import com.interviewcodingtest.holidays.model.Holiday;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;


public class JsonHandler {

    private static List<Holiday> holidayList;


    public JsonHandler() throws IOException {
//        JSONObject json =
        readJsonFromUrl("https://www.1823.gov.hk/common/ical/en.json");
//        System.out.println(json.toString());
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
        JSONArray arr = json.getJSONArray("vcalendar");
        JSONArray events = arr.getJSONObject(0).getJSONArray("vevent");
        int num = events.length();
        ArrayList<Holiday> tmpArrayList = new ArrayList<>();
        int id = 0;
        for (int i=0; i<num; ++i) {
            String currUid = events.getJSONObject(i).getString("uid");
            String currSummary = events.getJSONObject(i).getString("summary");
            JSONArray currDtstartArray = events.getJSONObject(i).getJSONArray("dtstart");
            String currDtstart = currDtstartArray.getString(0);
            JSONArray currDtendArray = events.getJSONObject(i).getJSONArray("dtend");
            String currDtend = currDtendArray.getString(0);
            System.out.println(currUid + " " + currDtstart + " " + currDtend + " " + currSummary);
            tmpArrayList.add(new Holiday(id, currUid, currDtstart, currDtend, currSummary));
        }
        holidayList = tmpArrayList;
    }

    public static void readJsonFromUrl(String url) throws IOException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            System.out.println("check1");
            String jsonText = readAll(rd);
//            System.out.println(jsonText.substring(1,10));
            JSONObject json = new JSONObject(jsonText.substring(1));
            readAllJson(json);
//            System.out.println("check2");
//            return json;
        } finally {
            is.close();
        }
    }
}
