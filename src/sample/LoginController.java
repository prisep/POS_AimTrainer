package sample;

import gui.levelController;
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
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import sample.bll.User;
import sample.dal.dao.Dao;
import sample.dal.dao.UserDBDao;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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

    Dao dao;
    List<User> users = new ArrayList<>();
    List<String> usernames = new ArrayList<>();
    List<String> passwords = new ArrayList<>();


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
        }
    }

    @FXML
    private void loginClicked(ActionEvent actionEvent) {
        showAlert();
        User checkUser = new User(tfusername.getText(), pfPassword.getText());
        if(allowedToLogin(checkUser)){
            currentUser = checkUser;
            showLevelScreen();
            Stage thisStage = (Stage) tfusername.getScene().getWindow();
            thisStage.close();
        }


    }

    private  void showLevelScreen()  {
        try {
            FXMLLoader loader = null;
            AnchorPane root = null;
            levelController controller = null;
            loader = new FXMLLoader(LoginController.class.getResource("level.fxml"));
            Stage stage = null;
            Scene scene = null;
            root = loader.load();
            controller = loader.getController();
            controller.setUser(currentUser);
            stage = new Stage();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Levels");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        }
        catch(Exception e){
            System.out.println(e);
        }

    }

    public boolean allowedToLogin(User checkUser){
        boolean found = false;
        for (User user: users) {
            if(user.equals(checkUser)){
                return found = true;
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
