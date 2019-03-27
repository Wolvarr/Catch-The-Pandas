package Catch_The_Pandas;

import Catch_The_Pandas.Resources.*;

import java.util.Scanner;

public class SkeletonMain {

    public static void main(String[] args) {
        System.out.println("\n               Catch the Pandas Skeleton");
        System.out.println("\n\nYou can press the following numbers to test the functions listed next to them");
        System.out.println((
                "1 - Orangutan steps to empty tile\n" + //Pasics
                "2 - Panda steps to empty tile\n" + //Pasics
                "3 - Orangutan grabs a panda, that has already grabbed another\n" + //Domián
                "4 - Orangutan steps, two panda follows it\n" + //Domián
                "5 - Panda steps into a hole\n" + //Pasics
                "6 - Two pandas in a row step, the first falls down\n" + //Örvényesi
                "7 - Scared panda releases the one following him\n" + //Máthé
                "8 - Orangutan exits with a panda following him\n" + //Máthé
                "9 - Orangutan steps to an immovable object\n" + //Máthé
                "10 - Armchair pulls panda\n" + //Molnár
                "11 - Orangutan gets trapped\n" + //Örvényesi
                "12 - Orangutan steps into a hole\n" + //Molnár
                "13 - Arcade beeps panda next to it jumps\n" + //Molnár
                "14 - Panda in line moves to hole\n" + //Molnár
                "15 - Orangutan grabs panda (first)\n" + //Domián
                "16 - Orangutan wardrobe steps into a wardrobe\n" + //Örvényesi
                "17 - Exit skeleton"));





        //reading user input
        Scanner reader = new Scanner(System.in);  // Reading from System.in
        int input =0;

        //main loop for the skeleton
        //each option tests on of our functions
    while (input != 17)
    {
        input = reader.nextInt(); // Scans the next token of the input as an int.
        switch (input)
        {
            case 1: orangutanStepsEmpty(); break;

            case 2: pandaStepsEmpty(); break;

            case 3: orangutanBumpsIntoPandaWithPanda(); break;
            
            case 4: orangutanMovesLeadingPandas(); break;
            
            case 5: pandaFallsDown(); break;

            case 6: pandasStepInHole(); break;
            
            case 7: pandaScares(); break;
            
            case 8: pandaExits(); break;
            
            case 9: orangutanStepsUnmoveable(); break;
            
            case 10: pandaSits(); break;

            case 11: orangutanTrapped(); break;
            
            case 12: orangutanMovesToHole(); break;
            
            case 13: arcadeBeepsPandaJumps(); break;
            
            case 14: pandaInLineMovesToHole(); break;
            
            case 15: orangutanGrabsFirst(); break;

            case 16: wardrobeWarp(); break;

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
        t1.setOnTileObject(o);
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
        t1.setOnTileObject(p);
        t1.addNeighbour(t2);

        System.out.println("The panda moves to a tile next to it :");
        p.move(t2);
    }
    
    //DOMIAN
  //function for user choice 3
    public static void orangutanBumpsIntoPandaWithPanda() 
    {
    	//set up test environment
    	Floor floor = new Floor();
        Tile t1 = new Tile(floor);
        Tile t2 = new Tile(floor);
        Tile t3 = new Tile(floor);
        
        Orangutan o = new Orangutan();
        Panda p1 = new Panda();
        Panda p2 = new Panda();
        
        floor.addTile(t1);
        floor.addTile(t2);
        floor.addTile(t3);
        t1.setOnTileObject(p1);
        t2.setOnTileObject(o);
        t3.setOnTileObject(p2);
        t1.addNeighbour(t2);
        t2.addNeighbour(t3);
        o.grab(p1);
        p1.grabpreviousAnimal(o);
        System.out.println("\nThe orangutan Bumps Into Panda With Panda :");
        o.move(t3);
    }
    
    //DOMIAN
    //function for user choice 4
    public static void orangutanMovesLeadingPandas() 
    {
    	//set up test environment
    	Floor floor = new Floor();
    	
    	Tile t0 = new Tile(floor);
        Tile t1 = new Tile(floor);
        Tile t2 = new Tile(floor);
        Tile t3 = new Tile(floor);
        
        Orangutan o = new Orangutan();
        Panda p1 = new Panda();
        Panda p2 = new Panda();
        
        floor.addTile(t0);
        floor.addTile(t1);
        floor.addTile(t2);
        floor.addTile(t3);
        
        t0.setOnTileObject(p2);
        t1.setOnTileObject(p1);
        t2.setOnTileObject(o);
        
        t0.addNeighbour(t1);
        t1.addNeighbour(t2);
        t2.addNeighbour(t3);
        o.grab(p1);
        p1.grabpreviousAnimal(o);
        p1.grab(p2);
        p2.grabpreviousAnimal(p1);
        System.out.println("AFTER :");
        o.move(t3);
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
        t1.setOnTileObject(p);
        t1.addNeighbour(t2);

        System.out.println("The panda moves to a fragile tile next to it :");
        p.move(t2);
    }
    //function for user choice 6
    //Örvényesi
    public static void pandasStepInHole(){
        //setting up
        Floor floor = new Floor();
        Panda p1 = new Panda();
        Panda p2 = new Panda();
        //sorrend: ft, t1, t2
        FragileTile ft = new FragileTile(floor, 1);
        Tile t1 = new Tile(floor);
        Tile t2 = new Tile(floor);

        floor.addTile(ft);
        floor.addTile(t1);
        floor.addTile(t2);

        ft.addNeighbour(t1);
        t1.addNeighbour(t2);

        t1.setOnTileObject(p1);
        t2.setOnTileObject(p2);

        p1.grab(p2);

        System.out.println("End of setup\n\n");
        p1.move(ft);




    }
    
    //function for user choice 7
    public static void pandaScares() {
 	   System.out.println("A panda scares and releases the line");
    	
 	   	Floor floor = new Floor();
    	Tile t1 = new Tile(floor);
    	Tile t2 = new Tile(floor);
    	CowardPanda cp = new CowardPanda();
    	Panda p = new Panda();
    	Arcade arcade = new Arcade();
    	
    	floor.addTile(t1);
    	floor.addTile(t2);
    	t1.addNeighbour(t2);
    	cp.grab(p);

        t1.setOnTileObject(cp);
        t2.setOnTileObject(arcade);

    	arcade.setLocation(t2);
    	
    	arcade.ring();
    	
    }
    
    //function for user choice 8
    public static void pandaExits() {
 	   System.out.println("The orangutan guides a panda to the Exit");
    	Orangutan o = new Orangutan();
    	Floor f1 = new Floor();
    	Tile t1 = new Tile(f1);
    	Tile t2 = new Tile(f1);
    	Panda p = new Panda();
    	Exit e = new Exit(f1);
    	Entrance en = new Entrance(f1);
    }
    
    //function for user choice 9
    public static void orangutanStepsUnmoveable() {
    	Orangutan o = new Orangutan();
    	Floor f1 = new Floor();
    	Tile t1 = new Tile(f1);
    	Tile t2 = new Tile(f1);
    	CandyVending cv = new CandyVending();

    	t1.addNeighbour(t2);
    	t1.setOnTileObject(o);
    	t2.setOnTileObject(cv);
    	cv.setLocation(t2);

    	o.move(t2);

    }
    
    //function for user choice 10
	public static void pandaSits() {
		System.out.println("Armchair pulls panda");
		Floor floor = new Floor();
		Tile t1 = new Tile(floor);
		Tile t2 = new Tile(floor);
		LazyPanda lp = new LazyPanda();
		Panda p1 = new Panda();
		Panda p2 = new Panda();
		Armchair armchair = new Armchair();

		floor.addTile(t1);
		floor.addTile(t2);
		t1.addNeighbour(t2);
		p1.grab(lp);
		lp.grab(p2);
		t1.receive(lp);
		t2.setOnTileObject(armchair);
		armchair.setLocation(t2);
		armchair.pull();

	}

    // function for user choice 11
    public static void orangutanTrapped(){
        Floor f = new Floor();
        //this is where the orangutan stands
        Tile t1 = new Tile(f);
        Tile t2 = new Tile(f);
        Tile t3 = new Tile(f);
        //setting up tile neighbourhoods
        t1.addNeighbour(t2);
        t1.addNeighbour(t3);
        //creating non steppable objects and orangutan
        Orangutan o = new Orangutan();
        Armchair a = new Armchair();
        CandyVending cv = new CandyVending();
        //adding them to the tiles
        t1.setOnTileObject(o);
        t2.setOnTileObject(a);
        t3.setOnTileObject(cv);
        //másik irányú kapcsolat
        a.setLocation(t2);
        cv.setLocation(t3);
        System.out.println("end of setting up\n\n");
        //maga a teszt:
        o.eachTurn();

    }

	
	// function for user choice 12
	public static void orangutanMovesToHole() {
    	System.out.println("Orangutan moves to a hole");
    	Floor floor = new Floor();
    	Tile t = new Tile(floor);
    	Tile ft = new FragileTile(floor, 0);
    	Orangutan o = new Orangutan();
    	floor.addTile(t);
    	floor.addTile(ft);
    	t.addNeighbour(ft);
    	t.setOnTileObject(o);
    	o.move(ft);
    }
	
	// function for user choice 13
	public static void arcadeBeepsPandaJumps() {
		System.out.println("Panda jumps frightened, fragileTile's health decreases");
		Floor floor = new Floor();
    	Tile t = new Tile(floor);
    	FragileTile ft = new FragileTile(floor, 4); // fragileTile's health > 0

    	JumpyPanda jp = new JumpyPanda();
    	CandyVending cv = new CandyVending();

    	floor.addTile(t);
    	floor.addTile(ft);
    	t.addNeighbour(ft);


    	ft.setOnTileObject(jp);
    	jp.setLocation(ft);
    	
    	t.setOnTileObject(cv);
    	cv.setLocation(t);

    	cv.beep();
    	
	}
	
	// function for user choice 14
	public static void pandaInLineMovesToHole() {
		System.out.println("Panda moves to a hole, releases next panda");
		Floor floor = new Floor();
    	Tile t1 = new Tile(floor);
    	Tile t2 = new Tile(floor);
    	FragileTile ft = new FragileTile(floor, 0);
    	Panda p1 = new Panda();
    	Panda p2 = new Panda();
    	floor.addTile(t1);
    	floor.addTile(t2);
    	floor.addTile(ft);
    	t2.addNeighbour(t1);
    	t1.addNeighbour(ft);
    	p1.grab(p2);

        t2.setOnTileObject(p2);
        t1.setOnTileObject(p1);
        p1.move(ft);
	}
    
    //DOMIAN
    //function for user choice 15
    public static void orangutanGrabsFirst() {
    	System.out.println("Orangutan grabs the first panda");
    	
    	//set up test environment
    	Floor floor = new Floor();
        Tile t1 = new Tile(floor);
        Tile t2 = new Tile(floor);
        Orangutan o = new Orangutan();
        floor.addTile(t1);
        floor.addTile(t2);
        t1.receive(o);
        t1.addNeighbour(t2);
        Panda p = new Panda();
        t2.setOnTileObject(p);
        o.move(t2);
    }
    //Örvényesi
    //function for user choice 16
    public static void wardrobeWarp(){
        //setting up floor
        Floor f = new Floor();
        //setting up tiles for the wardrobes
        Tile t0 = new Tile(f); //this is where the monkey starts
        Tile t1 = new Tile(f); //this is where the wardrobe which it steps in is
        Tile t2 = new Tile(f);
        Tile t22 = new Tile(f); //neighbouring tile to t2
        Tile t3 = new Tile(f);
        Tile t33 = new Tile(f); //neighbouring tile to t3
        //setting up neigbourhood
        t2.addNeighbour(t22);
        t3.addNeighbour(t33);
        t0.addNeighbour(t1);
        t1.addNeighbour(t0);
        //Adding the tiles to the floor
        f.addTile(t1);
        f.addTile(t2);
        f.addTile(t3);
        f.addTile(t0);
        //setting up wardrobes
        Wardrobe w1 = new Wardrobe(); //this is where the orangutan's gonna step
        Wardrobe w2 = new Wardrobe();
        Wardrobe w3 = new Wardrobe();
        //connecting the wardrobes to their locations
        w1.setLocation(t1);
        w2.setLocation(t2);
        w3.setLocation(t3);
        //and the other way around
        t1.setOnTileObject(w1);
        t2.setOnTileObject(w2);
        t3.setOnTileObject(w3);
        //creating our dear monkey
        Orangutan o = new Orangutan();
        o.setLocation(t0);
        t0.setOnTileObject(o);
        //setting up the wardrobes
        w1.addWardrobe(w2);
        w1.addWardrobe(w3);
        //easier copying
        w2.importList(w1.exportList());
        w3.importList(w1.exportList());
        System.out.println("end of setting up\n\n");
        //the test iteself:
        o.move(t1);
        System.out.println("new location is: " + o.getLocation().toString());
        //multiple test to prove that it's actually random
        for (int i=0; i<5; i++) {
            System.out.println("run #" + i);
            //setting everything back to it's original place
            o.setLocation(t0);
            t0.setOnTileObject(o);
            o.move(t1);
            System.out.println("new location is: " + o.getLocation().toString()+ "\n");

        }


    }
    
}
