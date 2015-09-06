import java.io.*;
import java.util.*;

/**
Date: 04-17-2015
Program: Camp Posanivee
@author Ethan Miller, Harrison Lingren
This program reads in a text file and enrolls, withdraws, and displays
different kinds of information of a variety of campers.
*/

public class CampMain
{
  public static void main(String [] args) 
    throws FileNotFoundException //G2G
	{
		BST camp = new BST();
    System.out.println("Welcome to Camp Posanivee!!");
    readIn(camp);
    
    System.out.println("\nCommand: Q");
    System.out.println("End of Program.\nBring plenty of Calomine!");
	}
	
	public static void readIn(BST t) 
    throws FileNotFoundException //FINISH
	{
		Scanner steve = new Scanner(new FileReader("camp.txt"));
		char command;
		Camper nodeKey = new Camper();
		
		command = steve.next().charAt(0);
		while (command != 'Q')
		{
      System.out.println();
			switch (command)
			{
				case 'H': // good
					System.out.println("Command: H");
          help();
					break;
				case 'E': // good
					System.out.println("Command: E");
					enroll(t, steve);
					break;
				case 'W': // good
					System.out.println("Command: W");
					nodeKey = bstLookup(t, steve);
          withdraw(t, nodeKey);
					break;
				case 'D': // good
					System.out.println("Command: D");
          nodeKey = bstLookup(t, steve);
          display(nodeKey);
					break;
				case 'A': // good
					System.out.println("Command: A");
          average(t);
					break;
				case 'L': // good
					System.out.println("Command: L");
          alphabetize(t);
					break;
				case 'S': // FIX THIS
					System.out.println("Command: S");
          genCount(t);
					break;
				case 'P': // FIX THIS
					System.out.println("Command: P");
          preorder(t);
					break;
			}
			command = steve.next().charAt(0);
		}
    
    System.out.println();
    // t.print();
	}
	
  public static void alphabetize(BST t) // G2G
  {
    t.reset();
    // System.out.println(t.size());
    // System.out.println(t.hasNext());
    ArrayList<String> words = new ArrayList<String>();
    while (t.hasNext()) 
    {
      words.add( ( (Camper)(t.getNext()) ).name );
    }
    
    Collections.sort(words);
    System.out.println("Alphabetical list of campers:");
    
    for (String word : words) 
    {
      System.out.println("  "+word);
    }
  }
  
  public static Camper bstLookup(BST t, Scanner s) //G2G
  {
    //Fish key=new Fish("Cera","","",0);
    //Fish answer=(Fish) oak.lookup(key);
    
    String search = s.next();
    Camper key = new Camper(0,'A',search);
    
    Camper answer = (Camper)
    t.lookup(key);
    
    if(answer==null)
    {
      System.out.println(search+" not found");
      return null;
    }
    else
      return answer;
  }
  
	private static void help() //G2G
	{
    System.out.println();
		System.out.println("-------- Camp Posanivee Program: Command List --------");
    System.out.println("   H - Prints this list of commands.");
    System.out.println("   E <name> <age> <gender> - Enrolls a camper.");
    System.out.println("   W <name> - Withdraw a camper.");
    System.out.println("   D <name> - Display the age and gender of a camper.");
    System.out.println("   A - Print the average age of the campers.");
    System.out.println("   L - List all campers names in alphabetical order.");
    System.out.println("   S - Print the number of boy and girl campers.");
    System.out.println("   P - List all campers names in preorder.");
    System.out.println("   Q - Quits the program.");
    System.out.println();
	}
	
	private static void enroll(BST t, Scanner s) //G2G
	{
    t.insert(new Camper(s));
    System.out.println("New camper added.");
	}
	
	private static void withdraw(BST t, Camper key) //G2G
	{
		t.delete(key);
    System.out.println("Camper withdrawn.");
	}
	
	private static void display(Camper key) //G2G
	{
		System.out.println( "  Name: "+key.name+"\n"+"  Age: "+key.age+"\n"+"  Gender: "+key.gender );
	}
	
	private static void average(BST t) //G2G
	{
    t.reset();
    
    double totalAge=0;
    double count=0;
    while(t.hasNext())
    {
      totalAge += (double)getAge( (Camper)t.getNext() );
      count++;
    }
    
    if (count>0)
    {
      double avg = (double)(totalAge / count);
      System.out.println("The average age is: "+avg);
    }
    else
      System.out.println("Error: No campers");
	}
	
  private static int getAge(Camper x) //G2G
  {
    return x.age;
  }
  
  private static void genCount(BST t) //FIX THIS
  {
    int boyCount=0;
    int girlCount=0;
    t.reset();
    do
    {
      if ( (((Camper)(t.getNext())).gender)=='M' )
        boyCount++;
      else if ( (((Camper)(t.getNext())).gender)=='F' )
        girlCount++;
    } while(t.hasNext());
    System.out.println("Camper count:\n  Boys:  "+boyCount+"\n  Girls: "+girlCount);
  }
  
	private static void preorder(BST t) //FIX THIS
	{
    t.reset(BST.PREORDER);
    System.out.println("Preorder traversal:");
	  while(t.hasNext())
      System.out.println("  "+((Camper)(t.getNext())).name);
	}
}
