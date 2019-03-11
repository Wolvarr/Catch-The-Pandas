package Catch_The_Pandas;

public class Exit extends Tile {

    private int score;

    public Exit(Floor f) {
        super(f);
    }

    public void setScore(int i)
    {
        score = i;
    }
}
