package Catch_The_Pandas.Resources.GameElements;

public class JumpyPanda extends Panda {
	
	public void jump() {
		//System.out.println("Called function JumpyPanda.jump()");
		location.jumpedOn(this);
	}

	@Override
	public void interact(CandyVending obj) {
		//System.out.println("Called function JumpyPanda.interact(CandyVending)");
		this.jump();


	}


	}

