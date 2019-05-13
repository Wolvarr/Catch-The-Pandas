package Catch_The_Pandas.IO;

import Catch_The_Pandas.Resources.GameElements.Floor;
import Catch_The_Pandas.Resources.GameElements.OnTileObject;
import Catch_The_Pandas.Resources.GameElements.Tile;
import Catch_The_Pandas.Resources.GameElements.Wardrobe;


import java.util.ArrayList;
import java.util.HashMap;

public class Map {
    Floor underConstruction;
    String sourceFolder = null;
    java.util.Map<Integer, Tile> tiles = new HashMap<Integer, Tile>();
    ArrayList<TileConnection> connections = new ArrayList<TileConnection>();
    HashMap<Integer, OnTileObject> objects = new HashMap<Integer, OnTileObject>();
    public ArrayList<TileView> tileGraphics = new ArrayList<>();

    //konstrukot, amely beállítja a szcenáriót tartalmazó mappát
    public Map(String sourcefolder){
        sourceFolder = sourcefolder;
    }

    public Floor build(){
        underConstruction = new Floor();
        EnveriomentParser eparser = new EnveriomentParser(underConstruction,sourceFolder);
        //a csempelista betöltése
        try {
            tiles = (HashMap<Integer, Tile>)eparser.parse(InputType.tiles);
            if (tiles!=null) System.out.println("tiles ok");
        } catch (Exception e) {
            e.printStackTrace();
        }

        //a játéktéren található objetumok betöltése
        try {
            objects = (HashMap<Integer, OnTileObject>) eparser.parse(InputType.objects);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Wardrobe dummywardrobe = new Wardrobe();
        ArrayList<Wardrobe> wardrobes = new ArrayList<>();
        for(OnTileObject ot: objects.values()){
            if(ot.getClass().equals(dummywardrobe.getClass()))
                wardrobes.add((Wardrobe)ot);
        }

        for(Wardrobe w: wardrobes){
            w.importList(wardrobes);
        }

        //a csempék közti kapcsolatok listájának betöltése
        try {
            connections = (ArrayList<TileConnection>) eparser.parse(InputType.connections);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //beolvassuk a csempék koordinátáit a grafikus felülethez
        try {
            tileGraphics = (ArrayList<TileView>) eparser.parse(InputType.graphics);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //a csempék közti kapcsolatok összeszerelése
        for(TileConnection tc : connections){
            tiles.get(tc.ID1).connect(tiles.get(tc.ID2));
        }

        //hozzáadjuk a beolvasott pályaelemeket
        objects.forEach((id,obj) -> tiles.get(id).setOnTileObject(obj));

        //hozzáadjuk a floorhoz a kész tile-okat;
        for (java.util.Map.Entry<Integer, Tile> t : tiles.entrySet()) {
            underConstruction.addTile(t.getKey(),t.getValue());
        }

        //majd visszatérünk az elkészült floorral
        return underConstruction;
    }


}
