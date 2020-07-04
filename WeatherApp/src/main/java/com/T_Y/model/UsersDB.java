package com.T_Y.model;

import com.T_Y.controller.DBTablePrinter;

import java.sql.*;

public class UsersDB {
    public UsersDB() {
    }

    public void insertFavoriteToDB(User tempUser) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String[] favArr = { "null", "empty city slot#1", "empty city slot#2", "empty city slot#3", "empty city slot#4", "empty city slot#5", "empty city slot#6" };
        int i = 1;
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/weatherapp?autoReconnect=true&useSSL=false", "root", "");
        PreparedStatement ps = conn.prepareStatement("insert into usersFavorites (username,city1,city2,city3,city4,city5,city6)  values (?,?,?,?,?,?,?)");
        ps.setString(1, tempUser.getUsername());
        ps.setString(2, (favArr[i++]));
        ps.setString(3, (favArr[i++]));
        ps.setString(4, (favArr[i++]));
        ps.setString(5, (favArr[i++]));
        ps.setString(6, (favArr[i++]));
        ps.setString(7, (favArr[i++]));
        int updateFlag = ps.executeUpdate();
        if (updateFlag > 0) {
            throw new ArithmeticException("favorites table was updated");
        } else {
            throw new ArithmeticException("problem updating favorites table");
        }
    }

    public void registerUserToDB(User tempUser) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/weatherapp?autoReconnect=false&useSSL=false", "root", "");
        PreparedStatement sel = conn.prepareStatement("select username from users where username=?");
        ResultSet rs = null;
        sel.setString(1, tempUser.getUsername());
        rs = sel.executeQuery();
        if (rs.next()) {
            throw new ArithmeticException("username already exists");
        }
        PreparedStatement ps = conn.prepareStatement("insert into users (username,password,secretQuestion,secretAnswer)  values (?,?,?,?)");
        ps.setString(1, tempUser.getUsername());
        ps.setString(2, tempUser.getPassword());
        ps.setString(3, tempUser.getSecretQuestion());
        ps.setString(4, tempUser.getSecretAnswer());
        int updateFlag = ps.executeUpdate();
        if (updateFlag > 0) {

            throw new ArithmeticException("Registration Completed");

        } else {
            throw new ArithmeticException("registration failed");
        }
    }

    public void loginUserToDB(User tempUser) throws ClassNotFoundException, SQLException, ArithmeticException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/weatherapp?autoReconnect=true&useSSL=false", "root", "");
        PreparedStatement sel = conn.prepareStatement("select username from users where username=? AND password=?");
        ResultSet rs = null;
        sel.setString(1, tempUser.getUsername());
        sel.setString(2, tempUser.getPassword());
        rs = sel.executeQuery();
        if (!rs.next()) {
            throw new ArithmeticException("username or password is wrong");
        } else {
            throw new ArithmeticException("Login Allowed");
        }

    }

    public void loginAdminToDB(User tempUser) throws ClassNotFoundException, SQLException, ArithmeticException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/weatherapp?autoReconnect=true&useSSL=false", "root", "");
        PreparedStatement sel = conn.prepareStatement("select username from admins where username=? AND password=?");
        ResultSet rs = null;
        sel.setString(1, tempUser.getUsername());
        sel.setString(2, tempUser.getPassword());
        rs = sel.executeQuery();
        if (!rs.next()) {
            throw new ArithmeticException("username or password is wrong");
        } else {
            throw new ArithmeticException("Admin Login Allowed");
        }

    }

    public void showUsersTable() throws ClassNotFoundException, SQLException, ArithmeticException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/weatherapp?autoReconnect=true&useSSL=false", "root", "");
        PreparedStatement sel = conn.prepareStatement("select * from users");
        ResultSet rs = null;
        rs = sel.executeQuery();
        DBTablePrinter.printResultSet(rs);
    }

    public boolean deleteUserFromDB(User tempUser) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/weatherapp?autoReconnect=true&useSSL=false", "root", "");
        PreparedStatement ps = conn.prepareStatement("delete from users where username=?");
        ps.setString(1, tempUser.getUsername());
        int updateFlag = ps.executeUpdate();
        if (updateFlag > 0) {
            return true;
        } else {
            return false;
        }

    }

    public String[] showUserDbFavorites(User tempUser) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/weatherapp?autoReconnect=false&useSSL=false", "root", "");
        PreparedStatement sel = conn.prepareStatement("select city1,city2,city3,city4,city5,city6 from usersfavorites where username=?");
        ResultSet rs = null;
        sel.setString(1, tempUser.getUsername());


        rs = sel.executeQuery();
        if (rs.next()) {
            String[] favoritesArr = new String[7];
            for (int i = 1; i < 7; i++) {
                favoritesArr[i] = rs.getString(i);
            }
            return favoritesArr;
        } else {
            throw new ArithmeticException("no such User");
        }
    }

    public boolean editUserDbFavorites(User tempUser, char index, City tempCT) throws ClassNotFoundException, SQLException, ArithmeticException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/weatherapp?autoReconnect=true&useSSL=false", "root", "");
        String updateString = new String("update usersFavorites set city");
        updateString = updateString + index + "='" + tempCT.getCityName() + "' ";
        PreparedStatement upd = conn.prepareStatement(updateString + "where username=?");
        upd.setString(1, tempUser.getUsername());
        int updateFlag = upd.executeUpdate();
        if (updateFlag > 0) {
            return true;
        } else {
            return false;
        }

    }

    public boolean getUserSecretInfo(User tempUser) throws ClassNotFoundException, SQLException, ArithmeticException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/weatherapp?autoReconnect=false&useSSL=false", "root", "");
        PreparedStatement sel = conn.prepareStatement("select secretQuestion,secretAnswer from users where username=?");
        ResultSet rs = null;
        sel.setString(1, tempUser.getUsername());


        rs = sel.executeQuery();
        if (rs.next()) {
            tempUser.setSecretQuestion(rs.getString(1));
            tempUser.setSecretAnswer(rs.getString(2));
            return true;
        }
        return false;
    }

    public boolean usernameSearch(User tempUser) throws ClassNotFoundException, SQLException, ArithmeticException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/weatherapp?autoReconnect=false&useSSL=false", "root", "");
        PreparedStatement sel = conn.prepareStatement("select username from users where username=?");
        ResultSet rs = null;
        sel.setString(1, tempUser.getUsername());


        rs = sel.executeQuery();
        if (rs.next()) {
            return true;
        }
        return false;
    }

    public void updateUserPasswordToDB(User tempUser, String password) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/weatherapp?autoReconnect=true&useSSL=false", "root", "");
        PreparedStatement ps = conn.prepareStatement("update users set password=? where username=?");
        ps.setString(1, password);
        ps.setString(2, tempUser.getUsername());
        int updateFlag = ps.executeUpdate();
        if (updateFlag > 0) {
            throw new ArithmeticException("password was updated");
        } else {
            throw new ArithmeticException("problem updating password for username " + tempUser.getUsername());
        }

    }
}
