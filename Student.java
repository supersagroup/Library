
import org.json.JSONObject;

import java.util.ArrayList;

public class Student extends User {
    private int already_borrowed;
    private int loan;
    private boolean limited;
    private ArrayList<Book> books;
    public static final int limited_number = 10;

    Student(String Username) {
        JSONObject respond = new TcpClient("localhost", 8080).action(new Object() {
            public String act = "getstudentinfo";
            public String usn = Username;
        });
        this.already_borrowed = respond.getInt("already_borrowed");
        this.limited = respond.getBoolean("limited");
        this.loan = respond.getInt("loan");
        setId(respond.getString("id"));
        setIdentity(respond.getInt("identity"));
        setName(respond.getString("name"));
        setPassword(respond.getString("password"));
        JSONObject book = new JSONObject(respond.getString("books"));
        int bks = respond.getInt("booknumber");
        for (int i = 0; i < bks; i++) {
            this.books.add(new Book(book.getInt(String.valueOf(i))));
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
