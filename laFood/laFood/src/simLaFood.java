import java.io.*;
import java.util.*;

/** @author Harrison Lingren
Driver class for the La Food program */

public class simLaFood
{
	
	public Queue waitList = new QueueLL();
	public Queue checkedIn = new QueueLL();
	public int[] Tables = new int[3];
	public int checkedInCustomers=0;
	public int timeWaited=0;
  
	
	public void start()
		throws IOException
	{		

		System.out.println();
		System.out.println("*** Welcome to the La Food Restaurant Simulator ***");

		readFile();
		
		int currentTime=0;
		int tableIndex=0;
		
		while ( currentTime<20 )
		{
		  currentTime++;
		  
      if (!waitList.isEmpty() && currentTime==((Customer)waitList.getFront()).getArrivalTime() )
      {
        System.out.println("Please wait at the bar,");
        System.out.println("  Party"+((Customer)waitList.getFront()).toString()+".");
        System.out.println();
        checkedIn.enqueue(new Customer( ((Customer)waitList.getFront()) ));
        
        waitList.dequeue();
      }
      
      else if(currentTime==Tables[tableIndex])
      {
        System.out.println( "Table for "+((Customer)checkedIn.getFront()).getName()+"!" );
        System.out.println();
        avgWaitTime( ((Customer)checkedIn.getFront()).groupNumber, ((Customer)checkedIn.getFront()).getArrivalTime(), currentTime );
        checkedInCustomers += ((Customer)checkedIn.getFront()).groupNumber;
        
        checkedIn.dequeue();
        tableIndex++;
      }
      
      // for debugging
      // else
        // System.out.println("t="+currentTime);
		}
		
		System.out.println("*** Simulation complete! ***");
		System.out.println();
		
		// for debugging
		//System.out.println("timeWaited:"+timeWaited+", customersSeated:"+checkedInCustomers);
		
		double average = ((double)timeWaited / (double)checkedInCustomers);
		System.out.println("The average waiting time was: "+String.format( "%.2f", average )+".");
		
		if ( !checkedIn.isEmpty() )
		{  
		  System.out.println("The following parties were never seated:");
		  
		  while (!checkedIn.isEmpty() )
		  {
		    System.out.println( ((Customer)checkedIn.getFront()).toString() );
		    checkedIn.dequeue();
		  }
		}
	}
	
	public void readFile() 
		throws IOException
	{
		//System.out.println("Please enter the name of the data file:  ");
    //Scanner sysInput = new Scanner(System.in);
    
    //String inFile = sysInput.next();
		Scanner fileInput = new Scanner(new FileReader("customers.txt"));
		//fileInput.useDelimiter(System.getProperty("line.separator"));
		int index=0;
		boolean eof=false;
		String action;
		
		while(!eof)
		{
			action = fileInput.next();
			
			if (action.equals("A"))
			{
			  waitList.enqueue(new Customer(fileInput.nextInt(), fileInput.nextInt(), fileInput.nextLine()) );
		    // for debugging
		    // System.out.println("Q'd & "+( ( (Customer)waitList.getBack()).toString() ) );
			}
			
			else if (action.equals("T"))
			{
				Tables[index] = fileInput.nextInt();
				// for debugging
				// System.out.println("Table time..."+index+":"+Tables[index]);
				index++;
				
			}
			
			else if (action.equals("Q"))
      {
        // for debugging
        // System.out.println("Breaking read...");
        eof=true;
        return;
        }
      
			else
			{
			  System.out.println("Unknown token: "+action);
			}
		}
	}
	
	public void avgWaitTime(int partySize, int arrivalTime, int seatTime)
	{ 
	  double average=0;
    timeWaited += partySize*(seatTime - arrivalTime);
	}
}