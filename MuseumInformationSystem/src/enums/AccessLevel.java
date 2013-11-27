package enums; 

public enum AccessLevel {
	ADMIN(0), GUEST(1);
	
	private int value;
	
	private AccessLevel(int value){
		this.value = value;
	}
	 public int getValue(){
		 return value;
	 }
}
