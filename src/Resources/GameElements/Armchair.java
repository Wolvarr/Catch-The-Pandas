package Resources.GameElements;

public class Armchair extends Item {
	
	public void pull() {
		//System.out.println("Called function armchair.pull()");
			for(Tile tile : location.getNeighbours()) {
				if(tile.getOnObject() != null)	
					tile.getOnObject().interact(this);
		}
	}
	
	@Override
    public boolean steppedOn(Animal inComingAnimal){
        //System.out.print("You cant step to an Armchair");
        return false;
    }

    @Override
    public String toString(){
		return "Arcade";
	}

	
}
