package managerUi;

import java.util.Date;
import java.util.ArrayList;

import login.User;
import login.Book;
import login.BookLocation;
import login.TcpClient;

import com.alibaba.fastjson.JSONObject;

public class Attandent extends User {
	
public Attandent(String name,String id,String password) {
	super(name,id,password);
}

public boolean borrow(String stu_id,String book_id) {
	JSONObject respond=new TcpClient("localhost",8080).action(new confirm_request(stu_id));
	if(respond.getBoolean( key:"result")){
		Book b = new Book(book_id);
		return b.borrowed(stu_id);
	}
	return false;
}

public boolean pay_fine(String stu_id) {
	JSONObject respond=new TcpClient("localhost",8080).action(new pay_fine_request(stu_id));
	return respond.getBoolean("result");
}
			
public boolean return_book(String book_id){
	Book b=new Book(book_id);;
    return b.returned();
}
			
public boolean add_book(String na,String wr,String pub,String ID,BookLocation loc,int la_ti) {
	JSONObject respond = new TcpClient("localhost", 8080).action(new add_request(na,wr,pub,ID,loc,la_ti));
	return respond.getBoolean("result");
}
				
public boolean remove_book(String book_id) {
	JSONObject respond=new TcpClient("localhost",8080).action(new remove_request(book_id));
	boolean result=respond.getBoolean("result");
	return result;
}
				
			
public ArrayList<Book> print_log(String na,String wr,String pub,String ID){
	JSONObject respond=new TcpClient("localhost",8080).action(new search_request(na,wr,pub,ID));
	ArrayList<Book> books=new ArrayList<Book>();
	int nubmer=respond.getInteger("number");
	for(int i=0;i<nubmer;i++)
	{
		books.add(new Book(respond.getString("number"+i)));
	}
	return books;
}

class search_request
{
	public String act="attandent_search";
	public String name;
	public String writer;
	public String publisher;
	public String book_id;
	
	public search_request(String na,String wr,String pu,String ID) {
		name=na;
        writer=wr;
        publisher=pu;
        book_id=ID;
	}
}

class add_request
{
	public String act="add";
	public String name;
	public String writer;
	public String publisher;
	public String book_id;
    public BookLocation locaton;
	public int last_time;
	
	public add_request(String na,String wr,String pu,String ID,BookLocation loc,int last_ti) {
		name=na;
        writer=wr;
        publisher=pu;
        book_id=ID;
        locaton=loc;
        last_time=last_ti;
	}
}

class remove_request
{
	public String act="remove";
	public String book_id;
	
	public remove_request(String ID) {
        book_id=ID;
	}
}

class pay_fine_request
{
	public String act="pay_fine";
	public String stu_id;
	
	public pay_fine_request(String id) {
		stu_id=id;
	}
}

class confirm_request
{
public String act="confirm";
public String stu_id;
public confirm_request(String id)
{
	stu_id=id;
}
}}

