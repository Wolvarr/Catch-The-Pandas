package Home_work;

public class Animal implements IMoveable {


    protected Tile location;

    public void fall()
    {

    }

    @Override
    public boolean Move(Tile tileTo) {
        return false;
    }
}
