import java.io.*;
import java.util.*;

class guessGame
{

	public static void main(String [] args)
	{
		
		System.out.println("*** Welcome to the Guessing Game! ***");
		
		System.out.println("I have a number between 1 and 100. Can you guess it?");
		
		int n = (int)(1+100*Math.random());
		
		getGuess(n);
	
	}
	
	static int getGuess(int num)
	{
		Scanner cin=new Scanner(System.in);
		
		System.out.println("Enter your guess here:  ");
		
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
		}
		
		return num;
	}
	
}