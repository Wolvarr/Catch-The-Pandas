package Catch_The_Pandas.Resources;

public class Exit extends Tile {

    private int score;

    public Exit(Floor f) {
        super(f);
    }

    public void setScore(int i)
    {
    	System.out.println("Called function exit.setScore()");
    	score = i;
    }
}
