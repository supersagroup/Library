package Client;

import com.alibaba.fastjson.*;


import java.util.ArrayList;


public class Attandent extends User {

    public Attandent(String id) {

        super();

        JSONObject respond = new TcpClient("localhost", 8080).action(new InitAttaninfo(id));

        this.setId(respond.getString("id"));

        this.setIdentity(1);

        this.setName(respond.getString("name"));

        this.setPassword(respond.getString("password"));

    }

    public boolean borrow(String stu_id, String book_id) {

        JSONObject respond = new TcpClient("localhost", 8080).action(new confirm_request(stu_id, book_id));

        if (respond.getBoolean("result")) {

            Book b = new Book(book_id);

            return b.borrowed(stu_id);

        }

        return false;

    }

    public boolean pay_fine(String stu_id) {

        JSONObject respond = new TcpClient("localhost", 8080).action(new pay_fine_request(stu_id));

        return respond.getBoolean("result");

    }

    public boolean return_book(String book_id) {

        Book b = new Book(book_id);

        return b.returnBook();

    }

    public boolean add_book(String na, String wr, String pub, String ID, BookLocation loc, int la_ti) {

        JSONObject respond = new TcpClient("localhost", 8080).action(new add_request(na, wr, pub, ID, loc, la_ti));

        return respond.getBoolean("result");

    }

    public boolean remove_book(String book_id) {

        JSONObject respond = new TcpClient("localhost", 8080).action(new remove_request(book_id));

        boolean result = respond.getBoolean("result");

        return result;

    }

    public ArrayList<Book> print_log(String na, String wr, String pub) {

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

class add_request {

    public String act = "addbook";

    public String name;

    public String writer;

    public String publisher;

    public String book_id;

    public BookLocation locaton;

    public int last_time;


    public add_request(String na, String wr, String pu, String ID, BookLocation loc, int last_ti) {

        name = na;

        writer = wr;

        publisher = pu;

        book_id = ID;

        locaton = loc;

        last_time = last_ti;

    }

}

class remove_request {

    public String act = "remove";

    public String book_id;


    public remove_request(String ID) {

        book_id = ID;

    }

}

class pay_fine_request {

    public String act = "pay_fine";

    public String stu_id;


    public pay_fine_request(String id) {

        stu_id = id;

    }

}

class confirm_request {

    public String act = "confirmborrow";

    public String stuid;
    public String bookid;

    public confirm_request(String id, String bookid) {

        this.stuid = id;
        this.bookid = bookid;

    }

}

class InitAttaninfo {

    public String act = "getattaninfo";

    public String id;

    public InitAttaninfo(String id) {

        this.id = id;

    }
}