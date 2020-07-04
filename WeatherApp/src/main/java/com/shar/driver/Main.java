package com.shar.driver;

import com.shar.view.LookAndFeel;
import com.shar.view.WelcomeWindow;

public class Main {
    public static void main(String[] args) {
        try {
            LookAndFeel.tweakPLAF();
            new WelcomeWindow();

        } catch (Exception e) {
            System.out.println(e);
        }


    }
}
