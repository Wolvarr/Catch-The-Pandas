package Catch_The_Pandas;

public class FragileTile extends Tile {

    private int health;

    public FragileTile(Floor f, int h) {
        super(f);
        health = h;
    }

    @Override
    public boolean receive(Animal a) {
        if(health != 0)
        return super.receive(a);

        else
        {
            a.fall();
            return false;
        }
    }
}
