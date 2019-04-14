import java.sql.Time;

import javax.print.attribute.standard.RequestingUserName;
import javax.tools.DocumentationTool.Location;
import javax.xml.ws.soap.AddressingFeature.Responses;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;



public class Book {
String name;
String writer;//
String publisher;//出版商
String ID;//书ID
//int number;书数量,
String ownerID;
String borrowed;
BookLocation location;
int lastTime;
public Book(String id)
{
	//向服务器发送请求获取书籍信息，初始化类
	
	JSONObject respond = new TcpClient("localhost", 8080).action(new BookInfoRequest(ID));
    this.name = respond.getString("name");
    this.writer = respond.getString("writer");
    this.publisher=respond.getString("publisher");
 //   this.number = respnd.getInt("number");
    this.ownerID=respond.getString("ownerID");
    this.borrowed=respond.getString("borrowedTime");//借阅时间改为字符串储存
    this.lastTime=respond.getInteger("lastTime");
    JSONObject l = JSON.parseObject(respond.getString("Location"));
    location=new BookLocation(l.getString("story"),l.getString("roomnumber"),l.getString("name"),l.getString("bookshelf"));
}
public boolean borrowed(String id)//参数为结束人id
{
	ownerID=id;
	//向服务器发送请求更新数据库
	JSONObject respond = new TcpClient("localhost", 8080).action(new BookBorrowed(ID,id));
	return respond.getBoolean("success");
}
public boolean returnBook() {
	JSONObject respond = new TcpClient("localhost", 8080).action(new BookReturn());
	return respond.getBoolean("success");
}
public void print()
{
	//TODO:输出表单,向服务器发送请求，回传数据库数据，打印
}
}
