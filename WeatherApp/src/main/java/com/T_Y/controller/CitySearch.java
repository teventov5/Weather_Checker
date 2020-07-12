package com.T_Y.controller;

import com.T_Y.model.*;

import javax.swing.*;

public class CitySearch {
    private JFrame errorMessage;

    public CitySearch() {
    }

    public ForcastResult searchForCityResult(String cityUserInput) {
        try {
            try {
                City ct = new City(cityUserInput, "0000");
                if(ct.getCityName().contains("empty city slot#"))
                    throw new ArithmeticException("Change list default value to an actual city");
                new CityCodeSearch(ct);
                new Repository().doHttpGet(ct);
                new Repository().getHangouts(ct);
                return ct.getResult();
            } catch (Exception e2) {
                if(e2.getMessage().contains("The allowed number of requests has been exceeded"))
                JOptionPane.showMessageDialog(errorMessage, "The allowed number of Api requests has been exceeded", "Dialog", JOptionPane.ERROR_MESSAGE);
                else
                    JOptionPane.showMessageDialog(errorMessage, "There is a problem getting weather data for the specified city", "Dialog", JOptionPane.ERROR_MESSAGE);

            }

        } catch (Exception e1) {
            System.out.println("Unknown Error");
        }
        return null;
    }


}
