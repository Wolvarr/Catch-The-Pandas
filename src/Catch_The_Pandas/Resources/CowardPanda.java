package Catch_The_Pandas.Resources;

public class CowardPanda extends Panda {
	
	public void scare() {
		System.out.println("Called function CowardPanda.scare()");
		release();
	}

	//Dani
//	@Override
//	public void interact(OnTileObject obj) {
//		System.out.println("Called function CowardPanda.interact()");
//		//Erre ki kéne találni valamit
//
//		return;
//	}

	public void interact(Arcade a) {
		System.out.println("Called function CowardPanda.interact(Arcade)");
			this.scare();
		return;
	}
}
