package Catch_The_Pandas;

import Catch_The_Pandas.IO.*;
import Catch_The_Pandas.Resources.GameElements.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;

import javax.swing.text.html.ObjectView;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class UIController {


    Integer radius = 40;
    //stores the tiles mapped to 2d points on the plane of the canvas
    private Map<OnTileObjectView, Tile> objectNodes = new HashMap<>();
    private Map<TileView, Tile> tileNodes = new ConcurrentHashMap<>();

    //contains the floor and the scores for each orangutan
    private Game game;

    //represents if the current click is the first one
    //moves are represented as 2 clicks, the first chooses the orangutan to move
    //the second chooses the tile where the user chooses to move to
    private boolean firstclick = true;
    private Integer orangutanOnTurn = 0;
    Catch_The_Pandas.IO.Map testmap ;
    //represents the command currently being assembled
    Command currentCommand = null;
    ImageContainer testic = new ImageContainer();
    ObservableList<String> observableOrangutanList;

    public UIController() throws FileNotFoundException {
    }

    @FXML
    void initialize() {


        String basePath = null;
        File currentDir = new File(".");
        try {
            basePath = currentDir.getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("This is the prototype main class");
        testmap = new Catch_The_Pandas.IO.Map(basePath + "/testmap0/");
        Floor testfloor = testmap.build();
        try {
            for (int i = 0; i < testmap.tileGraphics.size(); i++) {
                tileNodes.put(testmap.tileGraphics.get(i), testfloor.getTile(i));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        game = new Game(testfloor);

        //testing purposes, pls remove before release
        mainGameCanvas.setOnMouseMoved(event -> {
            //System.out.println(event.getX() + "   " + event.getY());
        });

        mainGameCanvas.setOnMouseClicked(event -> {
            //logic to detect on what object did the user click on
            //getting the location of the mouse when the user clicked
            Point2D clickedPoint = new Point2D(event.getX(), event.getY());

            //starting from a large enough distance that every distance is going to be closer
            //(the window is definitely not 9999 pixels across)
            double smallestDistance = 9999;
            Tile selectedTile = null;
            TileView selectedTileView = null;
            //finds the closest point resembling a node

            for (TileView p : tileNodes.keySet()) {
                //if the distance is the closest yet, we choose the corresponding tile as the closest
                if (p.location == null)
                    System.out.println("object Location is null");
                if (clickedPoint.distance(p.location) < radius) {
                    selectedTile = tileNodes.get(p);
                    System.out.println("BINGO");
                    break;
                }
            }


            if (game.floor.getOrangutan(orangutanOnTurn) != null && selectedTile != null) {
                currentCommand = new Command(CommandType.move, game.floor);
                currentCommand.setTarget(selectedTile);
                currentCommand.setOrangutan(game.floor.getOrangutan(orangutanOnTurn));

                if (currentCommand.execute()) {
                    orangutanOnTurn += 1;
                    if(orangutanOnTurn == game.floor.getAllOrangutans().size())
                        orangutanOnTurn = 0;
                    System.out.println("orangutan should move pls move you fat monkey");
                }
                currentCommand = null;
            }

            try {
                drawSomeShit(new ActionEvent());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });

        releaseButton.setOnMouseClicked(event -> {
            currentCommand = new Command(CommandType.release, game.floor);
            currentCommand.setOrangutan(game.floor.getOrangutan(orangutanOnTurn));
            if(currentCommand.execute()){
                orangutanOnTurn+=1;
                if(orangutanOnTurn == game.floor.getAllOrangutans().size())
                    orangutanOnTurn = 0;
            }

        });

    }

    //the canvas where the main game area is being drawn
    @FXML
    Button releaseButton;

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
    public synchronized void drawSomeShit(ActionEvent actionEvent) throws FileNotFoundException {
        refreshViews();
        /**
         letörli a kijelzőt és kirajzolja a vonalakat a node-ok pozíciói közé
         */
        mainGameCanvas.getGraphicsContext2D().clearRect(0, 0, 2000, 2000);
        for (TileView p : tileNodes.keySet()) {
            for (Tile tile : tileNodes.get(p).getNeighbours()) {
                for (Map.Entry<TileView, Tile> entry : tileNodes.entrySet()) {
                    if (tile.equals(entry.getValue())) {
                        //itt rajzolja a vonalat
                        //nem hatékony, de működik
                        mainGameCanvas.getGraphicsContext2D().strokeLine(p.location.getX(), p.location.getY(),
                                entry.getKey().location.getX(), entry.getKey().location.getY());
                    }
                }
            }
//        }
            /**
             átszínezi az orángutánhoz tartozó sort
             */
            for (Orangutan o : game.floor.getAllOrangutans()) {
                TileView orangutanLocation = null;

                //ez a ciklus keresi meg az adott orángután pillanatnyi helyzetét
                for (Map.Entry<TileView, Tile> entry : tileNodes.entrySet()) {
                    if (entry.getValue().equals(o.getLocation()))
                        //és menti ebbe a lokális változóba
                        orangutanLocation = entry.getKey();
                }

                if (o.getGrabbed() != null) {
                    System.out.println("orangutan has a line");
                    Panda tempp = o.getGrabbed();
                    //végigmegy a pandasoron
                    while (tempp != null) {
                        //megkeresi az éppen vizsgált panda helyét a grafikus felületen
                        for (Map.Entry<TileView, Tile> e : tileNodes.entrySet()) {
                            if (tempp.getLocation().equals(e.getValue())) {
                                //assert orangutanLocation != null;
                                tileNodes.remove(e.getKey());
                                e.getKey().objectView.colour = orangutanLocation.objectView.colour;
                                tileNodes.put(e.getKey(), e.getValue());
                                System.out.println(e.getKey().objectView.colour);
                                System.out.println("panda should be coloured");
                            }
                        }
                        //lépteti a pandasor következő elemére
//                    System.out.println("another panda boi");
                        tempp = tempp.getNextPanda();
                    }
                }
            }

            /**
             kirajzolja a a csempéket és rájuk a rajtuk álló játékelemeket
             */
//        for (TileView p : tileNodes.keySet()) {
            //a csempe rajzolása
            mainGameCanvas.getGraphicsContext2D().drawImage(p.images.get(TileState.ok), p.location.getX() - radius, p.location.getY() - radius, 2 * radius, 2 * radius);
            //ha létezik, a csempén álló játékelem rajzolása
            if (p.objectView != null) {
                tileNodes.get(p).getOnObject().eachTurn();
                System.out.println("gecisfasz: " + p.objectView.colour);
                mainGameCanvas.getGraphicsContext2D().drawImage(p.objectView.images.get(p.objectView.colour), p.location.getX() - radius, p.location.getY() - radius, 2 * radius, 2 * radius);
            }
        }


    }

    private void refreshViews() {
        for (Tile tile : game.floor.getAllTiles()) {
            for (Map.Entry<TileView, Tile> entry : tileNodes.entrySet()) {
                if (tile.equals(entry.getValue())) {
                    if (tile.getOnObject() != null)
                        switch (tile.getOnObject().getClass().getSimpleName()) {
                            case "Orangutan":
                                Orangutan tempo = (Orangutan) tile.getOnObject();
                                tileNodes.remove(entry.getKey());
                                TileView temptv = entry.getKey();
                                temptv.objectView = new OnTileObjectView((Orangutan) tile.getOnObject(), testic);
                                temptv.objectView.setColour(tempo.getID());
                                tileNodes.put(temptv, entry.getValue());
                                break;
                            case "CowardPanda":
                                entry.getKey().objectView = new OnTileObjectView((CowardPanda) tile.getOnObject(), testic);
                                break;
                            case "JumpyPanda":
                                entry.getKey().objectView = new OnTileObjectView((JumpyPanda) tile.getOnObject(), testic);
                                break;
                            case "LazyPanda":
                                entry.getKey().objectView = new OnTileObjectView((LazyPanda) tile.getOnObject(), testic);
                                break;
                            case "Arcade":
                                entry.getKey().objectView = new OnTileObjectView((Arcade) tile.getOnObject(), testic);
                                break;
                            case "Armchair":
                                entry.getKey().objectView = new OnTileObjectView((Armchair) tile.getOnObject(), testic);
                                break;
                            case "CandyVending":
                                entry.getKey().objectView = new OnTileObjectView((CandyVending) tile.getOnObject(), testic);
                                break;
                            case "Wardrobe":
                                entry.getKey().objectView = new OnTileObjectView((Wardrobe) tile.getOnObject(), testic);
                                break;
                            default:
                                System.out.println("defualt case :((");
                        }
                    else entry.getKey().objectView = null;
                }
            }
            observableOrangutanList = FXCollections.<String>observableArrayList("Orangutan1", "Orangutan2", "Orangutan3", "Orangutan4");
            orangutanList.getItems().clear();
            orangutanList.setMouseTransparent(true);
            orangutanList.setFocusTraversable(false);
            orangutanList.getItems().addAll(observableOrangutanList);
            orangutanList.getSelectionModel().select(orangutanOnTurn+1-1);

        }

    }


}
