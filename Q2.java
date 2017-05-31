/** Assignment 2 Question 2: Find lowest common ancestor of two elements in a binary tree
 */

import static org.junit.Assert.assertEquals;
import java.util.HashMap;
import org.junit.Test;

/** An object of Result class contains
 * a "potentialAncestor" tree node: contain either p or q (or both) in its subtree
 * a boolean field "isAncestor": whether the potentialAncestor is the lowest common ancestor of p and q 
 */
class Result{
	Node<Integer> potentialAncestor = null;
	boolean isAncestor = false;
	
	public Result(){
		this.potentialAncestor = null;
		this.isAncestor = false;
	}
	public Result(Node<Integer> potentialAncestor, boolean isAncestor){
		this.potentialAncestor = potentialAncestor;
		this.isAncestor = isAncestor;
	}
}

public class Q2 {	
	
	/** findCommonAncestor(): finds lowest common ancestor of p and q in binary tree t
	 * 
	 * @param t
	 * @param p
	 * @param q
	 */
	public static String findCommonAncestor(Tree t, int p, int q){
		StringBuilder str = new StringBuilder();
		if(t == null){
			str.append("Tree not initiated");
			return str.toString();
		}
		if(t.root == null){
			str.append("Empty tree");
			return str.toString();
		}
		Result res = commonAncestorHelper(t.root, p, q);
		if( res.potentialAncestor != null && res.isAncestor == true){
			str.append("Lowest Common Ancestor of "+p+" and "+q+" is "+res.potentialAncestor.data);
			return str.toString();
		}
		else{
			str.append("Either "+p+" or "+q+" is not present");
			return str.toString();
		}
	}
	
	/** commonAncestorHelper() returns an object of a Result class.
	 *  if the "isAncestor" field of the object is true, then "potentialAncestor" contains the least common ancestor
	 *  otherwise, no common ancestor of p and q was found in the subtree rooted at "root"
	 * 
	 * @param root
	 * @param p
	 * @param q
	 * @return
	 */
	static Result commonAncestorHelper(Node<Integer> root, int p, int q){
				
		//if root is null, then return null node as potentialAncestor with isAncestor = false
		if(root == null){
			return new Result(null, false);
		}
		//System.out.println("Node: "+root.data);
		
		//Check if root = p = q
		if(root.data == p && root.data == q){
			//If root = p = q, then return root with isAncestor = true
			return new Result (root, true);
		}
		
		//Check is root is one of p or q
		if(root.data == p ||root.data == q){//if root contains p or q, then search for the other
			if((root.data == p && find(root, q)) || (root.data == q && find(root, p))){
				//if root = p and if q is found in the subtree, then return root with isAncestor = true
				//or, if root = q and if p is found in the subtree, then also return root with isAncestor = true
				return new Result (root, true);
			}
			return new Result (root, false);
		}
		
		//If root does not contain p nor q, search the left subtree for p and/or q
		Result leftRes = commonAncestorHelper(root.left, p, q);
		
		//Check whether left subtree has already found the common ancestor
		if(leftRes.isAncestor){//If yes, return the common ancestor obtained from left subtree
			return leftRes;
		}

		//If common ancestor id not yet found in left subtree, look at right subtree for p or q
		Result rightRes = commonAncestorHelper(root.right, p, q);
		//Check whether right subtree has already found the common ancestor
		if(rightRes.isAncestor){//If yes, return the common ancestor obtained from right subtree
			return rightRes;
		}
		
		//If common ancestor is not obtained from left or right subtree
		//check whether both left and right has got either p or q (the potentialAncestor field of Result captures it) 
		if(leftRes.potentialAncestor != null && rightRes.potentialAncestor != null){
			//If both left and right subtree has one of p or q, then root is the common ancestor
			return new Result(root, true);
		}
		
		//Check whether any one of left or right has p or q
		if(leftRes.potentialAncestor != null || rightRes.potentialAncestor != null){
			//then root is a potentialAncestor
			return new Result(root, false);
		}
		
		//If none of the above is true, then the root along with the subtree neither contain p nor q
		//hence return null as potentialAncestor with isAncestor = false
		return new Result(null, false);
	}
	
