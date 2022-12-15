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

//    public void setId(int id) {
//        this.id = id;
//    }

    public String getUid() {
        return uid;
    }

//    public void setUid(String uid) {
//        this.uid = uid;
//    }

    public String getSummary() {
        return summary;
    }

//    public void setSummary(String summary) {
//        this.summary = summary;
//    }

    public String getDtstart() {
        return dtstart;
    }

//    public void setDtstart(String dtstart) {
//        this.dtstart = dtstart;
//    }

    public String getDtend() {
        return dtend;
    }

//    public void setDtend(String dtend) {
//        this.dtend = dtend;
//    }

    @Override
    public String toString() {
        return String.format(
                "Holiday[uid=%s, dtstart='%s', dtend='%s', summary='%s']",
                uid, dtstart, dtend, summary);
    }

}
