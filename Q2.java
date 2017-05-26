
class Result{
	Node<Integer> potentialAncestor = null;
	boolean isAncestor = false;
	
	public Result(){
		this.potentialAncestor = null;
		this.isAncestor = false;
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
		Result res = new Result();
		
		//if root is null, then return null node as potentialAncestor with isAncestor = false
		if(root == null){
			return res;
		}
		//System.out.println("Node: "+root.data);
		
		//Check if root contains p
		if(root.data == p){//if root contains p, then search for q in the subtree
			if(find(root, q)){//if q is found in the subtree, then return root with isAncestor = true
				res.isAncestor = true;
			}
			//if q is not found in the subtree, then return root as potentialAncestor
			//with isAncestor = false, indicating that this node contains either p or q
			res.potentialAncestor = root;
			return res;
		}
		
		//Check if root contains q
		if(root.data == q){//if root contains q, then search for q in the subtree			
			if(find(root, p)){//if p is found in the subtree, then return root with isAncestor = true
				res.isAncestor = true;
			}
			//if p is not found in the subtree, then return root as potentialAncestor
			//with isAncestor = false, indicating that this node contains either p or q
			res.potentialAncestor = root;
			return res;
		}
		
		//If root does not contain p or q, look at the left subtree for p or q
		Result leftRes = commonAncestor(root.left, p, q);
		
		//Check whether left subtree has already found the common ancestor
		if(leftRes.isAncestor){//If yes, return the common ancestor obtained from left subtree
			return leftRes;
		}

		Result rightRes = commonAncestor(root.right, p, q);
		//Check whether right subtree has already found the common ancestor
		if(rightRes.isAncestor){//If yes, return the common ancestor obtained from right subtree
			return rightRes;
		}
		
		//If common ancestor is not obtained from left or right subtree
		//check if both as got p or q (the potentialAncestor field of Result captures it) 
		if(leftRes.potentialAncestor != null && rightRes.potentialAncestor != null){
			//If both left and right subtree that one of p or q, then root is the common ancestor
			res.isAncestor = true;
			res.potentialAncestor = root;
			return res;
		}
		
		//Check if one of left or right has p or q
		if(leftRes.potentialAncestor != null || rightRes.potentialAncestor != null){
			//then root is a potentialAncestor
			res.potentialAncestor = root;
			return res;
		}
		
		//If none of the above is true, then the root along with the subtree neither contain p nor q
		//hence return null as potentialAncestor with isAncestor = false
		return res;
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
		int data[] = {1,2,3,4,5,6,7,8,9,10,11,12,13};
		t.populateTree(data);
		t.printTree();
		
		findCommonAncestor(t,9,8);
	}
}
