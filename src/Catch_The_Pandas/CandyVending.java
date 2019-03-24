package Catch_The_Pandas;

public class CandyVending extends Item {
	
    @Override
    public boolean steppedOn(Animal inComingAnimal){
        System.out.print("Nem tudsz ide lépni!");
        return false;
    }
    
    public void beep() {
		System.out.println("Called function candyvending.beep()");
			for(Tile tile : location.getNeighbours()) {
				if(tile.getOnObject() != null)	
					tile.getOnObject().interact(this);
		}
	}
}
