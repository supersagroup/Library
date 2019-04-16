package login;

public class LoginRequest {
    public String act="loginrequest";
    public String id;
    public String pwd;
    LoginRequest(String id,String pwd)
    {
        this.id=id;
        this.pwd=pwd;
    }
}
