package Catch_The_Pandas;


public class Orangutan extends Animal{


    private Panda grabbedPanda;

    @Override
    public boolean move(Tile tileTo) {
       if(location.getNeighbours().contains(tileTo))
       {
           if(tileTo.getOnObject() == null)
           {
               System.out.println(this.toString() + " moved to " + tileTo.toString() + " from " + location.toString() + "\n");
               location.movedFrom();
               tileTo.receive(this);
               return true;
           }

           else return tileTo.getOnObject().steppedOn(this);
       }

       return false;
    }

    public void grab(Panda p)
    {

    }

}
