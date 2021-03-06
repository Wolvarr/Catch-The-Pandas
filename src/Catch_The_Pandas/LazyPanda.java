package Catch_The_Pandas;

public class LazyPanda extends Panda {
	
	public void sit() {
		System.out.println("Called function LazyPanda.sit()");
		release();
	}

	@Override
	public void release() {
		System.out.println("Called function LazyPanda.release()");
		this.nextPanda.grab((Panda)this.previousAnimal);
		this.previousAnimal = null;
		this.nextPanda = null;
	}
	
	@Override
	public void interact(Arcade obj) {
		System.out.println("Called function LazyPanda.interact(Arcade)");

		return;
	}
	
	public void interact(Armchair a) {
		System.out.println("Called function LazyPanda.interact(Armchair)");
		this.sit();
		return;
	}
}
