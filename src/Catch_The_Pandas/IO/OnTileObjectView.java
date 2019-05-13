package Catch_The_Pandas.IO;

import Catch_The_Pandas.ImageContainer;
import Catch_The_Pandas.Resources.GameElements.*;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;

import java.util.HashMap;

public class OnTileObjectView {
    public HashMap<Colour, Image> images = new HashMap<>();
    public TileView tileView;
    public Colour colour = Colour.none;
    public OnTileObjectView(Image none, Image red, Image blue, Image yellow, Image green){
        images.put(Colour.none, none);
        images.put(Colour.red, red);
        images.put(Colour.blue, blue);
        images.put(Colour.yellow, yellow);
        images.put(Colour.green, green);
    }
    public OnTileObjectView(Image none){
        images.put(Colour.none, none);
    }

    public void setColour(Integer i){
        switch (i){
            case 0: colour = Colour.red;
            case 1: colour = Colour.blue;
            case 2: colour = Colour.yellow;
            case 3: colour = Colour.green;
        }
    }

    public OnTileObjectView(Orangutan onTileObject, ImageContainer imageContainer){
        images.put(Colour.none, imageContainer.orangutanImage);
        images.put(Colour.red, imageContainer.orangutanImage1);
        images.put(Colour.blue, imageContainer.orangutanImage2);
        images.put(Colour.yellow, imageContainer.orangutanImage3);
        images.put(Colour.green, imageContainer.orangutanImage4);
        System.out.println("orangutan");
    }


    public OnTileObjectView(LazyPanda onTileObject, ImageContainer imageContainer){
        images.put(Colour.none, imageContainer.sleepyPandaImage);
        images.put(Colour.red, imageContainer.sleepyPandaImage1);
        images.put(Colour.blue, imageContainer.sleepyPandaImage2);
        images.put(Colour.yellow, imageContainer.sleepyPandaImage3);
        images.put(Colour.green, imageContainer.sleepyPandaImage4);
        System.out.println("lazypanda");
    }
    public OnTileObjectView(JumpyPanda onTileObject, ImageContainer imageContainer){
        images.put(Colour.none, imageContainer.jumpyPandaImage);
        images.put(Colour.red, imageContainer.jumpyPandaImage1);
        images.put(Colour.blue, imageContainer.jumpyPandaImage2);
        images.put(Colour.yellow, imageContainer.jumpyPandaImage3);
        images.put(Colour.green, imageContainer.jumpyPandaImage4);
        System.out.println("jumpypanda");
    }
    public OnTileObjectView(CowardPanda onTileObject, ImageContainer imageContainer){
        images.put(Colour.none, imageContainer.scaredPandaImage);
        images.put(Colour.red, imageContainer.scaredPandaImage1);
        images.put(Colour.blue, imageContainer.scaredPandaImage2);
        images.put(Colour.yellow, imageContainer.scaredPandaImage3);
        images.put(Colour.green, imageContainer.scaredPandaImage4);
        System.out.println("cowardpanda");
    }
    public OnTileObjectView(Arcade onTileObject, ImageContainer imageContainer){
        images.put(Colour.none, imageContainer.arcadeImage);
        System.out.println("arcade");
    }
    public OnTileObjectView(CandyVending onTileObject, ImageContainer imageContainer){
        images.put(Colour.none, imageContainer.candyVendingImage);
        System.out.println("candy");
    }
    public OnTileObjectView(Wardrobe onTileObject, ImageContainer imageContainer){
        images.put(Colour.none, imageContainer.wardrobeImage);
        System.out.println("wardrobe");
    }
    public OnTileObjectView(Armchair onTileObject, ImageContainer imageContainer){
        images.put(Colour.none, imageContainer.sofaImage);
        System.out.println("armchair");
    }

}
