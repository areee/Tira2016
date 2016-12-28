/* 
   MinHeap
   
   Simple min-heap that contains integers.

*/

class MinHeap
{ 
    private static final int   root = 1; // index of root
    private              int   last;     // index of last element
    private              int[] table;    // heap elements
    
    /*
      public MinHeap (int maxElements)
      creates a new heap with space for maxElements integers
    */
    public MinHeap (int maxElements)
    {
	last  = 0;
	table = new int[maxElements+1];
    }

    /*
      public boolean isEmpty ()
      return true if the heap is empty, false otherwise
    */

    public boolean isEmpty () 
    {
	return last == 0;
    }
  
    /*
      public boolean isFull ()
      return true if the heap is full, false otherwise
    */

    public boolean isFull ()
    {
	return last == table.length-1;
    }

    /*
      public int elements ()      
      return the number of elements in stored in the heap
    */
    public int elements ()
    {
	return last;
    }


    /*
      private void swap (int i, int j)
      swaps the elements stored in the indices i and j
    */
    private void swap (int i, int j)
    {
	int tmp = table[i];
	table[i] = table[j];
	table[j] = tmp;
    }
 
    /*
      private static int parent (int i)
      returns the index of the parent of the node in index i
    */
    private static int parent (int i)
    {
	return i/2;
    }

    /*
      private static int leftChild (int i)
      returns the index of the left child of the node in index i
    */
    private static int leftChild (int i)
    {
	return i*2;
    }
    /*
      private static int rightChild (int i)
      returns the index of the right child of the node in index i
    */
    private static int rightChild (int i)
    {
	return i*2+1;
    }

    /*
      public int extractMin ()
      removes the smallest integer in the heap
    */
    public int extractMin ()
    { 
	int result = 0;	
	if (!isEmpty())
	    {
		result = table[root];
		table[root] = table[last];
		--last;
		percolateDown (root);
	    }
	else
	    System.out.println ("ERROR: extract from empty queue.");
	return result;
    }
    
    /*
      private int percolateDown (int idx)
      takes element in index and moves it down in the heap until the element 
      below is greater or it hits the bottom
    */
    private int percolateDown (int idx)
    {
	while (((leftChild(idx) <= last) 
		&& (table[idx] > table[leftChild(idx)]))
	       || ((rightChild(idx) <= last) 
		   && (table[idx] > table[rightChild(idx)])))
	    {
		int swapidx = leftChild(idx);
		if ((rightChild (idx) <= last)
		    && (table [leftChild (idx)] > table[rightChild (idx)]))
		    swapidx = rightChild (idx);
		swap (idx, swapidx);
		idx = swapidx;
	    }
	return idx;
    }

    /*
      public void insert (int key)
      inserts key into the heap
    */  
    public void insert (int key)
    {
	if (! isFull ())
	    {
		int i;
		last++;  
		table[last] = key;
		percolateUp (last);
	    }
	else
	    System.out.println ("ERROR: insertion into a full heap.");
    }

    /*
      private void percolateUp (int idx)
      moves the element in index idx up until the element above is
      smaller or it hits the root
    */
    private int percolateUp (int idx)
    {
	while ((idx != root) && (table[idx] < table[parent(idx)]))
	    {
		swap (idx, parent(idx));
		idx = parent(idx);
	    }
	return idx;
    }

    /*
      public void checkHeapOrder ()
      checks that the heap is in correct order
    */
    public void checkHeapOrder ()
    {
	checkHeapOrderRec (leftChild(root));
	checkHeapOrderRec (rightChild(root));
    }

    private void checkHeapOrderRec (int i)
    {
	if (i <= last)
	    {
		if (table[i] < table[parent(i)])
		    System.out.println("Error in the heap order " + table[i] + " < "
				       + table[parent(i)] + " in indices " + i + " and " 
				       + parent(i) + ".");
		checkHeapOrderRec (leftChild(i));
		checkHeapOrderRec (rightChild(i));
	    }
    }

    /*
      public void print ()
      prints the heap in tree form
    */
    public void print ()
    {
	int h = height ();
	for (int i = 0; i < h; ++i)
	    {
		System.out.print (i + ":\t");
		printLevel (0, i, root);
		System.out.println();
	    }
    }

    private void printLevel (int c, int l, int idx)
    {
	if (idx <= last)
	    if (c == l)
		{
		    for (int i = width (leftChild(idx)); i > 0; --i)
			System.out.print (" ");
		    System.out.print (table[idx]);
		    for (int i = width (rightChild(idx)); i > 0; --i)
			System.out.print (" ");
		}
	    else
		{
		    printLevel (c+1, l, leftChild(idx));
		    for (int i = (""+table[idx]).length(); i > 0; --i)
			System.out.print (" ");
		    printLevel (c+1, l, rightChild(idx));
		}
    }
  
    private int width (int idx)
    {
	if (idx > last)
	    return 0;
	else
	    return width (leftChild(idx)) + width(rightChild(idx)) 
		+ (""+table[idx]).length();
    }

    private int height ()
    {
	int h = 0;
	for (int i = last; i > 0; i /= 2)
	    ++h;
	return h;
    }  
}


