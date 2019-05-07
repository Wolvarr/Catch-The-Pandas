package Resources.GameElements;

import Resources.Interfaces.IMovable;

public abstract class Animal extends OnTileObject implements IMovable {


    public void fall()
    {
    	//System.out.println(toString() + "Called function animal.fall()");
    	//System.out.println(this.toString() + " falls down");
        //location.movedFrom();
    }
    public void swapLocation(Orangutan incoming)
    {

    }

    public void releaseNextPanda()
    {

    }

    @Override
    public boolean move(Tile tileTo) {
    	//System.out.println(toString() + "Called function animal.move( " + tileTo.toString() + ")");
        return false;
    }

    @Override
    public void eachTurn() {
    	//System.out.println(toString() + "Called function animal.eachTurn()");
    }

    @Override
    public boolean steppedOn(Animal inComingAnimal) {

    	//System.out.println(toString() + "Called function animal.steppedOn()");
        return false;
    }

    @Override
    public void interact(Arcade obj) {
    	//System.out.println(toString() + "Called function animal.interact(Arcade)");
    }

    @Override
    public void interact(CandyVending obj) {
        //System.out.println(toString() + "Called function animal.interact(CandyVending)");
    }

    @Override
    public void interact(Armchair obj) {
       // System.out.println(toString() + "Called function animal.interact(Armchair)");

    }

    @Override
    public String toString(){
        return "Animal: " + String.valueOf(hashCode() + " ");
    }

}
