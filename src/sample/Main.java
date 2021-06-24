package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.bll.User;

public class Main extends Application {
    int[][] enemycoordinates = new int[1000][1000];

    @Override
    public void start(Stage primaryStage) throws Exception {

        loginOpen();

    }

    private void loginOpen() {
        try {
            FXMLLoader loader = null;
            AnchorPane root = null;
            LoginController controller = null;
            loader = new FXMLLoader(getClass().getResource("login.fxml"));
            Stage stage = null;
            Scene scene = null;
            root = loader.load();
            controller = loader.getController();
            stage = new Stage();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Login");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
            if( controller.isSuccess()){
                showLevelScreen(controller.getCurrentUser());
            }

        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    private  void showLevelScreen(User currentUser) {
        try {
            FXMLLoader loader = null;
            AnchorPane root = null;
            levelController controller = null;
            loader = new FXMLLoader(getClass().getResource("level.fxml"));
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
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
