package Catch_The_Pandas.Resources.GameElements;

public class Panda extends Animal {

	protected Panda nextPanda = null;
	protected Animal previousAnimal = null;

	public void setPreviousAnimal(Animal a)
	{
		this.previousAnimal = a;
	}

	@Override
	public boolean move(Tile tileTo) {
		System.out.println(toString() + "Called function panda.move(" + tileTo.toString() + ")");
		//Megnézi hogy érvényes helyre lépünk-e
		if (location.getNeighbours().contains(tileTo)) {
			if (tileTo.getOnObject() == null) {
				location.movedFrom();
				
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
		p.setPreviousAnimal(this);
		System.out.println(toString() + "Called function panda.grab(pandaa)");
	}

	//ez szerintem nem kell
	public void release() {
		System.out.println(toString() + "Called function panda.release()");
		//Ez szerintem kell ide
		//Mivel több irányú a kötés
		// Az elozo állatnak is el kell engednie ot
		//this.previousAnimal.releaseNextPanda();
		this.previousAnimal = null;
		this.nextPanda = null;
	}

	public Panda getNextPanda() {
		return nextPanda;
	}

	//Domian
	// Ez kell szerintem hogy felbomoljon a sor
	public void releaseAll() {
		this.previousAnimal.releaseNextPanda();
		System.out.println(toString() + "Called function panda.release()");
		this.previousAnimal = null;
		if (this.nextPanda != null) {
			this.nextPanda.releaseAll();
			this.nextPanda=null;
		}

	}


	//Domian
	@Override
	public void releaseNextPanda() {
		if (nextPanda!=null){
			this.nextPanda = null;
		}

	}


	// DOMIAN
	public boolean steppedOn(Orangutan o) {

		System.out.println(toString() + "Called function panda.steppedOn(oranguan)");

		// teszeli hogy o.grabbed==NULL
		if (o.getGrabbed() == null) {
			//this.setPreviousAnimal(o);
			o.grab(this);
		}

		//helycsere
		// a korabban fogottat megragadja amire érkeztek(becsatolodik a vegere)
		// az orangutan is megragadja amire erkezik
		else {
			//o.getGrabbed().releasePrevious();
			this.swapLocation(o);
			this.grab(o.getGrabbed());

			o.grab(this);

			//this.setPreviousAnimal(o);

		}

		return false;
	}

	//Domian
	// pandara lépve is lehet csere
	@Override
	public void swapLocation(Orangutan incoming){
		Tile pandat=this.location;
		Tile orangutant= incoming.location;
		//this.location.movedFrom();
		//incoming.location.movedFrom();
		pandat.setOnTileObject(incoming);
		orangutant.setOnTileObject(this);

	}


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
		return "Panda";
	}

	//Wardobe-ba lépés eserén el kell tüntetni a pandákat
	public void disappearPandas(){
		while(this.nextPanda != null){
			this.location = null;
			this.location.setObject(null);
			disappearPandas();
		}
	}


}
