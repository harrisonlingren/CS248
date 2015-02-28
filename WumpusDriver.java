import java.io.*;
import java.util.*;

class WumpusDriver
{
   
  int currentRoom;
  int arrowCount;
  Room caveStructure[];
  
  int wumpRoom;
  int pitRoom1;
  int pitRoom2;
  int spidRoom1;
  int spidRoom2;
  int batsRoom;
  int supplyRoom;
  
  Scanner input = new Scanner(System.in);
	
  public void setRooms()
    throws IOException
  {
    System.out.println("");

    // open the caves file
    Scanner caveScan=new Scanner(new FileReader("cave.txt"));
   
    // read the file into an array
    int n=caveScan.nextInt();
    caveStructure=new Room[n];
    for(int i=0; i<caveStructure.length; i++)
			caveStructure[i]=new Room(caveScan);
			
		// generate random numbers to assign the wumpus, 2 pits,
		// and 2 spiders into rooms
		
		// wumpRoom=generate.nextInt(caveStructure.length)+1;
		// pitRoom1=generate.nextInt(caveStructure.length)+1;
		// pitRoom2=generate.nextInt(caveStructure.length)+1;
		// spidRoom1=generate.nextInt(caveStructure.length)+1;
		// spidRoom2=generate.nextInt(caveStructure.length)+1;
		
		int[] roomAssign = new int[caveStructure.length];
		for(int i=0; i<caveStructure.length; i++)
      {
        roomAssign[i]=i+2;
        System.out.print(i+"="+roomAssign[i]+", ");
      }
    System.out.println();
		roomAssign=RandomizeArray(roomAssign);
		
		wumpRoom=roomAssign[0];
    pitRoom1=roomAssign[1];
    pitRoom2=roomAssign[2];
    spidRoom1=roomAssign[3];
    spidRoom2=roomAssign[4];
    batsRoom=roomAssign[5];
    supplyRoom=roomAssign[6];
	}
  
  // shuffle
  /*static void shuffleArray(int[] array)
  {
    Random rand = new Random();
    for (int i=array.length-1; i>0; i--)
    {
      int index=rand.nextInt(i);
      // swap
      int a=array[index];
      array[index]=array[i];
      array[i]=a;
      System.out.print(i+"="+array[i]+", ");
    }
  }*/
  
  public static int[] RandomizeArray(int[] array){
    Random rgen = new Random();  // Random number generator      
 
    for (int i=0; i<array.length; i++) 
    {
        int randomPosition = rgen.nextInt(array.length-2);
        int temp = array[i];
        array[i] = array[randomPosition];
        array[randomPosition] = temp;
    }
    
    for(int i=0; i<array.length; i++)
    {
      System.out.print(i+"="+array[i]+", ");
    }
    
    return array;
  }
  
  public void startGame()
		throws IOException, InterruptedException
  {
    setRooms();
    arrowCount=3;
    currentRoom=1;
     
	  //prints ASCII title
	
	/*
  	System.out.print("Welcome to");
  	Thread.sleep(250);
  	System.out.print(".");
  	Thread.sleep(250);
    System.out.print(".");
    Thread.sleep(250);
    System.out.print(".");
    Thread.sleep(250);
      
  	System.out.println();			
  	System.out.println("                    _     _   _            __    __                                  ");
  	Thread.sleep(50);
  	System.out.println("  /\\  /\\_   _ _ __ | |_  | |_| |__   ___  / / /\\ \\ \\_   _ _ __ ___  _ __  _   _ ___ TM");
  	Thread.sleep(50);
  	System.out.println(" / /_/ / | | | '_ \\| __| | __| '_ \\ / _ \\ \\ \\/  \\/ / | | | '_ ` _ \\| '_ \\| | | / __| ");
  	Thread.sleep(50);
  	System.out.println("/ __  /| |_| | | | | |_  | |_| | | |  __/  \\  /\\  /| |_| | | | | | | |_) | |_| \\__ \\ ");
  	Thread.sleep(50);
  	System.out.println("\\/ /_/  \\__,_|_| |_|\\__|  \\__|_| |_|\\___|   \\/  \\/  \\__,_|_| |_| |_| .__/ \\__,_|___/ ");
  	Thread.sleep(50);
  	System.out.println("                                                                   |_|               ");
  	Thread.sleep(250);
		*/
    startTurn(currentRoom, caveStructure);
    
  }

	public boolean wumpCheck(int r)
	{
		if(r==wumpRoom) {return true;}
		else {return false;}
	}
	 
	public boolean spiderCheck(int r)
	{
	  if(r==spidRoom1 || r==spidRoom2) {return true;}
	  else {return false;}
	}
	 
	public boolean pitCheck(int r)
	{
	  if(r==pitRoom1 || r==pitRoom2) {return true;}
	  else {return false;}
	}
	
	public boolean supplyCheck(int r)
	{
	  if(r==supplyRoom) {return true;}
	  else {return false;}
	}
	
	public boolean batCheck(int r)
	{
	  if(r==batsRoom) {return true;}
	  else {return false;}
	}
	
