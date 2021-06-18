package sample.bll;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Random;

public class SpeedMode {


    public SpeedMode() {
    }

    public static int[][] place_Random_Rectangle(GraphicsContext context) {
        int[][] enemycoordinates = new int[1000][1000];

        for (int i = 0; i < 30; i++) {

            boolean foundsameCo = false;
            Random rand = new Random(); //instance of random class
            int upperbound = 980;
            //generate random values from 0-24
            int x = rand.nextInt(upperbound);
            int y = rand.nextInt(upperbound);

            if (true) {
                /*for (int outcnt = 0; outcnt < 20; outcnt++) {
                    for (int incnt = 0; incnt < 20; incnt++) {
                        enemycoordinates[y+outcnt][x+incnt] = true;
                    }
                }*/


                enemycoordinates[i][0] = x;
                enemycoordinates[i][1] = y;


                context.setFill(Color.GOLD);
                context.fillRect(x, y, 20, 20);
            } else {
                i--;
            }
            //int forcnt =(x+20)*(y+20) - x * y;

            //out = y     in = x

        }
        for (int outcnt = 0; outcnt < 30; outcnt++) {
            System.out.println("x: " + enemycoordinates[outcnt][0] + " y: " + enemycoordinates[outcnt][1]);

        }
        return enemycoordinates;
    }

}
