/**
HuntTheWumpus program - WumpusDriver class for HuntTheWumpus main
@author Harrison Lingren
*/

import java.io.*;
import java.util.*;

public class WumpusDriver
{
  /**
    @param currentRoom the room number that the player is currently in
    @param arrowCount the number of arrows the player has
    @param cave array containing the room objects
    @param pitRoom1 the first room with a pit
    @param pitRoom2 the second room with a pit
    @param spidRoom1 the first room with spiders
    @param spidRoom2 the first room with spiders
    @param batsRoom the room with the bats
    @param supplyRoom the room with more arrows
    @param hasUsedSupply true if the player has used the supply room
    @param rgen random generator for the shuffleArray function
    @param input the player input scanner
  */
  
  int currentRoom;
  int arrowCount;
  int wumpRoom;
  int pitRoom1;
  int pitRoom2;
  int spidRoom1;
  int spidRoom2;
  int batsRoom;
  int supplyRoom;
  boolean hasUsedSupply=false;
  Room cave[];
  Random rgen = new Random();
  Scanner input = new Scanner(System.in);
	
	/** shuffles and assigns the obstacles to rooms */
  public void setRooms()
    throws IOException
  {
    // open the caves file
    Scanner caveScan=new Scanner(new FileReader("cave.txt"));
   
    // read the file into an array
    int n=caveScan.nextInt();
    cave=new Room[n];
    for(int i=0; i<cave.length; i++)
			cave[i]=new Room(caveScan);
			
		// generate random numbers to assign the wumpus, 2 pits, 2 spiders, bats, and supply room
		int[] roomAssign = new int[cave.length];
		for(int i=0; i<cave.length-1; i++)
      {
        roomAssign[i]=i+2;
        // for debugging, prints intial roomAssign
        // System.out.print(i+"="+roomAssign[i]+", ");
      }
      
		roomAssign=shuffleArray(roomAssign);
		
		wumpRoom=roomAssign[0];
    pitRoom1=roomAssign[1];
    pitRoom2=roomAssign[2];
    spidRoom1=roomAssign[3];
    spidRoom2=roomAssign[4];
    batsRoom=roomAssign[5];
    supplyRoom=roomAssign[6];
	}

  /** @return the array after shuffling
    @param array the input array */
  public int[] shuffleArray(int[] array){
 
    for (int i=0; i<array.length-1; i++) 
    {
        int randomPosition = rgen.nextInt(array.length-2);
        int temp = array[i];
        array[i] = array[randomPosition];
        array[randomPosition] = temp;
    }
    
    // for debugging, prints shuffled roomAssign
    /*
    for(int i=0; i<array.length; i++)
    {
      System.out.print(i+"="+array[i]+", ");
    }
    */
    return array;
  }
  
  /** starts the game, also used to restart after game over */
  public void startGame()
		throws IOException, InterruptedException
  {
    setRooms();
    arrowCount=3;
    currentRoom=1;
     
	  //prints ASCII title
	  System.out.println();
  	System.out.print("Welcome to");
  	Thread.sleep(250);
  	System.out.print(".");
  	Thread.sleep(250);
    System.out.print(".");
    Thread.sleep(250);
    System.out.print(".");
    Thread.sleep(250);
    
    System.out.println();
		System.out.println();      
    System.out.println("|   |          |        --.--|             . . .                         ");
    Thread.sleep(50);
    System.out.println("|---|.   .,---.|---       |  |---.,---.    | | |.   .,-.-.,---..   .,---.TM");
    Thread.sleep(50);
    System.out.println("|   ||   ||   ||          |  |   ||---'    | | ||   || | ||   ||   |`---.");
    Thread.sleep(50);
    System.out.println("`   '`---'`   '`---'      `  `   '`---'    `-'-'`---'` ' '|---'`---'`---'");
    Thread.sleep(50);
    System.out.println("                                                          |               ");
    Thread.sleep(250);
  	
    startTurn(currentRoom, cave);
    
  }

  /** @return true if the wumpus is in 'r'
    @param r the room being checked */
	public boolean wumpCheck(int r)
	{
		if(r==wumpRoom) {return true;}
		else {return false;}
	}
  
  /** @return true if spiders are in 'r' 
    @param r the room being checked */
	public boolean spiderCheck(int r)
	{
	  if(r==spidRoom1 || r==spidRoom2) {return true;}
	  else {return false;}
	}

  /** @return true if there is a pit in 'r'
    @param r the room being checked */
	public boolean pitCheck(int r)
	{
	  if(r==pitRoom1 || r==pitRoom2) {return true;}
	  else {return false;}
	}
	
	/** @return true if 'r' is the supply room
    @param r the room being checked */
	public boolean supplyCheck(int r)
	{
	  if(r==supplyRoom) {return true;}
	  else {return false;}
	}
	
	/** @return true if there are bats in 'r'
    @param r the room being checked */
	public boolean batsCheck(int r)
	{
	  if(r==batsRoom) {return true;}
	  else {return false;}
	}
	
