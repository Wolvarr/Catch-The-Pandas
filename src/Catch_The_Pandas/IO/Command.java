package Catch_The_Pandas.IO;


import Catch_The_Pandas.Resources.GameElements.Orangutan;
import Catch_The_Pandas.Resources.GameElements.Panda;
import Catch_The_Pandas.Resources.GameElements.Tile;
import Catch_The_Pandas.Resources.GameElements.Floor;

public class Command {
    private CommandType type;
    private Tile target;
    private Orangutan orangutan;
    private Floor floor;

    public Command(CommandType ct, Floor ff){
        type = ct;
        floor = ff;
    }

    public void setTarget(Tile tt){
        //csekkoljuk, hogy van-e ilyen mező a floorban
        if (floor.getTile(tt.getID())!=null)
            target = tt;
    }

    public void setOrangutan(Orangutan o) {
        if (floor.getOrangutan(o.getID()) != null) {
            orangutan = o;
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

            case eachTurn:
                break;

            default:
                break;
        }
    }

}

