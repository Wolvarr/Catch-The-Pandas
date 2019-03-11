package Home_work;

import java.util.ArrayList;

public class Tile {


    private OnTileObject on;
    private ArrayList<Tile> neighbours;
    private Floor floor;


    public void jumpedOn(Animal a)
    {

    }



    public boolean receive(Animal a)
    {
        return false;
    }

    public  void movedFrom()
    {
        this.on = null;
    }
}
