import java.util.List;
import java.util.ArrayList;
public class Exchange{
	public int number;
	//public List<Exchange> children = new ArrayList<Exchange>();
	public ExchangeList children = new ExchangeList();
	public Exchange parent;// = null;

	public MobilePhoneSet residentSet;
	public Exchange(){
		//parent = null;
		children = new ExchangeList();
		//children = new ArrayList();
		//children = new ExchangeList();
		residentSet = new MobilePhoneSet();
		//number = -22;

	}
	public Exchange(int a){
		//parent = null;
		children = new ExchangeList();
		//children = new LinkedList();
		residentSet = new MobilePhoneSet();
		number = a;
	}
	/*public Exchange(int num, Exchange hello){
		parent = hello;
		//children = new ArrayList();
		//children = new LinkedList();
		number = num;

	}*/
	//public void register(int a){
	//	residentSet.Insert(a);
	//	a.setLocation(this);
	//}
	public void register(MobilePhone a){
		//this.residentSet.Insert(a);
		a.setLocation(this);
		Exchange temp = a.location;
		while(temp != null){
			temp.residentSet.Insert(a);
			temp = temp.parent;
		}
	}
	/*public static void deRegister(int a){
		residentSet.Delete(a);
	}
	public static void deRegister(MobilePhone a){
		Exchange baseLink = a.location();
		while(baseLink != null){
			baseLink.residentSet.Delete(a.number);
			baseLink = baseLink.parent;
		}
	}*/
	
	/*public Exchange(int number){
		this.number = number;
	}*/
	public void addChild(Exchange child){
	//	System.out.println("debug 1");
		child.parent = this;
	//	System.out.println("debug 2");
		this.children.exchanges.addLast(child);
	//	System.out.println("debug 3");
		this.residentSet = this.residentSet.Union(child.residentSet);
	//	System.out.println("debug 1");
	}
	public void addChild(int number){

		Exchange newChild = new Exchange(number);
		newChild.setParent(this);
		this.children.exchanges.addLast(newChild);
	//	System.out.println("hello");
		this.residentSet = this.residentSet.Union(newChild.residentSet);
	}
	/*public void addChildren(List<Exchange> children){
		for(Exchange t : children){
			children.setParent(this);
		}
		this.children.addAll(children);
	}*/
	/*public List<Exchange> getChildren(){
		return children;
	}*/
	public int getNumber(){
		return number;
	}
	/*public void setNumber(int number){
		this.number = number;
	}*/
	public void setParent(Exchange parent){
		this.parent = parent;
	}
	public Exchange parent(){
		return parent;
	}
	public boolean isRoot(){
		return (this.parent == null);
	}
	public boolean isLeaf(){
		return (this.children.exchanges.getSize() == 0);
	}
	public int numChildren(){
        return children.exchanges.getSize();
    }
    public Exchange child(int i){
    	//System.out.println("Hello");
    	//Exchange temp = new Exchange(0);
    	Exchange temp = this.children.get(i);
        return temp;
    }
    public MobilePhoneSet residentSet(){
    	return residentSet;
    }

    //*pending*
    //routing map subtree
    /*public RoutingMapTree subtree(int i){
    	//RoutingMapTree tempRoot = RoutingMapTree(children.get(i));
    	Exchange newRoot = this.child(i);
    	if(newRoot != null){
    		RoutingMapTree answer = new RoutingMapTree();
    		answer.root = newRoot;
    		return answer;
    	}
    	return null;
    	//m.root = children.exchanges.get(i).data;
    	//return m;
    }*/
    public MobilePhone returnMobilePhone(int mobileNumber){
		
		if(residentSet.isMember(mobileNumber)){
			LinkedList.Node temp = residentSet.llist.head;
			while(((MobilePhone)(temp.data)).number != mobileNumber){
				temp = temp.next;
			}
			return ((MobilePhone)(temp.data));
		}
		return null;
	}
    public RoutingMapTree subtree(int i){
    	Exchange newRoot = child(i);
    	//System.out.println("hello ldy");
    	if(newRoot != null){
    	//	System.out.println("Debug 2");
    		RoutingMapTree tree = new RoutingMapTree(newRoot);
    		return tree;
    	}
    	//System.out.println("Debug 2");
    	return null;
    }
    public void printResidentSet(){
    	String answer = "";
    	try{
					if(this != null){
						int n = this.residentSet.getSize();
						LinkedList.Node tempNode = this.residentSet.llist.head;
						while(tempNode != null){
							if(((MobilePhone)(tempNode.data)).status()){
								answer = answer + Integer.toString(((MobilePhone)(tempNode.data)).number());
								if(tempNode.next != null){
									answer = answer + ", ";
								}
							}
							tempNode = tempNode.next;
						}
						//return answer;
						System.out.println(answer);
					}
					else{
						throw new Exception();
					}
				}
				catch(Exception e){		
				}
    }
}