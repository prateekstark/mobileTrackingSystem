public class MobilePhoneSet extends Myset{
	public LinkedList<MobilePhone> llist;
	MobilePhoneSet(){
		llist = new LinkedList<MobilePhone>();
	}
	/*public boolean isEmpty(){
		return llist.isEmpty();
	}*/
	//public boolean isMember(MobilePhone e){
	//	return llist.isMember(e);
	//}
	public boolean isMember(int e){
		LinkedList.Node temp = llist.head;
		if(llist.isEmpty()){
			return false;
		}
		
		while(temp != null){
			if(((MobilePhone)temp.data).number() == e){
				return true;
			}
			temp = temp.next;
		}
		// if(((MobilePhone)temp.data).number() == e){
		// 	return true;
		// }
		return false;
	}
	/*public boolean isMember(MobilePhone e){
		LinkedList.Node temp = llist.head;
		if(isEmpty()){
			return false;
		}
		
		while(temp.next != null){
			if(((MobilePhone)temp.data).number() == e.number()){
				return true;
			}
			temp = temp.next;
		}
		if(((MobilePhone)temp.data).number() == e.number()){
			return true;
		}
		return false;
	}*/
	public void Insert(int e){
		if(this.isMember(e)){
			return;
		}
		LinkedList.Node temp = new LinkedList.Node();
		MobilePhone m = new MobilePhone(e);
		temp.data =m;
		temp.next = null;
		llist.addLast(temp);
	}
	public void Insert(MobilePhone e){
		if(this.isMember(e.number())){
			return;
		}
		LinkedList.Node temp = new LinkedList.Node();
		MobilePhone m = e;
		temp.data = m;
		temp.next = null;
		llist.addLast(temp);
	}
	/*public void Delete(int e){
		LinkedList.Node temp = llist.head;
		LinkedList.Node temp1;
		LinkedList.Node temp2 = null;
		if(temp == null){
			return;
		}
		while(temp != null){
			if(((MobilePhone)(temp.data)).number() == e){
				temp1 = temp.next;
				temp2.next = temp1;
				llist.size--;
				return;
			}
			temp2 = temp;
			temp = temp.next;
		}
	}*/
	public void Delete(MobilePhone e){
		LinkedList.Node temp = llist.head;
		LinkedList.Node temp1;
		LinkedList.Node temp2 = temp;
		if(temp == null){
			return;
		}
		while(temp != null){
			if(((MobilePhone)(temp.data)).number == e.number){
				temp1 = temp.next;
				temp2.next = temp1;
				llist.size--;
				return;
			}
			temp2 = temp;
			temp = temp.next;
		}
	}
	public int getSize(){
		return llist.getSize();
	}
	public MobilePhoneSet Union(MobilePhoneSet a){
		//System.out.println("Enter Union");
		MobilePhoneSet c = new MobilePhoneSet();
		LinkedList.Node temp1 = this.llist.head;
		LinkedList.Node temp2 = a.llist.head;
		int i,j;
		for(i=0;i<llist.getSize();i++){
			if(temp1 != null){
				c.Insert(((MobilePhone)(temp1.data)));
			}
		}
		for(i=0;i<a.llist.getSize();i++){
			c.Insert(((MobilePhone)(temp2.data)));
			temp2 = temp2.next;
		}
		return c;
	}
	/*
	public MobilePhoneSet Intersection(MobilePhoneSet a){
		MobilePhone temp = llist.head;
		MobilePhoneSet c = new MobilePhoneSet();
		for(int i=0;i<llist.size;i++){
			if(temp != null){
				if(a.isMember(temp)){
					c.Insert(temp);
				}
				temp = temp.next;
			}
		}
		return c;
	}*/
	/*public static void printSet(MobilePhoneSet a){
		int i;
		MobilePhone temp = a.llist.head;
		if(temp == null){
			return;
		}
		for(i=0;i<a.llist.size;i++){
			if(temp != null){
				System.out.println(temp.data);
				temp = temp.next;
			}
		}
		return;
	}*/
}