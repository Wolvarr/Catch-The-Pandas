package Catch_The_Pandas;

import java.util.ArrayList;

public class Tile {


    private OnTileObject on;
    private ArrayList<Tile> neighbours;
    private Floor floor;


    public Tile(Floor f)
    {
        neighbours = new ArrayList<>();
        floor = f;
    }

    public OnTileObject getOnObject()
    {
        return on;
    }

    public ArrayList getNeighbours()
    {
        return  neighbours;
    }

    public void addNeighbour(Tile t)
    {
    	System.out.println("Called function tile.addNeighbour()");
    	neighbours.add(t);
        if(!t.neighbours.contains(this))
            t.addNeighbour(this);
    }


    public void jumpedOn(Animal a)
    {
    	System.out.println("Called function tile.jumpedOn()");
    }



    public boolean receive(Animal a)
    {
    	System.out.println("Called function tile.recieve()");
    	if(on == null) {
           on = a;
           on.setLocation(this);
            return true;
       }

       return on.steppedOn(a);
    }

    public  void movedFrom()
    {
    	System.out.println("Called function tile.movedFrom()");
    	this.on = null;
    }
}
