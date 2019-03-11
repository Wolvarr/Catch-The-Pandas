package Catch_The_Pandas;

public class Animal extends OnTileObject implements IMoveable {


    public void fall()
    {
        System.out.println(this.toString() + " falls down");
        location.movedFrom();
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
