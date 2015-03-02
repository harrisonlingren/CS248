/**
HuntTheWumpus program - Room class for HuntTheWumpus main
@author Harrison Lingren
*/

import java.io.*;
import java.util.*;

public class Room
{
    /**
      @param roomNum
      @param scenario
      @param adj1
      @param adj2
      @param adj3
    */
    
    int roomNum;
    String scenario;
    int adj1, adj2, adj3;
		
    /*  
    public Room()
    {
      roomNum=1;
      scenario="";
    }
    
    
      @param rN the room number
      @param ar1 the first adjacent room
      @param ar2 the second adjacent room
      @param ar3 the third adjacent room
      @param scn the room scenario description
    */
    
    
    /*public Room(int rN, int ar1, int ar2, int ar3, String scn)
    {
      roomNum=rN; adj1=ar1; adj2=ar2; 
      adj3=ar3; scenario=scn;
    }*/
    
    /** intializes the room object
      @param s input scanner to read from file */
    public Room(Scanner s)
    {
      roomNum=s.nextInt();
      
      adj1=s.nextInt();
      adj2=s.nextInt();
      adj3=s.nextInt();
      
      s.nextLine();
      scenario=s.nextLine();
    }
    
    /** writes the Room to string for debugging */
    public String toString()
    {
      String ans=(roomNum+" adj1="+adj1+" adj2="+adj2+" adj3="+adj3+" scenario="+scenario);
      return ans;
    }
}