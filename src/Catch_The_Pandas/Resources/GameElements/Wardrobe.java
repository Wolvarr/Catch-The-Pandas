
package Catch_The_Pandas.Resources.GameElements;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Wardrobe extends Item {

    public boolean deterministic = false;

    List<Wardrobe> otherWardrobes = new ArrayList<Wardrobe>();

    //adds a wardobe to the list
    public void addWardrobe(Wardrobe w){
        //safety check, so a wardrobe will never teleport you to itself...
        if(w!=this)
            otherWardrobes.add(w);
    }

    @Override
    public boolean steppedOn(Orangutan o){
        System.out.println(toString()+"Called function wardrobe.steppedOn");
        //creating a new Random for more enjoyable gameplay resulting from randomly warpin' around the hood
        Random r = new Random();
        //choosing the new location randomly
        Tile newlocation;
        if(!deterministic)
            newlocation = otherWardrobes.get(r.nextInt(otherWardrobes.size())).getLocation();
        else newlocation = otherWardrobes.get(0).getLocation();
        //making the warp, setting up connection between the animal and it's new tile
        o.setLocation(newlocation.getNeighbours().get(0));
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
