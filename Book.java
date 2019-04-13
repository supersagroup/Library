import java.sql.Time;

import javax.tools.DocumentationTool.Location;
import javax.xml.ws.soap.AddressingFeature.Responses;

import org.json.JSONObject;
public class Book {
String name;
String writer;//
String publisher;//出版商
String ID;//书ID
//int number;书数量,
int ownerID;
String borrowed;
BookLocation location;
int lastTime;
public Book(String id)
{
	//向服务器发送请求获取书籍信息，初始化类；
	JSONObject respond = new TcpClient("localhost", 8080).action(new Object() {
        public String act = "getbookinfo";
        public String usn = id;
    });
    
    this.name = respond.getString("name");
    this.writer = respond.getString("writer");
    this.publisher=respond.getString("publisher");
 //   this.number = respond.getInt("number");
    this.ownerID=respond.getInt("ownerID");
    this.borrowed=respond.getString("borrowedTime");//借阅时间改为字符串储存
    this.lastTime=respond.getInt("lastTime");
    JSONObject l = new JSONObject(respond.getString("location"));
    location=new BookLocation(l.getString("story"),l.getString("roomnumber"),l.getString("name"),l.getString("bookshelf"));
}
public void borrowed(int id)//参数为结束人id
{
	ownerID=id;
	//向服务器发送请求更新数据库
	JSONObject respond = new TcpClient("localhost", 8080).action(new Object() {
        public String act = "borrowbook";
        public String bookID =ID ;
        public int ownerid=ownerID;
        //borrowtime为服务器端生成之后存到数据库中
    });
}
public void print()
{
	//TODO:输出表单,向服务器发送请求，回传数据库数据，打印
}
}
