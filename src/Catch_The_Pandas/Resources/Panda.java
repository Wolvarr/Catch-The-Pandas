package Catch_The_Pandas.Resources;

public class Panda extends Animal {

	protected Panda nextPanda;
	protected Animal previousAnimal;

	public void setPreviousAnimal(Animal a)
	{
		this.previousAnimal = a;
	}

	@Override
	public boolean move(Tile tileTo) {
		System.out.println(toString() + "Called function panda.move(" + tileTo.toString() + ")");
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
		System.out.println(toString() + "Called function panda.grab(pandaa)");
	}

	// DOMIAN
	//megragadja az elotte levo allatot
	public void grabpreviousAnimal(Animal a) {

		this.previousAnimal = a;
		System.out.println(toString() + "Called function panda.grabPreviousAnimal()");
	}

	public void release() {
		System.out.println(toString() + "Called function panda.release()");
		this.previousAnimal = null;
		this.nextPanda = null;
	}


	// DOMIAN
	public boolean steppedOn(Orangutan o) {

		System.out.println(toString() + "Called function panda.steppedOn(oranguan)");

		// teszeli hogy o.grabbed==NULL
		if (o.getGrabbed() == null) {
			this.grabpreviousAnimal(o);
			o.grab(this);
		}
		// ha nem akkor elengedi a fogott pandat
		// a korabban fogott megragadja amire érkezik (becsatolodik a vegere)
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

		System.out.println(toString() + "Called function panda.steppedOn(PANDA)");

		return false;
	}
	
	@Override
	public void fall()
    {
    	System.out.println(toString() + "Called function panda.fall()");
    	if(nextPanda != null) {
    		release();
    	}
    }

    @Override
	public String toString(){
		return "Panda: " + hashCode()+ " ";
	}


}
