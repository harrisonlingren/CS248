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
  boolean hasUsedSupply=false;
  
  Random rgen = new Random();
  
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
			
		// generate random numbers to assign the wumpus, 2 pits, 2 spiders, bats, and supply room
		int[] roomAssign = new int[caveStructure.length];
		for(int i=0; i<caveStructure.length-1; i++)
      {
        roomAssign[i]=i+2;
        // for debugging, prints intial roomAssign
        // System.out.print(i+"="+roomAssign[i]+", ");
      }
    System.out.println();
		roomAssign=shuffleArray(roomAssign);
		
		wumpRoom=roomAssign[0];
    pitRoom1=roomAssign[1];
    pitRoom2=roomAssign[2];
    spidRoom1=roomAssign[3];
    spidRoom2=roomAssign[4];
    batsRoom=roomAssign[5];
    supplyRoom=roomAssign[6];
	}

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
	
	public boolean supplyCheck(int r)
	{
	  if(r==supplyRoom) {return true;}
	  else {return false;}
	}
	
	public boolean batsCheck(int r)
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
	  if( ans.equals("M") ) { startMove(r, cave); }
	  else if( ans.equals("S") ) { shoot(r, cave); }
	  else 
	  {
		  // starts move over
		  System.out.println("Did you say something?");
		  Thread.sleep(100);
		  startTurn(r, cave);
	  }
  }
    
  // initializes new move with 'r'
  public void startMove(int r, Room[] cave)
    throws IOException, InterruptedException
  {
    int ansNum=-1;
    
    System.out.println();
    System.out.println("Which room? ("+cave[r-1].adj1+", "+cave[r-1].adj2+", or "+cave[r-1].adj3+")");
    Thread.sleep(100);
    System.out.print(":");
    ansNum=input.nextInt();
    
    if(ansNum==(cave[r-1].adj1) ) { r=cave[r-1].adj1; }
    else if(ansNum==(cave[r-1].adj2) ) { r=cave[r-1].adj2; }
    else if(ansNum==(cave[r-1].adj3) ) { r=cave[r-1].adj3; }
    else
    {
      System.out.println("You can't get to there from here.");
      Thread.sleep(500);
      startMove(r, cave);
    } 
  }
  
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
    
    if(ans.equals("y") ) {startGame();}
    
  }
  
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
    if(ans.equals("y") ) { startGame(); }
  }
}