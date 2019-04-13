
import com.alibaba.fastjson.*;

import java.util.ArrayList;

public class Student extends User {
    private int already_borrowed;
    private int loan;
    private boolean limited;
    private ArrayList<Book> books;
    public static final int limited_number = 10;

    Student(String ID) {
        JSONObject respond = new TcpClient("localhost", 8080).action(new InitStuInfo(ID));
        this.already_borrowed = respond.getIntValue("already_borrowed");
        this.limited = respond.getBoolean("limited");
        this.loan = respond.getIntValue("loan");
        setId(respond.getString("id"));
        setIdentity(respond.getIntValue("identity"));
        setName(respond.getString("name"));
        setPassword(respond.getString("password"));
        JSONObject book = JSON.parseObject(respond.getString("books"));
        int bks = respond.getIntValue("booknumber");
        for (int i = 0; i < bks; i++) {
            this.books.add(new Book(book.getIntValue(String.valueOf(i))));
        }
    }

    public void print_info() {
        //结合界面
    }

    public void print_books() {
        //结合界面
    }

    @Override
    public void cancel() {
        new TcpClient("localhost", 8080).action(new Object() {
            public String act = "cancel";
            public String id = getId();
        });
    }

}
