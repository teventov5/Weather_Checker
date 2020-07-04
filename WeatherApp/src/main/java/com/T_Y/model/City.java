package com.T_Y.model;

public class City {
    private String cityName;
    private String cityCode;
    private ForcastResult result;

    public City(String cityName, String cityCode, ForcastResult result) {
        setCityName(cityName);
        setCityCode(cityCode);
        setResult(result);
    }

    public City(String cityName, String cityCode) {
        setCityName(cityName);
        setCityCode(cityCode);
    }

    public City() {
        setCityName("Holon");
        setCityCode("215838");
    }

    public ForcastResult getResult() {
        return result;
    }

    public void setResult(ForcastResult result) {
        this.result = result;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }
}
