package Catch_The_Pandas;


public class Panda extends Animal{


    protected Panda nextPanda;
    protected Animal previousAnimal;

    @Override
    public boolean move(Tile tileTo) {
    	System.out.println("Called function panda.move()");
    	/* if(location.getNeighbours().contains(tileTo))
        {
            if(tileTo.getOnObject() == null)
            {
                location.movedFrom();
                tileTo.receive(this);
                return true;
            }

            else return tileTo.getOnObject().steppedOn(this);
        } */

        return false;
    }

    public void grab(Panda p)
    {
    	System.out.println("Called function panda.grab()");
    }

    public void release()
    {
    	System.out.println("Called function panda.release()");
    }
}
