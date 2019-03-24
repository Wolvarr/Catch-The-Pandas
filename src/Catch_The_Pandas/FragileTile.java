package Catch_The_Pandas;

public class FragileTile extends Tile {

    private int health;

    public FragileTile(Floor f, int h) {
        super(f);
        health = h;
    }

    @Override
    public boolean receive(Animal a) {
    	System.out.println("Called function fragiletile.recieve()");
    	if(health != 0)
        return super.receive(a);

        else
        {
            a.fall();
            return false;
        }
    }
    
    @Override
    public void jumpedOn(Animal jp) {
    	System.out.println("Called function fragiletile.jumpedOn()");
    	if(health > 0) {
    		health--;
    		System.out.println("Health decreased");
    	}
    }
}