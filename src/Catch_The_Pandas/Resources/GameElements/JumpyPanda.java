package Catch_The_Pandas.Resources.GameElements;

import java.util.ArrayList;
import java.util.Random;

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
	@Override
	public void eachTurn(){
		System.out.println("panda eachturnje");
		ArrayList<Tile> notNullNeighbours = new ArrayList<>();
		for (int i=0; i<getLocation().getNeighbours().size(); i++){
			if (getLocation().getNeighbours().get(i) != null)
					notNullNeighbours.add(getLocation().getNeighbours().get(i));
		}
		if (notNullNeighbours.size() == 0)
			return;
		Random r = new Random();
		super.move(notNullNeighbours.get(r.nextInt(notNullNeighbours.size())));


	}


	}

