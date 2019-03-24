package Catch_The_Pandas;

public class JumpyPanda extends Panda {
	
	public void jump() {
		System.out.println("Called function JumpyPanda.jump()");
		location.jumpedOn(this);
	}

	@Override
	public void interact(OnTileObject obj) {
		System.out.println("Called function JumpyPanda.interact()");
		this.interact((CandyVending)obj);
		return;
	}

	public void interact(Arcade a) {
		System.out.println("Called function CowardPanda.interact(Arcade)");
			this.jump();
		return;
	}
}
