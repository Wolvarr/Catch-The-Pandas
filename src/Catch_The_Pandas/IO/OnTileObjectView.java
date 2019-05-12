package Catch_The_Pandas.IO;

import Catch_The_Pandas.Resources.GameElements.OnTileObject;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;

import java.util.HashMap;

public class OnTileObjectView {
    public Colour colour;
    public HashMap<Colour, Image> images = new HashMap<>();
    public OnTileObject oto;
    public TileView tileView;
    public OnTileObjectView(Image none, Image red, Image blue, Image yellow, Image green){
        images.put(Colour.none, none);
        images.put(Colour.red, red);
        images.put(Colour.blue, blue);
        images.put(Colour.yellow, yellow);
        images.put(Colour.green, green);
        colour = Colour.none;
    }
    public OnTileObjectView(Image none){
        images.put(Colour.none, none);
        colour = Colour.none;
    }

}
