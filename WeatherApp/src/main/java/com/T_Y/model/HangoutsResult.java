package com.T_Y.model;

public class HangoutsResult {

    private String headline;
    private String rating;
    private String link;

    public HangoutsResult() {
        this.setHeadline("test");
        this.setRating("test2");
        this.setLink("test3");
    }

    public HangoutsResult(String headline, String rating) {
        setHeadline(headline);
        setRating(rating);
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getHeadline() {
        return headline;
    }

    ;

    public void setHeadline(String headline) {
        this.headline = headline;
    }
}
