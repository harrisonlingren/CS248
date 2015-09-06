/**
HuntTheWumpus program - Main class
@author Harrison Lingren
*/

import java.io.*;

public class HuntTheWumpus
{
  
  /** main class */
	public static void main(String[] args)
		throws IOException, InterruptedException
	{
		WumpusDriver driver= new WumpusDriver();
		driver.startGame();
	}
}