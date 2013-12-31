// Koushik Krishnan
/*
 * Tree is a binary search tree but can also be re-linked into a linked list. 
 * Tree uses TreeNodes as its nodes.
 * 
 * Methods:
 * 		toString - Tree has three different toStrings. The default
 * 				   toString is an in order traversal but there are also post and
 * 				   pre order traversals as well
 * 		treeToList - will re-link the tree so that it is a double circular linked
 * 					 list. private helper methods are used
 * 		add - will add a new TreeNode to the Tree. it is overloaded to take
 * 			  both an integer and a TreeNode
 * 		printList - will print out the tree if it has been converted into a 
 * 					linked list. there is also a method to print the list backwards
 * Data:
 * 		root - reference to root of the tree. Also a reference to the headNode
 * 			   when the Tree is a linked list
 */
public class Tree {
	// reference to the root node
	private TreeNode root;
	/*
	 * constructors: can specify a root or, if not, the root 
	 * 				becomes the first node that is added
	 */
	public Tree(TreeNode root){
		this.root = root;
	}
	public Tree(){
		root = null;
	}
	/*
	 * return root node
	 */
	public TreeNode getRoot(){
		return root;
	}
	// in order traversal, will start toString at root
	public String toString(){
		return toString(root);
	}
	// pre order traversal, will start at root
	public String toStringPre(){
		return toStringPre(root);
	}
	// post order traversal, will start at root
	public String toStringPost(){
		return toStringPost(root);
	}
	// in order toString, will start at root
	// when finished, the values in the Tree will be
	// printed in order
	public String toString(TreeNode root){
		if(root == null)
			return "";
		String s = "";
		s += toString(root.getLeft());
		s += root.toString();
		s += toString(root.getRight());
		return s;
	}
	// pre-order toString. will start at root
	// and form a String of the pre-order traversal
	public String toStringPre(TreeNode root){
		if(root == null)
			return "";
		String s = "";
		s += root.toString();
		s += toStringPre(root.getLeft());
		s += toStringPre(root.getRight());
		return s;
	}
	// post order toString. will start at root
	// and form a String or the post order traversal
	public String toStringPost(TreeNode root){
		if(root == null)
			return "";
		String s = "";
		s += toStringPost(root.getLeft());
		s += toStringPost(root.getRight());
		s += root.toString();
		return s;
	}
	/*
	 * given a TreeNode, will add it to the end of the linked list
	 * this method is used in conjunction with treeToList to re-link
	 * to three into a doubly circular linked list 
	 * 
	 * first - the headNode of the linked list
	 * node - the node to be added
	 */
	private void addToList(TreeNode node, TreeNode first){
		// the method will only execute if node != null
		if (node != null) {
			/* By checking if the left child of first is null,
			 * the method checks whether the tree has already started to 
			 * be re-linked into a linked list. this is because first will 
			 * be the lowest value in the tree
			 * 
			 * If this condition is true, set root equal to node.
			 * this will establish root to be the head node of the list.
			 * Then, set the left and right children of the root to itself
			 * to make the list doubly circular
			 */
			if (first.getLeft() == null){
				root = node;
				root.setLeft(node);
				root.setRight(node);
			} 
			/*
			 * If the tree has already started to be re-linked, find the
			 * end of the list be getting the left child of the root. This
			 * is the tailNode. If the tailNode does not equal null, set its
			 * right child to node and set node's left child to the tailNode.
			 * Then, make node the tailNode by setting the root's left child
			 * to node and setting node's right child to root.
			 */
			else {
				TreeNode last = root.getLeft();
				if (last != null) {
					last.setRight(node);
					node.setLeft(last);
				}
				root.setLeft(node);
				node.setRight(root);
			}
		}
	}
	/*
	 * This method will recursively re-link the tree into a doubly
	 * circular linked list. The method traverses the tree using an
	 * in order traversal and calls the addToList method to each node
	 * it visits.
	 * 
	 * node - used in the in order traversal and represents the current
	 * 		  node being visited
	 * first - the head node of the linked list
	 */
	private void treeToList(TreeNode node, TreeNode first){
		// base case
		if(node == null) return;
		// store left and right
		TreeNode right = node.getRight();
		TreeNode left = node.getLeft();
		// in order traversal
		treeToList(left, first);
		addToList(node, first);
		treeToList(right, first);
	}
	/*
	 * will find the head node for the linked list before 
	 * treeToList is called. 
	 * It will return the TreeNode with the lowest value in the tree
	 */
	private TreeNode getStart(){
		TreeNode current = root;
		// checks to make sure the root has been initialized
		if(current != null)
			// iterate through the tree until the left most node
			// is reached
			while(current.getLeft() != null){
				current = current.getLeft();
			}
		
		return current;
	}
	/*
	 * overloaded method.
	 * will get the headNode then then call 
	 * the other version of treeToList using
	 * the root and the newly found head node
	 */
	public void treeToList(){
		TreeNode start = getStart();
		treeToList(root, start);
	}
	/*
	 * adds TreeNode t into the tree. 
	 * The rules go as follows:
	 * 		- left child nodes are less than or equal to the parent
	 * 		- right child nodes are greater than the parent
	 * 
	 */
	public void add(TreeNode t){
		// store the int value of t
		int value = t.getValue();
		// if the root has not been initialized, set t as the root
		if(root == null){
			root = t; 
			return;
		}
		// will add t to the left
		if(value <= root.getValue()){	
			// if a left child does exist, call add on the subtree of the left node
			if(root.getLeft() != null){
				Tree tree = new Tree(root.getLeft());
				tree.add(t);
			}
			// if a left child node does not exist, set t as the left node
			else{
				root.setLeft(t);
				return;
			}
		}
		// will add t to the right
		if(value > root.getValue()){
			// if a right child does exist, call add on the subtree of the right node
			if(root.getRight() != null){
				Tree tree = new Tree(root.getRight());
				tree.add(t);
			}
			// if a right child node does not exist, set t as the right node	
			else{
				root.setRight(t);
				return;
			}
		}
	}
	/*
	 * adds a TreeNode with the given value to the tree
	 */
	public void add(int value){
		TreeNode t = new TreeNode(value);
		add(t);
	}
	/*
	 * returns the String representation of the linked list
	 * produces the same output if toString() was called
	 */
	public String printList(){
		TreeNode current = root;
		String s = "";
		while(current != null){
			s += current;
			current = current.getRight();
			// if current has reached the headNode end the loop
			if(current == root)
				return s;
		}
		return s;
		
	}
	/*
	 * returns the String representation of the linked list
	 * in reverse order
	 */
	public String printInverseList(){
		// store the tailNode
		TreeNode current = root.getLeft();
		String s = "";
		
		while(current != null){
			s += current;
			// if current is the last value in the reverse linked list,
			// end the loop
			if(current == root)
				return s;
			current = current.getLeft();
			
		}
		return s;
	}
}