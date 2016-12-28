
import java.util.Random;

class MinHeapTest
{
    public static void main (String[] args)
    {
	// comment the next lines the other way around if you wish to 
	// test the heap 
	// development aid (make the number smaller at first)
	doRandomInsertsAndExtract (20);
	// testing
	 testHeap (10); 
    }

    /*
      static void doRandomInsertsAndExtract (int n)
      
      prints the heaps obtained by inserting the numbers
      0..n-1 in random order, then extracts the the elements from the heap.
      i.e. you can follow how the heap is built and consumed.
    */
    
    static void doRandomInsertsAndExtract (int n)
    {
	MinHeap h   = new MinHeap (n);
	int[]   tab = randomArray (n);    
	for (int i = 0; i < n; ++i)
	    {
		System.out.println ("insert(" + tab[i] + ")");
		h.insert (tab[i]);
		h.checkHeapOrder();
		h.print ();
	    }
	for (int i = 0; i < n; ++i)
	    {
		System.out.println ("extractMin() => " + h.extractMin());
		h.checkHeapOrder();
		h.print ();
	    }
    }
    
    /*
      private static void testHeap (int n)
      tests the heap when the keys 0..n-1 are inserted in random
      order and are extracted in order. Prints errors if any.
    */
    private static void testHeap (int n)
    {
	System.out.println ("starting test...");
	MinHeap h = new MinHeap (n);
	int[]   tab = randomArray (n);
	for (int i = 0; i < n; ++i)
	    h.insert (tab[i]);
	for (int i = 0; i < n ; ++i)
	    {
		int m = h.extractMin ();
		if (m != i)
		    System.out.println("ERROR: extractMin returned " + m + " should be " + i+ ".");
		h.checkHeapOrder ();
		if (h.elements() != (n-i-1))
		    System.out.println ("ERROR: heap size wrong after extract, was "
					+ h.elements() + " should be " + i + ".");
	    }
	System.out.println ("test ended.");
    }
    
    /*
      private static int[] randomArray (int n)
      Generate random permutation of integers 0..n-1 in an array
      and return it.
    */
    private static int[] randomArray (int n)
    {
	Random r   = new Random ();
	int[] keys = new int [n];
	for (int i = 0; i < n; ++i)
	    keys[i] = i;
	for (int i = 0; i < n; ++i)
	    {
		int pos = i + r.nextInt(n-i);
		int tmp = keys[pos];
		keys[pos] = keys[i];
		keys[i] = tmp;
	    }
	return keys;
    }

}
