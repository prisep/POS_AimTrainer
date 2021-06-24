package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Controller;
import sample.bll.Mode;
import sample.bll.Modes;
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



    public void setUser(User user){
        currentUser = user;
        lbl_welcomeMsg.setText("Welcome Back " + currentUser.getUsername());
    }


    private void openSelectedGameMode(Modes selectedMode) {

        try{
            Canvas c = new Canvas(1000,1000);
            GraphicsContext context = c.getGraphicsContext2D();
            FXMLLoader loader = null;
            GridPane root = null;
            Controller controller = null;
            loader = new FXMLLoader(getClass().getResource("sample.fxml"));
            Stage stage = null;
            Scene scene = null;

            root = loader.load();
            controller = loader.getController();
                switch (selectedMode){
                    case Accuracy: controller.setMode(new Mode(Modes.Accuracy, c, context));
                        break;
                    case Speed: controller.setMode(new Mode(Modes.Speed, c , context));
                        break;
                    case Precision: controller.setMode(new Mode(Modes.Precision, c, context));
                        break;
                }
            stage = new Stage();
            scene = new Scene(root);
            root.getChildren().add( c); 
            stage.setScene(scene);
            stage.setTitle("Game");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    @FXML
    private void accuracyGameMode(ActionEvent actionEvent) {
        openSelectedGameMode(Modes.Accuracy);
    }

    @FXML
    private void speedGameMode(ActionEvent actionEvent) {
        openSelectedGameMode(Modes.Speed);
    }

    @FXML
    private void precisionGameMode(ActionEvent actionEvent) {
        openSelectedGameMode(Modes.Precision);
    }
}
