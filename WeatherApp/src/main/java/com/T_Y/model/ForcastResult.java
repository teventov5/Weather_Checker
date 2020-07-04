package com.T_Y.model;

public class ForcastResult {
    private String date;
    private String headline;
    private String minTemperature;
    private String iconNumber;
    private long time;
    private HangoutsResult[] hangoutsResultsArr = new HangoutsResult[12];

    public ForcastResult(String[] finalResult) {
        this.setDate(finalResult[0]);
        this.setHeadline(finalResult[1]);
        this.setMinTemperature(finalResult[2]);
        this.setIconNumber(finalResult[3]);
        this.setTime(Long.parseLong(finalResult[4]));
    }

    public ForcastResult(HangoutsResult[] hangoutArr) {
        this.setHangoutsResultsArr(hangoutArr);
    }

    public HangoutsResult[] getHangoutsResultsArr() {
        return hangoutsResultsArr;
    }

    public void setHangoutsResultsArr(HangoutsResult[] hangoutsResultsArr) {
        this.hangoutsResultsArr = hangoutsResultsArr;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(String minTemperature) {
        this.minTemperature = minTemperature;
    }

    public String getIconNumber() {
        return iconNumber;
    }

    public void setIconNumber(String iconNumber) {
        this.iconNumber = iconNumber;
    }

    public void print() {
        System.out.println(this.date);
        System.out.println(this.headline);
        System.out.println(this.minTemperature);
        System.out.println(this.getIconNumber());

    }

}
