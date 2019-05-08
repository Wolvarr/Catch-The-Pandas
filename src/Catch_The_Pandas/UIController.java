package Catch_The_Pandas;

import Catch_The_Pandas.IO.Command;
import Catch_The_Pandas.IO.CommandType;
import Catch_The_Pandas.IO.OutputDestination;
import Catch_The_Pandas.IO.OutputWriter;
import Catch_The_Pandas.Resources.GameElements.Orangutan;
import Catch_The_Pandas.Resources.GameElements.Tile;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UIController {
    //stores the tiles mapped to 2d points on the plane of the canvas
    private Map<Point2D, Tile> nodes = new HashMap<>();

    //contains the floor and the scores for each orangutan
    private Game game;

    //represents if the current click is the first one
    //moves are represented as 2 clicks, the first chooses the orangutan to move
    //the second chooses the tile where the user chooses to move to
    private boolean firstclick = true;
    private Integer orangutanOnTurn;

    //represents the command currently being assembled
    Command currentCommand = null;

    @FXML
    void initialize(){
        //testing purposes, pls remove before release
        mainGameCanvas.setOnMouseMoved(event -> {
            System.out.println(event.getX() + "   " + event.getY());
        });

        mainGameCanvas.setOnMouseClicked(event -> {
            //logic to detect on what object did the user click on
            //getting the location of the mouse when the user clicked
            Point2D clickedPint = new Point2D(event.getX(), event.getY());

            //starting from a large enough distance that every distance is going to be closer
            //(the window is definitely not 9999 pixels across)
            double smallestDistance = 9999;
            Tile closestTile = null;
            //finds the closest point resembling a node
            for(Point2D p: nodes.keySet()){
                //if the distance is the closest yet, we choose the corresponding tile as the closest
                if(clickedPint.distance(p)<smallestDistance){
                    closestTile = nodes.get(p);
                };
            }

                currentCommand.setTarget(closestTile);
                currentCommand.execute();
                currentCommand = null;

            //flips the first click boolean
            firstclick = !firstclick;
        });

    }
    //the canvas where the main game area is being drawn
    @FXML
    public Canvas mainGameCanvas;

    //the exit menu from the top left corner
    @FXML
    public MenuItem exitMenuItem;

    //the list containing the orangutans shown on the left
    //the orangutan currently making a move is highlited
    @FXML
    public ListView orangutanList;


    @FXML
    public void exit(ActionEvent actionEvent) {
       System.exit(0);
    }


    //testing purposes, pls remove before release
    @FXML
    public void drawSomeShit(ActionEvent actionEvent){
        mainGameCanvas.getGraphicsContext2D().fillRect(100,100,100,100);
    }

}
