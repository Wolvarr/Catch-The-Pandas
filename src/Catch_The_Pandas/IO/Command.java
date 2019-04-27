package Catch_The_Pandas.IO;

import Catch_The_Pandas.Resources.GameElements.Floor;
import Catch_The_Pandas.Resources.GameElements.Orangutan;
import Catch_The_Pandas.Resources.GameElements.Panda;
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
                output.write("display tiles around orangutan: " + orangutan.getID());
                for(Tile t: orangutan.getLocation().getNeighbours()){
                    output.write("TileID: " + t.getID());
                    if (t.getOnObject()!=null)
                        output.write("   " + t.getOnObject().toString());
                }
                output.write("::::::::::::::::::::::");
                break;

            case displayAll:
                output.write("displayAll");
                for (Tile t: floor.getAllTiles()){
                    output.write("TileID: " + t.getID());
                    if (t.getOnObject()!=null)
                        output.write("   " + t.getOnObject().toString());
                    else output.write("   empty");
                    String neighbours = "   Neighbours: ";
                    for(Tile tt: t.getNeighbours()){
                        neighbours+= "ID::" + tt.getID() + " ";
                    }
                    output.write(neighbours);
                }
                output.write("::::::::::::::::::::::");
                break;

            case displayLine:
                output.write("displayLine on orangutan: " + orangutan.getID());
                if (orangutan.getGrabbed() == null)
                    output.write("This orangutan does not lead a line");
                else {
                    Panda tempp = orangutan.getGrabbed();
                    while (tempp != null) {

                        output.write("TileID: " +
                                tempp.getLocation().getID());
                        output.write("   " + tempp.toString());
                        tempp = tempp.getNextPanda();
                    }
                    output.write("::::::::::::::::::::::");

                }
                break;

            case eachTurn:
                break;

            default:
                break;
        }
    }

}

