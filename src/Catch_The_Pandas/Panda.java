package Catch_The_Pandas;

public class Panda extends Animal {

	protected Panda nextPanda;
	protected Animal previousAnimal;

	@Override
	public boolean move(Tile tileTo) {
		System.out.println("Called function panda.move()");
		if (location.getNeighbours().contains(tileTo)) {
			if (tileTo.getOnObject() == null) {
				location.movedFrom();
				tileTo.receive(this);
				return true;
			}

			else
				return tileTo.getOnObject().steppedOn(this);
		}

		return false;
	}

	public void grab(Panda p) {
		System.out.println("Called function panda.grab()");
	}

	// DOMIAN
	public void grabpreviousAnimal(Animal a) {

		System.out.println("Called function panda.grabpreviousAnimal()");
	}

	public void release() {
		System.out.println("Called function panda.release()");
		this.nextPanda.previousAnimal = null;
		this.nextPanda = null;
	}

	/*// DOMIAN
	public boolean steppedOn(Animal Incoming) {

		System.out.println("Called function panda.steppedOn()");
		// CHECK IF ITS AN ORANGUTAN

		// check if o.grabbed==NULL
		if (Incoming.getGrabbed() == null) {
			this.grabpreviousAnimal(Incoming);
			Incoming.grab(this);
		} else {
			Incoming.getGrabbed().release();
			this.grabpreviousAnimal(Incoming);
			Incoming.grab(this);
			Incoming.getGrabbed().grab(this);

		}

		return false;
	}*/

	public boolean steppedOn(Orangutan o) {

		System.out.println("Called function panda.steppedOn(ORANGUTAN)");
		// CHECK IF ITS AN ORANGUTAN

		// check if o.grabbed==NULL
		if (o.getGrabbed() == null) {
			this.grabpreviousAnimal(o);
			o.grab(this);
		} else {
			o.getGrabbed().release();
			this.grabpreviousAnimal(o);
			o.grab(this);
			o.getGrabbed().grab(this);

		}

		return false;
	}

	public boolean steppedOn(Panda p) {

		System.out.println("Called function panda.steppedOn(PANDA)");

		return false;
	}

}
