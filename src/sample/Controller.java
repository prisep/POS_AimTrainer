package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import sample.bll.Mode;
import sample.bll.Modes;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    Mode currentMode;

    public void setMode(Mode mode){
        currentMode = mode;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        switch (currentMode.getMode()){
            case Accuracy: currentMode.accuracyLevel(currentMode.getC(), currentMode.getContext());
                break;
            case Speed: currentMode.speedLevel(currentMode.getC(), currentMode.getContext());
                break;
            case Precision: currentMode.precisionLevel(currentMode.getC(), currentMode.getContext());
                break;
        }
    }
}
