package Catch_The_Pandas.Resources;

public class Arcade extends Item {
	
	public void ring() {
		System.out.println(toString() + "Called function arcade.ring()");
			for(Tile tile : location.getNeighbours()) {
				if(tile.getOnObject() != null)	
					tile.getOnObject().interact(this);
		}
	}

	@Override
	public boolean steppedOn(Orangutan o) {
		System.out.println(toString() + "Called function item.steppedOn(Orangutan)");
		return false;
	}

	@Override
	public boolean steppedOn(Panda p) {
		System.out.println(toString() + "Called function item.steppedOn(Panda)");
		return false;
	}

	@Override
	public boolean steppedOn(Animal a) {
		System.out.println(toString() + "Called function item.steppedOn(Animal)");
		return false;
	}

	@Override
	public String toString(){
		return "Arcade: " + hashCode() + " ";
	}
}
