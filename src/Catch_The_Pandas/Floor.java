package Catch_The_Pandas;

import java.util.ArrayList;

public class Floor {

    private ArrayList<Tile> tiles;
    private int pandaCount;

    public Floor()
    {
        tiles = new ArrayList<Tile>();
        pandaCount=0;
    }

    public void addTile(Tile t)
    {
    	System.out.println("Called function floor.addTile()");
    	tiles.add(t);
    }

    public void changePandaCount( int i)
    {
    	System.out.println("Called function floor.changePandaCount()");
    	pandaCount += i;
    }

}
