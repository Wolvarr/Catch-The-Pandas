package Home_work;

public class Animal extends OnTileObject implements IMoveable {


    public void fall()
    {

    }

    @Override
    public boolean move(Tile tileTo) {
        return false;
    }

    @Override
    public void eachTurn() {

    }

    @Override
    public boolean steppedOn(Animal inComingAnimal) {
        return false;
    }

    @Override
    public void interact(OnTileObject obj) {

    }
}
