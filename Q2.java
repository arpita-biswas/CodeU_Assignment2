
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
	
	public static void findCommonAncestor(Tree t, int p, int q){
		if(t == null || t.root == null){
			return;
		}
		Result res = commonAncestor(t.root, p, q);
		if( res.potentialAncestor != null && res.isAncestor == true){
			System.out.println("Common Ancestor of "+p+" and "+q+" is "+res.potentialAncestor.data);
		}
		else{
			System.out.println("There exist no common ancestor of "+p+" and "+q);
		}
	}
	
	static Result commonAncestor(Node<Integer> root, int p, int q){
				
		//if root is null, then return null node as potentialAncestor with isAncestor = false
		if(root == null){
			return new Result(null, false);
		}
		//System.out.println("Node: "+root.data);
		
		//Check if root contains p
		if(root.data == p){//if root contains p, then search for q in the subtree
			if(find(root, q)){//if q is found in the subtree, then return root with isAncestor = true
				return new Result (root, true);
			}
			//if q is not found in the subtree, then return root as potentialAncestor
			//with isAncestor = false, indicating that this node contains either p or q
			return new Result (root, false);
		}
		
		//Check if root contains q
		if(root.data == q){//if root contains q, then search for q in the subtree			
			if(find(root, p)){//if p is found in the subtree, then return root with isAncestor = true
				return new Result (root, true);
			}
			//if p is not found in the subtree, then return root as potentialAncestor
			//with isAncestor = false, indicating that this node contains either p or q
			return new Result (root, false);
		}
		
		//If root does not contain p or q, look at the left subtree for p or q
		Result leftRes = commonAncestor(root.left, p, q);
		
		//Check whether left subtree has already found the common ancestor
		if(leftRes.isAncestor){//If yes, return the common ancestor obtained from left subtree
			return leftRes;
		}

		//If common ancestor id not yet found in left subtree, look at right subtree for p or q
		Result rightRes = commonAncestor(root.right, p, q);
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
		Tree t = new Tree();
		int data[] = {16,9,18,3,14,Tree.NullNode,19,1,5};
		t.populateTree(data);
		t.printTree();
		
		findCommonAncestor(t,5,14);
	}
}
