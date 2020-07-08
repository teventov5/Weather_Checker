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
    private JFrame errorMessage;

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
                if (e2.getMessage() == "Registration Completed") {
                    JOptionPane.showMessageDialog(errorMessage, e2.getMessage(), "Dialog", JOptionPane.INFORMATION_MESSAGE);
                    try {
                        new UsersDB().insertFavoriteToDB(tempUser);

                    } catch (Exception e1) {
                        if (e1.getMessage() == "favorites table was updated") {
                            return true;
                        }
                    }
                }
                else
                    JOptionPane.showMessageDialog(errorMessage, e2.getMessage(), "Dialog", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return false;
    }

    public boolean loginUser(User tempUser) throws SQLException, ClassNotFoundException, IOException {
        try {
            if(new UsersDB().loginUserToDB(tempUser)) {
                JOptionPane.showMessageDialog(errorMessage, "Login Allowed", "Dialog", JOptionPane.INFORMATION_MESSAGE);
                new UserCustomizedScreen(tempUser);
                return true;
            }
        } catch (Exception e1) {
           e1.printStackTrace();
            JOptionPane.showMessageDialog(errorMessage, e1.getMessage(), "Dialog", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    public boolean loginAdmin(User tempUser) throws SQLException, ClassNotFoundException, IOException {
        try {
           boolean loginSuccess= new UsersDB().loginAdminToDB(tempUser);
           return loginSuccess;
        }
        catch (ArithmeticException e1) {
            e1.printStackTrace();
            }
        return false;
    }

    public String[] showFavorites(User tempUser) {
        try {
            try {
                return (new UsersDB().showUserDbFavorites(tempUser));
            } catch (Exception e2) {
               e2.printStackTrace();;
            }
        } catch (Exception e1) {
           e1.printStackTrace();;
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
            JOptionPane.showMessageDialog(errorMessage, e1.getMessage(), "Dialog", JOptionPane.ERROR_MESSAGE);

            return false;
        }
        return false;
    }
}







