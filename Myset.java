public class Myset<E>{
	public LinkedList<E> llist;
	Myset(){
		llist = new LinkedList();
	}
	public boolean isEmpty(){
		return llist.isEmpty();
	}
	public boolean isMember(LinkedList.Node e){
		return llist.isMember(e);
	}
	public void Insert(LinkedList.Node e){
		if(llist.isMember(e)){
			return;
		}
		llist.addLast(e);
	}
	public void Delete(LinkedList.Node e){
		LinkedList.Node temp = llist.head;
		LinkedList.Node temp1;
		LinkedList.Node temp2 = temp;
		if(temp == null){
			return;
		}
		while(temp != null){
			if(temp.data == e.data){
				llist.size--;
				temp1 = temp.next;
				temp2.next = temp1;
				return;
			}
			temp2 = temp;
			temp = temp.next;
		}
	}
	public int getSize(){
		return llist.getSize();
	}
	public Myset Union(Myset a){
		Myset c = new Myset();
		LinkedList.Node temp1 = llist.head;
		LinkedList.Node temp2 = a.llist.head;
		int i,j;
		for(i=0;i<llist.getSize();i++){
			if(temp1 != null){
				c.llist.addLast(temp1);
				temp1 = temp1.next;
			}
		}
		for(i=0;i<a.llist.getSize();i++){
			c.Insert(temp2);
			temp2 = temp2.next;
		}
		return c;
	}
	public Myset Intersection(Myset a){
		LinkedList.Node temp = llist.head;
		Myset c = new Myset();
		for(int i=0;i<llist.getSize();i++){
			if(temp != null){
				if(a.isMember(temp)){
					c.Insert(temp);
				}
				temp = temp.next;
			}
		}
		return c;
	}
	public static void printSet(Myset a){
		int i;
		LinkedList.Node temp = a.llist.head;
		if(temp == null){
			return;
		}
		for(i=0;i<a.llist.getSize();i++){
			if(temp != null){
				System.out.println(temp.data);
				temp = temp.next;
			}
		}
		return;
	}
}