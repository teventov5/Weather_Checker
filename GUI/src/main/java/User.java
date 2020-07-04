public class User {
    private String username;
    private String password;
    private String secretQuestion;
    private String secretAnswer;
    protected String[] favoritesArr=new String[]{"Empty City Slot","Empty City Slot","Empty City Slot","Empty City Slot","Empty City Slot","Empty Slot"};

    public User(String username,String password,String secretQuestion,String secretAnswer){
        this.setUsername(username);
        this.setPassword(password);
        this.setSecretQuestion(secretQuestion);
        this.setSecretAnswer(secretAnswer);
    }
    public User(String username,String password)
    {
        this.setUsername(username);
        this.setPassword(password);
    }

    public String getPassword() {
        return password;
    }
    public String getSecretAnswer() {
        return secretAnswer;
    }
    public String getSecretQuestion() {
        return secretQuestion;
    }
    public String getUsername() {
        return username;
    }

    public String[] getFavoritesArr() {
        return (new userManagement().showFavorites(this));
    }

    public void setFavoritesArr(String[] favoritesArr) {
        this.favoritesArr = favoritesArr;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public void setSecretAnswer(String secretAnswer) {
        this.secretAnswer = secretAnswer;
    }
    public void setSecretQuestion(String secretQuestion) {
        this.secretQuestion = secretQuestion;
    }
    public void setUsername(String username) {
        this.username = username;
    }
}
