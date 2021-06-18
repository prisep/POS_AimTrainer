package sample.dal;

//import sample.bll.User;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class DatabaseManager {
//    private static DatabaseManager instance;
//    private String driver;
//    private String url;
//    private String username;
//    private String password;
//
//    private DatabaseManager(){
//        PropertyMa.getInstance().setFilemname("db.properties");
//        this.driver = PropertyManager.getInstance().readProperty("driver", "oracle.jdbc.OracleDriver");
//        this.url = PropertyManager.getInstance().readProperty("url", "jdbc:oracle:thin:@tcif.htl-villach.at:1521/orcl");
//        this.username = PropertyManager.getInstance().readProperty("username", "d3a09");;
//        this.password = PropertyManager.getInstance().readProperty("password", "d3a09");;
//    }
//
//    private Connection createConnection(){
//        Connection con = null;
//        //Laden des Treibers
//        try {
//            Class.forName(driver);
//            //Erzeugen der Verbindung
//            con = DriverManager.getConnection(url, username, password);
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//        }
//        return con;
//
//    }
//
//    public static DatabaseManager getInstance(){
//        if(instance == null){
//            instance = new DatabaseManager();
//        }
//        return instance;
//    }

//    public List<User> getAllUsers(){
//        List<User> all = new ArrayList<>();
//        Statement stmt;
//        ResultSet resultSet;
//
//        String query = "SELECT * FROM user";
//        try {
//            try(Connection con = createConnection()){ // Wird automatisch am ende von try geschlossen
//                //Statement wird erzeugt
//                stmt = con.createStatement();
//                resultSet = stmt.executeQuery(query);
//                //resultset durchiterieren
//                while(resultSet.next()){
//                    allStudents.add(new Student(resultSet.getInt(1), resultSet.getInt(2),
//                            resultSet.getString(3), resultSet.getString(4)));
//
//                }
//                return allStudents;
//            }
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//
//        return allStudents;
//    }
//
//    public boolean updateStudent(Student s){
//        boolean result = false;
//        PreparedStatement preparedStatement;
//        String stmt_insert = "UPDATE student SET catalognr=?, firstName=?, lastName=? WHERE idStudent=?";
//        try {
//            try(Connection con = createConnection()){ // Wird automatisch am ende von try geschlossen
//                preparedStatement = con.prepareStatement(stmt_insert);
//                preparedStatement.setInt(1, s.getCatalognr());
//                preparedStatement.setString(2, s.getFirstName());
//                preparedStatement.setString(3, s.getLastname());
//                preparedStatement.setInt(4, s.getIdStudent());
//
//
//                int rowsUpdated = preparedStatement.executeUpdate();
//                if (rowsUpdated > 0) {
//                    result = true;
//                }
//            }
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//
//        return result;
//    }
//
//    public boolean insertStudent (Student s){
//        boolean result = false;
//        PreparedStatement preparedStatement;
//        String stmt_insert = "INSERT INTO student (catalognr, firstName, lastName) VALUES (?, ?, ?)";
//        ResultSet resultSet;
//        int id = -1;
//
//        try {
//            try(Connection con = createConnection()){ // Wird automatisch am ende von try geschlossen
//                preparedStatement = con.prepareStatement(stmt_insert, new String[]{"idStudent"});
//                preparedStatement.setInt(1, s.getCatalognr());
//                preparedStatement.setString(2, s.getFirstName());
//                preparedStatement.setString(3, s.getLastname());
//                preparedStatement.execute();
//
//                resultSet = preparedStatement.getGeneratedKeys();
//                if(resultSet.next()){
//                    id = resultSet.getInt(1);
//                    s.setIdStudent(id);
//                    result = true;
//                }
//            }
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//
//        return result;
//    }
//
//    public boolean deleteStudent (int id){
//        boolean result = false;
//        PreparedStatement preparedStatement;
//        String stmt_insert = "DELETE FROM student WHERE idStudent=?";
//
//        try {
//            try(Connection con = createConnection()){ // Wird automatisch am ende von try geschlossen
//                preparedStatement = con.prepareStatement(stmt_insert);
//                preparedStatement.setInt(1, id);
//                int rowsDeleted = preparedStatement.executeUpdate();
//                if (rowsDeleted > 0) {
//                    result =  true;
//                }
//            }
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//
//        return result;
//    }
//
//    public Student getById(int id){
//        Student s = null;
//        ResultSet resultSet;
//        String stmt = "SELECT * FROM student WHERE idStudent=?";
//        PreparedStatement preparedStatement;
//
//        try {
//            try(Connection con = createConnection()){ // Wird automatisch am ende von try geschlossen
//                //Statement wird erzeugt
//                preparedStatement = con.prepareStatement(stmt);
//                preparedStatement.setInt(1, id);
//                resultSet = preparedStatement.executeQuery();
//                if(resultSet.next()){
//                    s = new Student(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3), resultSet.getString(4));
//                }
//
//            }
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//
//        return s;
//    }
//}
