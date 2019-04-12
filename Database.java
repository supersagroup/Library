package bms;

import java.util.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class Database {
	final static String usr="root";
	final static String pwd="123456";
	public Statement stmt;
	private ResultSet rs;
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
			Connection con=DriverManager.getConnection(dbURL);
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
	
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		Database db=new Database();
	}

}
