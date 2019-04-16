package bms;

public class InitBookResponse {
	private String name;
	private String writer;
	private String publisher;
	private String ownerID;
	private String borrowedTime;
	private String ID;
	private String location;
	
	public InitBookResponse(String name,String writer,String publisher,String ownerID,String borrowedTime,String ID,String location) {
		setName(name);
		setWriter(writer);
		setPublisher(publisher);
		setOwnerID(ownerID);
		setBorrowedTime(borrowedTime);
		setID(ID);
		setLocation(location);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getOwnerID() {
		return ownerID;
	}
	public void setOwnerID(String ownerID) {
		this.ownerID = ownerID;
	}
	public String getBorrowedTime() {
		return borrowedTime;
	}
	public void setBorrowedTime(String borrowedTime) {
		this.borrowedTime = borrowedTime;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	
}
