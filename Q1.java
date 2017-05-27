import static org.junit.Assert.*;
import org.junit.Test;

/** Assignment 2 Question 1: Print all ancestors of a node in a binary tree
 */
public class Q1 {
	
	/** allAncestors() returns a string with comma separated list of 
	 * ancestors of a given number "num" in a binary tree "t"
	 * 	
	 * @param t
	 * @param num
	 * @return
	 */
	public static String allAncestors(Tree t, int num){
		StringBuilder str = new StringBuilder();
		if(t == null){
			str.append("Tree not initiated");
			return str.toString();
		}
		if(t.root == null){
			str.append("Empty tree");
			return str.toString();
		}
		if(!allAncestorHelper(t.root, num, str)){
			str.append(num+" not found");
		}
		return str.toString();
	}
	
	static boolean allAncestorHelper(Node<Integer> root, int num, StringBuilder str){
		if(root == null){
			return false;
		}
		if(root.data == num){
			return true;
		}
		if(allAncestorHelper(root.left, num, str)){
			str.append(root.data+", ");
			return true;
		}
		if(allAncestorHelper(root.right, num, str)){
			str.append(root.data+", ");
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) {
		
		//Tests
		testNullTree(); //When (t == null)
		testEmptyTree(); //When (t.root == null)
		testElementNotPresent();
		testAncestorsOfRoot(); //If element is in the root, then there is no ancestor
		testAncestorsOfLeftChildOfRoot();
		testAncestorsOfRightChildOfRoot();
		testAncestorsOfLeafNode();
	}
	
	@Test
	public static void testNullTree() {
		Tree t = null;
	    assertEquals("Should return: Tree not initiated", allAncestors(t, 8), "Tree not initiated");
	}
	
	@Test
	public static void testEmptyTree() {
		Tree t = new Tree();
			
	    assertEquals("Should return: Empty tree", allAncestors(t, 8), "Empty tree");
	}
	
	@Test
	public static void testElementNotPresent() {
		Tree t = new Tree();
		int data[] = {16,9,18,3,14,Tree.NullNode,19,1,5};
		t.populateTree(data);	
		System.out.println(t.toString());
		
		int num=8;
		System.out.println(allAncestors(t, num));
	    assertEquals("The number is not present in the tree", allAncestors(t, num), num+" not found");
	}

	@Test
	public static void testAncestorsOfRoot() {
		Tree t = new Tree();
		int data[] = {16,9,18,3,14,Tree.NullNode,19,1,5};
		t.populateTree(data);	
		
		int num=16;
		System.out.println(allAncestors(t, num));
	    assertEquals("No ancestors of root", allAncestors(t, num), "");
	}
	
	@Test
	public static void testAncestorsOfLeftChildOfRoot() {
		Tree t = new Tree();
		int data[] = {16,9,18,3,14,Tree.NullNode,19,1,5};
		t.populateTree(data);	
		
		int num=9;
		System.out.print("The ancestors of "+num+" are (bottom-up order): ");
		System.out.println(allAncestors(t, num));
	    assertEquals("Root is the only ancestor", allAncestors(t, num), t.root.data+", ");
	}
	
	@Test
	public static void testAncestorsOfRightChildOfRoot() {
		Tree t = new Tree();
		int data[] = {16,9,18,3,14,Tree.NullNode,19,1,5};
		t.populateTree(data);	
		
		int num=18;
		System.out.print("The ancestors of "+num+" are (bottom-up order): ");
		System.out.println(allAncestors(t, num));
	    assertEquals("Root is the only ancestor", allAncestors(t, num), t.root.data+", ");
	}
	
	@Test
	public static void testAncestorsOfLeafNode() {
		Tree t = new Tree();
		int data[] = {16,9,18,3,14,Tree.NullNode,19,1,5};
		t.populateTree(data);	
		
		int num=5;
		System.out.print("The ancestors of "+num+" are (bottom-up order): ");
		System.out.println(allAncestors(t, num));
	    assertEquals("Should return all 3 ancestors", allAncestors(t, num), "3, 9, 16, ");
	}
}
