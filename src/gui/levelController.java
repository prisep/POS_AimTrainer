package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import sample.Modes;
import sample.bll.User;

import java.io.IOException;

public class levelController {
    @FXML
    private Button bttn_launchAccuray;
    @FXML
    private Button bttn_launchSpeed;
    @FXML
    private Button bttn_launchPrecision;

    private User currentUser;
    @FXML
    private Label lbl_welcomeMsg;

    private void openAccuracyMode() throws IOException {
        Stage aScene = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../sample/level.fxml"));
        Parent localRoot = loader.load();
        aScene.setTitle("Accuracy Mode");
        aScene.setScene(new Scene(localRoot));
        aScene.show();

    }
    private void openSpeedMode(){

    }
    private void openPrecisionMode(){

    }

    public void setUser(User user){
        currentUser = user;
        lbl_welcomeMsg.setText("Welcome Back " + user.getUsername());
    }


    private void OpenSelectedGameMode(Modes selectedMode, Scene oldScene) {
        try{
            switch (selectedMode){
                case Accuracy: openAccuracyMode();
                    break;
                case Speed: openSpeedMode();
                    break;
                case Precision: openPrecisionMode();
                    break;
            }
        }
        catch (IOException e){
            System.out.println(e);
        }


    }

    @FXML
    private void accuracyGameMode(ActionEvent actionEvent) {
    }

    @FXML
    private void speedGameMode(ActionEvent actionEvent) {
    }

    @FXML
    private void precisionGameMode(ActionEvent actionEvent) {
    }
}
