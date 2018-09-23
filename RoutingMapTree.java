import java.util.Scanner;
public class RoutingMapTree{
	Exchange root;

	//necessary constructors

	public RoutingMapTree(Exchange a){
		this.root = a;
	}

	public RoutingMapTree(){
		root = new Exchange(0);
	}

	public Exchange findPhone(MobilePhone m){
		try{
			if(root.residentSet.isMember(m.number)){
				try{
					if(m.status()){
						return m.location;
					}
					else{
						throw new Exception();
					}
				}
				catch(Exception e){
					System.out.println("Phone is switched off");
				}
			}
			else{
				throw new Exception();
			}
		}
		catch(Exception e){
			System.out.println("Mobile phone not present");
		}
		return null; 
	}

	public Exchange lowestRouter(Exchange a, Exchange b){
		Exchange temp1 = a;
		Exchange temp2 = b;
		while(temp1.parent != temp2.parent){
			temp1 = temp1.parent;
			temp2 = temp2.parent;
		}
		return temp1.parent;
	}

	public ExchangeList routeCall(MobilePhone a, MobilePhone b){
		
		ExchangeList list1 = new ExchangeList();
		ExchangeList list2 = new ExchangeList();
		ExchangeList finalList = new ExchangeList();
		Exchange temp1 = a.location;
		Exchange temp2 = b.location;
		Exchange nearestStation = lowestRouter(temp1, temp2);
		if(temp1 == temp2){
			finalList.exchanges.addLast(temp1);
			return finalList;
		}
		while(temp1 != nearestStation){
			list1.exchanges.addLast(temp1);
			temp1 = temp1.parent;
		}
		list1.exchanges.addLast(nearestStation);
		while(temp2 != nearestStation){
			list2.exchanges.addFront(temp2);
			temp2 = temp2.parent;
		}
		while(!list2.exchanges.isEmpty()){
			list1.exchanges.addLast(list2.exchanges.removeFirst());
		}
		return list1;
	}

//have to get my register and deregister working 

	public void movePhone(MobilePhone a, Exchange b){
		try
		{
			if(a.status()){
				try{
					if(findTreeNode(b.number)!=null){

						deRegister(a);
						b.register(a);
					}
					else{
						throw new Exception();
					}
				}
				catch(Exception e){
					System.out.println(containsNode(b.number));
					System.out.println("Exchange not found :(");
				}
			}
			else{
				throw new Exception();
			}
		}
		catch(Exception e){
			System.out.println("Mobile is switched off");
		}
	}
	
	public MobilePhone returnMobilePhone(int mobileNumber){
	 	if(root.residentSet.isMember(mobileNumber)){
	 		int i;
	 		LinkedList.Node temp = root.residentSet.llist.head;
	 		for(i=0;i<root.residentSet.llist.getSize();i++){
	 			if((((MobilePhone)(temp.data)).number) == mobileNumber){
	 				return ((MobilePhone)(temp.data));
	 			}
	 			temp = temp.next;
	 		}
	 	}
	 	return null;
	}

	public Exchange findTreeNode(int exchangeNumber){
			Exchange answer = null;
			if(this.root.number == exchangeNumber){
				return this.root;
			}
			
			for(int i=0; i<root.children.exchanges.getSize();i++){
				answer = root.subtree(i).findTreeNode(exchangeNumber);
				
				if(answer != null){
					return answer;
				}
				
			}
			return null;
			
	}

	public void switchOn(MobilePhone mobileNumber, Exchange exchangeNumber){
		try{	
			if(!mobileNumber.status()){
				mobileNumber.switchOn();
				exchangeNumber.register(mobileNumber);
				mobileNumber.setLocation(exchangeNumber);
			}
			else{
				throw new Exception();
			}
		}
		catch(Exception e){
			
		}
	}
	 
	public boolean containsNode(int exchangeNumber){
		if(root != null){
			if(root.number == exchangeNumber){
				return true;
			}
			int i;
			for(i=0;i<root.children.exchanges.getSize();i++){
				return root.subtree(i).containsNode(exchangeNumber);
			}

		}
		return false;
	}

	public void switchOff(MobilePhone a){
		if(a.status()){
			a.switchOff();
		}
		Exchange z = a.location();
		deRegister(a);

	}

	public void switchOff(int mobileNumber){
		MobilePhone a = root.returnMobilePhone(mobileNumber);
		if(a.status()){
			a.switchOff();
			deRegister(a);
		}
	}

	public void deRegister(MobilePhone a){
		Exchange baseLink = a.location();
		Exchange temp = baseLink;
		while(temp != null){
			temp.residentSet.Delete(a);
			temp = temp.parent;
		}
	}

