package bms;

public class GetAttanResponse {
	private int identity;
	private String name;
	private String password;
	
	public GetAttanResponse(String name,String password) {
		this.setIdentity(1);
		this.setName(name);
		this.setPassword(password);
	}
	
	public int getIdentity() {
		return identity;
	}
	public void setIdentity(int identity) {
		this.identity = identity;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
