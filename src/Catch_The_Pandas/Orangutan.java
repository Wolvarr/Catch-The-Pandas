package Catch_The_Pandas;

import java.util.ArrayList;

public class Orangutan extends Animal {

	private Panda grabbedPanda;

	@Override
	public boolean move(Tile tileTo) {
		System.out.println(toString() + "Called function orangutan.move()");
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
		System.out.println(toString() + "Called function orangutan.grab()");
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
		//mindig hamissal tér vissza
		System.out.println(toString() + "Called function orangutan.steppedOn(Orangutan)");
		return false;
	}

	//DOMIAN
	// Szinten nem csinal semmit
	@Override
	public boolean steppedOn(Panda p) {
		
		System.out.println(toString() + "Called function orangutan.steppedOn(Panda)");
		return false;
	}

	@Override
	//minden kör elején lefut majd
	public void eachTurn(){
		//megnézi, hogy az orángután beszorult-e
		boolean freetomove = false;
		//elkéri az alatta lévő csempe szomszédait
		ArrayList<Tile> neighbours = location.getNeighbours();
		//majd végignézi azokat, lehet-e rájuk lépni
		for(Tile t : neighbours){
			if (t.getOnObject()!=null)
				//amennyiben bármelyik is igazzal tér vissza, az orángután nincs beszorulva
				freetomove = freetomove || t.getOnObject().steppedOn(this);
		}

		//itt majd a game controller játék vége funkcióját hívjuk
		if (!freetomove) System.out.println(toString() + "GAME OVER, YOU GOT TRAPPED");
	}

	@Override
	public String toString(){
		return "Orangutan: " + hashCode() + " ";
	}



}
