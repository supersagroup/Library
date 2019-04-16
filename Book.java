package Client;

import com.alibaba.fastjson.*;

public class Book {

    String name;

    String writer;

    String publisher;

    String ID;//books' id

    String ownerID;

    String borrowed;//whether be borrowed

    BookLocation location;

    int lastTime;//the time being borrowed

    public Book(String id) {

        //向服务器发送请求获取书籍信息，初始化类

        JSONObject respond = new TcpClient("localhost", 8080).action(new BookInfoRequest(id));

        this.name = respond.getString("name");

        this.writer = respond.getString("writer");

        this.publisher = respond.getString("publisher");

        this.ownerID = respond.getString("ownerID");

        this.borrowed = respond.getString("borrowedTime");//借阅时间改为字符串储存

        this.lastTime = respond.getInteger("lastTime");

        JSONObject response = JSON.parseObject(respond.getString("Location"));

        this.location = new BookLocation(response.getString("story"), response.getString("roomnumber"), response.getString("name"), response.getString("bookshelf"));

    }

    public boolean borrowed(String id){

        ownerID = id;

        //向服务器发送请求更新数据库

        JSONObject respond = new TcpClient("localhost", 8080).action(new BookBorrowed(this.ID, id));

        return respond.getBoolean("success");

    }

    public boolean returnBook() {

        JSONObject respond = new TcpClient("localhost", 8080).action(new BookReturn(this.ID));

        return respond.getBoolean("success");

    }

    public void print() {

        //TODO:输出表单,向服务器发送请求，回传数据库数据，打印

    }

    public String getBookName() {

        return this.name;
    }

    public String getWriter() {

        return this.writer;
    }

    public String getPublisher() {

        return this.publisher;

    }

    public String getID() {

        return this.ID;

    }

    public String getOwnerID() {

        return this.ownerID;

    }

    public String getBorrowTime() {

        return this.borrowed;

    }

    public int getLastTime() {

        return this.lastTime;

    }

    public BookLocation getLocation() {

        return this.location;

    }

}

class BookLocation {

    private String story;

    private String roomNumber;

    private String name;

    private String bookshelf;

    public BookLocation(String s, String r, String n, String b) {

        story = s;

        roomNumber = r;

        name = n;

        bookshelf = b;

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

class BookInfoRequest {

    public String act = "getbookinfo";

    public String bookid;

    public BookInfoRequest(String id) {

        // TODO Auto-generated constructor stub

        bookid = id;

    }

}

class BookBorrowed {

    public String act = "borrowbook";

    public String bookID;

    public String ownerID;

    public BookBorrowed(String bookid, String ownerid) {

        // TODO Auto-generated constructor stub

        bookID = bookid;

        ownerID = ownerid;

    }

}

class BookReturn {

    public String act = "returnbook";

    public String bookid;

    BookReturn(String id) {
        this.bookid = id;
    }


}