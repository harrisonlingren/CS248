public class BST
{
  private class treenode
  {
    public Comparable data;
    public treenode left;
    public treenode right;
  }

  treenode root;
  int count;
  
  Queue Q;
  
  public BST()
  {
    root = null; count=0;
    Q = new QueueLL();
  }
  
  public void insert(Comparable x)
  {
    root = insert(x, root);
    count++;
  }
  
  private treenode insert(Comparable x, treenode r)
  {
    if (r == null) // base case!
    {
      treenode ntn = new treenode();
      ntn.data = x;
      ntn.left = ntn.right = null;
      return ntn;
    }
    // else search
    if (x.compareTo(r.data) < 0) // go left!
      r.left = insert(x, r.left);
    else
      r.right = insert(x, r.right);
    return r;
  }
  
  public void print()
  { 
	  print(root); 
	}
	
  private void print(treenode r)
  {
    if (r != null)
    {
      // print inorder
      print(r.left);
      System.out.println(r.data);
      print(r.right);
    }
  }
  
  public Comparable lookup(Comparable x)
  {
    return lookup(x, root);
  }
  
  private Comparable lookup(Comparable x, treenode r)
  {
    if (r == null)
    {
      return null;
    }
    if (x.compareTo(r.data) == 0)
    {
      return r.data;
    }
    if(x.compareTo(r.data) < 0)
    {
      return lookup(x, r.left);
    }
    else
    {
      return lookup(x, r.right);
    }
  }
  
  public void delete(Comparable x)
  { 
    root = delete(x, root);
  }
	
  private treenode delete(Comparable x, treenode r)
  {
    if (r == null) 
    {
      return null;
    }
    if (x.compareTo(r.data) < 0)
    { 
      r.left = delete(x, r.left);
      return r;
    }
    if (x.compareTo(r.data) > 0)
    { 
      r.right = delete(x, r.right); 
      return r;
    }
    // found it!
    count--;
    // 0 children
    if (r.left == null && r.right == null)
    {
      return null;
    }
    // 1 child
    if (r.left == null)
    {
      return r.right;
    }
    if(r.right == null)
    {
      return r.left;
    }
    // 2 children!!!!!!!
    Comparable is = findmin(r.right);
    r.data = is;
    r.right = delete(is, r.right);
    count++; // adjustment
    return r;
  }
  
  private static Comparable findmin(treenode r)
  {
    if (r == null)
    {
      return null;
    }
    if (r.left == null)
    {
      return r.data;
    }
    return findmin(r.left);
  }
  
  public int size() 
  { 
    return count;
  }
  
  // Iterators!
  public static final int PREORDER=-1;
  public static final int INORDER=0;
  public static final int POSTORDER=1;
  
  public void reset()
  { 
    reset(INORDER); 
  }
  
  public void reset(int order)
  {
    Q.makeEmpty();
    // recursive reset
    reset(order,root);
  }
  
  private void reset(int order,treenode r)
  {
    if (r == null) 
    {
      return;
    }
    if (order == PREORDER)
    {
      Q.enqueue(r.data);
    }
    reset(order, r.left);
    if (order == INORDER) 
    {
      Q.enqueue(r.data);
    }
    reset(order, r.right);
    if (order == POSTORDER)
    {
      Q.enqueue(r.data);
    }
  }
  
  public Comparable getNext()
  { 
    return (Comparable) Q.dequeue(); 
  }
  
  public boolean hasNext()
  { 
    return !Q.isEmpty();
  }
}
