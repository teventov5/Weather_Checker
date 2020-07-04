public class hangoutsResult {

    private String headline;
    private String rating;
    private String link;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getRating() {
        return rating;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
public hangoutsResult(){
        this.setHeadline("test");
        this.setRating("test2");
        this.setLink("test3");
};

    public hangoutsResult(String headline,String rating){
        setHeadline(headline);
        setRating(rating);
    }
}
