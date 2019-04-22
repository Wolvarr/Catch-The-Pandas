package Catch_The_Pandas.IO;

import Catch_The_Pandas.Resources.GameElements.Floor;
import Catch_The_Pandas.Resources.GameElements.Orangutan;
import Catch_The_Pandas.Resources.GameElements.Tile;

public class Command {
    private CommandType type;
    private Tile target;
    private Orangutan orangutan;
    private Floor floor;
    OutputWriter output;

    public Command(CommandType ct, Floor ff, OutputWriter o){
        type = ct;
        floor = ff;
        output = o;
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

            case display:
                output.write("probageci");
                break;

            case displayAll:
                break;

            case displayLine:
                break;

            case eachTurn:
                break;

            default:
                break;
        }
    }

}

