/*
– MobilePhone(Int number):  constructor to create a mobile
phone. Unique identifier for a mobile phone is an integer.
– public Int number():  returns the id of the mobile phone.
– public Boolean status():  returns the status of the phone, i.e.
switched on or switched off.
– public void switchOn():  Changes the status to switched on.
– public void switchOff():  Changes the status to switched off.
– public Exchange location(): returns the base station with which
the phone is registered if the phone is switched on and an exception  if  the  phone  is  off.
The  class Exchange will  be  described next
*/
public class MobilePhone{
	public int number;
	public Exchange location;
	MobilePhone(int number){
		this.number = number;
	}
	public int number(){
		return number;
	}
	private boolean status;
	public boolean status(){
		return status;
	}
	public void switchOn(){
		status = true;
	}
	public void switchOff(){
		status = false;
	}
	/*pending*/
	/*public Exchange location(){
	}*/
	public void setLocation(Exchange a){
		this.location = a;
	}
	public Exchange location(){
		if(this.status()){
			return this.location;
		}
		//System.out.println("Phone is Switched Off");
		return null;
	}
}