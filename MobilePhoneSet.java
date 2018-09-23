public class MobilePhoneSet extends Myset{
	public LinkedList<MobilePhone> llist;
	MobilePhoneSet(){
		llist = new LinkedList<MobilePhone>();
	}
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
		return false;
	}
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
}
