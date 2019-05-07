
package Resources.GameElements;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Wardrobe extends Item {

    public boolean deterministic = false;
    //choosing the new location randomly
    Tile newlocation;

    List<Wardrobe> otherWardrobes = new ArrayList<Wardrobe>();

    //adds a wardobe to the list
    public void addWardrobe(Wardrobe w){
        //safety check, so a wardrobe will never teleport you to itself...
        if(w!=this)
            otherWardrobes.add(w);
    }

    public Tile getNewLocation(){
        return newlocation;
    }

    @Override
    public boolean steppedOn(Orangutan o){
        //System.out.println(toString()+"Called function wardrobe.steppedOn");
        o.setTeleported(true);
        //creating a new Random for more enjoyable gameplay resulting from randomly warpin' around the hood
        Random r = new Random();
        //Wardroba lépés esetén eltűnik
        if(o.getGrabbed()!=null)
            o.getGrabbed().disappearPandas();
        if(!deterministic)
            newlocation = otherWardrobes.get(r.nextInt(otherWardrobes.size())).getLocation();
        else newlocation = otherWardrobes.get(0).getLocation();
        o.setteleportedto(newlocation.getNeighbours().get(0));
        //making the warp, setting up connection between the animal and it's new tile
        //Mi van ha a mellette lévő mező csapda?!
        o.setLocation(newlocation.getNeighbours().get(0)); // Ez nem biztos hogy kell mivel a setontileobject is beállítja a locationt
        newlocation.getNeighbours().get(0).setOnTileObject(o);
        return true;
    }

    public void setDeterministic(boolean deterministic) {
        this.deterministic = deterministic;
    }

    @Override
    public String toString(){
        return "Wardrobe: " + hashCode()+ " ";
    }

    //exports the other wardrobes list plus itself, for easier setting up
    public ArrayList<Wardrobe> exportList(){
        //creates temporary list to return with
        ArrayList<Wardrobe> temp = new ArrayList<Wardrobe>();
        //adds all of it's current data to it
        temp.addAll(otherWardrobes);
        //plus itself
        temp.add(this);
        return temp;
    }

    //imports a list, and changes it's current list for it
    public void importList(ArrayList<Wardrobe> wlist){
        otherWardrobes.clear();
        for(Wardrobe w: wlist)
            if (w != this)
                otherWardrobes.add(w);
    }

    public String listOthers(){
        String temp = "";
        for(Wardrobe w: otherWardrobes) temp += w.toString() + "\n";
        return temp;
    }



}
