package Home_work;

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
