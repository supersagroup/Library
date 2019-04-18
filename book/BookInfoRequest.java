package book;
public class BookInfoRequest {
	 public String act = "getbookinfo";
     public String bookid;
     public BookInfoRequest(String id) {
		// TODO Auto-generated constructor stub
    	 this.bookid=id;
	}
}
