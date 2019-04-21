package Catch_The_Pandas.Resources.GameElements;

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


	public void release() {
		System.out.println(toString() + "Called function panda.release()");
		//Ez szerintem kell ide
		//Mivel t�bb ir�ny� a k�t�s
		// Az elozo �llatnak is el kell engednie ot
		this.previousAnimal.releaseNextPanda();
		this.previousAnimal = null;
		this.nextPanda = null;
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
	public void releasePrevious() {

		this.previousAnimal = null;
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
			this.setPreviousAnimal(o);
			o.grab(this);
		}
		// ha nem akkor elengedi a fogott orangutant
		// a korabban fogott megragadja amire �rkezik (becsatolodik a vegere)
		// az orangutan is megragadja amire erkezik
		// majd amire erkeztek is megfogja az elotte levo orangutant
		else {
			o.getGrabbed().releasePrevious();
			this.swapLocation(o);
			o.getGrabbed().grab(this);

			o.grab(this);

			this.setPreviousAnimal(o);

		}

		return false;
	}

	//Domian
	@Override
	public void swapLocation(Orangutan incoming){
		Tile panda=this.location;
		Tile orangutan= incoming.location;
		this.location.movedFrom();
		incoming.location.movedFrom();
		panda.setOnTileObject(incoming);
		orangutan.setOnTileObject(this);

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
