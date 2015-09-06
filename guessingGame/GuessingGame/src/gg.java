import java.io.*;
import java.util.*;

/*

	Harrison Lingren, CS 248
	Guessing Game Project
	13 January 2014
	
*/

class guessGame
{

	public static void main(String [] args)
	{
		
		// executes program
		startGame();
	
	}
	
	static void startGame()
	{
		
		//starts the game, also is the point where it starts over if needed
		
		System.out.println("*** Welcome to the Guessing Game! ***");
		System.out.println("***------by Harrison Lingren------***");

		System.out.println("I have a number between 1 and 100. Can you guess it?");
		
		//random number to guess
		int n = (int)(1+100*Math.random());
		
		getGuess(n);
	
	}
	
	static String getInsult(String insult)
	{
	
		// this function picks a random insult out of 5 options
	
		int insultNum = (int)(1+5*Math.random());
		
		if(insultNum == 1)
		{
			insult = "cheese-head!";
		}
		
		else if(insultNum == 2)
		{
			insult = "taco-brain!";
		}
		
		else if(insultNum == 3)
		{
			insult = "four-eyes!";
		}
		
		else if(insultNum == 4)
		{
			insult = "tooth-face!";
		}
		
		else if(insultNum == 5)
		{
			insult = "fart-breath!";
					}
		
		return insult;
	}
	
	static int getGuess(int num)
	{
	
		// main i/o method, takes the input and determines if too high or too low
		
		Scanner cin = new Scanner(System.in);
		Scanner ans = new Scanner(System.in);
		
		System.out.print("Enter your guess here:  ");
				
		// input
		int x=cin.nextInt();
		String randomIns = "";
		
		// checks input
		if(x > num)
		{
			// too high, starts guessing over and gets insult
			System.out.println("Too high, "+getInsult(randomIns));
			getGuess(num);
		}
		else if(x < num)
		{
			// too low, starts guessing over and gets insult
			System.out.println("Too low, "+getInsult(randomIns));
			getGuess(num);
		}
		else
		{
			System.out.println("You win!");
			System.out.print("Do you want to play again? (yes/no):  ");
			
			String response = ans.next();
			
			if(response.equals("yes"))
			{
				startGame();
			}
				
		}
		
		// returns the original response from the initial input for re-use
		return num;
	}
	
	// end
	
}
