package Catch_The_Pandas.Resources.GameElements;

public class CowardPanda extends Panda {
	
	public void scare() {
		System.out.println("Called function CowardPanda.scare()");
		// ez itt nemhiszem hogy jó Danikám
		release();
		//this.nextPanda.releaseAll();
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
