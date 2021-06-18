package sample.bll;

import java.sql.Date;

public class User {
    private String username;
    private String password;
    private int highscore;
    private Date lastOnline;
    private int idUser;

    public User(String username, String password, int highscore, Date lastOnline, int idUser) {
        this.username = username;
        this.password = password;
        this.highscore = highscore;
        this.lastOnline = lastOnline;
        this.idUser = idUser;
    }

    public User(String username, String password, int highscore, Date lastOnline) {
        this.username = username;
        this.password = password;
        this.highscore = highscore;
        this.lastOnline = lastOnline;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
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

    public int getHighscore() {
        return highscore;
    }

    public void setHighscore(int highscore) {
        this.highscore = highscore;
    }

    public Date getLastOnline() {
        return lastOnline;
    }

    public void setLastOnline(Date lastOnline) {
        this.lastOnline = lastOnline;
    }
}
