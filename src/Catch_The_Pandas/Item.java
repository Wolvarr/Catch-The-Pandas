package Catch_The_Pandas;

public class Item extends OnTileObject {
    @Override
    public void eachTurn() {
    	System.out.println("Called function item.eachTurn()");
    }

    @Override
    public boolean steppedOn(Animal inComingAnimal) {
    	System.out.println("Called function item.steppedOn()");
    	return false;
    }

    @Override
    public void interact(OnTileObject obj) {
    	System.out.println("Called function item.interact()");
    }
}
