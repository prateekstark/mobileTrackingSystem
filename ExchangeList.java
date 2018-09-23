public class ExchangeList{
	public LinkedList<Exchange> exchanges;
	public ExchangeList(){
		exchanges = new LinkedList<Exchange>();
	}
	public Exchange get(int ithnode){
		LinkedList.Node temp = exchanges.head;
		int i;
		try{
			if(ithnode < exchanges.getSize() && ithnode>=0){
				for(i=0;i<ithnode;i++){
					temp = temp.next;
				}
				return ((Exchange)(temp.data));
			}
			else{
				throw new Exception();
			}
		}
		catch(Exception e){
			System.out.println("OutOfBoundsError");
		}
		return null;
	}
}