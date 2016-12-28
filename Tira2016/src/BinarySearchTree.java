class BinarySearchTree
{
    public BinarySearchTree ()
    {}

    public boolean isEmpty ()
    {
		return root == null;
    }

    public void insert (int key, Object datum)
    {
		if (isEmpty ()) 
	    {
			root = new BinaryTreeNode (key, datum);
	    }
		else
	    {
			BinaryTreeNode curr, prev;
			prev = curr = root; 
			while (curr != null)
		    {
				prev = curr;
				if (key < curr.key)
				    curr = curr.left;
				else
				    curr = curr.right;
		    }
			BinaryTreeNode n = new BinaryTreeNode (key, datum);
			if (key < prev.key)
			    prev.left = n;
			else
			    prev.right = n;
	    }
    }

    public Object find (int key)
    {
		BinaryTreeNode curr = root;
		while (curr != null && curr.key != key)
	    {
			if (key < curr.key)
			    curr = curr.left;
			else
			    curr = curr.right;
	    }
		return curr == null ? null : curr.datum;     // returning null if curr==null or curr.datum if curr!=null
    }

    public boolean delete (int key)
    {
		boolean found = false;
		BinaryTreeNode  curr = root;
		BinaryTreeNode  parent = null;
		while (curr != null && curr.key != key)   // moving through the tree starting from the root
	    {
			parent = curr;
			if (key < curr.key)
			    curr = curr.left;
			else 
			    curr = curr.right;
	    }    // looping until the target node (curr) has the key that we are looking for or we reach a leaf (curr==null), parent points to the parent of curr

		if (curr != null) // if curr is node (not leaf)
	    { 
		    found = true;
		    if (curr.left == null && curr.right == null)  // curr has no children, we can just delete curr node
		    {
				if (curr == root)
				    root = null;
				else if (parent.left == curr)
				    parent.left = null;
				else
				    parent.right = null;
		    }
		    else if (curr.left == null)  // curr has only right child, we replace curr with the existing child
		    {
				if (curr == root)
				    root = curr.right;
				else if (parent.left == curr)
				    parent.left = curr.right;
				else
				    parent.right = curr.right;				
		    }
		    else if (curr.right == null)  // cur has only left child, we replace curr with the existing child
		    {
				if (curr == root)
				    root = curr.left;
				else if (parent.left == curr)
				    parent.left = curr.left;
				else
				    parent.right = curr.left;
		    }
		    else   // curr has two children, things get more complicated [katso luentomateriaalin sivu 332]
		    {
			    BinaryTreeNode min = curr.right; // taking the right subtree of curr
			    BinaryTreeNode minParent = curr;
			    while (min.left != null) // moving through the curr's right subtree in order to find the leftmost node
			    {
				minParent = min;
				min = min.left;
			    } // after this loop min is the leftmost node of the right subtree of curr, and we also know its parent (minParent)

				min.left = curr.left; // starting to move min to replace the curr (deleted) and linking the left child of deleted node to min
			    if (min != curr.right) // if min was not right child of the deleted we need to do some extra linking tasks
			    {
				minParent.left = min.right; // re-linking the only (right) child of min 
				min.right = curr.right;     // linking new right child to min 
			    }
			    if (curr == root) // if deleted was the root
				root = min;
			    else if (parent.left == curr) // if not root then linking min to the parent of deleted (curr)
				parent.left = min;
			    else
			    	parent.right = min;
		    }
	    } // returning false if we reached leaf, meaning no entry for given key in the tree

		return found;
    }

    private BinaryTreeNode root;
    
    public boolean checkTreeOrder ()
    {
		return checkTreeOrderRec (root, 
			  java.lang.Integer.MIN_VALUE,
			  java.lang.Integer.MAX_VALUE);
    }
    
    private boolean checkTreeOrderRec (BinaryTreeNode n, int low, int high)
    {
		boolean result = true;
		if (n != null)
	    {
			if ((n.key <= low) || (n.key >= high))
		    {
				System.out.println ("ERROR in tree order: should be " + low + " < " + n.key + " < " + high + ".");
				result = false;
		    }
			else
		    {
				result = checkTreeOrderRec (n.left,    low, n.key)
				    && checkTreeOrderRec (n.right, n.key, high);
		    }
	    }
		return result;
    }
}
