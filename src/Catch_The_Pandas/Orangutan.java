package Catch_The_Pandas;


public class Orangutan extends Animal{


    private Panda grabbedPanda;

    @Override
    public boolean move(Tile tileTo) {
 	   System.out.println("Called function orangutan.move()");
 	   if(location.getNeighbours().contains(tileTo))
       {
           if(tileTo.getOnObject() == null)
           {
               location.movedFrom();
               tileTo.receive(this);
               return true;
           }

           else return tileTo.getOnObject().steppedOn(this);
       }

       return false;
    }
    
    @Override
    public void grab(Panda p)
    {
    	grabbedPanda=p;
    	System.out.println("Called function orangutan.grab()");
    }
    
    @Override
    public boolean steppedOn(Animal inComingAnimal) {
    	
    	System.out.println("Called function orangutan.steppedOn()");
        return false;
    }
    
    //DOMIAN
    @Override
    public Panda getGrabbed() {
    	System.out.println("Called function orangutan.getGrabbed()");
    	return grabbedPanda;
    }
    
    //DOMIAN
    @Override
    public void release() 
    {
    	System.out.println("Called function orangutan.release()");
    	grabbedPanda=null;
    }

}
