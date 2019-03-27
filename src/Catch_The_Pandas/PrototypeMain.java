package Catch_The_Pandas;


import Catch_The_Pandas.IO.InputParser;
import Catch_The_Pandas.IO.InputType;
import Catch_The_Pandas.IO.Map;
import Catch_The_Pandas.Resources.GameElements.Floor;
import Catch_The_Pandas.Resources.GameElements.Tile;

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
        testfloor.getOrangutan(0).move(testfloor.getTile(1));



    }
}
