import java.util.*;

/**
@author Harrison Lingren
Defines the Customer object for use in the La Food program.
*/

public class Customer
{

	int arrivalTime = 0;
	int groupNumber = 0;
	//int bribe;
	String name = "";

	/** Default basic constructor  */
	public Customer()
	{
		arrivalTime = 0;
		groupNumber = 0;
		//bribe = 0;
		name = "";
	}
  
	/**
	@param at arrival time of customer
	@param g number of people in group
	// @param b bribe total from customer
	@param n name of customer
	*/
	public Customer(int at, int g, String n)
	{
		arrivalTime = at;
		groupNumber = g;
		//bribe = b;
		name = n;
	}
  
  public Customer(Customer c)
  {
    
    arrivalTime = c.arrivalTime;
    groupNumber = c.groupNumber;
    name = c.name;
    
  }
  
	/** @param s Scanner (connected to a text file) */
	public Customer(Scanner s)
	{
		arrivalTime = s.nextInt();
		groupNumber = s.nextInt();
		//bribe = s.nextInt();
		name = s.next();
	}

	/** @return the arrival time of the customer */
	public int getArrivalTime()
		{ return arrivalTime; }
	
	/** @return the number of people in the group */
	public int getGroupNumber()
		{ return groupNumber; }
	
	/** @return the bribe presented by the customer */
	//public int getBribe()
		//{ return bribe; }
	
	/** @return the name of the customer */
	public String getName()
		{ return name; }
	
	public String toString()
	{
		return (name + " of " + groupNumber + " people");
	}
}
