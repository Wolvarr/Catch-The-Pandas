package Catch_The_Pandas.Resources.GameElements;

import java.util.*;

public class Floor {

    //private ArrayList<Tile> tiles;
    private Map <Integer, Tile> tiles = new HashMap<>();
    private int pandaCount;
    private Map<Integer, Orangutan> orangutans = new HashMap<>();

    public Floor()
    {
        //tiles = new ArrayList<Tile>();
        pandaCount=0;
    }
    //talán nem fog kelleni, addig marad
    /* public Floor(Orangutan o)
    {
        tiles = new ArrayList<Tile>();
        pandaCount=0;
        orangutans.put(0,o);

    }
    */
    public Orangutan getOrangutan(int ID){
        return orangutans.get(ID);
    }

    public void addOrangutan(Integer ID, Orangutan o){
        orangutans.put(ID,o);
    }

    public void addTile(int ID, Tile t)
    {
        tiles.put(ID, t);
    	//tiles.add(t);
    }

    public Collection<Tile> getAllTiles(){
        return tiles.values();
    }

    public Collection<Integer> getAllOrangutanIDs() { return orangutans.keySet();}
    public Collection<Orangutan> getAllOrangutans() { return orangutans.values();}


    public void changePandaCount( int i)
    {
    	//System.out.println("Called function floor.changePandaCount()");
    	pandaCount += i;
    }


    public Tile getTile(int id) {
        return tiles.get(id);
    }

    public void addTile(Tile t1) {
        tiles.put(-1, t1);
    }
}
