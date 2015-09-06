/**
@author Harrison Lingren
This program calculates the minimum, maximum, average, and median scores from a
list of scores stored in a file. It also counts them by letter grade.
*/

import java.io.*;
import java.util.*;

public class examStatistics 
{

	static Scanner cin=new Scanner(System.in);
	static Scanner infile;	


	public static void main(String[] args) 
		throws IOException
	{

		System.out.println("");
		System.out.println("***Welcome to the Exam Statistics Program***");
		System.out.println("***---------by Harrison Lingren----------***");
		System.out.println("");
			
		// Get file input
		System.out.print("Please enter the name of your data file here:  ");
				
		String fileName=cin.next();
		
		infile=new Scanner( new FileReader(fileName) );

		// Read file and store in array
		int n=infile.nextInt();

		int [] scores;
		scores=new int[n];

		for(int i=0; i<n; i++)
		{
			scores[i]=infile.nextInt();
		}
		
		System.out.println("");
		
		// Add minimum score, maximum score, average score, and median score and print.
		System.out.println("Minimum score:  "+getMinValue(scores) );
		System.out.println("Maximum score:  "+getMaxValue(scores) );
		System.out.println("Median  score:  "+getMedValue(scores) );
		System.out.println("Average score:  "+getAverageValue(scores) );
		
		System.out.println("");
		
		// Count number of each letter grade and print
		System.out.println("Number of scores by letter grade:");
		
		CountGrades(scores);
		
		System.out.println("There are "+scores.length+" total scores.");
		
	}

	public static int getMaxValue(int[] array)
	{
		// calculate max array element value
		int maxValue = array[0];  
		for(int i=1;i < array.length;i++)
		{  
			if(array[i] > maxValue)
			{  
				maxValue = array[i];
			}  
		}
		return maxValue;
	}
	
	public static int getMinValue(int[] array)
	{
		// calculate min array element value
		int minValue = array[0];  
		for(int i=1;i < array.length;i++)
		{  
			if(array[i] < minValue)
			{  
				minValue = array[i];  
			}  
		}
		return minValue;
	}

	public static int getAverageValue(int[] array)
	{
		// calculate avg of array elements
		int total=0;
		for(int i=0; i<array.length; i++)
		{
			total=total+array[i];
		}
	
		int average=total/array.length;
	
		return average;
	
	}

	public static double getMedValue(int[] array)
	{
		// sort array elements in increasing order and find median value
		Arrays.sort(array);
			
		if ( array.length/2 % 2 == 0)
		{
			int numIndex= array.length/2;

			double medVal=( (double) array[numIndex] );
			return medVal;
		}
		else
		{
			int [] numIndex;
			numIndex = new int[2];

			numIndex[0]=( array.length/2);
			numIndex[1]=( array.length/2)-1;

			double medVal=( (double) array[ numIndex[1] ] + (double) array[ numIndex[0] ]) / 2;
			return medVal;
		}
		
	}

	public static void CountGrades(int[] array)
	{
		// count the number of letter grades in array
		int numAs=0;
		int numBs=0;
		int numCs=0;
		int numDs=0;
		int numFs=0;
		
		//count number of letter grades
		for(int i=0; i<array.length; i++)
		{
			if(array[i]>=90)
			{
				numAs++;
			}
			else if(array[i]<90 && array[i]>=80)
			{
				numBs++;
			}
			else if(array[i]<80 && array[i]>=70)
			{
				numCs++;
			}
			else if(array[i]<70 && array[i]>=60)
			{
				numDs++;
			}
			else
			{
				numFs++;
			}
		}
		// Print values
		System.out.println("A:  "+numAs);
		System.out.println("B:  "+numBs);
		System.out.println("C:  "+numCs);
		System.out.println("D:  "+numDs);
		System.out.println("F:  "+numFs);
	}
}