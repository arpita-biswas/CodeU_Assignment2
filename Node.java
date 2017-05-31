
//Implementing a node
public class Node<T>{
	T data;
	Node<T> left = null;
	Node<T> right = null;
	
	public Node(){
		this.data = null;
		this.left = null;
		this.right = null;
	}
	
	public Node(T data){
		this.data = data;
	}
	
	void addRight(Node<T> nextNode){
		this.right = nextNode;
	}
	
	void addLeft(Node<T> nextNode){
		this.left = nextNode;
	}
	
	T getData(){
		return this.data;
	}	
	
	@Override
	public String toString(){
		StringBuilder str = new StringBuilder();
		if(this.data == null){
			return "null";			
		}
		else{
			str.append("Node: "+this.data+" ");
			if(this.left == null){
				str.append("Left: null ");
			}
			else{
				str.append("Left: "+this.left.data+" ");
			}
			if(this.right == null){
				str.append("Right: null ");
			}
			else{
				str.append("Right: "+this.right.data+" ");
			}
			str.append("\n");
			return str.toString();
		}
	}
	
}
