
public class Q1 {
	
	public static void printAllAncestors(Tree t, int num){
		if(t==null || t.root == null){
			System.out.println("Empty tree");
			return;
		}
		System.out.println("\nThe ancestors of "+num+" are (bottom-up order):");
		if(!printAncestor(t.root, num)){
			System.out.println(num+" not found");
		}
	}
	
	static boolean printAncestor(Node<Integer> root, int num){
		if(root == null){
			return false;
		}
		if(root.data == num){
			return true;
		}
		if(printAncestor(root.left, num)){
			System.out.print(root.data+", ");
			return true;
		}
		if(printAncestor(root.right, num)){
			System.out.print(root.data+", ");
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) {
		Tree t = new Tree();
		int data[] = {16,9,18,3,14,Tree.NullNode,19,1,5};
		t.populateTree(data);
		t.printTree();
		
		printAllAncestors(t, 5);
	}

}
