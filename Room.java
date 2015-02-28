import java.io.*;
import java.util.*;

public class Room
{
    
    int roomNum;
    String scenario;
    int adj1, adj2, adj3;
		
    
    public Room()
    {
      roomNum=1;
      scenario="";
    }
    
    /**
    @param rN the room number
    @param ar1 the first adjacent room
    @param ar2 the second adjacent room
    @param ar3 the third adjacent room
    @param scn the room scenario description
    */
    
    public Room(int rN, int ar1, int ar2, int ar3, String scn)
    {
      roomNum=rN; adj1=ar1; adj2=ar2; 
      adj3=ar3; scenario=scn;
    }
    
    /**
    @param s Scanner (connected to a text file)
      to read in to initialize the room
    */
    
    public Room(Scanner s)
    {
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
}