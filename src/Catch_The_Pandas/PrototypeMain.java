package Catch_The_Pandas;


import Catch_The_Pandas.IO.InputParser;
import Catch_The_Pandas.IO.InputType;
import Catch_The_Pandas.IO.Map;
import Catch_The_Pandas.Resources.GameElements.Floor;

public class PrototypeMain {
    public static void main(String[] args){
        System.out.println("This is the prototype main class");
        Map testmap = new Map("C://inputtest/");
        Floor testfloor = testmap.build();
        testfloor.getOrangutan(0).move(testfloor.getTile(0));


    }
}
