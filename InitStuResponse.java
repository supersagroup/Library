package bms;

public class InitStuResponse {
	private int alreadyborrowednum;
	private boolean limited;
	private String name;
	private String id;
	private String pwd;
	private String books;
	
	public InitStuResponse(int alreadyborrowednum,String name,String id,String pwd,String books) {
		setName(name);
		setId(id);
		setPwd(pwd);
		setAlreadyborrowednum(alreadyborrowednum);
		setBooks(books);
	}
	
	public boolean isLimited() {
		return limited;
	}

	public void setLimited() {
		if(this.alreadyborrowednum>10)
			this.limited=false;
		else
			this.limited=true;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public int getAlreadyborrowednum() {
		return alreadyborrowednum;
	}
	public void setAlreadyborrowednum(int alreadyborrowednum) {
		this.alreadyborrowednum = alreadyborrowednum;
	}
	public String getBooks() {
		return books;
	}
	public void setBooks(String books) {
		this.books = books;
	}
}