	public String performAction(String actionMessage){
		Scanner sc = new Scanner(actionMessage);
		String action = sc.next();
		switch(action){
			case "addExchange":
			{	System.out.println(actionMessage);
				int a = sc.nextInt();
				int b = sc.nextInt();
				Exchange B = new Exchange(b);
				
				Exchange exchangeNode = findTreeNode(a);
				if(exchangeNode != null){
					exchangeNode.addChild(B);
				}
				
				break;
			}

			case "switchOnMobile":
			{	System.out.println(actionMessage);
				int mobileNumber = sc.nextInt();
				int exchangeNumber = sc.nextInt();
				
				Exchange z = findTreeNode(exchangeNumber);
				MobilePhone m = new MobilePhone(mobileNumber);
				try{
					if(z != null){
						switchOn(m,z);
					}
					else{
						throw new Exception();
					}
				}
				catch(Exception e){
					return "Error- No exchange with identifier b";
				}
				break;
			}

			case "switchOffMobile":
			{	System.out.println(actionMessage);
				int a = sc.nextInt();
				MobilePhone answer = returnMobilePhone(a);
				try{
					if(answer != null){
						answer.switchOff();	
					}
					else{
						throw new Exception();
					}
				}
				catch(Exception e){
					System.out.println("Mobile is not present");
				}
			
				break;
			}

			case "queryNthChild":
			{	System.out.println(actionMessage);

				int a = sc.nextInt();
				int b = sc.nextInt();
				Exchange z = findTreeNode(a);
				int n = z.children.exchanges.getSize();
				try{
					if(b>=0 && b<n){
						Exchange answer = z.child(b);
						String string = actionMessage + ": " + Integer.toString(answer.number);
						//return string;
						System.out.println(string);
					}
					else{
						throw new Exception();
					}
				}
				catch(Exception e){
					System.out.println("Error - No b child of Exchange a ");
				}
				break;
			}

			case "queryMobilePhoneSet":
			{	System.out.println(actionMessage);
				int a = sc.nextInt();
				Exchange z = findTreeNode(a);
				String answer = actionMessage + ": ";
				try{
					if(z != null){
						int n = z.residentSet.getSize();
						LinkedList.Node tempNode = z.residentSet.llist.head;
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
				break;

			}
		
			case "findPhone":
			{	System.out.println(actionMessage);
				int m = sc.nextInt();
				MobilePhone mob = returnMobilePhone(m);
				Exchange answer = null;
				String str = "queryFindPhone" + " "+ m+": ";
				try{
					if(mob != null){
						answer = findPhone(mob);
						str = str + Integer.toString(mob.location.number);
						//return str;
						System.out.println(str);
					}
					else{
						throw new Exception();
					}
				}
				catch(Exception e){
					System.out.println(str + "Error - No mobile phone with identifier " + m +  " found in the network");
				}
				break;
			}

			case "lowestRouter":
			{	System.out.println(actionMessage);
				int a = sc.nextInt();
				int b = sc.nextInt();
				String answer = "queryLowestRouter"+" " +a+" "+b  + ": ";
				Exchange e1 = findTreeNode(a);
				Exchange e2 = findTreeNode(b);
				try{
					if(e1 != null && e2 != null){
						answer = answer + Integer.toString((lowestRouter(e1,e2)).number);
						//return answer;
						System.out.println(answer);

					}
					else{
						throw new Exception();
					}
				}
				catch(Exception e){
					System.out.println("Either of the phone does not exist.!");

				}
				break;
			}

			case "findCallPath":
			{	System.out.println(actionMessage);
				int a = sc.nextInt();
				int b = sc.nextInt();
				String answer = "queryFindCallPath"+" "+a+" "+b + ": ";
				//String waste = "";
				MobilePhone m1 = returnMobilePhone(a);
				//System.out.println(m1.location.number);
				MobilePhone m2 = returnMobilePhone(b);
				//System.out.println(m2.location.number);
				try{
					if(m1 != null && m2 != null){
						try{
							if(m1.status() && m2.status()){
								ExchangeList list = routeCall(m1,m2);
								LinkedList.Node temp = list.exchanges.head;
								while(temp != null){
								//System.out.println(answer);
								answer = answer + Integer.toString(((Exchange)temp.data).number);
								if(temp.next != null){
									answer = answer + ", ";
								}
								temp = temp.next;						}
								//return answer; 
								System.out.println(answer);
							}
							else{
								if(!m1.status()){
									System.out.println(answer+"Error - Mobile phone with identifier " + m1.number + " is currently switched off");
									//return(answer+"Error - Mobile phone with identifier " + m1.number + " is currently switched off");
								}
								if(!m2.status()){
									//return(answer+"Error - Mobile phone with identifier " + m2.number + " is currently switched off");
									System.out.println(answer+"Error - Mobile phone with identifier " + m2.number + " is currently switched off");
								}
								throw new Exception();
							}
						}
						catch(Exception e){

						}

						
					}
					else{
						throw new Exception();
					}
				}
				catch(Exception e){
					System.out.println("Either mobile is missing!!");
				}
				break;
			}
			case "movePhone":
			{	System.out.println(actionMessage);
				int a = sc.nextInt();
				int b = sc.nextInt();
				Exchange inpExchange = findTreeNode(b);
				MobilePhone inpMobilePhone = returnMobilePhone(a);
				movePhone(inpMobilePhone,inpExchange);
				break;
			}
		}
		return null;
	}
}