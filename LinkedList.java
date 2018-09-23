public class LinkedList<E>{
	//SubClass of LinkedList Node
	public static class Node<E>{
		//We can store any type of data in Linked list. Any DataType
		public E data;
		public Node<E> next;
		public Node(){
			data = null;
			next = null;
		}
		public Node(E e, Node<E> n){
			data = e;
			next = n;
		}
		public E getData(){
			return data;
		}
		public Node<E> getNext(){
			return next;
		}
		public void setNext(Node<E> n){
			next = n;
		}
	}
	/*end of node class*/
	/* instance variables of Singly Linked List*/
	/*Head and tail used to access and work with the linked list*/
	public Node<E> head = null;
	public Node<E> tail = null;
	public int size = 0;
	public LinkedList(){
	/*Constructs an empty linked list*/
	}
	/**Access Methods**/
	public boolean isEmpty(){
		return size == 0;
	}
	/*Update Methods*/
	public void addLast(E e){
		Node<E> newest = new Node<>(e, null);
		if(isEmpty()){
			head = newest;
		}
		else{
			tail.setNext(newest);
		}
		tail = newest;
		size++;
	}

	public void addLast(Node<E> e){
		Node<E> newest = e;
		if(isEmpty()){
			head = newest;
		}
		else{
			//System.out.println("HELLP");
			tail.setNext(newest);
		}
		tail = newest;
		size++;
	}

	public void addFront(Node<E> e){
		Node<E> newNode = e;
		if(isEmpty()){
			tail = newNode;
		}
		else{
			newNode.next = head;
		}
		head = newNode;
		size++;
	}

	public void addFront(E e){
		Node<E> newNode = new Node(e,null);
		if(isEmpty()){
			tail = newNode;
		}
		else{
			newNode.next = head;
		}
		head = newNode;
		size++;
	}

	public Node<E> get(int n){
		int i;
		Node<E> temp = head;
		for(i=0;i<n;i++){
			if(temp != null){
				temp = temp.next;
			}
			else{
				return null;
			}
		}
		return temp;
	}

	public boolean isMember(Node<E> e){
		Node<E> temp = head;
		if(isEmpty()){
			return false;
		}
		
		while(temp.next != null){
			if(temp.data == e.data){
				return true;
			}
			temp = temp.next;
		}
		if(temp.data == e.data){
			return true;
		}
		return false;
	}

	public E removeFirst(){
		if(isEmpty()){
			return null;
		}
		E answer = head.getData();
		head = head.getNext();
		size--;
		if(size == 0){
			tail = null;
		}
		return answer;
	}

	public int getSize(){
		Node temp = head;
		int n=0;
		while(temp != null){
			temp = temp.next;
			n++;
		}
		return n;
	}
}