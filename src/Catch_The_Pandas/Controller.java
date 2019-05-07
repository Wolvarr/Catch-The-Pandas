package Catch_The_Pandas;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;

public class Controller {
    @FXML
    public Canvas mainGameCanvas;
    @FXML
    public MenuItem exitMenuItem;

    @FXML
    public void exit(ActionEvent actionEvent) {
       System.exit(0);
    }

    @FXML
    public void drawSomeShit(ActionEvent actionEvent){
        mainGameCanvas.getGraphicsContext2D().fillRect(100,100,100,100);
    }
}
