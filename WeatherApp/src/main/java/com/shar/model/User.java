package com.shar.model;

import com.shar.controller.UserManagement;

public class User {
    protected String[] favoritesArr = new String[] { "Empty City Slot", "Empty City Slot", "Empty City Slot", "Empty City Slot", "Empty City Slot", "Empty Slot" };
    private String username;
    private String password;
    private String secretQuestion;
    private String secretAnswer;

    public User(String username, String password, String secretQuestion, String secretAnswer) {
        this.setUsername(username);
        this.setPassword(password);
        this.setSecretQuestion(secretQuestion);
        this.setSecretAnswer(secretAnswer);
    }

    public User(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSecretAnswer() {
        return secretAnswer;
    }

    public void setSecretAnswer(String secretAnswer) {
        this.secretAnswer = secretAnswer;
    }

    public String getSecretQuestion() {
        return secretQuestion;
    }

    public void setSecretQuestion(String secretQuestion) {
        this.secretQuestion = secretQuestion;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String[] getFavoritesArr() {
        return (new UserManagement().showFavorites(this));
    }

    public void setFavoritesArr(String[] favoritesArr) {
        this.favoritesArr = favoritesArr;
    }
}
