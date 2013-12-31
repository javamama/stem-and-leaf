// Koushik Krishnan
/*
 * TreeTester serves as a test class for Tree. It will have a main method
 * that creates a tree with 20 random values from 0 - 100 and performs method tests 
 * on the tree. Then it prints out the output.
 */
public class TreeTester {
	
	public static void main(String [] args){
		
		Tree tree = new Tree();
		java.util.Random numGen = new java.util.Random();
		
		String values = "";
		// add 20 random values from 0 - 100
		for(int i = 0; i < 20; i++){
			int value = numGen.nextInt(100);
			values += value + " ";
			tree.add(value);
		}
		
		System.out.println("Values Added:  " + values);
		System.out.println("-------------------------------");
		System.out.println("In order:      " + tree.toString());
		System.out.println("Pre order:     " + tree.toStringPre());
		System.out.println("Post order:    " + tree.toStringPost());
		
		tree.treeToList();
		System.out.println("-------------------------------");
		
		System.out.println("Tree Forwards: " + tree.printList());
		System.out.println("Tree Inverse:  " + tree.printInverseList());
		
	}
	
}
