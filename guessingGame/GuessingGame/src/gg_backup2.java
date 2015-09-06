import java.io.*;
import java.util.*;

class guessGame
{

	public static void main(String [] args)
	{
		
		startGame();
	
	}
	
	static void startGame()
	{
	
		System.out.println("*** Welcome to the Guessing Game! ***");
		
		System.out.println("I have a number between 1 and 100. Can you guess it?");
		
		int n = (int)(1+100*Math.random());
		
		getGuess(n);
	
	}
	
	static int getGuess(int num)
	{
		Scanner cin=new Scanner(System.in);
		Scanner ans=new Scanner(System.in);
		
		System.out.print("Enter your guess here:  ");
		
		int x=cin.nextInt();
		
		if(x > num)
		{
			System.out.println("Too high, dummy!");
			getGuess(num);
		}
		else if(x < num)
		{
			System.out.println("Too low, weirdo!");
			getGuess(num);
		}
		else
		{
			System.out.println("You win!");
			System.out.print("Do you want to play again? (yes/no):  ");
			
			String response=ans.next();
			
			if(response.equals("yes"))
			{
				startGame();
			}
				
		}
		
		return num;
	}
	
}