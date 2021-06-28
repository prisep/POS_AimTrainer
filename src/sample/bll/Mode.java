package sample.bll;

import com.sun.prism.paint.Paint;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import sample.dal.dao.Dao;
import sample.dal.dao.UserDBDao;

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
    Dao dao = new UserDBDao();

    public User getCurrentUser() {
        return CurrentUser;
    }

    public void setCurrentUser(User currentUser) {
        CurrentUser = currentUser;
    }

    User CurrentUser;

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
        place_Random_Rectangle_With_High_Points(context);
        final int[][] firstClick = {{0}};
        final double[] timeStart = new double[1];
        c.setOnMouseClicked(event -> {
            if(firstClick[0][0] == 0){
                timeStart[0] = System.currentTimeMillis();
            }
            firstClick[0][0]++;
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
                        context.setFill(Color.WHITE);

                        final double timeEnd = System.currentTimeMillis();
                        context.fillText("Sie haben von maximalen " + maxPoints + " Punkten  " + points + " Punkte erreicht", 250,420);
                        context.fillText("Sie haben " + (timeEnd - timeStart[0])/(double)1000 + " Sekunden gebraucht", 250,440);
                        System.out.println("Sie haben von maximalen " + maxPoints + "  " + points + " erreicht");


                        //Highscore
                        if((timeEnd - timeStart[0])/(double)1000 < CurrentUser.getHighscorePrecision()){
                            CurrentUser.setHighscorePrecision((int)(timeEnd - timeStart[0])/1000);
                            dao.update(CurrentUser);
                        }
                        context.fillText("Highscore: "+CurrentUser.getHighscorePrecision() + " Sekunden", 250,460);

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


        final boolean[] fertig = {false};


        Random rand = new Random(); //instance of random class
        Screen screen = Screen.getPrimary();
        Rectangle2D screenBounds = screen.getBounds();
        double height = screenBounds.getHeight();
        double width  = screenBounds.getWidth();
        int upperboundx = (int) width;
        int upperboundy = (int) height;
        //generate random values from 0-24

        int xstart = rand.nextInt(upperboundx - 40);
        int ystart = rand.nextInt(upperboundy - 40);
        context.setFill(Color.GOLD);
        context.fillRect(xstart,ystart,40,40);


        enemycoordinates[0][0] = xstart;
        enemycoordinates[0][1] = ystart;
        final int[][] firstClick = {{0}};
        final double[] timeStart = new double[1];
        c.setOnMouseClicked(event -> {

            if(firstClick[0][0] == 0){
                timeStart[0] = System.currentTimeMillis();
            }
            firstClick[0][0]++;
            accuracyIdx++;
            cntShoots++;
            int x = (int)event.getX();
            int y = (int)event.getY();
            System.out.println("X-Achse: " + x + "  Y-Achse " + y);




                if(enemycoordinates[0][0] <= x && enemycoordinates[0][0]+40 >= x && enemycoordinates[0][1] <= y && enemycoordinates[0][1]+40 >= y){
                    System.out.println("HIT!!!");
                    context.setFill(Color.BLACK);
                    context.fillRect(enemycoordinates[0][0],enemycoordinates[0][1],40,40);
                    enemycoordinates[0][0] *= -1;
                    enemycoordinates[0][1] *= -1;
                    cntHits++;
                    if(accuracyIdx >= 30){
                        //System.out.println("Sie haben alle Ziele getroffen");
                        fertig[0] = true;
                        final double timeEnd = System.currentTimeMillis();
                        System.out.println("Sie haben von " + cntShoots + " Schüssen " + cntHits + " getroffen.");
                        System.out.println("Sie haben eine Trefferrate von: " + (double)cntHits/cntShoots * 100 + "%");
                        context.setFill(Color.WHITE);

                        context.fillText("Sie haben von " + cntShoots + " Schüssen " + cntHits + " getroffen.", 250,400);
                        context.fillText("Sie haben eine Trefferrate von: " + (double)cntHits/cntShoots * 100 + "%", 250,420);
                        context.fillText("Sie haben " + (timeEnd - timeStart[0])/(double)1000 + " Sekunden gebraucht", 250,440);


                        //Highscore
                        if((timeEnd - timeStart[0])/(double)1000 < CurrentUser.getHighscoreAccuracy()){
                            CurrentUser.setHighscorePrecision((int)(timeEnd - timeStart[0])/1000);
                            dao.update(CurrentUser);
                        }
                        context.fillText("Highscore: "+CurrentUser.getHighscorePrecision() + " Sekunden", 250,460);
                    }

                }else{
                    cntFails++;
                    context.setFill(Color.BLACK);
                    context.fillRect(enemycoordinates[0][0],enemycoordinates[0][1],40,40);
                }

            if(fertig[0] == false){
                int x1 = rand.nextInt(upperboundx);
                int y1 = rand.nextInt(upperboundy);

                enemycoordinates[0][0] = x1;
                enemycoordinates[0][1] = y1;

                context.setFill(Color.GOLD);
                context.fillRect(x1,y1,40,40);
            }
        });
    }

    public void speedLevel(Canvas c, GraphicsContext context){
        context.setFill(Color.BLACK);
        context.fillRect(0,0,1000,1000);
        place_Random_Rectangle(context);
        final int[][] firstClick = {{0}};
        final double[] timeStart = new double[1];
        c.setOnMouseClicked(event -> {

            if(firstClick[0][0] == 0){
                timeStart[0] = System.currentTimeMillis();
            }
            firstClick[0][0]++;
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
                        //System.out.println("Sie haben alle Ziele getroffen");
                        System.out.println("Sie haben von " + cntShoots + " Schüssen " + cntHits + " getroffen.");
                        System.out.println("Sie haben eine Trefferrate von: " + (double)cntHits/cntShoots * 100 + "%");
                        final double timeEnd = System.currentTimeMillis();
                        System.out.println("Sie haben " + (timeEnd - timeStart[0])/(double)1000 + " Sekunden gebraucht");

                        context.setFill(Color.WHITE);

                        context.fillText("Sie haben von " + cntShoots + " Schüssen " + cntHits + " getroffen.", 250,400);
                        context.fillText("Sie haben eine Trefferrate von: " + (double)cntHits/cntShoots * 100 + "%", 250,420);
                        context.fillText("Sie haben " + (timeEnd - timeStart[0])/(double)1000 + " Sekunden gebraucht", 250,440);


                        //Highscore
                        if((timeEnd - timeStart[0])/(double)1000 < CurrentUser.getHighscoreSpeed()){
                            CurrentUser.setHighscorePrecision((int)(timeEnd - timeStart[0])/1000);
                            dao.update(CurrentUser);
                        }

                        if(CurrentUser.getHighscoreSpeed() != 0){
                            context.fillText("Highscore: "+CurrentUser.getHighscorePrecision() + " Sekunden", 250,460);
                        }
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
            Screen screen = Screen.getPrimary();
            Rectangle2D screenBounds = screen.getBounds();
            double height = screenBounds.getHeight();
            double width  = screenBounds.getWidth();
            int upperboundx = (int) width;
            int upperboundy = (int) height;
            int x = rand.nextInt(upperboundx - 40);
            int y = rand.nextInt(upperboundy - 40);

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
            //generate random values from 0-24
            Screen screen = Screen.getPrimary();
            Rectangle2D screenBounds = screen.getBounds();
            double height = screenBounds.getHeight();
            double width  = screenBounds.getWidth();
            int upperboundx = (int) width;
            int upperboundy = (int) height;
            int x = rand.nextInt(upperboundx - 40);
            int y = rand.nextInt(upperboundy - 40);

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
