public class User {
    private String name;
    private String id;
    private String password;
    private int identity;

    User(String name, String id, String password, int identity) {
        this.name = name;
        this.id = id;
        this.password = password;
        this.identity = identity;
    }

    User() {

    }

    public void setName(String Username) {
        this.name = Username;
    }

    public void setId(String Userid) {
        this.id = Userid;
    }

    public void setPassword(String Userpassword) {
        this.password = Userpassword;
    }

    public void setIdentity(int Useridentity) {
        this.identity = Useridentity;
    }

    public String getName() {
        return this.name;
    }

    public String getId() {
        return this.id;
    }

    public String getPassword() {
        return this.password;
    }

    public int getIdentity() {
        return this.identity;
    }

    public void cancel() {

    }

}
