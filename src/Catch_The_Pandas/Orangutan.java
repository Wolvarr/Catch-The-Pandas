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
				if (grabbedPanda != null) {
					grabbedPanda.move(location);
				}
				tileTo.receive(this);
				//
				return true;
			}

			else
				return tileTo.getOnObject().steppedOn(this);
		}

		return false;
	}

	public void grab(Panda p) {
		grabbedPanda = p;
		System.out.println("Called function orangutan.grab()");
	}

	@Override
	public boolean steppedOn(Animal inComingAnimal) {

		System.out.println("Called function orangutan.steppedOn()");
		return false;
	}

	// DOMIAN
	public Panda getGrabbed() {
		System.out.println("Called function orangutan.getGrabbed()");
		return grabbedPanda;
	}

}
