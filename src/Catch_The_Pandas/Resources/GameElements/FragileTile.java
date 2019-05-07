package Catch_The_Pandas.Resources.GameElements;

public class FragileTile extends Tile {

    private int health;

    public FragileTile(Floor f, int h) {
        super(f);
        health = h;
    }
    public FragileTile(Floor f, int id, int h) {
        super(f,id);
        health = h;
    }

    @Override
    public boolean receive(Animal a) {
    	//System.out.println(toString() + "Called function fragiletile.recieve()");
    	health--;
    	if(health != 0)
        return super.receive(a);

        else
        {
            a.fall();
            //System.out.println(toString() + "Animal has fallen");
            return false;

        }
    }
    
    @Override
    public void jumpedOn(Animal jp) {
    	//System.out.println("Called function fragiletile.jumpedOn()");
    	if(health > 0) {
    		health--;
    		System.out.println("Health decreased");
    	}
    }

    public void setHealth(int h){
        health = h;
    }

    @Override
    public String toString(){
        return "Fragile tile: " + hashCode() + " ";
    }
}
