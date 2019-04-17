package bms;

public class Response {

}

class ConfirmResponse
{
	boolean result;
	ConfirmResponse(boolean x)
	{
		this.result=x;
	}
	public boolean isResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
	
}
