package Catch_The_Pandas;

public class Orangutan extends Animal {

	private Panda grabbedPanda;

	@Override
	public boolean move(Tile tileTo) {
		System.out.println("Called function orangutan.move()");
		if (location.getNeighbours().contains(tileTo)) {
			if (tileTo.getOnObject() == null) {
				location.movedFrom();
				//DOMIAN
				//Ha sorban vannak mogotte a pandak akkor meghivodik az o move-juk is
				if (this.grabbedPanda != null) {
					this.grabbedPanda.move(location);
				}
				tileTo.receive(this);
				return true;
			} else
				return tileTo.getOnObject().steppedOn(this);
		}
		return false;
	}

	public void grab(Panda p) {
		this.grabbedPanda = p;
		System.out.println("Called function orangutan.grab()");
	}


	// DOMIAN
	// Visszaadja a megragadott pandat
	public Panda getGrabbed() {
		// System.out.println("Called function orangutan.getGrabbed()");
		return grabbedPanda;
	}
	
	//DOMIAN
	// Nem csinal egyenlore semmit
	@Override
	public boolean steppedOn(Orangutan o) {
		
		System.out.println("Called function orangutan.steppedOn(Orangutan)");
		return false;
	}

	//DOMIAN
	// Szinten nem csinal semmit
	@Override
	public boolean steppedOn(Panda p) {
		
		System.out.println("Called function orangutan.steppedOn(Panda)");
		return false;
	}

}
