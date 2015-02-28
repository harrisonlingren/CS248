import java.io.*;
import java.util.*;

public class Room
{
    
    int roomNum;
    String scenario;
    int adj1, adj2, adj3;
		
    
    public Room()
    {
      /* 
      name=""; color=""; species="";
      fins=0;
      count++; 
      */
      
      roomNum=1;
      scenario="";
   // count++;
      
    }
    
    /**
    @param n name of the fish
    @param c color of the fish
    @param s species of the fish
    @param f number of fins, if applicable
    */
    
    public Room(int rN, int ar1, int ar2, int ar3, String scn)
    {
      /*
      name=n; color=c; species=s; fins=f;
      count++;
      */
      roomNum=rN; adj1=ar1; adj2=ar2; 
      adj3=ar3; scenario=scn;
   // count++;
      
    }
    
    /**
    @param s Scanner (connected to a text file)
      to read in to initialize the fish
    */
    
    public Room(Scanner s)
    {
      /*
      name=s.next();
      color=s.next();
      species=s.next();
      fins=s.nextInt();
      */
      roomNum=s.nextInt();
      
      adj1=s.nextInt();
      adj2=s.nextInt();
      adj3=s.nextInt();
      
      s.nextLine();
      scenario=s.nextLine();
    
    }
    
    public String toString()
    {
      String ans=(roomNum+" adj1="+adj1+" adj2="+adj2+" adj3="+adj3+" scenario="+scenario);
      return ans;
    }
    
    // accessors
    /**
    @return the name of the fish
    
    
    public String getName()
      { 
          return name; 
      }
      
    public String getColor()
      {
          return color;
      }
      
    public String getSpecies()
      { 
          return species;
      }
      
    public int getFins()
      {
          return fins;
      }
      
    public static void main(String[] args)
    {
       System.out.println(roomNum);
       System.out.println(adj1,adj2,adj3);
       System.out.println(scenario);
    }
    */
}