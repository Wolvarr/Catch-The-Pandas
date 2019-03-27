package Catch_The_Pandas;


import Catch_The_Pandas.IO.Command;
import Catch_The_Pandas.IO.CommandParser;
import Catch_The_Pandas.IO.CommandType;
import Catch_The_Pandas.IO.Map;
import Catch_The_Pandas.Resources.GameElements.Floor;

import java.util.ArrayList;

public class PrototypeMain {
    public static void main(String[] args){
        System.out.println("This is the prototype main class");
        Map testmap = new Map("C://inputtest/");
        Floor testfloor = testmap.build();
        //testfloor.getOrangutan(0).move(testfloor.getTile(0));
        for (Integer i = 0; i<10; i++){
            if (testfloor.getTile(i)!=null) System.out.println(i + ". ok");
            else System.out.println(i + ". nem ok");
        }
        //új parser
        CommandParser cp = new CommandParser(testfloor, "C://inputtest/");
        //beolvasunk, és a parancsokat lokál tároljuk
        ArrayList<Command> commandlist = cp.parse();
        //automatizálva van, ez lefuttatja az összes parancsot, gyártsatok teszteseteket, testvéreim!
        for (Command c: commandlist)
                c.execute();
    }
}
