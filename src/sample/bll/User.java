package sample.bll;

import java.sql.Date;
import java.util.Objects;

public class User {

    private String username;
    private String password;
    private int highscoreSpeed;
    private int highscoreAccuracy;
    private int highscorePrecision;
    private Date lastOnline;

    public User(String username, String password, int highscoreSpeed, int highscoreAccuracy, int highscorePrecision, Date lastOnline) {
        this.username = username;
        this.password = password;
        this.highscoreSpeed = highscoreSpeed;
        this.highscoreAccuracy = highscoreAccuracy;
        this.highscorePrecision = highscorePrecision;
        this.lastOnline = lastOnline;

    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.highscoreSpeed = 0;
        this.highscoreAccuracy = 0;
        this.highscorePrecision = 0;
        long millis=System.currentTimeMillis();
        java.sql.Date date =new java.sql.Date(millis);
        this.lastOnline = date;
    }

    public User(String username, String password, Date lastOnline) {
        this.username = username;
        this.password = password;
        this.lastOnline = lastOnline;
    }

    public int getHighscoreSpeed() {
        return highscoreSpeed;
    }

    public void setHighscoreSpeed(int highscoreSpeed) {
        this.highscoreSpeed = highscoreSpeed;
    }

    public int getHighscoreAccuracy() {
        return highscoreAccuracy;
    }

    public void setHighscoreAccuracy(int highscoreAccuracy) {
        this.highscoreAccuracy = highscoreAccuracy;
    }

    public int getHighscorePrecision() {
        return highscorePrecision;
    }

    public void setHighscorePrecision(int highscorePrecision) {
        this.highscorePrecision = highscorePrecision;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLastOnline() {
        return lastOnline;
    }

    public void setLastOnline(Date lastOnline) {
        this.lastOnline = lastOnline;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return username.equals(user.username) &&
                password.equals(user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }
}
