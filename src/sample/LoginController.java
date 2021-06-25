package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.bll.User;
import sample.dal.dao.Dao;
import sample.dal.dao.UserDBDao;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    private  User currentUser;
    @FXML
    private TextField tfusername;
    @FXML
    private PasswordField pfPassword;
    @FXML
    private Button btnRegister;

    private boolean success = false;

    public boolean isSuccess() {
        return success;
    }



    Dao dao;
    List<User> users = new ArrayList<>();
    List<String> usernames = new ArrayList<>();
    List<String> passwords = new ArrayList<>();

    public User getCurrentUser() {
        return currentUser;
    }

    @FXML
    private void registerClicked(ActionEvent actionEvent) {
        showAlert();
        if(users.contains(tfusername.getText())){
            Alert alert = new Alert(Alert.AlertType.ERROR, "username already taken");
            alert.show();
        }
        else{
            User newUser = new User(tfusername.getText(), pfPassword.getText());
            users.add(newUser);
            usernames.add(newUser.getUsername());
            passwords.add(newUser.getPassword());
            dao.insert(newUser);
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Succesfully registered");
            alert.show();
        }
    }

    @FXML
    private void loginClicked(ActionEvent actionEvent) {
        showAlert();
        User checkUser = new User(tfusername.getText(), pfPassword.getText());
        User validUser = allowedToLogin(checkUser);
        if(validUser != null){
            currentUser = validUser;
            Stage thisStage = (Stage) tfusername.getScene().getWindow();
            thisStage.close();
            this.success = true;

        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR, "No user found: Password or Username incorrect");
            alert.show();
        }
    }


    public User allowedToLogin(User checkUser){
        User found = null;
        for (User user: users) {
            if(user.equals(checkUser)){
                return user;
            }
        }
        return found;
    }

    public void showAlert(){
        if(this.tfusername.getText().isEmpty() || this.pfPassword.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR, "No password or username provided");
            alert.show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dao = new UserDBDao();
        users = dao.getAll();
        usernames = new ArrayList<>();
        for (User user: users) {
            usernames.add(user.getUsername());
        }
        for (User user: users) {
            passwords.add(user.getPassword());
        }


    }
}
