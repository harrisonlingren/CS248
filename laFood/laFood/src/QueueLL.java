public class QueueLL implements Queue
{
  
	int count;
  
	private class node
  {
    public Object data;
		public node next;
  }
	
	node front,back;
  
  public QueueLL()
  {
    front=back=null;
		count=0;
  }

  public void enqueue(Object x)
  {
    node newnode=new node();
		newnode.data=x;
		newnode.next=null;
		
		if(count==0)
			front=newnode;
		else
			back.next=newnode;
			back=newnode;
			count++;
  }
  
  public Object dequeue()
  {
    if(isEmpty()) return null;
		Object dqed=front.data;
		front=front.next;
		count--;
		if(count==0) back=null;
		return dqed;
  }
  
  public Object getFront()
  {
    //return (isEmpty())?null:front.data;
	
		if(isEmpty()) return null;
		else return front.data;

  }
  
  public Object getBack()
  {
      
      if (isEmpty()) return null;
      else return back.data;
      
  }
  
	public int size()
    { return count; }
  
	public void makeEmpty()
  {
    front=back=null;
		count=0;
  }
  
	public boolean isFull() 
    { return false; } 
  
	public boolean isEmpty()
    { return count==0; }
}
