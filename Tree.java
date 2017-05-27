/**Implemented tree data structure
 * public data member: root
 * public method: poplulateTree(int [] data) creates tree from data array  
 */
public class Tree{
	static final int NullNode = Integer.MIN_VALUE;
	Node<Integer> root = null;
	
	public Tree(){
		this.root = null;
	}
	
	/** populateTree(): Creates tree from data array
	 * 
	 * @param data
	 * 
	 * For each index i of the array, 
	 * 		the data at index (2*i + 1) is the left child
	 * 		the data at index (2*i + 2) is the right child
	 */
	public void populateTree(int data[]){
		if(data == null || data.length == 0){
			return;
		}
		this.root = new Node<Integer>(data[0]);
		//System.out.println("Root: "+this.root.data);
		createTree(this.root, 0, data);				
	}
	
	private void createTree(Node<Integer> root, int n, int data[]){
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
	
	@Override
	public String toString(){
		StringBuilder str = new StringBuilder();
		if(this.root == null){
			str.append("Empty Tree");
			return str.toString();
		}
		else{
			str.append("Printing tree(DFS)...\n");
			str.append(print(root));
			return str.toString();
		}		
	}
	
	private StringBuilder print(Node<Integer> root){
		StringBuilder str = new StringBuilder();
		if(root == null){
			return str;
		}
		
		str.append("Node: "+root.data+" ");
		if(root.left != null){
			str.append("Left: "+root.left.data+" ");
		}
		else{
			str.append("Left: null ");
		}
		if(root.right !=null){
			str.append("Right: "+root.right.data+" ");
		}
		else{
			str.append("Right: null ");
		}
		str.append("\n");
		str.append(print(root.left));
		str.append(print(root.right));		
		return str;
	}
}
