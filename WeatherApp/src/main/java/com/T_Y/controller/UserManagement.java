package com.T_Y.controller;

import com.T_Y.model.User;
import com.T_Y.model.UsersDB;
import com.T_Y.view.*;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.function.IntPredicate;
import java.util.regex.Pattern;

public class UserManagement {
    public final Pattern textPattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$");
    private FavoriteCitySearch dialog;
    private DeleteUserFromDbView dialog2;
    private boolean isRealUser = false;

    public UserManagement() {
    }

    public boolean isPasswordValid(String value) {
        return containsNumber(value) && (containsLowerCase(value) || containsUpperCase(value));

    }

    private boolean containsLowerCase(String value) {
        return contains(value, i -> Character.isLetter(i) && Character.isLowerCase(i));
    }

    private boolean containsUpperCase(String value) {
        return contains(value, i -> Character.isLetter(i) && Character.isUpperCase(i));
    }

    private boolean containsNumber(String value) {
        return contains(value, Character::isDigit);
    }

    private boolean contains(String value, IntPredicate predicate) {
        return value.chars().anyMatch(predicate);
    }

    private void passwordCheck(User tempUser) {
        String tempPassword = tempUser.getPassword();
        if (tempPassword.length() > 8 || tempPassword.length() < 4) {
            throw new ArithmeticException("Password has to be between 4-8 chars ");
        }
        //checking if the password contains at least one digit and one letter
        if (!isPasswordValid(tempPassword)) {
            throw new ArithmeticException("Password has to include at least one digit and one letter ");
        }
    }

    public boolean registerUser(User tempUser) throws SQLException, ClassNotFoundException {
        try {
            try {
                passwordCheck(tempUser);
                System.out.println("user password meets requirements");
                new UsersDB().registerUserToDB(tempUser);
            } catch (Exception e2) {
                JOptionPane.showMessageDialog(new JFrame(), e2.getMessage(), "Dialog", JOptionPane.ERROR_MESSAGE);
                if (e2.getMessage() == "Registration Completed") {
                    try {
                        new UsersDB().insertFavoriteToDB(tempUser);

                    } catch (Exception e1) {
                        if (e1.getMessage() == "favorites table was updated") {
                            UserCustomizedScreen userCustomizedScreen = new UserCustomizedScreen(tempUser);
                            userCustomizedScreen.setVisible(true);
                        }
                    }

                    return true;

                }
            }
        } catch (Exception e1) {
            System.out.println(e1);
        }
        return false;
    }

    public boolean loginUser(User tempUser) throws SQLException, ClassNotFoundException, IOException {
        try {
            new UsersDB().loginUserToDB(tempUser);
            UserCustomizedScreen userCustomizedScreen = new UserCustomizedScreen(tempUser);
            userCustomizedScreen.setVisible(true);
        } catch (Exception e1) {
            System.out.println(e1);
            JOptionPane.showMessageDialog(new JFrame(), e1.getMessage(), "Dialog", JOptionPane.INFORMATION_MESSAGE);
            if (e1.getMessage() == "Login Allowed") {

                UserCustomizedScreen userCustomizedScreen = new UserCustomizedScreen(tempUser);
                userCustomizedScreen.setVisible(true);
                return true;
            }

        }
        return false;
    }

    public boolean loginAdmin(User tempUser) throws SQLException, ClassNotFoundException, IOException {
        try {
            new UsersDB().loginAdminToDB(tempUser);
            AdminCustomizedScreen adminCustomizedScreen = new AdminCustomizedScreen(tempUser);
            adminCustomizedScreen.setVisible(true);
        } catch (Exception e1) {
            System.out.println(e1);
            JOptionPane.showMessageDialog(new JFrame(), e1.getMessage(), "Dialog", JOptionPane.ERROR_MESSAGE);
            if (e1.getMessage() == "Admin Login Allowed") {
                AdminCustomizedScreen adminCustomizedScreen = new AdminCustomizedScreen(tempUser);
                adminCustomizedScreen.setVisible(true);
                return true;
            }

        }
        return false;
    }

    public String[] showFavorites(User tempUser) {
        try {
            try {
                return (new UsersDB().showUserDbFavorites(tempUser));
            } catch (Exception e2) {
                System.out.println(e2);
            }
        } catch (Exception e1) {
            System.out.println(e1);
        }
        return tempUser.getFavoritesArr();
    }

    public boolean editFavorites(User tempUser, char index) throws SQLException, ClassNotFoundException, IOException, ArithmeticException {
        try {
            dialog = new FavoriteCitySearch(tempUser, index);
            dialog.setVisible(true);
        } catch (Exception e1) {
            return true;
        }
        return false;
    }


    public boolean resetUserPassword(User tempUser) throws SQLException, ClassNotFoundException, IOException {
        try {
            isRealUser = new UsersDB().getUserSecretInfo(tempUser);
            if (isRealUser) {
                return true;
            }
        } catch (Exception e1) {
            JOptionPane.showMessageDialog(new JFrame(), e1.getMessage(), "Dialog", JOptionPane.ERROR_MESSAGE);

            return false;
        }
        return false;
    }
}







