
public class BookLocation {
private String story;
private String roomNumber;
private String name;
private String bookshelf;
public BookLocation(String s,String r,String n,String b) {
	story=s;
	roomNumber=r;
	name=n;
	bookshelf=b;
}
public String getStory() {
	return this.story;
}
public String getRoomNumber() {
	return this.roomNumber;
}
public String getName() {
	return this.name;
}
public String getBookshelf() {
	return this.bookshelf;
}
}
