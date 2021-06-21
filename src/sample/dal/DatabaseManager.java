package sample.dal;

import sample.bll.User;
import sample.util.PropertyManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private static DatabaseManager instance;
    private String driver;
    private String url;
    private String username;
    private String password;

    private DatabaseManager(){
        PropertyManager.getInstance().setFilemname("db.properties");
        this.driver = PropertyManager.getInstance().readProperty("driver", "oracle.jdbc.OracleDriver");
        this.url = PropertyManager.getInstance().readProperty("url", "jdbc:oracle:thin:@tcif.htl-villach.at:1521/orcl");
        this.username = PropertyManager.getInstance().readProperty("username", "d3a09");;
        this.password = PropertyManager.getInstance().readProperty("password", "d3a09");;
    }

    private Connection createConnection(){
        Connection con = null;
        //Laden des Treibers
        try {
            Class.forName(driver);
            //Erzeugen der Verbindung
            con = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return con;

    }

    public static DatabaseManager getInstance(){
        if(instance == null){
            instance = new DatabaseManager();
        }
        return instance;
    }

    public List<User> getAllUsers(){
        List<User> all = new ArrayList<>();
        Statement stmt;
        ResultSet resultSet;

        String query = "SELECT * FROM user";
        try {
            try(Connection con = createConnection()){ // Wird automatisch am ende von try geschlossen
                //Statement wird erzeugt
                stmt = con.createStatement();
                resultSet = stmt.executeQuery(query);
                //resultset durchiterieren
                while(resultSet.next()){
                    all.add(new User(resultSet.getString(1), resultSet.getString(2),
                            resultSet.getInt(3), resultSet.getInt(4), resultSet.getInt(5), resultSet.getDate(4)));

                }
                return all;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return all;
    }

    public boolean updateUser(User s){
        boolean result = false;
        PreparedStatement preparedStatement;
        String stmt_insert = "UPDATE user SET password=?, WHERE idUser=?";
        try {
            try(Connection con = createConnection()){ // Wird automatisch am ende von try geschlossen
                preparedStatement = con.prepareStatement(stmt_insert);
                preparedStatement.setString(1, s.getPassword());
                preparedStatement.setInt(2, s.getIdUser());


                int rowsUpdated = preparedStatement.executeUpdate();
                if (rowsUpdated > 0) {
                    result = true;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return result;
    }

    public boolean insertUser (User s){
        boolean result = false;
        PreparedStatement preparedStatement;
        String stmt_insert = "INSERT INTO user (username, password) VALUES (?, ?)";
        ResultSet resultSet;
        int id = -1;

        try {
            try(Connection con = createConnection()){ // Wird automatisch am ende von try geschlossen
                preparedStatement = con.prepareStatement(stmt_insert, new String[]{"idUser"});
                preparedStatement.setString(1, s.getUsername());
                preparedStatement.setString(2, s.getPassword());
                preparedStatement.execute();

                resultSet = preparedStatement.getGeneratedKeys();
                if(resultSet.next()){
                    id = resultSet.getInt(1);
                    s.setIdUser(id);
                    result = true;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return result;
    }

    public boolean deleteUser (int id){
        boolean result = false;
        PreparedStatement preparedStatement;
        String stmt_insert = "DELETE FROM user WHERE idUser=?";

        try {
            try(Connection con = createConnection()){ // Wird automatisch am ende von try geschlossen
                preparedStatement = con.prepareStatement(stmt_insert);
                preparedStatement.setInt(1, id);
                int rowsDeleted = preparedStatement.executeUpdate();
                if (rowsDeleted > 0) {
                    result =  true;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return result;
    }

    public User getById(int id){
        User s = null;
        ResultSet resultSet;
        String stmt = "SELECT * FROM user WHERE idUser=?";
        PreparedStatement preparedStatement;

        try {
            try(Connection con = createConnection()){ // Wird automatisch am ende von try geschlossen
                //Statement wird erzeugt
                preparedStatement = con.prepareStatement(stmt);
                preparedStatement.setInt(1, id);
                resultSet = preparedStatement.executeQuery();
                if(resultSet.next()){
                    s = new User(resultSet.getString(1), resultSet.getString(2),
                            resultSet.getInt(3), resultSet.getInt(4), resultSet.getInt(5), resultSet.getDate(4));
                }

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return s;
    }
}
