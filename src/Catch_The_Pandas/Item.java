package Catch_The_Pandas;

public abstract class Item extends OnTileObject {
    @Override
    public void eachTurn() {
    	System.out.println("Called function item.eachTurn()");
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
    
    @Override
    public boolean steppedOn(Animal inComingAnimal) {
    	System.out.println("Called function item.steppedOn(Animal)");
    	return false;
    }

    @Override
	public boolean steppedOn(Orangutan o) {
    	
    	System.out.println("Called function item.steppedOn(Orangutan)");
		return false;
	}

	@Override
	public boolean steppedOn(Panda p) {
		
		System.out.println("Called function item.steppedOn(Panda)");
		return false;
	}
}
