
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
}