	static boolean find(Node<Integer> root, int num){
		if(root == null){
			return false;		
		}
		if( (root.data == num) || find(root.left, num) || find(root.right, num) ){
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) {
		
		//Tests
		testNullTree(); //When (t == null)
		testEmptyTree(); //When (t.root == null)
		testElementsNotPresent(); //When p, q or both not present in the tree
		testSamePQ(); //When p and q 
		testP_ancestorOf_Q(); //When p is one of the ancestors of q
		testQ_ancestorOf_P(); //When q is one of the ancestors of p
		testNonPQ_CommonAncestor();

	}
	
	@Test
	public static void testNullTree() {
		Tree t = null;
		int p=5, q=14;
	    assertEquals("Should return: Tree not initiated", findCommonAncestor(t,p,q), "Tree not initiated");
	}
	
	@Test
	public static void testEmptyTree() {
		Tree t = new Tree();
		int p=5, q=14;
	    assertEquals("Should return: Empty tree", findCommonAncestor(t,p,q), "Empty tree");
	}
	
	@Test
	public static void testElementsNotPresent() {
		Tree t = new Tree();
//		int data[] = {16,9,18,3,14,Tree.NullNode,19,1,5};
//		t.populateTree(data);	
		//System.out.println(t.toString());
		
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		map.put(0, 16);
		map.put(1, 9);
		map.put(2, 18);
		map.put(3, 3);
		map.put(4, 14);
		map.put(6, 19);
		map.put(7, 1);
		map.put(8, 5);
		t.populateTree(map);
		
		int p=8, q=14;
		//System.out.println(findCommonAncestor(t,p,q));
	    assertEquals("Either "+p+" or "+q+" is not present in the tree", findCommonAncestor(t,p,q), "Either "+p+" or "+q+" is not present");
	}
	
	@Test
	public static void testSamePQ() {
		Tree t = new Tree();
//		int data[] = {16,9,18,3,14,Tree.NullNode,19,1,5};
//		t.populateTree(data);	
		
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		map.put(0, 16);
		map.put(1, 9);
		map.put(2, 18);
		map.put(3, 3);
		map.put(4, 14);
		map.put(6, 19);
		map.put(7, 1);
		map.put(8, 5);
		t.populateTree(map);
		
		int p=3, q=3;
		//System.out.println(findCommonAncestor(t,p,q));
	    assertEquals("p=q=lowestCommonAncestor", findCommonAncestor(t,p,q), "Lowest Common Ancestor of "+p+" and "+q+" is "+3);
	}
	
	@Test
	public static void testP_ancestorOf_Q() {
		Tree t = new Tree();
		int data[] = {16,9,18,3,14,Tree.NullNode,19,1,5};
		t.populateTree(data);	
		
		int p=16, q=3;
		//System.out.println(findCommonAncestor(t,p,q));
	    assertEquals("p=q=lowestCommonAncestor", findCommonAncestor(t,p,q), "Lowest Common Ancestor of "+p+" and "+q+" is "+16);
	}
	
	@Test
	public static void testQ_ancestorOf_P() {
		Tree t = new Tree();
//		int data[] = {16,9,18,3,14,Tree.NullNode,19,1,5};
//		t.populateTree(data);	
		
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		map.put(0, 16);
		map.put(1, 9);
		map.put(2, 18);
		map.put(3, 3);
		map.put(4, 14);
		map.put(6, 19);
		map.put(7, 1);
		map.put(8, 5);
		t.populateTree(map);
		
		int p=5, q=9;
		//System.out.println(findCommonAncestor(t,p,q));
	    assertEquals("p=q=lowestCommonAncestor", findCommonAncestor(t,p,q), "Lowest Common Ancestor of "+p+" and "+q+" is "+9);
	}
	
	@Test
	public static void testNonPQ_CommonAncestor() {
		Tree t = new Tree();
//		int data[] = {16,9,18,3,14,Tree.NullNode,19,1,5};
//		t.populateTree(data);	
		
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		map.put(0, 16);
		map.put(1, 9);
		map.put(2, 18);
		map.put(3, 3);
		map.put(4, 14);
		map.put(6, 19);
		map.put(7, 1);
		map.put(8, 5);
		t.populateTree(map);
		
		int p=1, q=19;
		//System.out.println(findCommonAncestor(t,p,q));
	    assertEquals("p=q=lowestCommonAncestor", findCommonAncestor(t,p,q), "Lowest Common Ancestor of "+p+" and "+q+" is "+16);
	}

}