	/** starts the next turn, prompts user for action
    @param r the current room
    @param cave the input array of room objects*/ 
  public void startTurn(int r, Room[] cave)
		throws IOException, InterruptedException
  {
	  System.out.println();
		
  	//initialize input
  	String ans="";
  	int ansNum;
		
		// print the room number
    System.out.println("You are in room "+r+".");
    Thread.sleep(100);
    // print the arrow count
    System.out.println("You have "+arrowCount+" arrows left.");
    Thread.sleep(100);
    System.out.println();
    // print the room scenario
    System.out.println(cave[r-1].scenario);
    Thread.sleep(100);
    // print the adjacent rooms
    System.out.println("There are tunnels to rooms "+cave[r-1].adj1+", "+cave[r-1].adj2+", and "+cave[r-1].adj3+".");
    Thread.sleep(100);
    // for debugging
    // System.out.println( "wumpus:"+wumpRoom+wumpCheck(r)+" pits:"+pitRoom1+","+pitRoom2+":"+pitCheck(r)+" spider:"+spidRoom1+","+spidRoom2+":"+spiderCheck(r)+" bats:"+batsRoom+":"+batsCheck(r)+" supply:"+supplyRoom+":"+supplyCheck(r) );
		
    // check for bats, spiders, pits, and the wumpus to print warnings
		if(wumpCheck(cave[r-1].adj1) || wumpCheck(cave[r-1].adj2) || wumpCheck(cave[r-1].adj3) )
		  { System.out.println("You smell some nasty Wumpus!"); Thread.sleep(100); }
		
		if(spiderCheck(cave[r-1].adj1) || spiderCheck(cave[r-1].adj2) || spiderCheck(cave[r-1].adj3) )
      { System.out.println("You hear a faint clicking noise."); Thread.sleep(100);}
    
    if(pitCheck(cave[r-1].adj1) || pitCheck(cave[r-1].adj2) || pitCheck(cave[r-1].adj3) )
      { System.out.println("You smell a dank odor."); Thread.sleep(100);}
    
    if(batsCheck(cave[r-1].adj1) || batsCheck(cave[r-1].adj2) || batsCheck(cave[r-1].adj3) )
      { System.out.println("You hear squeaks around the corner..."); Thread.sleep(100);}
		  
		//prompt for action
  	System.out.println();
  	System.out.println("(M)ove or (S)hoot?");
  	System.out.print(":");
	
	  ans=input.next();
		
  	//if move...
	  if( ans.equals("M") || ans.equals("m") ) { startMove(r, cave); }
	  else if( ans.equals("S") || ans.equals("s") ) { shoot(r, cave); }
	  else 
	  {
		  // starts move over
		  System.out.println("Did you say something?");
		  Thread.sleep(1000);
		  startTurn(r, cave);
	  }
  }
    
  /** starts the next movement, prompts user for room to move to
    @param r the current room
    @param cave the input array of room objects*/
  public void startMove(int r, Room[] cave)
    throws IOException, InterruptedException
  {
    int ansNum=-1;
    
    System.out.println();
    System.out.println("Which room? ("+cave[r-1].adj1+", "+cave[r-1].adj2+", or "+cave[r-1].adj3+")");
    Thread.sleep(100);
    System.out.print(":");
    ansNum=input.nextInt();
    
    if(ansNum==(cave[r-1].adj1) || ansNum==(cave[r-1].adj2) || ansNum==(cave[r-1].adj3) )
      { move(ansNum, cave); }
    else
    {
      System.out.println("You can't get to there from here.");
      Thread.sleep(500);
      startMove(r, cave);
    } 
  }
  
  /** moves to target room, 'r'
    @param r the current room
    @param cave the input array of room objects*/
  public void move(int r, Room[] cave)
    throws IOException, InterruptedException
  {
    
    System.out.println("Moving to room "+r+"...");
    Thread.sleep(500);
    currentRoom=r;
    
    //check for supplyRoom
    if(supplyCheck(r))
    {
      if(hasUsedSupply==false)
      {
        System.out.println("You discovered a supply room hidden in the shadows!");
        Thread.sleep(500);
        System.out.println("Your arrows have been replensished!");
        Thread.sleep(750);
        arrowCount=3;
        hasUsedSupply=true;
      }
    }
    
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
    else if(batsCheck(r) )
    {
      System.out.println("Oh no! The bats carried you away!");
      Thread.sleep(100);
      int newR = 1+rgen.nextInt(cave.length-1);
      move(newR, cave);
    }
    
    startTurn(r, cave);
  }
  
  /** starts the shoot action, prompts user for room to shoot into
    @param r the current room
    @param cave the input array of room objects*/
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
    Thread.sleep(100);
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
    System.out.println("Your arrow goes down the tunnel and is lost.");
    Thread.sleep(750);
    
    startTurn(r, cave);
    
  }
  
  /** method to end the game if the user hits the Wumpus */
  public void winGame()
    throws IOException, InterruptedException
  {
    String ans;
    
    System.out.println("Your arrow goes down the tunnel and finds its mark!");
    Thread.sleep(500);
    System.out.println("Congratulations! You win!");
    Thread.sleep(1000);
    System.out.println("Do you want to play again? (y/n)");
    Thread.sleep(100);
    System.out.print(":");
    ans=input.next();
    
    if(ans.equals("y") || ans.equals("Y") ) {startGame();}
    else {return;}
    
  }
  
  /** method to end the game if the user is killed */
  public void gameOver(String reason)
    throws IOException, InterruptedException
  {
    String ans;
    
    System.out.println("Oh no!");
    Thread.sleep(100);
    System.out.println(reason);
    Thread.sleep(1000);
    System.out.println("GAME OVER...");
    Thread.sleep(100);
    System.out.println("Do you want to play again? (y/n)");
    Thread.sleep(100);
    System.out.print(":");
    ans=input.next();
    if(ans.equals("y") || ans.equals("Y") ) { startGame(); }
    else {return;}
  }
}