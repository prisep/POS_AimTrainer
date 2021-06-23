package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
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
import javafx.stage.WindowEvent;
import sample.bll.SpeedMode;

public class Main extends Application {
    int[][] enemycoordinates = new int[1000][1000];

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Canvas c = new Canvas(1000,1000);
        GraphicsContext context = c.getGraphicsContext2D();



        SpeedMode mode = new SpeedMode();


        /*c.setOnMouseDragged(event -> {
            double x = event.getX();
            double y = event.getY();
            context.fillRect(x,y,3,3);
            System.out.println("X-Achse: " + x + "  Y-Achse " + y);

        });*/
        c.setOnMouseClicked(event -> {
            int x = (int) event.getX();
            int y = (int) event.getY();
            System.out.println("X-Achse: " + x + "  Y-Achse " + y);
            for (int i = 0; i < 30; i++) {
                if (enemycoordinates[i][0] <= x && enemycoordinates[i][0] + 20 >= x && enemycoordinates[i][1] <= y && enemycoordinates[i][1] + 20 >= y) {
                    System.out.println("HIT!!!");
                    context.setFill(Color.BLACK);
                    context.fillRect(enemycoordinates[i][0], enemycoordinates[i][1], 20, 20);
                }
            }
        });

        loginOpen();

        //primaryStage.setTitle("Aim Tester");
        //primaryStage.setScene(new Scene(new Pane(c)));
        //primaryStage.show();
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
        }
        catch(Exception e){
            System.out.println(e);
        }
    }



    public static void main(String[] args) {
        launch(args);
    }
}
