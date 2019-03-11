package Home_work;

public abstract class OnTileObject {

    protected Tile location;


    public abstract  void eachTurn();
    public abstract boolean steppedOn(Animal inComingAnimal);
    public abstract void interact(OnTileObject obj);

    public void setLocation(Tile tile) {
        location = tile;
    }
}
