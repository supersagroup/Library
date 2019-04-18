package managerUi;


import book.Book;
import book.BookLocation;
import login.User;

import login.TcpClient;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;

public class Attandent extends User {
	
public Attandent(String id) {
	super();
	JSONObject respond=new TcpClient("localhost",8080).action(new login_request(id));
	this.setId(id);
	this.setIdentity(1);
	this.setName(respond.getString("name"));
	this.setPassword(respond.getString("password"));
}

public boolean borrow(String stu_id,String book_id) throws Exception{
	JSONObject respond=new TcpClient("localhost",8080).action(new confirm_request(stu_id));
	if(respond.getBoolean( "result")){
		Book b = new Book(book_id);
		return b.borrowed(stu_id);
	}
	return false;
}

public boolean pay_fine(String stu_id) {
	JSONObject respond=new TcpClient("localhost",8080).action(new pay_fine_request(stu_id));
	return respond.getBoolean("result");
}
			
public boolean return_book(String book_id) throws Exception{
	Book b=new Book(book_id);
    return b.returnBook();
}
			
public boolean add_book(String na, String wr, String pub, String ID, String loc, int la_ti) {
	JSONObject respond = new TcpClient("localhost", 8080).action(new add_request(na,wr,pub,ID,loc,la_ti));
	return respond.getBoolean("result");
}
				
public boolean remove_book(String book_id) {
	JSONObject respond=new TcpClient("localhost",8080).action(new remove_request(book_id));
	boolean result=respond.getBoolean("result");
	return result;
}
				
			
public ArrayList<Book> print_log(String na, String wr, String pub, String ID)throws Exception{
	JSONObject respond=new TcpClient("localhost",8080).action(new search_request(na,wr,pub,ID));
	ArrayList<Book> books=new ArrayList<Book>();
	for(int i=1;i<=10;i++)
	{
		if(!respond.getString("book"+i).equals(""))
			books.add(new Book(respond.getString("book"+i)));
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
    public String location;
	public int last_time;
	
	public add_request(String na,String wr,String pu,String ID,String loc,int last_ti) {
		name=na;
        writer=wr;
        publisher=pu;
        book_id=ID;
		this.location=loc;
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
public String act="confirmborrow";
public String stu_id;
public confirm_request(String id)
{
	stu_id=id;
}
}


class login_request
{
public String act="getattaninfo";
public String id;
public login_request(String id)
{
	this.id=id;
}
}
}

