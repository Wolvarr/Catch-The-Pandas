package Catch_The_Pandas.IO;

import Catch_The_Pandas.Resources.GameElements.Floor;
import Catch_The_Pandas.Resources.GameElements.Orangutan;
import Catch_The_Pandas.Resources.GameElements.Tile;

public class Command {
    private CommandType type;
    private Tile target;
    private Orangutan orangutan;
    private Floor floor;

    public Command(Orangutan o, Tile ttarget,  Floor f) throws RuntimeException{
        type = CommandType.move;
        //magyarul létezik a cél
        if(f.getTile(ttarget.getID())!=null){
            target = ttarget;
            floor = f;
        } else throw new RuntimeException("Floor does not contain the target tile!");
        if (f.getOrangutan(o.getID())!=null){
            orangutan = o;
        } else throw new RuntimeException("Floor does not contain the Orangutan!");
    }

    public Command(Orangutan o, Floor f){
        type = CommandType.release;
        if (f.getOrangutan(o.getID())!=null){
            orangutan = o;
        } else throw new RuntimeException("Floor does not contain the Orangutan!");
    }

    public void modify(Tile newtarget) {
        //létezik a cél és move típusú a parancs
        if ((floor.getTile(newtarget.getID()) != null)&& type==CommandType.move) {
            target = newtarget;
        }
    }

    public void execute(){
        switch(type){
            case move:
                orangutan.move(target);
                break;
            case release:
                orangutan.releasePanda();
                break;
            default:
                break;
        }
    }

}

