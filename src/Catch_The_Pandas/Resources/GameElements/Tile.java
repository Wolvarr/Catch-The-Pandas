package Catch_The_Pandas.Resources.GameElements;

import java.util.ArrayList;

public class Tile {

    protected int ID;
	protected int number=0;
	protected static int stat=0;

	
    protected OnTileObject on;
    protected ArrayList<Tile> neighbours;
    protected Floor floor;

    protected void setID(int id){
        ID = id;
    }

    public int getID(){
        return ID;
    }



    //visszaadja, hogy a pamaéterül kapott Tile már szomszédja-e ennek
    public boolean contains(Tile f){
        return neighbours.contains(f);
    }

    //Ez a wardrobe miatt kell
    public void setObject(OnTileObject object){
        this.on = object;
    }

    //beállítja a kétirányú ismeretséget a paraméterül kapott játékelem és önmaga között
    public void setOnTileObject(OnTileObject object)
    {
        this.on = object;
        on.setLocation(this);
    }


    //konstruktor, mely beállítja a tile kezdőértékeit és az emletet, majd hozzáadja magát az emelethez is
    public Tile(Floor f)
    {
    	neighbours = new ArrayList<Tile>();
        floor = f;
        this.number=stat;
        stat++;

    }
    public Tile(Floor f, int id){
        ID = id;
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

    public boolean connect(Tile t)
    {
        //kétirányú kapcsolatot hoz létre this és a paraméterül kapott t között
    	neighbours.add(t);
        if(!t.contains(this)){
            t.connect(this);
            return true;
        }
        else return false;

    }

    //mindkét irányban megszünteti a kapcsolatot a két csempe között
    public boolean disconnect(Tile t){
        if(neighbours.contains(t)){
            neighbours.remove(t);
            if (t.contains(this)) //hogy ne fusson örökké
                t.disconnect(this);
            return true;
        }
        return false;
    }


    public void jumpedOn(Animal a)
    {
    	//System.out.println(toString() + "Called function tile.jumpedOn()");
    }



    public boolean receive(Animal a)
    {
    	//System.out.println(toString() + "Called function tile.recieve()");
    	if(on == null) {
           on = a;
           on.setLocation(this);
            return true;
       }

       return on.steppedOn(a);
    }

    public  void movedFrom()
    {
    	//System.out.println(toString() + "Called function tile.movedFrom()");
    	this.on = null;
    }
}
