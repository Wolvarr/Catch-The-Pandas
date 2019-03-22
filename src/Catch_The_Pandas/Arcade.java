package Catch_The_Pandas;

public class Arcade extends Item {
	
	public void ring() {
		System.out.println("Called function arcade.ring()");
			for(Tile tile : location.getNeighbours()) {
				if(tile.getOnObject() != null)	
					tile.getOnObject().interact(this);
		}
	}
}
