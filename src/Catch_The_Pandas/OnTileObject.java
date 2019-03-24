package Catch_The_Pandas;

public abstract class OnTileObject {

    protected Tile location;


    public abstract  void eachTurn();
    public abstract boolean steppedOn(Animal a);
    public abstract boolean steppedOn(Orangutan o);
    public abstract boolean steppedOn(Panda p);
    public abstract void interact(Arcade obj);
    public abstract void interact(CandyVending obj);
    public abstract void interact(Armchair obj);

    public void setLocation(Tile tile) {
        location = tile;
    }
}
