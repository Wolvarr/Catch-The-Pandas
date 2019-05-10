package Catch_The_Pandas;

import Catch_The_Pandas.IO.*;
import Catch_The_Pandas.Resources.GameElements.Floor;
import Catch_The_Pandas.Resources.GameElements.Orangutan;
import Catch_The_Pandas.Resources.GameElements.Tile;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UIController {

    Image orangutanImage1;
    Image orangutanImage2;
    Image orangutanImage3;
    Image orangutanImage4;

    Image exitImage;
    Image entranceImage;

    Image jumpyPandaImage;
    Image sleepyPandaImage;
    Image scaredPandaImage;
    Image arcadeImage;
    Image candyVendingImage;
    Image sofaImage;
    Image wardrobeImage;


    Integer radius = 50;
    //stores the tiles mapped to 2d points on the plane of the canvas
    private Map<Point2D, Tile> nodes = new HashMap<>();

    //contains the floor and the scores for each orangutan
    private Game game;

    //represents if the current click is the first one
    //moves are represented as 2 clicks, the first chooses the orangutan to move
    //the second chooses the tile where the user chooses to move to
    private boolean firstclick = true;
    private Integer orangutanOnTurn = 0;

    //represents the command currently being assembled
    Command currentCommand = null;

    public UIController() throws FileNotFoundException {
    }

    @FXML
    void initialize()  {
        String imgbase = "GraphicsResources";

        try {
            orangutanImage1 = new Image(new FileInputStream("Orangutan.png"));
            orangutanImage2 = new Image(new FileInputStream("Orangutan.png"));
            orangutanImage3 = new Image(new FileInputStream("Orangutan.png"));
            orangutanImage4 = new Image(new FileInputStream("Orangutan.png"));

            exitImage = new Image(new FileInputStream("Exit.png"));
            entranceImage = new Image(new FileInputStream("Entrance.png"));

            jumpyPandaImage = new Image(new FileInputStream("JumpyPanda.png"));
            sleepyPandaImage = new Image(new FileInputStream("SleepyPanda.png"));
            scaredPandaImage = new Image(new FileInputStream("ScaredPanda.png"));
            arcadeImage = new Image(new FileInputStream("Arcade.png"));
            candyVendingImage = new Image(new FileInputStream("CandyVending.png"));
            sofaImage = new Image(new FileInputStream("Sofa.png"));
            wardrobeImage = new Image(new FileInputStream("Wardrobe.png"));
        } catch (Exception e) {e.printStackTrace();}

        String basePath = null;
        File currentDir = new File (".");
        try {
            basePath = currentDir.getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("This is the prototype main class");
        Catch_The_Pandas.IO.Map testmap = new Catch_The_Pandas.IO.Map(basePath + "/testmap0/");
        Floor testfloor = testmap.build();
        for(int i= 0; i< testfloor.getAllTiles().size(); i++){
            nodes.put( testmap.graphics.get(i) , testfloor.getTile(i));
        }



        game = new Game(testfloor);

        //testing purposes, pls remove before release
        mainGameCanvas.setOnMouseMoved(event -> {
            System.out.println(event.getX() + "   " + event.getY());
        });

        mainGameCanvas.setOnMouseClicked(event -> {
            //logic to detect on what object did the user click on
            //getting the location of the mouse when the user clicked
            Point2D clickedPoint = new Point2D(event.getX(), event.getY());

            //starting from a large enough distance that every distance is going to be closer
            //(the window is definitely not 9999 pixels across)
            double smallestDistance = 9999;
            Tile selectedTile = null;
            //finds the closest point resembling a node
            for (Point2D p : nodes.keySet()) {
                //if the distance is the closest yet, we choose the corresponding tile as the closest
                if (clickedPoint.distance(p) < radius) {
                    selectedTile = nodes.get(p);
                    break;
                }
                ;
            }


            if (game.floor.getOrangutan(orangutanOnTurn) != null && selectedTile != null) {
                currentCommand = new Command(CommandType.move, game.floor);
                currentCommand.setTarget(selectedTile);
                currentCommand.setOrangutan(game.floor.getOrangutan(orangutanOnTurn));
                currentCommand.execute();
                currentCommand = null;
                System.out.println("BINGO");
            }
            try {
                drawSomeShit(new ActionEvent());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            System.out.println("THE USER HAS CLICKED ON SOME SERIOUS SHIT");
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
    public void drawSomeShit(ActionEvent actionEvent) throws FileNotFoundException {

        for(Point2D p : nodes.keySet()) {
            mainGameCanvas.getGraphicsContext2D().strokeOval(p.getX()-50, p.getY()-50,70,70);
            mainGameCanvas.getGraphicsContext2D().drawImage(scaredPandaImage, p.getX() - 50, p.getY() - 50, 70, 70);
        }
    }

}
