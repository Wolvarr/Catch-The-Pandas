package Catch_The_Pandas;

import java.util.Scanner;

public class SkeletonMain {

    public static void main(String[] args) {
        System.out.println("\n               Catch the Pandas Skeleton");
        System.out.println("\n\nYou can press the following numbers to test the functions listed next to them");
        System.out.println((
                "1 - Orángután lép egyet üresre\n" + //Pasics
                "2 - Panda lép üresre\n" + //Pasics
                "3 - Orángután nekimegy pandának ami már vezet másik pandát\n" + //Domián
                "4 - Orángután vezet két pandát, lépnek egyet\n" + //Domián
                "5 - Vándorló panda leesik egy lyukba\n" + //Pasics
                "6 - Két panda lép, első lyukba lép másodikkal mi történik\n" + //Domián
                "7 - Pandák sorban mennek, egyik megijed és elengedi a sort\n" + //Máthé
                "8 - Orángután kivezet egy pandát a kijáraton\n" + //Máthé
                "9 - Orángután nekimegy objektumnak ami nem mozgatható\n" + //Máthé
                "10 - Fotel pulls panda\n" + //Molnár
                "11 - Orángután trapped\n" + //Örvényesi
                "12 - Orángután lyukba lép\n" + //Molnár
                "13 - Arcade beeps panda jumps\n" + //Molnár
                "14 - Panda in line moves to hole\n" + //Molnár
                "15 - Orangutan grabs panda (first)\n" + //Örvényesi
                "16 - Orangutan wardrobe -> teleport -> wardrobe már van ilyen\n" + //Örvényesi
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

        //the tested function
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
