package Catch_The_Pandas;

public class LazyPanda extends Panda {
	
	public void sit() {
		System.out.println("Called function LazyPanda.sit()");
		release();
	}

	@Override
	public void interact(OnTileObject obj) {
		System.out.println("Called function LazyPanda.interact()");
		this.interact((Armchair)obj);
		return;
	}
	
	public void interact(Arcade a) {
		System.out.println("Called function LazyPanda.interact(Armchair)");
			this.sit();
		return;
	}
}
