package Catch_The_Pandas;

public class CandyVending extends Item {
	
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
    
    public void beep() {
		System.out.println(toString() + "Called function candyvending.beep()");
			for(Tile tile : location.getNeighbours()) {
				if(tile.getOnObject() != null)	
					tile.getOnObject().interact(this);
		}
	}
	@Override
    public String toString(){
        return "CandyVending: " + hashCode() + " ";
    }

}
