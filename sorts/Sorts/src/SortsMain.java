/** Sorts Program for CS248
    @author Harrison Lingren */

import java.io.*;
import java.util.*;

public class SortsMain
{
  
  public static long time1;
  public static long time2;
  
  public static void main(String[] args)
    throws IOException
  {
   
    System.out.println();
    System.out.print("Size of list?  :");
    
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    
    boolean isLong=false;
    if(n>100) 
      isLong=true;
    
    Comparable [] list = new Comparable[n];
    Comparable [] bubbleList = new Comparable[n];
    Comparable [] insList = new Comparable[n];
    Comparable [] selectList = new Comparable[n];
    Comparable [] shellList = new Comparable[n];
    Comparable [] mergeList = new Comparable[n];
    
		int temp;
		for( int i=0; i<n; i++ )
    {
			temp = (int)(1+n*Math.random());
     	bubbleList[i] = temp;
			insList[i] = temp;
			selectList[i] = temp;
			shellList[i] = temp;
			mergeList[i] = temp;
		}
    
    if (!isLong)
    {
      System.out.print("Original List:  ");
    
      for(int i=0; i<bubbleList.length; i++)
      System.out.print(bubbleList[i]+", ");
    
      System.out.println();
      System.out.println();
    }
    
    // Go through each sort function with the list copy, restoring 
    // it each time to the original list and keeping track of time
    bubble(bubbleList);
    select(selectList);
    insert(insList);
    shell(shellList);
    merge(mergeList);
  }
    
  /** @param bubbleList
      the input Comparable array */
  public static void bubble(Comparable[] bubbleList)
  {
    System.out.println();
    
    time1=System.currentTimeMillis();
    Sorts.bubble(bubbleList);
    time2=System.currentTimeMillis();
    
    /*if(!isLong)
    for(int i=0; i<bubbleList.length; i++)
      System.out.print(bubbleList[i]+", ");*/
    
    System.out.println();
    System.out.println("Bubble Sort");
    System.out.println("  Time taken: "+(double)(time2-time1)+" milliseconds");
    System.out.println();
  }
  
  /** @param insList
      the input Comparable array */ */
  public static void insert(Comparable[] insList)
  {
    System.out.println();
    
    time1=System.currentTimeMillis();
    Sorts.insertion(insList);
    time2=System.currentTimeMillis();
    
    /*if(!isLong)
    for(int i=0; i<insList.length; i++)
      System.out.print(insList[i]+", ");*/
    
    System.out.println();
    System.out.println("Insertion Sort");
    System.out.println("  Time taken: "+(double)(time2-time1)+" milliseconds");
    System.out.println();
  }
  
  /** @param selectList
      the input Comparable array */ */
  public static void select(Comparable[] selectList)
  {
    System.out.println();
    
    time1=System.currentTimeMillis();
    Sorts.selection(selectList);
    time2=System.currentTimeMillis();
    
    /*if(!isLong)
    for(int i=0; i<selectList.length; i++)
      System.out.print(selectList[i]+", ");*/
    
    System.out.println();
    System.out.println("Selection Sort");
    System.out.println("  Time taken: "+(double)(time2-time1)+" milliseconds");
    System.out.println();
  }
  
  /** @param shellList
      the input Comparable array */ */
  public static void shell(Comparable[] shellList)
  {
    System.out.println();
    
    time1=System.currentTimeMillis();
    Sorts.shell(shellList);
    time2=System.currentTimeMillis();
    
    /*if(!isLong)
    for(int i=0; i<shellList.length; i++)
      System.out.print(shellList[i]+", ");*/
    
    System.out.println();
    System.out.println("Shell Sort");
    System.out.println("  Time taken: "+(double)(time2-time1)+" milliseconds");
    System.out.println();
  }
  
  /** @param mergeList
      the input Comparable array */ */
  public static void merge(Comparable[] mergeList)
  {
    System.out.println();
    
    time1=System.currentTimeMillis();
    Sorts.merge(mergeList);
    time2=System.currentTimeMillis();
    
    /*if(!isLong)
    for(int i=0; i<mergeList.length; i++)
      System.out.print(mergeList[i]+", ");*/
    
    System.out.println();
    System.out.println("Merge Sort");
    System.out.println("  Time taken: "+(double)(time2-time1)+" milliseconds");
    System.out.println();
  }
}
