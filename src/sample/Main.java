package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Dictionary;
import java.util.Random;

public class Main extends Application {
    int[][] enemycoordinates = new int[1000][1000];
    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Canvas c = new Canvas(1000,1000);
        GraphicsContext context = c.getGraphicsContext2D();
        context.setFill(Color.BLACK);
        context.fillRect(0,0,1000,1000);


        //context.fillRect(50,50,200,200);
        //context.setFill(Color.RED);
        //context.setStroke(Color.BLACK);
        //c.setOnMouseMoved();

        place_Random_Rectangle(context);

        /*c.setOnMouseDragged(event -> {
            double x = event.getX();
            double y = event.getY();
            context.fillRect(x,y,3,3);
            System.out.println("X-Achse: " + x + "  Y-Achse " + y);

        });*/
        c.setOnMouseClicked(event -> {
            int x = (int)event.getX();
            int y = (int)event.getY();
            System.out.println("X-Achse: " + x + "  Y-Achse " + y);
            for (int i = 0; i < 30; i++) {
                if(enemycoordinates[i][0] <= x && enemycoordinates[i][0]+20 >= x && enemycoordinates[i][1] <= y && enemycoordinates[i][1]+20 >= y){
                    System.out.println("HIT!!!");
                    context.setFill(Color.BLACK);
                    context.fillRect(enemycoordinates[i][0],enemycoordinates[i][1],20,20);
                }
            }
        });
        primaryStage.setTitle("Test Programm");
        primaryStage.setScene(new Scene(new Pane(c)));
        primaryStage.show();
    }
    private void hit(){

    }

    private void place_Random_Rectangle(GraphicsContext context){

        for (int i = 0; i < 30; i++) {

            boolean foundsameCo = false;
            Random rand = new Random(); //instance of random class
            int upperbound = 980;
            //generate random values from 0-24
            int x = rand.nextInt(upperbound);
            int y = rand.nextInt(upperbound);

            if(true){
                /*for (int outcnt = 0; outcnt < 20; outcnt++) {
                    for (int incnt = 0; incnt < 20; incnt++) {
                        enemycoordinates[y+outcnt][x+incnt] = true;
                    }
                }*/


                enemycoordinates[i][0] = x;
                enemycoordinates[i][1] = y;







                context.setFill(Color.GOLD);
                context.fillRect(x,y,20,20);
            }else{
                i--;
            }
            //int forcnt =(x+20)*(y+20) - x * y;

            //out = y     in = x

        }
        for (int outcnt = 0; outcnt < 30; outcnt++) {
            System.out.println("x: "+enemycoordinates[outcnt][0] + " y: "+enemycoordinates[outcnt][1]);

        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
