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



	// Visszaadja a megragadott pandat
	public Panda getGrabbed() {
		// System.out.println("Called function orangutan.getGrabbed()");
		return grabbedPanda;
	}
	
	// Nem csinal egyenlore semmit
	@Override
	public boolean steppedOn(Orangutan o) {
		//mindig hamissal tér vissza
		swapLocation(o);
		System.out.println("Called function " + this.hashCode() + ".steppedOn(orangutan)");
		return false;
	}

	//domian
	//ezt kicsit átírtam
	@Override
	public void swapLocation(Orangutan incoming)
	{
		Tile pandat=this.location;
		Tile orangutant= incoming.location;
		//this.location.movedFrom();
		//incoming.location.movedFrom();
		pandat.setOnTileObject(incoming);
		orangutant.setOnTileObject(this);

		incoming.grab(this.grabbedPanda);
		this.grabbedPanda = null;
		counter = 3;
	}

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

	@Override
	public void releaseNextPanda(){
		if (grabbedPanda!=null)
			grabbedPanda=null;
		System.out.println(toString() + "Called release function");
	}

	public void releasePanda(){
		if (grabbedPanda!=null)
			grabbedPanda.releaseAll();
		System.out.println(toString() + "Called release function");
	}



}