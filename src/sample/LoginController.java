package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sample.bll.User;
import sample.dal.dao.Dao;
import sample.dal.dao.UserDBDao;

import java.util.List;

public class LoginController {
    @FXML
    private TextField tfusername;
    @FXML
    private PasswordField pfPassword;
    @FXML
    private Button btnRegister;

    Dao dao = new UserDBDao();

    @FXML
    private void registerClicked(ActionEvent actionEvent) {
        showAlert();
        List<User> users = dao.getAll();
        //Todo: Search for duplicates
    }

    @FXML
    private void loginClicked(ActionEvent actionEvent) {
        showAlert();

    }

    public void showAlert(){
        if(this.tfusername.getText().isEmpty() && this.pfPassword.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR, "No password or username provided");
            alert.show();
        }
    }
}
