package Catch_The_Pandas.Resources.GameElements;

import java.util.ArrayList;

public class Orangutan extends Animal {

	private int ID;
	private Panda grabbedPanda = null;
	private int counter = 0;

	public void setID(int id){ID = id;}
	public int getID(){return ID;}

	public Orangutan (int id){
		ID = id;
	}


	public Orangutan(){
		ID = 0;
	}

	@Override
	public boolean move(Tile tileTo) {
		System.out.println("Called function " + this.hashCode() + ".move()");
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
		if (counter >0) return;
		this.grabbedPanda = p;
		p.setPreviousAnimal(this);
		System.out.println("Called function " + this.hashCode() + ".grab(Panda)");
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
		swapLine(o);
		System.out.println("Called function " + this.hashCode() + ".steppedOn(orangutan)");
		return false;
	}
	private void swapLine(Orangutan o)
	{
		Tile temp = o.location;
		o.setLocation(this.location);
		this.location.setOnTileObject(o);
		o.grab(this.grabbedPanda);
		this.grabbedPanda = null;
		this.location = temp;
		this.location.setOnTileObject(this);
		counter = 3;
	}

	//DOMIAN
	// Szinten nem csinal semmit
	@Override
	public boolean steppedOn(Panda p) {

		System.out.println("Called function " + this.hashCode() + ".steppedOn(panda)");
		return false;
	}

	@Override
	//minden kör elején lefut majd
	public void eachTurn(){
		if (counter>0) counter--;
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

	public void releasePanda(){
		if (grabbedPanda!=null)
			grabbedPanda.release();
		System.out.println(toString() + "Called release function");
	}



}
