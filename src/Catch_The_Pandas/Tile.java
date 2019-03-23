package Catch_The_Pandas;

import java.util.ArrayList;

public class Tile {
	
	//CHECK
	//static int number=0;
	//int current=0;
	
    private OnTileObject on;
    private ArrayList<Tile> neighbours;
    private Floor floor;
  
    //CHECK
   /* @Override
    public String toString() {
		return "Tile"+this.current;
    	
    }*/
    
    public void setOnTileObjext(OnTileObject object)
    {
        this.on = object;
    }

    public Tile(Floor f)
    {
    	neighbours = new ArrayList<>();
        floor = f;
       //CHECK
       // current=number;
       //number++;
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
    	System.out.println("Called function tile.jumpedOn()");
    }



    public boolean receive(Animal a)
    {
    	System.out.println("Called function tile.recieve()"/*+this.toString()*/);
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
