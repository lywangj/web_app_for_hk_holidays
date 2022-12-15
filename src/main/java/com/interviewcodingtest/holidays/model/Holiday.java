package com.interviewcodingtest.holidays.model;


public class Holiday {

    private int id;
    private String uid;
    private String summary;
    private String dtstart;
    private String dtend;

    public Holiday(){}

    public Holiday(int n, String inputUid, String inputDtstart, String inputDtend, String inputSummary){
        id = n;
        uid = inputUid;
        summary = inputSummary;
        dtstart = inputDtstart;
        dtend = inputDtend;
    }

    public int getId() {
        return id;
    }

    public String getUid() {
        return uid;
    }

    public String getSummary() {
        return summary;
    }

    public String getDtstart() {
        return dtstart;
    }

    public String getDtend() {
        return dtend;
    }

    @Override
    public String toString() {
        return String.format(
                "Holiday[uid=%s, dtstart='%s', dtend='%s', summary='%s']",
                uid, dtstart, dtend, summary);
    }

}
