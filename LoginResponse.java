package bms;

public class LoginResponse {
	private int confirm;
	private int identity;
	
	public LoginResponse(int confirm,int identity) {
		setConfirm(confirm);
		setIdentity(identity);
	}
	
	public int getConfirm() {
		return confirm;
	}
	public void setConfirm(int confirm) {
		this.confirm = confirm;
	}
	public int getIdentity() {
		return identity;
	}
	public void setIdentity(int identity) {
		this.identity = identity;
	}
}
