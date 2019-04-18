package login;
import book.Book;
import com.alibaba.fastjson.*;
import login.TcpClient;
import login.User;
import managerUi.Attandent;

import java.util.ArrayList;

public class Student extends User {
    public int already_borrowed;
    public int loan;
    public boolean limited;
    public ArrayList<Book> books;
    public static final int limited_number = 10;

    public Student(String ID){
        super();
        books=new ArrayList<>();
        JSONObject respond = new TcpClient("localhost", 8080).action(new InitStuInfo(ID));
        this.already_borrowed = respond.getIntValue("already_borrowed");
        this.limited = respond.getBoolean("limited");
        this.loan = respond.getIntValue("loan");
        setId(respond.getString("id"));
        setIdentity(0);
        setName(respond.getString("name"));
        setPassword(respond.getString("password"));
        try{
            for (int i = 1; i <= 10; i++) {
                if(!respond.getString("book"+i).equals(""))
                    this.books.add(new Book(respond.getString("book"+i)));
            }
        }catch (Exception e){
            //
        }
    }

    public void print_info() {
        //结合界面
    }

    public void print_books() {
        //结合界面
    }
    public ArrayList<Book> search(String na, String wr, String pub) throws Exception {

        JSONObject respond=new TcpClient("localhost",8080).action(new search_request(na,wr,pub));
        ArrayList<Book> books=new ArrayList<Book>();
        for(int i=1;i<=10;i++)
        {
            if(!respond.getString("book"+i).equals(""))
                books.add(new Book(respond.getString("book"+i)));
        }
        return books;
    }
    @Override
    public void cancel() {
        new TcpClient("localhost", 8080).action(new Object() {
            public String act = "cancel";
            public String id = getId();
        });
    }
}
class search_request {

    public String act = "attandent_search";

    public String name;

    public String writer;

    public String publisher;

//    public String book_id;


    public search_request(String na, String wr, String pu) {

        name = na;

        writer = wr;

        publisher = pu;



    }

}
class InitStuInfo {

    public String act="getstuinfo";
    public String id;
    InitStuInfo(String i)
    {
        this.id=i;
    }
}

