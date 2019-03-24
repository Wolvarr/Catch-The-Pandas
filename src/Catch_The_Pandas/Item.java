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

    public void interact(Arcade obj){ System.out.println("Called function item.interact()");}

    @Override
    public void interact(CandyVending obj) {
        System.out.println("Called function item.interact(CandyVending)");
    }

    @Override
    public void interact(Armchair obj) {
        System.out.println("Called function item.interact(Armchair)");

    }
}
