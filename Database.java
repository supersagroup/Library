package bms;

import java.util.*;

import com.alibaba.fastjson.JSON;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class Database {
	final static String usr="root";
	final static String pwd="123456";
	private Statement stmt;
	private ResultSet rs;
	private Connection con;
	private PreparedStatement pstmt;
	public Database() {
		Connectdb();
		Createtable();
	}
	
	public void Connectdb() {
		String JDriver="com.mysql.cj.jdbc.Driver";
		String DB_URL="jdbc:mysql://localhost:3306/bookbase";
		try {
			Class.forName(JDriver);
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			String dbURL=DB_URL+"?user="+usr+"&password="+pwd+"&serverTimezone=CTT";
			con=DriverManager.getConnection(dbURL);
			if(!con.isClosed())
				System.out.println("数据库连接成功！");
			stmt=con.createStatement();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void Createtable() {
		String createSql=null;
		String checkTable=null;
		//管理员表
		createSql="CREATE TABLE ADMIN"
				+ "(name varchar(10) not null,"
				+ "id varchar(10) not null,"
				+ "pwd varchar(10) not null)";
		try {
			checkTable="show tables like \"admin\"";
			ResultSet resultSet=stmt.executeQuery(checkTable);
			if(resultSet.next())
				System.out.println("admin表已经存在!");
			else {
				if(stmt.executeUpdate(createSql)==0)
					System.out.println("admin表创建成功!");
				else
					System.out.println("admin表创建失败!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//学生表
		createSql="CREATE TABLE USER"
				+ "(name varchar(10) not null,"
				+ "id varchar(10) not null,"
				+ "pwd varchar(10) not null,"
				+ "alreadyborrowednum int(10) not null,"
				+ "loan int(10) not null,"
				+ "book1 varchar(20),"
				+ "book2 varchar(20),"
				+ "book3 varchar(20),"
				+ "book4 varchar(20),"
				+ "book5 varchar(20),"
				+ "book6 varchar(20),"
				+ "book7 varchar(20),"
				+ "book8 varchar(20),"
				+ "book9 varchar(20),"
				+ "book10 varchar(20))";
		try {
			checkTable="show tables like \"user\"";
			ResultSet resultSet=stmt.executeQuery(checkTable);
			if(resultSet.next())
				System.out.println("user表已经存在!");
			else {
				if(stmt.executeUpdate(createSql)==0)
					System.out.println("user表创建成功!");
				else
					System.out.println("user表创建失败!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//图书表
		createSql="CREATE TABLE BOOK"
				+ "(name varchar(20) not null,"
				+ "writer varchar(10) not null,"
				+ "publisher varchar(20) not null,"
				+ "ID varchar(20) not null,"
				+ "number int(10) not null,"
				+ "location varchar(20) not null,"
				+ "owner varchar(20) not null,"
				+ "borrowtime date not null,"
				+ "lasttime int(10) not null)";
		try {
			checkTable="show tables like \"book\"";
			ResultSet resultSet=stmt.executeQuery(checkTable);
			if(resultSet.next())
				System.out.println("book表已经存在!");
			else {
				if(stmt.executeUpdate(createSql)==0)
					System.out.println("book表创建成功!");
				else
					System.out.println("book表创建失败!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void AddBook(String name,String writer,String publisher,String ID,int number,String location,String owner,String borrowtime,int lasttime) {
		String sql="INSERT INTO BOOK(name,writer,publisher,ID,number,location,owner,borrowtime,lasttime)values(?,?,?,?,?,?,?,?,?)";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, writer);
			pstmt.setString(3, publisher);
			pstmt.setString(4, ID);
			pstmt.setInt(5, number);
			pstmt.setString(6, location);
			pstmt.setString(7, owner);
			java.util.Date date=new SimpleDateFormat("yyyy-MM-dd").parse(borrowtime);
			java.sql.Date sqlDate=new java.sql.Date(date.getTime());
			pstmt.setDate(8, sqlDate);
			pstmt.setInt(9, lasttime);
			pstmt.executeUpdate();
			System.out.println("插入成功！");
		} catch (SQLException | ParseException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
	}
	
	public void CheckBook(String id) {
		String sql="SELECT * FROM book WHERE ID='"+id+"'";
		try {
			rs=stmt.executeQuery(sql);
			while(rs.next()) {
				String name=rs.getString(1);
				String writer=rs.getString(2);
				String publisher=rs.getString(3);
				String ID=rs.getString(4);
				int number=rs.getInt(5);
				String location=rs.getString(6);
				String owner=rs.getString(7);
				java.sql.Date dt=rs.getDate(8);
				String borrowtime=dt.toString();
				int lasttime=rs.getInt(9);
				System.out.println(name);
				System.out.println(writer);
				System.out.println(publisher);
				System.out.println(ID);
				System.out.println(number);
				System.out.println(location);
				System.out.println(owner);
				System.out.println(borrowtime);
				System.out.println(lasttime);
			}
//			return JSON.toJSONString(arg0);
			
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
	public void AddUser(String name,String id,String pwd,int alreadyborrowednum,int loan,String book1,String book2,String book3,String book4,String book5,String book6,String book7,String book8,String book9,String book10) {
		String sql="INSERT INTO USER(name,id,pwd,alreadyborrowednum,loan,book1,book2,book3,book4,book5,book6,book7,book8,book9,book10)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, id);
			pstmt.setString(3, pwd);
			pstmt.setInt(4, alreadyborrowednum);
			pstmt.setInt(5, loan);
			pstmt.setString(6, book1);
			pstmt.setString(7, book2);
			pstmt.setString(8, book3);
			pstmt.setString(9, book4);
			pstmt.setString(10, book5);
			pstmt.setString(11, book6);
			pstmt.setString(12, book7);
			pstmt.setString(13, book8);
			pstmt.setString(14, book9);
			pstmt.setString(15, book10);
			pstmt.executeUpdate();
			System.out.println("插入成功！");
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
	}

	public void CheckUser(String ID) {
		String sql="SELECT * FROM user WHERE id='"+ID+"'";
		try {
			rs=stmt.executeQuery(sql);
			while(rs.next()) {
				String name=rs.getString(1);
				String id=rs.getString(2);
				String pwd=rs.getString(3);
				int alreadyborrowednum=rs.getInt(4);
				int loan=rs.getInt(5);
				String book1=rs.getString(6);
				String book2=rs.getString(7);
				String book3=rs.getString(8);
				String book4=rs.getString(9);
				String book5=rs.getString(10);
				String book6=rs.getString(11);
				String book7=rs.getString(12);
				String book8=rs.getString(13);
				String book9=rs.getString(14);
				String book10=rs.getString(15);
				System.out.println(name);
				System.out.println(id);
				System.out.println(pwd);
				System.out.println(alreadyborrowednum);
				System.out.println(loan);
				System.out.println(book1);
				System.out.println(book2);
				System.out.println(book3);
				System.out.println(book4);
				System.out.println(book5);
				System.out.println(book6);
				System.out.println(book7);
				System.out.println(book8);
				System.out.println(book9);
				System.out.println(book10);
			}
//			return JSON.toJSONString(arg0);
			
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
	public boolean DeleteStudent(String ID) {
		String sql="DELETE FROM user WHERE ID='"+ID+"'";
		try {
			pstmt=con.prepareStatement(sql);
			int i=pstmt.executeUpdate();
			if(i>=0) 
				return true;
			else 
				return false;
		}catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean DeleteBook(String ID) {
		String sql="DELETE FROM book WHERE ID='"+ID+"'";
		try {
			pstmt=con.prepareStatement(sql);
			int i=pstmt.executeUpdate();
			if(i>=0) 
				return true;
			else 
				return false;
		}catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return false;
	}

	public void CheckBookNumber(String name) {
		String sql="SELECT * FROM book WHERE name='"+name+"'";
		try {
			rs=stmt.executeQuery(sql);
			int number=0;
			while(rs.next()) {
				number=rs.getInt(5);
			}
			System.out.println(name+"库存"+number+"本");
			//return JSON.toJSONString(arg0);
		}catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
	public boolean UpdateNumber(String name,int number) {
		String sql="UPDATE book SET number="+String.valueOf(number)+" WHERE name='"+name+"'";
		try {
			con.setAutoCommit(false);
			pstmt=con.prepareStatement(sql);
			int i=pstmt.executeUpdate();
			if(i>=0) {
				con.commit();
				return true;
			}else 
				return false;
		}catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return false;
	}

	
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		Database db=new Database();
//		db.AddBook("人间失格", "太宰治","不详", "66666", 5, "图书馆", "杜昕昱", "1999-01-09", 9);
//		db.CheckBook("66666");
//		db.AddUser("杜昕昱", "coolestdxy", "yzy", 1, 0, "66666", "", "", "", "", "", "", "", "", "");
//		db.CheckUser("coolestdxy");
		db.DeleteBook("66666");
		db.DeleteStudent("coolestdxy");
//		db.CheckBookNumber("人间失格");
//		db.UpdateNumber("人间失格", 3);
//		db.CheckBook("66666");
	}

}
