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
		
		Random generate = new Random();
		
		wumpRoom=generate.nextInt(caveStructure.length);
		pitRoom1=generate.nextInt(caveStructure.length);
		pitRoom2=generate.nextInt(caveStructure.length);
		spidRoom1=generate.nextInt(caveStructure.length);
		spidRoom2=generate.nextInt(caveStructure.length);
  }
   
  public void startGame()
		throws IOException, InterruptedException
  {
    setRooms();
    arrowCount=3;
    currentRoom=1;
     
	//prints ASCII title
	
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
    // print the room scenario
    System.out.println(cave[r-1].scenario);
    // print the adjacent rooms
    System.out.println("There are tunnels to rooms "+cave[r-1].adj1+", "+cave[r-1].adj2+", and "+cave[r-1].adj3);
    
    // System.out.println( "wumpus:"+wumpRoom+wumpCheck()+" pits:"+pitRoom1+":"+pitRoom2+pitCheck()+" spider:"+spidRoom1+":"+spidRoom2+spiderCheck() );
		
  	// run wumpCheck(), spiderCheck(), and pitCheck() to check for dangers
  	// after they are distributed in setRooms()
		
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
  
  // 
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
    }
    else if(pitCheck(r) )
    {
      gameOver("You fell into a bottomless pit!");
    }
    else if(spiderCheck(r) )
    {
      gameOver("Spiders attacked you!");
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
      if( wumpCheck(r) )
      {
        winGame();
      }
    }
    
    else if(ansNum==(cave[r-1].adj2) )
    {
      if( wumpCheck(r) )
      {
        winGame();
      }
    }
    
    else if(ansNum==(cave[r-1].adj3) ) 
    {
      if( wumpCheck(r) )
      {
        winGame();
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
  {
    
  }
  
  public void gameOver(String reason)
  {
    
    System.out.println("Oh no!");
    System.out.println(reason);
    
  }

}