	// 
  public void startTurn(int r, Room[] cave)
		throws IOException, InterruptedException
  {
	  System.out.println();
		
  	//initialize input
  	String ans="";
  	int ansNum;
		
    // print the room number
    System.out.println("You are in room "+r+".");
    // print the arrow count
    System.out.println("You have "+arrowCount+" arrows left.");
    System.out.println();
    // print the room scenario
    System.out.println(cave[r-1].scenario);
    // print the adjacent rooms
    System.out.println("There are tunnels to rooms "+cave[r-1].adj1+", "+cave[r-1].adj2+", and "+cave[r-1].adj3);
    // for debugging
    System.out.println( "wumpus:"+wumpRoom+wumpCheck(r)+" pits:"+pitRoom1+","+pitRoom2+":"+pitCheck(r)+" spider:"+spidRoom1+","+spidRoom2+":"+spiderCheck(r)+" bats:"+batsRoom+":"+batCheck(r)+" supply:"+supplyRoom+":"+supplyCheck(r) );
		
  
		if(wumpCheck(cave[r-1].adj1) || wumpCheck(cave[r-1].adj2) || wumpCheck(cave[r-1].adj3) )
		  { System.out.println("You smell some nasty Wumpus!"); }
		  
		
		
  	//prompt for action
  	System.out.println();
  	System.out.println("(M)ove or (S)hoot?");
  	System.out.print(":");
	
	  ans=input.next();
		
  	//if move...
	  if( ans.equals("M") ) { move(r, cave); }
	  else if( ans.equals("S") ) 
	  { 
	    shoot(r, cave);
	  }
	  else 
	  {
		  // starts move over
		  System.out.println("Did you say something?");
		  startTurn(r, cave);
	  }
  }
  
   
  public void move(int r, Room[] cave)
    throws IOException, InterruptedException
  {
    
    int ansNum=-1;
    
    System.out.println();
    System.out.println("Which room? ("+cave[r-1].adj1+", "+cave[r-1].adj2+", or "+cave[r-1].adj3+")");
    System.out.print(":");
    ansNum=input.nextInt();
    
    if(ansNum==(cave[r-1].adj1) ) { r=cave[r-1].adj1; }
    else if(ansNum==(cave[r-1].adj2) ) { r=cave[r-1].adj2; }
    else if(ansNum==(cave[r-1].adj3) ) { r=cave[r-1].adj3; }
    else
    {
      System.out.println("You can't get to there from here.");
      Thread.sleep(500);
      move(r, cave);
    }
    
    //starts a new move with new 'r'
    
    System.out.println("Moving to room "+ansNum+"...");
    Thread.sleep(500);
    currentRoom=r;
    
    // check for bats, pits, or wumpus
    if (wumpCheck(r) )
    {
      gameOver("The Wumpus got you!");
      return;
    }
    else if(pitCheck(r) )
    {
      gameOver("You fell into a bottomless pit!");
      return;
    }
    else if(spiderCheck(r) )
    {
      gameOver("Spiders attacked you!");
      return;
    }
    
    startTurn(r, cave);
    
  }
  
  public void shoot(int r, Room[] cave)
    throws IOException, InterruptedException
  {
    
    int ansNum;
    
    if(arrowCount==0)
    {
      System.out.println();
      System.out.println("You're out of arrows!");
      Thread.sleep(500);
      startTurn(r, cave);
      return;
    }
    
    System.out.println();
    System.out.println("Which room? ("+cave[r-1].adj1+", "+cave[r-1].adj2+", or "+cave[r-1].adj3+")");
    System.out.print(":");
    ansNum=input.nextInt();
    
    if(ansNum==(cave[r-1].adj1) ) 
    {
      if( wumpCheck(cave[r-1].adj1) )
      {
        winGame();
        return;
      }
    }
    
    else if(ansNum==(cave[r-1].adj2) )
    {
      if( wumpCheck(cave[r-1].adj2) )
      {
        winGame();
        return;
      }
    }
    
    else if(ansNum==(cave[r-1].adj3) ) 
    {
      if( wumpCheck(cave[r-1].adj3) )
      {
        winGame();
        return;
      }
    }
    
    else
    {
      System.out.println("You can't shoot that room from here!");
      Thread.sleep(500);
      shoot(r, cave);
    }
    
    System.out.println("Shooting into room "+ansNum+"...");
    arrowCount--;
    Thread.sleep(500);
    
    // if(etc....)
    startTurn(r, cave);
    
  }
  
  public void winGame()
    throws IOException, InterruptedException
  {
    String ans;
    
    System.out.println("Congratulations! You win!");
    Thread.sleep(1000);
    System.out.println("Do you want to play again? (y/n)");
    System.out.print(":");
    ans=input.next();
    
    if(ans.equals("y") ) {startGame();}
    
  }
  
  public void gameOver(String reason)
    throws IOException, InterruptedException
  {
    
    String ans;
    
    System.out.println("Oh no!");
    System.out.println(reason);
    Thread.sleep(1000);
    System.out.println("GAME OVER...");
    System.out.println("Do you want to play again? (y/n)");
    System.out.print(":");
    ans=input.next();
    
    if(ans.equals("y") ) {startGame();}
    
  }

}