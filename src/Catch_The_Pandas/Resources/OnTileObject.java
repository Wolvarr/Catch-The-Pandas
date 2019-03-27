package Catch_The_Pandas.Resources;

public abstract class OnTileObject {

    protected Tile location;


    //absztrakt közös ősosztály:

    //minden körben meghívódik
    public abstract  void eachTurn();
    //amikor megpróbál az önmagát paraméterül adó elem rálépni
    public abstract boolean steppedOn(Animal a);
    public abstract boolean steppedOn(Orangutan o);
    public abstract boolean steppedOn(Panda p);
    //amikor interakcióba lép az önmagát paraméterül adó elem
    public abstract void interact(Arcade obj);
    public abstract void interact(CandyVending obj);
    public abstract void interact(Armchair obj);


    public void setLocation(Tile tile) {
        System.out.println(toString() + "Called function OnTileObject.setLocation( " +tile.toString() + " )");
        location = tile;
    }
    public Tile getLocation() {
        System.out.println(toString() + "Called function OnTileObject.getLocation()");
        return location;
    }

    @Override
    public String toString(){
        return "OnTileObject" + hashCode() + " ";
    }
}
