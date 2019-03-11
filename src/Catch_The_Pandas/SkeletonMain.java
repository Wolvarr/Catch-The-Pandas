package Catch_The_Pandas;

import java.util.Scanner;

public class SkeletonMain {

    public static void main(String[] args) {
        System.out.println("\n               Catch the Pandas Skeleton");
        System.out.println("\n\nYou can press the following numbers to test the functions listed next to them");
        System.out.println(("1 - Orángután lép egyet üresre\n" +
                "2 - Panda lép üresre\n" +
                "3 - Orángután nekimegy pandának ami már vezet másik pandát\n" +
                "4 - Orángután vezet két pandát, lépnek egyet\n" +
                "5 - Vándorló panda leesik egy lyukba\n" +
                "6 - Két panda lép, első lyukba lép másodikkal mi történik\n" +
                "7 - Pandák sorban mennek, egyik megijed és elengedi a sort\n" +
                "8 - Orángután kivezet egy pandát a kijáraton\n" +
                "9 - Orángután nekimegy objektumnak ami nem mozgatható\n" +
                "10 - Fotel pulls panda\n" +
                "11 - Orángután trapped\n" +
                "12 - Orángután lyukba lép\n" +
                "13 - Arcade beeps panda jumps\n" +
                "14 - Panda in line moves to hole\n" +
                "14 - Game ends\n" +
                "15 - Orangutan grabs panda (first)\n" +
                "16 - Orangutan wardrobe -> teleport -> wardrobe már van ilyen\n" +
                "17 - Exit skeleton"));


        // Set up som test object to demonstrate the functions


        //reading user input
        Scanner reader = new Scanner(System.in);  // Reading from System.in
        int input =0;

    while (input != 17)
    {
        input = reader.nextInt(); // Scans the next token of the input as an int.
        switch (input)
        {
            case 1: orangutanStepsEmpty(); break;

            case 2: pandaStepsEmpty(); break;



            case 5: pandaFallsDown(); break;

            case 17: break;

            default: System.out.println("Press between 1-17 you dumb fuck");
        }
    }

        reader.close();
    }


    //function for user choice 1
    private static void orangutanStepsEmpty() {
        //set up test environment
        Floor floor = new Floor();
        Tile t1 = new Tile(floor);
        Tile t2 = new Tile(floor);
        Orangutan o = new Orangutan();
        floor.addTile(t1);
        floor.addTile(t2);
        t1.receive(o);
        t1.addNeighbour(t2);

        //te tested function
        System.out.println("The orangutan moves to a tile next to it :");
        o.move(t2);
    }

    //function for user choice 2
    private static void pandaStepsEmpty()
    {
        //set up test environment
        Floor floor = new Floor();
        Tile t1 = new Tile(floor);
        Tile t2 = new Tile(floor);
        Panda p = new Panda();
        floor.addTile(t1);
        floor.addTile(t2);
        t1.receive(p);
        t1.addNeighbour(t2);

        System.out.println("The panda moves to a tile next to it :");
        p.move(t2);
    }

    //function for user choice 5
    public static void pandaFallsDown()
    {
        //set up test environment
        Floor floor = new Floor();
        Tile t1 = new Tile(floor);
        Tile t2 = new FragileTile(floor,0);
        Panda p = new Panda();
        floor.addTile(t1);
        floor.addTile(t2);
        t1.receive(p);
        t1.addNeighbour(t2);

        System.out.println("The panda moves to a fragile tile next to it :");
        p.move(t2);
    }
}
