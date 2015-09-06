import java.io.*;
import java.util.*;

public class Sorts
{
  // swaps A[x] with A[y]
  private static void swap(Comparable [] A,
    int x, int y)
  {
    Comparable tmp=A[x];
	A[x]=A[y];
	A[y]=tmp;
  }
  
  private static int findmax(Comparable []A, int last)
  {
    int max=0;
	  
	  for(int i=1; i<=last; i++)
	    if(A[i].compareTo(A[max])>0)
	      max=i;
    return max;
  }
  
  public static void bubble(Comparable [] A)
  {
    // one pass
	  boolean swapped=true;
	
	  while(swapped)
	  {
	    swapped=false;
	    for(int i=0; i+1<A.length; i++)
	    {
	      if(A[i].compareTo(A[i+1])>0)
	      {
	        swap(A,i,i+1);
		      swapped=true;
	      }
	    }
	  }
  }
  
  public static void selection(Comparable [] A)
  {
    for(int i=A.length-1; i>0; i--)
	   {
      int maxpos=findmax(A,i);
	    swap(A,maxpos,i);
	   }
  }
  
  public static void insertion(Comparable [] A)
  {
    for(int toinsert=1; toinsert<A.length; toinsert++)
	  {
	    // 0..(toinsert-1) is sorted already
	    Comparable tmp=A[toinsert];
	    int i;
	    
	    for(i=toinsert-1; i>=0; i--)
	      if(A[i].compareTo(tmp)>0)
		      A[i+1]=A[i];
	      else break;
	    
      A[i+1]=tmp;
	  }
  }
  
  public static void insertion2(Comparable [] A, int offset, int gap)
  {
    for(int toinsert=offset+gap; toinsert<A.length; toinsert+=gap)
	  {
			Comparable tmp=A[toinsert];
			int i;
	  	for(i=toinsert-gap; i>=0; i-=gap)
	    	if(A[i].compareTo(tmp)>0)
					A[i+gap]=A[i];
				else break;
			A[i+gap]=tmp;
		}
  }
  
  public static void shell(Comparable [] A)
  {
    boolean printstuff=false;
    int gap=A.length/3+1;
	
		while(gap>1)
		{
	  	for(int offset=0; offset<gap; offset++)
	  	{
	    	insertion2(A,offset,gap);
	  	}
	  
	  	if(printstuff)
	  	{
	    	System.out.println("gap="+gap);
	    	print(A);
	  	}
	  	gap=(int) (gap/2.2);
		}
		insertion(A);
  }
  
  // Quicksort functions
  private static boolean qprint=true;
  private static int partition(Comparable[]A,int start,int stop)
  {
    // returns the position of the pivot
		if(qprint)
		{
	  	System.out.println("Before partition:");
	  	for(int i=start; i<=stop; i++)
	  	  System.out.print(A[i]+" ");
	  	System.out.println("");
		}
		Comparable pivot=A[stop];
		int left, right;
		left=start;
		right=stop-1;
		while(left<=right)
		{
	  	while(A[left].compareTo(pivot)<0)
	    	left++;
	  	while(right>=start && A[right].compareTo(pivot)>=0)
	    	right--;
	  	if(left<right)
	  		swap(A,left,right);
		}
		swap(A,left,stop);
		if(qprint)
		{
	  	System.out.println("After partition:");
	  	for(int i=start; i<=stop; i++)
	    	System.out.print(A[i]+" ");
	  	System.out.println("");
		}
		return left;
  }
  
  public static void quick(Comparable[]A)
    { quickhelp(A,0,A.length-1); }

  private static void quickhelp(Comparable []A, int start, int stop)
  {
    // base cases
		if(start>=stop) return;
		// recursive case
		int pivotpos=partition(A,start,stop);
		quickhelp(A,start,pivotpos-1);
		quickhelp(A,pivotpos+1,stop);
  }
  
  public static void merge(Comparable[]A)
    { mergehelp(A,0,A.length-1); }
	
  private static void mergehelp(Comparable[]A, int start, int stop)
  {
    // base cases
		if(start>=stop) return;
		// recursive case
		int mid=(start+stop)/2;
		mergehelp(A,start,mid);
		mergehelp(A,mid+1,stop);
		domerge(A,start,mid,stop);
  }
  
  private static void domerge(Comparable[]A,int start,int mid,int stop)
  {
    Comparable[]B=new Comparable[stop-start+1];
		int left=start, right=mid+1;
		for(int i=0; i<B.length; i++)
		{
	  	// if right is empty or
      // left is not empty and A[left]<A[right]
	  	if(right>stop || (left<=mid && A[left].compareTo(A[right])<0))
	    	B[i]=A[left++];
	  	else
	    	B[i]=A[right++];
		}
		for(int i=0; i<B.length; i++)
			A[start+i]=B[i];
  }
  
  static Comparable [] randlist(int n)
  {
    Integer []x=new Integer[n];
		for(int i=0; i<n; i++)
	  	x[i]=new Integer((int)(n*Math.random()));
		return x;
  }
  
  private static void print(Comparable[]A)
  {
  	for(int i=0; i<A.length; i++)
	  System.out.print(A[i]+" ");
		System.out.println("");
  }
  
  /*public static void main(String [] args)
    throws IOException
  {
    // open the fishfile
		Scanner fishfile=new Scanner(
	  new FileReader("fishfile.txt"));
	  
	  
	// read the file into an array
	int n=fishfile.nextInt();
	Comparable []lunch=new Comparable[n];

	for(int i=0; i<n; i++)
	  lunch[i]=new Fish(fishfile);
	  
	//bubble(lunch);
	
	for(int i=0; i<n; i++)
	  System.out.println(lunch[i]);
	  
	System.out.println("");
	  
	insertion2(lunch,0,2);
	
	for(int i=0; i<n; i++)
	  System.out.println(lunch[i]);
	  
	Comparable[]A=randlist(100);
	merge(A);
	print(A);
	
	A=randlist(100000);
	Comparable []B=new Comparable[A.length];
	for(int i=0; i<A.length; i++)
	  B[i]=A[i];
	  
	System.out.println("A race...");
	
	shell(A);
	//print(A);
	System.out.println("Shell done.");
	bubble(B);
	//print(B);
	System.out.println("Bubble done.");

  }*/
}
