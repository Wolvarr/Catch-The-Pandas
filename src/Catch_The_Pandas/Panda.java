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
				
				//DOMIAN
				// ha van kovetkezo panda akkor rekurzivan vegig megy
				if (this.nextPanda != null) {
					this.nextPanda.move(location);
				}
				tileTo.receive(this);
				return true;
			}

			else
				return tileTo.getOnObject().steppedOn(this);
		}

		return false;
	}

	public void grab(Panda p) {

		this.nextPanda = p;
		System.out.println("Called function panda.grab()");
	}

	// DOMIAN
	//megragadja az elotte levo allatot
	public void grabpreviousAnimal(Animal a) {

		this.previousAnimal = a;
		System.out.println("Called function panda.grabpreviousAnimal()");
	}

	public void release() {
		System.out.println("Called function panda.release()");
		this.previousAnimal = null;
		this.nextPanda = null;
	}


	// DOMIAN
	public boolean steppedOn(Orangutan o) {

		System.out.println("Called function panda.steppedOn(ORANGUTAN)");

		// teszeli hogy o.grabbed==NULL
		if (o.getGrabbed() == null) {
			this.grabpreviousAnimal(o);
			o.grab(this);
		}
		// ha nem akkor elengedi a fogott pandat
		// a kor�bban fogott megragadja amire �rkezik (becsatolodik a vegere)
		// az orangutan is megragadja amire erkezik
		// majd amire erkeztek is megfogja az elotte levo pandat
		else {
			o.getGrabbed().release();
			o.getGrabbed().grab(this);
			o.grab(this);

			this.grabpreviousAnimal(o);

		}

		return false;
	}

	// DOMIAN
	//nem csinal semmit ha panda lep pandara
	public boolean steppedOn(Panda p) {

		System.out.println("Called function panda.steppedOn(PANDA)");

		return false;
	}
	
	@Override
	public void fall()
    {
    	System.out.println("Called function panda.fall()");
    	if(nextPanda != null) {
    		release();
    	}
    }


}