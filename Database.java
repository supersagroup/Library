package bms;

import java.util.*;
import java.text.*;

import com.alibaba.fastjson.JSON;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Database {
	final static String usr = "root";
	final static String pwd = "123456";
	private Statement stmt;
	private ResultSet rs;
	private Connection con;
	private PreparedStatement pstmt;

	public Database() {
		Connectdb();
		Createtable();
	}

	public void Connectdb() {
		String JDriver = "com.mysql.cj.jdbc.Driver";
		String DB_URL = "jdbc:mysql://localhost:3306/bookbase";
		try {
			Class.forName(JDriver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			String dbURL = DB_URL + "?user=" + usr + "&password=" + pwd + "&serverTimezone=CTT";
			con = DriverManager.getConnection(dbURL);
			if (!con.isClosed())
				System.out.println("数据库连接成功！");
			stmt = con.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void Createtable() {
		String createSql = null;
		String checkTable = null;
		// 管理员表
		createSql = "CREATE TABLE ADMIN" + "(name varchar(10) not null," + "id varchar(10) not null,"
				+ "pwd varchar(10) not null)";
		try {
			checkTable = "show tables like \"admin\"";
			ResultSet resultSet = stmt.executeQuery(checkTable);
			if (resultSet.next())
				System.out.println("admin表已经存在!");
			else {
				if (stmt.executeUpdate(createSql) == 0) {
					this.AddAdmin("杜昕昱", "dxy", "123456");
					System.out.println("admin表创建成功!");
				} else
					System.out.println("admin表创建失败!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// 学生表
		createSql = "CREATE TABLE USER" + "(name varchar(10) not null," + "id varchar(10) not null,"
				+ "pwd varchar(10) not null," + "alreadyborrowednum int(10) not null," + "loan int(10) not null,"
				+ "book1 varchar(20)," + "book2 varchar(20)," + "book3 varchar(20)," + "book4 varchar(20),"
				+ "book5 varchar(20)," + "book6 varchar(20)," + "book7 varchar(20)," + "book8 varchar(20),"
				+ "book9 varchar(20)," + "book10 varchar(20))";
		try {
			checkTable = "show tables like \"user\"";
			ResultSet resultSet = stmt.executeQuery(checkTable);
			if (resultSet.next())
				System.out.println("user表已经存在!");
			else {
				if (stmt.executeUpdate(createSql) == 0)
					System.out.println("user表创建成功!");
				else
					System.out.println("user表创建失败!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// 图书表
		createSql = "CREATE TABLE BOOK" + "(name varchar(20) not null," + "writer varchar(10) not null,"
				+ "publisher varchar(20) not null," + "ID varchar(20) not null," + "location varchar(100) not null,"
				+ "owner varchar(20)," + "borrowtime date," + "lasttime int(10) not null)";
		try {
			checkTable = "show tables like \"book\"";
			ResultSet resultSet = stmt.executeQuery(checkTable);
			if (resultSet.next())
				System.out.println("book表已经存在!");
			else {
				if (stmt.executeUpdate(createSql) == 0)
					System.out.println("book表创建成功!");
				else
					System.out.println("book表创建失败!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void AddUser(String name, String id, String pwd, int alreadyborrowednum, int loan, String book1,
			String book2, String book3, String book4, String book5, String book6, String book7, String book8,
			String book9, String book10) {
		String sql = "INSERT INTO USER(name,id,pwd,alreadyborrowednum,loan,book1,book2,book3,book4,book5,book6,book7,book8,book9,book10)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			pstmt = con.prepareStatement(sql);
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
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

	}

	public void AddAdmin(String name, String id, String pwd) {
		String sql = "INSERT INTO admin(name,id,pwd)values(?,?,?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, id);
			pstmt.setString(3, pwd);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

	public String Borrow(String userID, String bookID) throws ParseException {
		String sql;
		String book;
		String owner = null;
		try {
			int i = 0;
			int alreadynum = 0;
			sql = "SELECT * FROM book WHERE ID ='" + bookID + "'";
			rs = stmt.executeQuery(sql);
			if (rs.next()) 
				owner = rs.getString(6);
			if (!owner.equals(""))
				return JSON.toJSONString(new ConfirmResponse(false));
			sql = "SELECT * FROM user WHERE ID='" + userID + "'";
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				alreadynum = rs.getInt(4);
				if (alreadynum == 10)
					return JSON.toJSONString(new ConfirmResponse(false));
				for (i = 0; i < 10; ++i) {
					book = rs.getString(i + 6);
					if (book.isEmpty()) {
						break;
					}
				}
			}
			sql = "UPDATE user SET book" + String.valueOf(i + 1) + "= '" + bookID + "',alreadyborrowednum="
					+ String.valueOf(alreadynum + 1) + " WHERE ID= '" + userID + "'";
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(sql);
			int j = pstmt.executeUpdate();
			if (j > 0) 
				con.commit();
			else 
				return JSON.toJSONString(new ConfirmResponse(false));

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
			java.util.Date now = new java.util.Date();
			String time = sdf.format(now);
			java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(time);
			java.sql.Date sqlDate = new java.sql.Date(date.getTime());
			sql = "UPDATE book SET owner= '" + userID + "',borrowtime= '" + sqlDate + "' WHERE ID= '" + bookID + "'";
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(sql);
			j = pstmt.executeUpdate();
			if (j > 0) 
				con.commit();
			else 
				return JSON.toJSONString(new ConfirmResponse(false));
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return JSON.toJSONString(new ConfirmResponse(false));
	}

	public String Return(String bookID) {
		String sql;
		String ownerID = null;
		int borrowNumber = 0;
		String book = null;
		int i = 0;
		try {
			sql = "SELECT * FROM book WHERE ID= '" + bookID + "'";
			rs = stmt.executeQuery(sql);
			if (rs.next()) 
				ownerID = rs.getString(6);
			if (ownerID.equals(""))
				return JSON.toJSONString(new ConfirmResponse(false));
			sql = "SELECT * FROM user WHERE ID= '" + ownerID + "'";
			rs = stmt.executeQuery(sql);
			boolean ifFind = false;
			if (rs.next()) {
				borrowNumber = rs.getInt(4);
				for (i = 0; i < 10; ++i) {
					book = rs.getString(i + 6);
					if (book.equals(bookID)) {
						ifFind = true;
						break;
					}
				}
			}
			if (!ifFind)
				return JSON.toJSONString(new ConfirmResponse(false));
			sql = "UPDATE user SET book" + String.valueOf(i + 1) + "= '" + "', alreadyborrowednum= "
					+ String.valueOf(borrowNumber - 1) + " WHERE ID= '" + ownerID + "'";
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(sql);
			int j = pstmt.executeUpdate();
			if (j > 0) 
				con.commit();
			else
				return JSON.toJSONString(new ConfirmResponse(false));
			java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse("2000-01-01");
			java.sql.Date sqlDate = new java.sql.Date(date.getTime());
			sql = "UPDATE book SET owner= '" + "' ,borrowtime= '" + sqlDate + "' WHERE ID= '" + bookID + "'";
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(sql);
			j = pstmt.executeUpdate();
			if (j > 0) 
				con.commit();
			else
				return JSON.toJSONString(new ConfirmResponse(false));
		} catch (SQLException | ParseException e) {
			e.printStackTrace();
		}
		return JSON.toJSONString(new ConfirmResponse(false));
	}

	public String Login(String id, String pwd) {
		LoginResponse loginres = new LoginResponse(0, 1);
		String sql = "SELECT pwd FROM admin WHERE id='" + id + "'";
		try {
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				String rt = rs.getString(1);
				if (rt.equals(pwd)) {
					loginres.setConfirm(1);
					loginres.setIdentity(1);
					return JSON.toJSONString(loginres);
				} 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		sql = "SELECT pwd FROM user WHERE id='" + id + "'";
		try {
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				String rt = rs.getString(1);
				if (rt.equals(pwd)) {
					loginres.setConfirm(1);
					loginres.setIdentity(0);
					return JSON.toJSONString(loginres);
				}
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON.toJSONString(loginres);
	}

	public String CheckUser(String ID) {
		String sql = "SELECT * FROM user WHERE id='" + ID + "'";
		try {
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				String name = rs.getString(1);
				String id = rs.getString(2);
				String pwd = rs.getString(3);
				int alreadyborrowednum = rs.getInt(4);
				String book1 = rs.getString(6);
				String book2 = rs.getString(7);
				String book3 = rs.getString(8);
				String book4 = rs.getString(9);
				String book5 = rs.getString(10);
				String book6 = rs.getString(11);
				String book7 = rs.getString(12);
				String book8 = rs.getString(13);
				String book9 = rs.getString(14);
				String book10 = rs.getString(15);

				Books book = new Books(book1, book2, book3, book4, book5, book6, book7, book8, book9, book10);
				String books = JSON.toJSONString(book);

				InitStuResponse initStuRes = new InitStuResponse(alreadyborrowednum, name, id, pwd, books);
				initStuRes.setLimited();

				return JSON.toJSONString(initStuRes);
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return JSON.toJSONString(new ConfirmResponse(false));
	}

	public String CheckBook(String id) {
		String sql = "SELECT * FROM book WHERE ID='" + id + "'";
		try {
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				String name = rs.getString(1);
				String writer = rs.getString(2);
				String publisher = rs.getString(3);
				String ID = rs.getString(4);
				String location = rs.getString(5);
				String owner = rs.getString(6);
				java.sql.Date dt = rs.getDate(7);
				String borrowtime = dt.toString();
				int lasttime = rs.getInt(8);

				InitBookResponse initBookRes = new InitBookResponse(name, writer, publisher, owner, borrowtime, ID,
						location, lasttime, true);
				return JSON.toJSONString(initBookRes);
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return JSON.toJSONString(new ConfirmResponse(false));
	}

	public String DeleteBook(String ID) {
		String sql;
		try {
			sql = "DELETE FROM book WHERE ID='" + ID + "'";
			pstmt = con.prepareStatement(sql);
			int i = pstmt.executeUpdate();
			if (i > 0) 
				return JSON.toJSONString(new ConfirmResponse(true));
			else
				return JSON.toJSONString(new ConfirmResponse(false));
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return JSON.toJSONString(new ConfirmResponse(false));
	}

	public String AddBook(String name, String writer, String publisher, String ID, String location, int lasttime) {
		String sql;
		try {

			sql = "INSERT INTO BOOK(name,writer,publisher,ID,location,owner,borrowtime,lasttime)values(?,?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, writer);
			pstmt.setString(3, publisher);
			pstmt.setString(4, ID);
			pstmt.setString(5, location);
			pstmt.setString(6, "");
			java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse("2000-01-01");
			java.sql.Date sqlDate = new java.sql.Date(date.getTime());
			pstmt.setDate(7, sqlDate);
			pstmt.setInt(8, lasttime);
			if (pstmt.executeUpdate() > 0) 
				return JSON.toJSONString(new ConfirmResponse(true));
			else 
				return JSON.toJSONString(new ConfirmResponse(false));
		} catch (SQLException | ParseException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return JSON.toJSONString(new ConfirmResponse(false));
	}

	public String CheckAdmin(String id) {
		String sql = "SELECT * FROM admin WHERE ID='" + id + "'";
		try {
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				String name = rs.getString(1);
				String pwd = rs.getString(3);

				GetAttanResponse getAttanRes = new GetAttanResponse(name, pwd);
				return JSON.toJSONString(getAttanRes);
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return null;
	}

	public String SearchBook(String name, String writer, String publisher) {
		boolean and = false;
		String sql = "SELECT * FROM book WHERE ";
		if (!name.isEmpty()) {
			sql = sql + "name= '" + name + "'";
			and = true;
		}
		if (!writer.isEmpty()) {
			if (and)
				sql = sql + " AND writer= '" + writer + "'";
			else {
				sql = sql + "writer= '" + writer + "'";
				and = true;
			}
		}
		if (!publisher.isEmpty()) {
			if (and)
				sql = sql + " AND publisher= '" + publisher + "'";
			else {
				sql = sql + "publisher= '" + publisher + "'";
				and = true;
			}
		}

		numbers Numbers = new numbers();
		try {
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String id = rs.getString(4);
				if (!id.isEmpty())
					Numbers.Add(id);
			}
			return JSON.toJSONString(Numbers);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return null;
	}

	public String ConfirmBorrow(String id) {
		String sql = "SELECT alreadyborrowednum FROM user WHERE ID='" + id + "'";
		ConfirmResponse b = new ConfirmResponse(false);
		try {
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				int alreadynum = rs.getInt(1);
				if (alreadynum <= 9) {
					b.setResult(true);
					return JSON.toJSONString(b);
				} else
					return JSON.toJSONString(b);
			} else
				return JSON.toJSONString(b);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return JSON.toJSONString(b);
	}

	public String payFine(String userID) {
		String sql = "UPDATE user SET loan = 0 WHERE ID = '" + userID + "'";
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(sql);
			int j = pstmt.executeUpdate();
			if (j > 0) {
				con.commit();
				return JSON.toJSONString(new ConfirmResponse(true));
			} else {
				return JSON.toJSONString(new ConfirmResponse(false));
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return JSON.toJSONString(new ConfirmResponse(false));
	}


	public static void main(String[] args) throws ParseException {
		// TODO 自动生成的方法存根
		Database db = new Database();
//		db.AddBook("我是猫", "夏目漱石", "凤凰文化出版社", "666", "1-2-3-4", 31);
//		db.AddBook("我是猫", "夏目漱石", "凤凰文化出版社", "555", "1-2-3-4", 31);
//		db.AddUser("钱鹏", "qp", "990612", 0, 0, "","","","","","","","","","");
//		db.Borrow("qp", "666");
//		db.Borrow("qp", "555");
//		db.Return("666");
		db.DeleteBook("666");
	}

}
