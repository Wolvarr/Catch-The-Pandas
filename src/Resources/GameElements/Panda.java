package Resources.GameElements;

public class Panda extends Animal {

	protected Panda nextPanda = null;
	protected Animal previousAnimal = null;


	public void setPreviousAnimal(Animal a)
	{
		this.previousAnimal = a;
	}

	@Override
	public boolean move(Tile tileTo) {
		//System.out.println(toString() + "Called function panda.move(" + tileTo.toString() + ")");
		//Megn�zi hogy �rv�nyes helyre l�p�nk-e
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


	//Teleport fv, hogy sz�pen �tl�that� legyen
	// kap egyet hogy hova kell menni
	// Hova kell teleport�lni
	// Orangut�nt is kap aj�nd�kba

	public boolean moveTeleported(Tile tileTo, Tile teleportloc, Orangutan o) {

		// ha v�gig fut sz�pen a rekurzi� �s a k�vi panda null
		// akkor m�r nem teleport�lgat
		if(this.nextPanda==null){
			o.setTeleported(false);
		}
		// ha nulla a helyzet teleport �s visszat�r
		if(this.location==null){
			location=teleportloc;
			teleportloc.setOnTileObject(this);
			return  true;
		}
		else if (location.getNeighbours().contains(tileTo)) {
			if (tileTo.getOnObject() == null) {
				location.movedFrom();

				// ha van kovetkezo panda akkor rekurzivan vegig megy

				if (this.nextPanda != null && this.location!=null) {
					this.nextPanda.moveTeleported(location, teleportloc,o);
				}
				// minden hasonl� mint a sima mozg�sn�l
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
		//System.out.println(toString() + "Called function panda.grab(pandaa)");
	}

	//ez szerintem nem kell
	public void release() {
		//System.out.println(toString() + "Called function panda.release()");
		//Ez szerintem kell ide
		//Mivel t�bb ir�ny� a k�t�s
		// Az elozo �llatnak is el kell engednie ot
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
		//System.out.println(toString() + "Called function panda.release()");
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

		//System.out.println(toString() + "Called function panda.steppedOn(oranguan)");

		// teszeli hogy o.grabbed==NULL
		if (o.getGrabbed() == null) {
			//this.setPreviousAnimal(o);
			o.grab(this);
		}

		//helycsere
		// a korabban fogottat megragadja amire �rkeztek(becsatolodik a vegere)
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
	// pandara l�pve is lehet csere
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

		//System.out.println(toString() + "Called function panda.steppedOn(PANDA)");

		return false;
	}
	
	@Override
	public void fall()
    {
    	//System.out.println(toString() + "Called function panda.fall()");
    	if(nextPanda != null) {
    		release();
    	}
    }

    @Override
	public String toString(){
		return "Panda";
	}

	//Wardobe-ba l�p�s eser�n el kell t�ntetni a pand�kat
	public void disappearPandas(){
		while(this.nextPanda != null){
			this.location.movedFrom();
			this.location = null;
			this.nextPanda.disappearPandas();
		}
	}


}
