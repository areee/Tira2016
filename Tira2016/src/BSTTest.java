import java.util.Random;

class BSTTest
{
    public static void main (String[] args)
    {
	SimpleTest ();
	Tests ();
    }

    public static void SimpleTest ()
    {
	BinarySearchTree tree = new BinarySearchTree ();
	int[] a = randomArray (10);
	for (int i = 0 ; i < 10; ++i)
	    {
		System.out.println ("Inserting " + a[i] + ".");
		tree.insert (a[i], new Integer(a[i]));
		System.out.print ("Checking tree order: ");
		boolean ok = tree.checkTreeOrder ();
		if (ok)
		    System.out.println ("order ok.");
	    }
	a = randomArray (10);
	for (int i = 0 ; i < 10; ++i)
	    {
		System.out.println ("Find " + a[i] + " => " +
				    tree.find (a[i]));
	    }
	a = randomArray (10);
	for (int i = 0 ; i < 10; ++i)
	    {
		System.out.println ("Delete " + a[i] + " => " +
				    tree.delete (a[i]));
		System.out.println ("Find " + a[i] + " => " + tree.find (a[i]));
		System.out.print ("Checking tree order: ");
		boolean ok = tree.checkTreeOrder ();
		if (ok)
		    System.out.println ("order ok.");
	    }
	if (tree.isEmpty ())
	    System.out.println ("Tree is empty.");
	else
	    System.out.println ("ERROR: Tree is not empty.");
    }
    

    public static void Tests ()
    {
	boolean pass;
	System.out.println ("Running TEST 1");
	pass = Test1 ();
	if (pass)
	    System.out.println ("TEST 1 passed.");
	else
	    System.out.println ("TEST 1 failed.");
	if (pass)
	    {
		System.out.println ("Running TEST 2");
		pass = Test2 ();
		if (pass)
		    System.out.println ("TEST 2 passed.");
		else
		    System.out.println ("TEST 2 failed.");
	    }
    }
    
    public static boolean Test1 ()
    {
	boolean ok = true;
	for (int j = 0; ok && j < 100; ++j)
	    {
		BinarySearchTree tree = new BinarySearchTree();    
		ok = tree.checkTreeOrder ();
		int[] a = randomArray (20);
		for (int i = 0; ok && i < 20; ++i)
		    {
			tree.insert (a[i], new Integer (a[i]));
			ok = tree.checkTreeOrder ();
		    }
		a = randomArray (20);
		for (int i = 0; ok && i < 20; ++i)
		    {
			Integer datum = (Integer)tree.find (a[i]);
			if (datum == null)
			    {
				System.out.println ("ERROR: key " + a[i] + 
						    " not found.");
				ok = false;
			    }
			else if (datum.intValue() != a[i])
			    {
				System.out.println ("ERROR: wrong value (" 
						    + datum.intValue() + 
						    ") returned for key " 
						    + a[i] + ".");
				ok = false;
			    }
		    }
	    }
	return ok;
    }
    
    public static boolean Test2 ()
    {
	boolean ok = true;
	for (int j = 0; ok && j < 100; ++j)
	    {
		BinarySearchTree tree = new BinarySearchTree();    
		ok = tree.checkTreeOrder ();
		int[] a = randomArray (20);
		for (int i = 0; ok && i < 20; ++i)
		    {
			tree.insert (a[i], new Integer (a[i]));
			ok = tree.checkTreeOrder ();
		    }
		a = randomArray (20);
		for (int i = 0; ok && i < 20; ++i)
		    {
			Integer datum = (Integer)tree.find (a[i]);
			if (datum == null)
			    {
				System.out.println ("ERROR: key " + a[i] + 
						    " not found.");
				ok = false;
			    }
			else if (datum.intValue() != a[i])
			    {
				System.out.println ("ERROR: wrong value (" 
						    + datum.intValue() + 
						    ") returned for key " 
						    + a[i] + ".");
				ok = false;
			    }
			else if (tree.delete (a[i]))
			    {
				if (tree.find (a[i]) != null)
				    {
					System.out.println ("ERROR: key " 
							    + a[i] + 
							    " still found after deletion");
					ok = false;
				    }
				else
				    ok = tree.checkTreeOrder();
			    }
			else
			    {
				System.out.println ("ERROR: deletion of key " 
						    + a[i] + 
						    " failed.");
					ok = false;
			    }
		    }
	    }
	return ok;
    }

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
