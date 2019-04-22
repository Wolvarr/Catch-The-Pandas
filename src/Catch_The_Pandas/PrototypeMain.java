package Catch_The_Pandas;


import Catch_The_Pandas.IO.Command;
import Catch_The_Pandas.IO.CommandParser;
import Catch_The_Pandas.IO.CommandType;
import Catch_The_Pandas.IO.Map;
import Catch_The_Pandas.Resources.GameElements.Floor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class PrototypeMain {
    public static void main(String[] args){

        //teszteléshez szükséges, hogy dinamikus fájlútvonalaink lehessenek,
        // és a tesztelő szkript változtatás nélkül fusson
        String basePath = null;
        File currentDir = new File (".");
        try {
            basePath = currentDir.getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("This is the prototype main class");
        Map testmap = new Map(basePath + args[0]);
        Floor testfloor = testmap.build();
        //új parser
        CommandParser cp = new CommandParser(testfloor, basePath + args[1]);
        //beolvasunk, és a parancsokat lokál tároljuk
        ArrayList<Command> commandlist = cp.parse();
        //automatizálva van, ez lefuttatja az összes parancsot, gyártsatok teszteseteket, testvéreim!
        for (Command c: commandlist)
                c.execute();
    }
}
