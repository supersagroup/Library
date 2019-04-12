import java.sql.Time;

import javax.tools.DocumentationTool.Location;
import org.json.JSONObject;
public class Book {
String name;
String writer;
String publisher;
String ID;
int number;
int ownerID;
Time borrowed;
Location location;
Time lastTime;
public Book(String id)
{
	JSONObject respond = new TcpClient("localhost", 8080).action(new Object() {
        public String act = "getbookinfo";
        public String usn = id;
    });
    
    this.name = respond.getString("name");
    this.writer = respond.getString("writer");
    this.publisher=respond.getString("publisher");
    this.number = respond.getInt("number");
    this.ownerID=respond.getInt("ownerID");
//TODO:服务器回传时间参数
	//TODO:向服务器发送请求获取书籍信息，初始化类；
}
public void borrowed(int id,Time borrowTime)
{
	ownerID=id;
	borrowed=borrowTime;
	//向服务器发送请求更新数据库
	JSONObject respond = new TcpClient("localhost", 8080).action(new Object() {
        public String act = "borrowbook";
        public String bookID =ID ;
        public Time borrowedTime=borrowTime;//具体实现？
    });
}
public void print()
{
	//TODO:输出表单
}
}
