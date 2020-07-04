package com.shar.controller;

import com.shar.model.*;

public class HangoutSearch {

    public HangoutSearch() {
    }

    public ForcastResult searchForCityResult(String cityUserInput) {
        try {
            try {
                City ct = new City(cityUserInput, "0000");
                new CityCodeSearch(ct);
                new Repository().doHttpGet(ct);
                //this.setCityName(ct.getCityName());
                //this.setCityCode(ct.getCityCode());
                return ct.getResult();
            } catch (Exception e2) {
                System.out.println("Api reached max get requests");
            }

        } catch (Exception e1) {
            System.out.println("Unknown Error");
        }
        return null;
    }


}
