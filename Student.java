package Client;
import com.alibaba.fastjson.*;

import java.util.ArrayList;

public class Student extends User {
    private int already_borrowed;
    private int loan;
    private boolean limited;
    private ArrayList<Book> books;
    public static final int limited_number = 10;

    Student(String ID) {
        super();
        JSONObject respond = new TcpClient("localhost", 8080).action(new InitStuInfo(ID));
        this.already_borrowed = respond.getIntValue("already_borrowed");
        this.limited = respond.getBoolean("limited");
        this.loan = respond.getIntValue("loan");
        setId(respond.getString("id"));
        setIdentity(0);
        setName(respond.getString("name"));
        setPassword(respond.getString("password"));
        JSONObject book = JSON.parseObject(respond.getString("books"));
        int bks = respond.getIntValue("booknumber");
        for (int i = 0; i < bks; i++) {
            this.books.add(new Book(book.getString(String.valueOf(i))));
        }
    }

    public void print_info() {
        //结合界面
    }

    public void print_books() {
        //结合界面
    }
    public ArrayList<Book> search(String na, String wr, String pub) {

        JSONObject respond = new TcpClient("localhost", 8080).action(new search_request(na, wr, pub));

        ArrayList<Book> books = new ArrayList<Book>();

        String book = respond.getString("book");

        JSONObject bk = JSON.parseObject(book);
        for (int i = 0; i < 10; i++) {

            if (bk.getString("id" + i).equals(""))
                break;
            else {
                books.add(new Book(bk.getString("id" + i)));
            }
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

    public String request="getstuinfo";
    public String id;
    InitStuInfo(String i)
    {
        this.id=i;
    }
}

