package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import sample.bll.Modes;

import java.io.IOException;
public class levelController {
    @FXML
    private Button bttn_launchAccuray;
    @FXML
    private Button bttn_launchSpeed;
    @FXML
    private Button bttn_launchPrecision;

    private void openAccuracyMode() throws IOException {
        Stage aScene = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("./level.fxml"));
        Parent localRoot = loader.load();
        aScene.setTitle("Accuracy Mode");
        aScene.setScene(new Scene(localRoot));
        aScene.show();
    }
    private void openSpeedMode(){
    }
    private void openPrecisionMode(){
    }

    private void OpenSelectedGameMode(Modes selectedMode, Scene oldScene) {
        try{
            switch (selectedMode){
                case Accuracy: openAccuracyMode();
                    break;
            }
        }
        catch (IOException e){
            System.out.println(e);
        }

    }
}