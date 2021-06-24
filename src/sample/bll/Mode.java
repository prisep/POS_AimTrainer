package sample.bll;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class Mode {
    private Modes mode;
    private Canvas c;
    private GraphicsContext context;
    int[][] enemycoordinates = new int[30][2];
    int cntHits = 0;
    int cntFails = 0;
    int cntShoots = 0;
    int points = 0;
    int maxPoints = 30 * 50;
    int accuracyIdx = 0;

    public Mode(Modes mode, Canvas c, GraphicsContext context) {
        this.mode = mode;
        this.c = c;
        this.context = context;
    }

    public Modes getMode() {
        return mode;
    }

    public void setMode(Modes mode) {
        this.mode = mode;
    }

    public Canvas getC() {
        return c;
    }

    public void setC(Canvas c) {
        this.c = c;
    }

    public GraphicsContext getContext() {
        return context;
    }

    public void setContext(GraphicsContext context) {
        this.context = context;
    }

    public void precisionLevel(Canvas c, GraphicsContext context) {
        AtomicBoolean isHit = new AtomicBoolean(false);
        context.setFill(Color.BLACK);
        context.fillRect(0,0,1000,1000);
        place_Random_Rectangle_With_High_Points(context);
        c.setOnMouseClicked(event -> {

            isHit.set(false);

            int x = (int)event.getX();
            int y = (int)event.getY();
            System.out.println("X-Achse: " + x + "  Y-Achse " + y);
            for (int i = 0; i < 30; i++) {
                if(enemycoordinates[i][0] <= x && enemycoordinates[i][0]+40 >= x && enemycoordinates[i][1] <= y && enemycoordinates[i][1]+40 >= y) {
                    if (enemycoordinates[i][0] + 15 <= x && enemycoordinates[i][0] + 25 >= x && enemycoordinates[i][1] + 15 <= y && enemycoordinates[i][1] + 25 >= y) {
                        System.out.println("SUPERRR HIT!!!");
                        points += 50;
                    } else {
                        System.out.println("HIT!!!");
                        points += 10;
                    }

                    context.setFill(Color.BLACK);
                    context.fillRect(enemycoordinates[i][0], enemycoordinates[i][1], 40, 40);
                    enemycoordinates[i][0] *= -1;
                    enemycoordinates[i][1] *= -1;

                    if (isLastHit()) {
                        System.out.println("Sie haben von maximalen " + maxPoints + "  " + points + " erreicht");
                    }
                    isHit.set(true);

                }
            }
            if(isHit.get() == false){
                points -= 10;
            }

        });

    }

    public void accuracyLevel(Canvas c, GraphicsContext context) {
        context.setFill(Color.BLACK);
        context.fillRect(0,0,1000,1000);


        final boolean[] fertig = {false};


        Random rand = new Random(); //instance of random class
        int upperbound = 960;
        //generate random values from 0-24
        int xstart = rand.nextInt(upperbound);
        int ystart = rand.nextInt(upperbound);

        enemycoordinates[0][0] = xstart;
        enemycoordinates[0][1] = ystart;

        context.setFill(Color.GOLD);
        context.fillRect(xstart,ystart,40,40);

        c.setOnMouseClicked(event -> {


            accuracyIdx++;
            cntShoots++;
            int x = (int)event.getX();
            int y = (int)event.getY();
            System.out.println("X-Achse: " + x + "  Y-Achse " + y);

            for (int i = 0; i < enemycoordinates.length; i++) {
                if(enemycoordinates[i][0] <= x && enemycoordinates[i][0]+40 >= x && enemycoordinates[i][1] <= y && enemycoordinates[i][1]+40 >= y){
                    System.out.println("HIT!!!");
                    context.setFill(Color.BLACK);
                    context.fillRect(enemycoordinates[i][0],enemycoordinates[i][1],40,40);
                    enemycoordinates[i][0] *= -1;
                    enemycoordinates[i][1] *= -1;
                    cntHits++;
                    if(accuracyIdx >= enemycoordinates.length){
                        //System.out.println("Sie haben alle Ziele getroffen");
                        fertig[0] = true;
                        System.out.println("Sie haben von " + cntShoots + " Schüssen " + cntHits + " getroffen.");
                        System.out.println("Sie haben eine Trefferrate von: " + (double)cntHits/cntShoots * 100 + "%");
                    }

                }else{
                    cntFails++;
                    context.setFill(Color.BLACK);
                    context.fillRect(enemycoordinates[accuracyIdx-1][0],enemycoordinates[accuracyIdx-1][1],40,40);
                }
            }
            if(fertig[0] == false){
                int x1 = rand.nextInt(upperbound);
                int y1 = rand.nextInt(upperbound);

                enemycoordinates[accuracyIdx][0] = x1;
                enemycoordinates[accuracyIdx][1] = y1;

                context.setFill(Color.GOLD);
                context.fillRect(x1,y1,40,40);
            }
        });
    }

    public void speedLevel(Canvas c, GraphicsContext context){
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

    private void place_Random_Rectangle_With_High_Points(GraphicsContext context){

        boolean valid = true;
        for (int i = 0; i < 30; i++) {


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

            if(valid == true){
                enemycoordinates[i][0] = x;
                enemycoordinates[i][1] = y;

                context.setFill(Color.GOLD);
                context.fillRect(x,y,40,40);

                context.setFill(Color.RED);
                context.fillRect(x+15,y+15,10,10);
            }else{
                i--;
            }
            valid = true;
        }
        for (int outcnt = 0; outcnt < 30; outcnt++) {
            System.out.println("x: "+enemycoordinates[outcnt][0] + " y: "+enemycoordinates[outcnt][1]);
        }
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