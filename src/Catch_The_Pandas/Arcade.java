package Catch_The_Pandas;

public class Arcade extends Item {
	
	public void ring() {
		System.out.println("Called function arcade.ring()");
			for(Tile tile : location.getNeighbours()) {
				if(tile.getOnObject() != null)	
					tile.getOnObject().interact(this);
		}
	}

	@Override
	public boolean steppedOn(Orangutan o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean steppedOn(Panda p) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean steppedOn(Animal a) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String toString(){
		return "Arcade: " + hashCode();
	}
}
