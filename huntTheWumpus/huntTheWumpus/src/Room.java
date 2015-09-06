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