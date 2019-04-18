package book;
public class BookLocation {
String story;
String roomNumber;
String name;
String bookshelf;
public BookLocation(String s,String r,String n,String b) {
	story=s;
	roomNumber=r;
	name=n;
	bookshelf=b;
}
public String toString(){
	return story+"-"+roomNumber+"-"+name+"-"+bookshelf;
}
}
