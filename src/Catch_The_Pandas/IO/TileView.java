package Catch_The_Pandas.IO;

import Catch_The_Pandas.Resources.GameElements.Tile;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;

import java.util.HashMap;

public class TileView {
    public Colour colour;
    public HashMap<TileState, Image> images = new HashMap<>();
    public Point2D location;
    public Tile tile;
    public TileView(Image ok, Image broken){
        images.put(TileState.ok, ok);
        images.put(TileState.broken,  broken);

    }
    public TileView(Image ok){
        images.put(TileState.ok, ok);
    }

}
