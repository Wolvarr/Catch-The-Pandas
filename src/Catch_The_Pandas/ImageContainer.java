package Catch_The_Pandas;

import javafx.scene.image.Image;

import java.io.FileInputStream;

public class ImageContainer {
    String imgbase = "GraphicsResources/";
    public Image orangutanImage1;
    public Image orangutanImage2;
    public Image orangutanImage3;
    public Image orangutanImage4;
    public Image exitImage;
    public Image entranceImage;
    public Image jumpyPandaImage;
    public Image sleepyPandaImage;
    public Image scaredPandaImage;
    public Image arcadeImage;
    public Image candyVendingImage;
    public Image sofaImage;
    public Image wardrobeImage;
    public Image jumpyPandaImage1;
    public Image jumpyPandaImage2;
    public Image jumpyPandaImage3;
    public Image jumpyPandaImage4;
    public Image orangutanImage;
    public Image sleepyPandaImage1;
    public Image sleepyPandaImage2;
    public Image sleepyPandaImage3;
    public Image sleepyPandaImage4;
    public Image scaredPandaImage1;
    public Image scaredPandaImage2;
    public Image scaredPandaImage3;
    public Image scaredPandaImage4;

    public Image normalTileImage;
    public Image fragileTileImage;
    public Image crackedTileImage;

    public ImageContainer(){
        try {
            orangutanImage = new Image(new FileInputStream(imgbase + "Orangutan.png"));
            orangutanImage1 = new Image(new FileInputStream(imgbase + "Orangutan1.png"));
            orangutanImage2 = new Image(new FileInputStream(imgbase + "Orangutan2.png"));
            orangutanImage3 = new Image(new FileInputStream(imgbase + "Orangutan3.png"));
            orangutanImage4 = new Image(new FileInputStream(imgbase + "Orangutan4.png"));

            jumpyPandaImage = new Image(new FileInputStream(imgbase + "JumpyPanda.png"));
            jumpyPandaImage1 = new Image(new FileInputStream(imgbase + "JumpyPanda1.png"));
            jumpyPandaImage2 = new Image(new FileInputStream(imgbase + "JumpyPanda2.png"));
            jumpyPandaImage3 = new Image(new FileInputStream(imgbase + "JumpyPanda3.png"));
            jumpyPandaImage4 = new Image(new FileInputStream(imgbase + "JumpyPanda4.png"));

            sleepyPandaImage = new Image(new FileInputStream(imgbase + "SleepyPanda.png"));
            sleepyPandaImage1 = new Image(new FileInputStream(imgbase + "SleepyPanda1.png"));
            sleepyPandaImage2 = new Image(new FileInputStream(imgbase + "SleepyPanda2.png"));
            sleepyPandaImage3 = new Image(new FileInputStream(imgbase + "SleepyPanda3.png"));
            sleepyPandaImage4 = new Image(new FileInputStream(imgbase + "SleepyPanda4.png"));

            scaredPandaImage = new Image(new FileInputStream(imgbase + "ScaredPanda.png"));
            scaredPandaImage1 = new Image(new FileInputStream(imgbase + "ScaredPanda1.png"));
            scaredPandaImage2 = new Image(new FileInputStream(imgbase + "ScaredPanda2.png"));
            scaredPandaImage3 = new Image(new FileInputStream(imgbase + "ScaredPanda3.png"));
            scaredPandaImage4 = new Image(new FileInputStream(imgbase + "ScaredPanda4.png"));


            exitImage = new Image(new FileInputStream(imgbase + "Exit.png"));
            entranceImage = new Image(new FileInputStream(imgbase + "Entrance.png"));


            arcadeImage = new Image(new FileInputStream(imgbase + "Arcade.png"));
            candyVendingImage = new Image(new FileInputStream(imgbase + "CandyVending.png"));
            sofaImage = new Image(new FileInputStream(imgbase + "ArmChair.png"));
            wardrobeImage = new Image(new FileInputStream(imgbase + "Wardrobe.png"));

            normalTileImage = new Image(new FileInputStream(imgbase + "Tile.png"));
            crackedTileImage = new Image(new FileInputStream(imgbase + "CrackedTile.png"));
        } catch (Exception e) {e.printStackTrace();}
    }

    public void load(){

    }
}
