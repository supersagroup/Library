
package book;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import login.TcpClient;


public class Book {
public String name;
    public String writer;//
    public String publisher;//出版商
    public String ID;//书ID
//int number;书数量,
public String ownerID;
    public String borrowed;
    public BookLocation location;
    public int lastTime;
public Book(String id) throws Exception
{
	//向服务器发送请求获取书籍信息，初始化类
	this.ID=id;
	JSONObject respond = new TcpClient("localhost", 8080).action(new BookInfoRequest(id));
	if(respond.getBoolean("result")){
        this.name = respond.getString("name");
        this.writer = respond.getString("writer");
        this.publisher=respond.getString("publisher");
 //   this.number = respond.getInt("number");
        this.ownerID=respond.getString("ownerID");
        this.borrowed=respond.getString("borrowedTime");//借阅时间改为字符串储存
        this.lastTime=respond.getInteger("lasttime");
     //   JSONObject l = JSON.parseObject(respond.getString("Location"));
        String[] bookLocInfo = respond.getString("location").split("-");
        location=new BookLocation(bookLocInfo[0],bookLocInfo[1],bookLocInfo[2],bookLocInfo[3]);
	}else{
	    throw new Exception("no such book");
    }
}
public boolean borrowed(String id)//参数为结束人id
{
	//向服务器发送请求更新数据库
	JSONObject respond = new TcpClient("localhost", 8080).action(new BookBorrowed(this.ID,id));
	return respond.getBoolean("result");
}
public boolean returnBook() {
	JSONObject respond = new TcpClient("localhost", 8080).action(new BookReturn(this.ID));
	return respond.getBoolean("result");
}
public void print()
{
	//TODO:输出表单,向服务器发送请求，回传数据库数据，打印
}
}
