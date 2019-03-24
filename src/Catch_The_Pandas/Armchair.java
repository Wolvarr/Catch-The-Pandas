package Catch_The_Pandas;

public class Armchair extends Item {
	
	public void pull() {
		System.out.println("Called function armchair.pull()");
			for(Tile tile : location.getNeighbours()) {
				if(tile.getOnObject() != null)	
					tile.getOnObject().interact(this);
		}
	}
	
	@Override
    public boolean steppedOn(Animal inComingAnimal){
        System.out.print("Nem tudsz ide lépni!");
        return false;
    }

	
}
