// Koushik Krishnan
/*
 * class TreeNode is a node in a binary search tree. A TreeNode stores an 
 * integer as data as well as a reference to the left and right children
 */
public class TreeNode {
	// left and right children temporarily set as null
	private TreeNode left = null;
	private TreeNode right = null;
	// integer data
	private int value;
	/*
	 * Constructor: provide an integer for the value
	 */
	public TreeNode(int a){
		value = a;
	}
	// get left child node
	public TreeNode getLeft() {
		return left;
	}
	// set the left child node
	public void setLeft(TreeNode left) {
		this.left = left;
	}
	// get right child node
	public TreeNode getRight() {
		return right;
	}
	// set right child node
	public void setRight(TreeNode right) {
		this.right = right;
	}
	// get int value
	public int getValue() {
		return value;
	}
	// set int value
	public void setValue(int value) {
		this.value = value;
	}
	// returns a String representation
	// returns value in String form
	public String toString(){
		return value + " ";
	}
	// returns true if the value of this and other are equal
	public boolean equals(TreeNode other){
		return other.getValue() == getValue();
	}
}