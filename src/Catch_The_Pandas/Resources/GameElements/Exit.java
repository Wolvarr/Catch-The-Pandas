package Catch_The_Pandas.Resources.GameElements;

public class Exit extends Tile {

    private int score;

    public Exit(Floor floor, int parseInt) {
        super(floor, parseInt);
    }


    public void setScore(int i)
    {
    	//System.out.println("Called function exit.setScore()");
    	score = i;
    }
}
