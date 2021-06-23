package sample.bll;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Random;

public class SpeedMode {

    int[][] enemycoordinates = new int[30][2];
    int cntHits = 0;
    int cntFails = 0;
    int cntShoots = 0;

    public SpeedMode() {
    }

    private void speedLevel(Canvas c, GraphicsContext context){
        context.setFill(Color.BLACK);
        context.fillRect(0,0,1000,1000);
        place_Random_Rectangle(context);
        c.setOnMouseClicked(event -> {

            cntShoots++;
            int x = (int)event.getX();
            int y = (int)event.getY();
            System.out.println("X-Achse: " + x + "  Y-Achse " + y);
            for (int i = 0; i < 30; i++) {
                if(enemycoordinates[i][0] <= x && enemycoordinates[i][0]+40 >= x && enemycoordinates[i][1] <= y && enemycoordinates[i][1]+40 >= y){
                    System.out.println("HIT!!!");
                    context.setFill(Color.BLACK);
                    context.fillRect(enemycoordinates[i][0],enemycoordinates[i][1],40,40);
                    enemycoordinates[i][0] *= -1;
                    enemycoordinates[i][1] *= -1;
                    cntHits++;
                    if(isLastHit()){
                        System.out.println("Sie haben alle Ziele getroffen");
                        System.out.println("Sie haben von " + cntShoots + " Schüssen " + cntHits + " getroffen.");
                        System.out.println("Sie haben eine Trefferrate von: " + (double)cntHits/cntShoots * 100 + "%");
                    }

                }else{
                    cntFails++;
                }
            }

        });
    }

    private boolean isLastHit(){
        boolean isLast = true;
        for (int i = 0; i < 30; i++) {
            if(enemycoordinates[i][0] < 0 && enemycoordinates[i][1] < 0){

            }else{
                isLast = false;
            }

        }
        return isLast;
    }

    private void place_Random_Rectangle(GraphicsContext context){

        boolean valid = false;
        for (int i = 0; i < 30; i++) {

            boolean foundsameCo = false;
            Random rand = new Random(); //instance of random class
            int upperbound = 960;
            //generate random values from 0-24
            int x = rand.nextInt(upperbound);
            int y = rand.nextInt(upperbound);

            for (int j = 0; j <= i; j++) {
                if(enemycoordinates[j][0]-40 <= x && enemycoordinates[j][0]+80 >= x && enemycoordinates[j][1]-40 <= y && enemycoordinates[j][1]+80 >= y){
                    valid = false;
                }
            }
            if(valid){
                enemycoordinates[i][0] = x;
                enemycoordinates[i][1] = y;

                context.setFill(Color.GOLD);
                context.fillRect(x,y,40,40);
            }else{
                i--;
            }
            valid = true;
        }
        for (int outcnt = 0; outcnt < 30; outcnt++) {
            System.out.println("x: "+enemycoordinates[outcnt][0] + " y: "+enemycoordinates[outcnt][1]);
        }
    }


}
