package Catch_The_Pandas;

public class CowardPanda extends Panda {
	
	public void scare() {
		System.out.println("Called function CowardPanda.scare()");
		release();
	}


	public void interact(Arcade a) {
		System.out.println("Called function CowardPanda.interact()");
			this.scare();
		return;

	}
}
