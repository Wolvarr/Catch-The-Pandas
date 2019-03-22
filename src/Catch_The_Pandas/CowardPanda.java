package Catch_The_Pandas;

public class CowardPanda extends Panda {
	
	public void scare() {
		System.out.println("Called function CowardPanda.scare()");
		release();
	}
	
	public void interact(OnTileObject obj) {
		System.out.println("Called function CowardPanda.interact()");
		if(obj.getClass().getName() == "Arcade") {
			this.scare();
		return;
		}
	}
}
