package Catch_The_Pandas;

public abstract class Animal extends OnTileObject implements IMoveable {


    public void fall()
    {
    	System.out.println("Called function animal.fall()");
    	//System.out.println(this.toString() + " falls down");
        //location.movedFrom();
    }

    @Override
    public boolean move(Tile tileTo) {
    	System.out.println("Called function animal.move()");
        return false;
    }

    @Override
    public void eachTurn() {
    	System.out.println("Called function animal.eachTurn()");
    }

    @Override
    public boolean steppedOn(Animal inComingAnimal) {
    	
    	System.out.println("Called function animal.steppedOn()");
        return false;
    }

    @Override
    public void interact(OnTileObject obj) {
    	System.out.println("Called function animal.interact()");
    }
    
    //DOMIAN
    public void grab(Panda p)
    {
    	//System.out.println("Called function orangutan.grab()");
    }
    
    //DOMIAN
    public Animal getGrabbed()
    {
    	System.out.println("Called function Animal.getGrabbed()");
    	return this;
    }
    
    //DOMIAN
    public abstract void release();

}
