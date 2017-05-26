
//Implementing tree data structure
public class Tree{
	static final int NullNode = Integer.MIN_VALUE;
	int data;
	Node<Integer> root = null;
	
	public Tree(){
		this.root = null;
	}
	
	public void populateTree(int data[]){
		if(data == null || data.length == 0){
			return;
		}
		this.root = new Node<Integer>(data[0]);
		//System.out.println("Root: "+this.root.data);
		createTree(this.root, 0, data);				
	}
	
	void createTree(Node<Integer> root, int n, int data[]){
		int leftN = 2*n + 1;
		int rightN = leftN + 1;
		if(leftN >= data.length){
			//System.out.println("Left of "+data[n]+" is not there and exceeds length");
			root.left = null;
			return;
		}
		else{
			if(data[leftN] != NullNode){		
				Node<Integer> left = new Node<Integer>(data[leftN]);
				//System.out.println("Left of "+data[n]+" is "+data[leftN]);
				root.left = left;
				createTree(left, leftN, data);
			}
			else{
				//System.out.println("Left of "+data[n]+" is not there");
				root.left = null;
			}
		}
		if(rightN >= data.length){
			//System.out.println("Right of "+data[n]+" is not there and exceeds length");
			root.right = null;
			return;
		}
		else{
			if(data[rightN] != NullNode){		
				Node<Integer> right = new Node<Integer>(data[rightN]);
				//System.out.println("Right of "+data[n]+" is "+data[rightN]);
				root.right = right;
				createTree(right, rightN, data);
			}
			else{
				//System.out.println("Right of "+data[n]+" is not there");
				root.right = null;
			}
		}		
	}
	
	public void printTree(){
		if(this.root == null){
			System.out.println("Empty Tree");
		}
		else{
			System.out.println("Printing tree(DFS)...");
			print(this.root);
		}		
	}
	
	void print(Node<Integer> root){
		if(root == null){
			return;
		}
		
		System.out.print("Node: "+root.data+" ");
		if(root.left != null){
			System.out.print("Left: "+root.left.data+" ");
		}
		else{
			System.out.print("Left: null ");
		}
		if(root.right !=null){
			System.out.print("Right: "+root.right.data+" ");
		}
		else{
			System.out.print("Right: null ");
		}
		System.out.println();
		print(root.left);
		print(root.right);				
	}
}
