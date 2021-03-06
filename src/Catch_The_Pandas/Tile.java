package Catch_The_Pandas;

import java.util.ArrayList;

public class Tile {

	private int number=0;
	private static int stat=0;
	
    private OnTileObject on;
    private ArrayList<Tile> neighbours;
    private Floor floor;


    public void setOnTileObject(OnTileObject object)
    {
        this.on = object;
        on.setLocation(this);
    }

    public Tile(Floor f)
    {
    	neighbours = new ArrayList<Tile>();
        floor = f;
        this.number=stat;
        stat++;
    }
    
    public String toString() {
    	return "Tile: "+ hashCode() + " ";
    }
    
    public OnTileObject getOnObject()
    {
        return on;
    }

    public ArrayList<Tile> getNeighbours()
    {
        return  neighbours;
    }

    public void addNeighbour(Tile t)
    {
    	neighbours.add(t);
        if(!t.neighbours.contains(this))
            t.addNeighbour(this);
    }


    public void jumpedOn(Animal a)
    {
    	System.out.println(toString() + "Called function tile.jumpedOn()");
    }



    public boolean receive(Animal a)
    {
    	System.out.println(toString() + "Called function tile.recieve()");
    	if(on == null) {
           on = a;
           on.setLocation(this);
            return true;
       }

       return on.steppedOn(a);
    }

    public  void movedFrom()
    {
    	System.out.println(toString() + "Called function tile.movedFrom()");
    	this.on = null;
    }
}
