package bms;

import java.util.*;
import java.text.*;

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
				System.out.println("���ݿ����ӳɹ���");
			stmt=con.createStatement();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void Createtable() {
		String createSql=null;
		String checkTable=null;
		//����Ա��
		createSql="CREATE TABLE ADMIN"
				+ "(name varchar(10) not null,"
				+ "id varchar(10) not null,"
				+ "pwd varchar(10) not null)";
		try {
			checkTable="show tables like \"admin\"";
			ResultSet resultSet=stmt.executeQuery(checkTable);
			if(resultSet.next())
				System.out.println("admin���Ѿ�����!");
			else {
				if(stmt.executeUpdate(createSql)==0){
					this.AddAdmin("�����","dxy", "123456");
					System.out.println("admin�����ɹ�!");
				}
				else
					System.out.println("admin����ʧ��!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//ѧ����
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
				System.out.println("user���Ѿ�����!");
			else {
				if(stmt.executeUpdate(createSql)==0)
					System.out.println("user�����ɹ�!");
				else
					System.out.println("user����ʧ��!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//ͼ���
		createSql="CREATE TABLE BOOK"
				+ "(name varchar(20) not null,"
				+ "writer varchar(10) not null,"
				+ "publisher varchar(20) not null,"
				+ "ID varchar(20) not null,"
				+ "number int(10) not null,"
				+ "location varchar(100) not null,"
				+ "owner varchar(20),"
				+ "borrowtime date,"
				+ "lasttime int(10) not null)";
		try {
			checkTable="show tables like \"book\"";
			ResultSet resultSet=stmt.executeQuery(checkTable);
			if(resultSet.next())
				System.out.println("book���Ѿ�����!");
			else {
				if(stmt.executeUpdate(createSql)==0)
					System.out.println("book�����ɹ�!");
				else
					System.out.println("book����ʧ��!");
			}
		} catch (SQLException e) {
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
			System.out.println("����ɹ���");
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		
	}
	
	public void AddAdmin(String name,String id,String pwd) {
		String sql="INSERT INTO admin(name,id,pwd)values(?,?,?)";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, id);
			pstmt.setString(3, pwd);
			pstmt.executeUpdate();
			System.out.println("����ɹ���");
		}catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
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
			// TODO �Զ����ɵ� catch ��
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
			System.out.println(name+"���"+number+"��");
			//return JSON.toJSONString(arg0);
		}catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
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
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return false;
	}

	public String Borrow(String userID,String bookID) throws ParseException {
		String sql;
		String book;
		String bookName=null;
		String owner=null;
		try {
			int bookNumber=0;
			int i=0;
			int alreadynum=0;
			sql="SELECT * FROM book WHERE ID ='"+bookID+"'";
			rs=stmt.executeQuery(sql);
			while(rs.next())
			{
				bookName=rs.getString(1);
				bookNumber=rs.getInt(5);
				owner=rs.getString(7);
			}
			if(!owner.isEmpty())
				return JSON.toJSONString(false);
			if(bookNumber<=0)
				return JSON.toJSONString(false);
			sql="SELECT * FROM user WHERE ID='"+userID+"'";
			rs=stmt.executeQuery(sql);
			while(rs.next()) {
				alreadynum=rs.getInt(4);
				if(alreadynum==10)
					return JSON.toJSONString(false);
				for(i=0;i<10;++i)
				{
					book = rs.getString(i+6);
					if(book.isEmpty())
					{
						break;
					}
				}
			}
		sql ="UPDATE user SET book"+String.valueOf(i+1)+"= '"+bookID+"',alreadyborrowednum="+String.valueOf(alreadynum+1)+" WHERE ID= '"+userID+"'";
		
		con.setAutoCommit(false);
		pstmt=con.prepareStatement(sql);
		int j=pstmt.executeUpdate();
			if(j>=0) {
				con.commit();
			}else {
				return JSON.toJSONString(false);
			}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//�������ڸ�ʽ
		java.util.Date now = new java.util.Date();
		String time = sdf.format(now);
		java.util.Date date=new SimpleDateFormat("yyyy-MM-dd").parse(time);
		java.sql.Date sqlDate=new java.sql.Date(date.getTime());
		sql="UPDATE book SET owner= '"+userID+"',borrowtime= '"+sqlDate+"' WHERE ID= '"+bookID+"'";
		con.setAutoCommit(false);
		pstmt=con.prepareStatement(sql);
		j=pstmt.executeUpdate();
		if(j>=0) {
			con.commit();

		}else {
			return JSON.toJSONString(false);
		}
		
		sql="UPDATE book SET number="+String.valueOf(bookNumber-1)+" WHERE name= '"+bookName+"'";
		con.setAutoCommit(false);
		pstmt=con.prepareStatement(sql);
		j=pstmt.executeUpdate();
		if(j>=0) {
			con.commit();
			return JSON.toJSONString(true);//success
		}else 
			return JSON.toJSONString(false);//fail
		}catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return JSON.toJSONString(false);
	}


	public String Return(String bookID) {
		String sql;
		String ownerID = null;
		String bookName=null;
		int booknumber=0;
		int borrowNumber=0;
		String book=null;
		int i=0;
		try {
			sql="SELECT * FROM book WHERE ID= '"+bookID+"'";
			rs=stmt.executeQuery(sql);
			while(rs.next())
			{
				ownerID=rs.getString(7);
				booknumber=rs.getInt(5);
				bookName=rs.getString(1);
			}
			if(ownerID.isEmpty())
				return JSON.toJSONString(false);
			sql="SELECT * FROM user WHERE ID= '"+ownerID+"'";
			rs=stmt.executeQuery(sql);
			boolean ifFind=false;
			while(rs.next()){
				borrowNumber=rs.getInt(4);
				for(i=0;i<10;++i)
				{
					book=rs.getString(i+6);
					if(book.equals(bookID))
					{
						ifFind=true;
						break;
					}
				}
			}
			if(!ifFind)
				return JSON.toJSONString(false);
			sql="UPDATE user SET book"+String.valueOf(i+1)+"= '"+"', alreadyborrowednum= "+String.valueOf(borrowNumber-1)+" WHERE ID= '"+ownerID+"'";
			con.setAutoCommit(false);
			pstmt=con.prepareStatement(sql);
			int j=pstmt.executeUpdate();
			if(j>=0) {
				con.commit();
			}else 
				return JSON.toJSONString(false);//fail
			java.util.Date date=new SimpleDateFormat("yyyy-MM-dd").parse("2000-01-01");
			java.sql.Date sqlDate=new java.sql.Date(date.getTime());
			sql="UPDATE book SET owner= '"+"' ,borrowtime= '"+sqlDate+"' WHERE ID= '"+bookID+"'";
			con.setAutoCommit(false);
			pstmt=con.prepareStatement(sql);
			j=pstmt.executeUpdate();
			if(j>=0) {
				con.commit();
			}else 
				return JSON.toJSONString(false);//fail
			sql="UPDATE book SET number= "+String.valueOf(booknumber+1)+" WHERE name= '"+bookName+"'";
			con.setAutoCommit(false);
			pstmt=con.prepareStatement(sql);
			j=pstmt.executeUpdate();
			if(j>=0) {
				con.commit();
				return JSON.toJSONString(true);
			}else 
				return JSON.toJSONString(false);//fail
		}catch (SQLException  | ParseException e) {
			e.printStackTrace();
		}
		return JSON.toJSONString(false);
	}

	public String Login(String id, String pwd) {
		LoginResponse loginres = new LoginResponse(0,1);
		String sql = "SELECT pwd FROM admin WHERE id='" + id + "'";
		try {
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				String rt = rs.getString(1);
				if (rt.equals(pwd)) {
					System.out.println("�˺�������ȷ��");
					loginres.setConfirm(1);
					loginres.setIdentity(1);
					return JSON.toJSONString(loginres);
				} else
					System.out.println("�˺����벻��ȷ��");
			} else
				System.out.println("���û����ǹ���Ա��");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		sql = "SELECT pwd FROM user WHERE id='" + id + "'";
		try {
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				String rt = rs.getString(1);
				if (rt.equals(pwd)) {
					System.out.println("�˺�������ȷ��");
					loginres.setConfirm(1);
					loginres.setIdentity(0);
					return JSON.toJSONString(loginres);
				} else 
					System.out.println("�˺����벻��ȷ��");
			} else
				System.out.println("���û������ڣ�");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON.toJSONString(loginres);
	}
	
	public String CheckUser(String ID) {
		String sql="SELECT * FROM user WHERE id='"+ID+"'";
		try {
			rs=stmt.executeQuery(sql);
			if(rs.next()) {
				String name=rs.getString(1);
				String id=rs.getString(2);
				String pwd=rs.getString(3);
				int alreadyborrowednum=rs.getInt(4);
				
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
				
				Books book=new Books(book1,book2,book3,book4,book5,book6,book7,book8,book9,book10);
				String books=JSON.toJSONString(book);
				
				InitStuResponse initStuRes=new InitStuResponse(alreadyborrowednum,name,id,pwd,books);
				initStuRes.setLimited();
				
				return JSON.toJSONString(initStuRes);
			}	
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return null;
	}

	public String CheckBook(String id) {
		String sql="SELECT * FROM book WHERE ID='"+id+"'";
		try {
			rs=stmt.executeQuery(sql);
			if(rs.next()) {
				String name=rs.getString(1);
				String writer=rs.getString(2);
				String publisher=rs.getString(3);
				String ID=rs.getString(4);
				String location=rs.getString(6);
				String owner=rs.getString(7);
				java.sql.Date dt=rs.getDate(8);
				String borrowtime=dt.toString();
				
				InitBookResponse initBookRes=new InitBookResponse(name,writer,publisher,owner,borrowtime,ID,location);
				return JSON.toJSONString(initBookRes);
			}
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return null;
	}
	
	public String DeleteBook(String ID) {
		String sql;
		int number=0;
		String name=null;
		try {
			sql="SELECT number,name FROM book WHERE id='"+ID+"'";
			rs=stmt.executeQuery(sql);
			if(rs.next()) {
				number=rs.getInt(1);
				name=rs.getString(2);
			}
			else
				return JSON.toJSONString(false);
			sql="DELETE FROM book WHERE ID='"+ID+"'";
			pstmt=con.prepareStatement(sql);
			int i=pstmt.executeUpdate();
			if(i>0) {
				if(number>1) {
					sql="UPDATE book SET number="+String.valueOf(number-1)+" WHERE name='"+name+"'";
					con.setAutoCommit(false);
					pstmt=con.prepareStatement(sql);
					int j=pstmt.executeUpdate();
					if(j>0) {
						con.commit();
						return JSON.toJSONString(true);
					}
					else 
						return JSON.toJSONString(false);
				}
				else
					return JSON.toJSONString(true);
			}
			else 
				return JSON.toJSONString(false);
		}catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return JSON.toJSONString(false);
	}

	public String AddBook(String name,String writer,String publisher,String ID,String location,int lasttime) {
		String sql;
		int number;
		try {
			sql="SELECT number FROM book WHERE name='"+name+"'";
			rs=stmt.executeQuery(sql);
			if(rs.next()) {
				number=rs.getInt(1);
			}
			else
				number=0;
			
			sql="INSERT INTO BOOK(name,writer,publisher,ID,number,location,owner,borrowtime,lasttime)values(?,?,?,?,?,?,?,?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, writer);
			pstmt.setString(3, publisher);
			pstmt.setString(4, ID);
			pstmt.setInt(5, number);
			pstmt.setString(6, location);
			pstmt.setString(7, "");
			java.util.Date date=new SimpleDateFormat("yyyy-MM-dd").parse("2000-01-01");
			java.sql.Date sqlDate=new java.sql.Date(date.getTime());
			pstmt.setDate(8, sqlDate);
			pstmt.setInt(9, lasttime);
			if(pstmt.executeUpdate()>0) {
				sql="UPDATE book SET number="+String.valueOf(number+1)+" WHERE name='"+name+"'";
				con.setAutoCommit(false);
				pstmt=con.prepareStatement(sql);
				int i=pstmt.executeUpdate();
				if(i>0) {
					con.commit();
					System.out.println("����ɹ���");
					return JSON.toJSONString(true);
				}
				else 
					return JSON.toJSONString(false);
			}
			else {
				System.out.println("ͼ��"+ID+"����ʧ�ܣ�");
				return JSON.toJSONString(false);
			}
		} catch (SQLException | ParseException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return JSON.toJSONString(false);
		
	}
	
	public String CheckAdmin(String id) {
		String sql="SELECT * FROM admin WHERE ID='"+id+"'";
		try {
			rs=stmt.executeQuery(sql);
			if(rs.next()) {
				String name=rs.getString(1);
				String pwd=rs.getString(3);
				
				GetAttanResponse getAttanRes=new GetAttanResponse(name,pwd);
				return JSON.toJSONString(getAttanRes);
			}
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return null;
	}
	
	public String SearchBook(String name,String writer,String publisher)
	{
		boolean and=false;
		String sql="SELECT * FROM book WHERE ";
		if(!name.isEmpty())
		{
			sql=sql+"name= '"+name+"'";
			and =true;
		}
		if(!writer.isEmpty())
		{
			if(and)
				sql=sql+" AND writer= '"+writer+"'";
			else
			{
				sql=sql+"writer= '"+writer+"'";
				and =true;
			}
		}
		if(!publisher.isEmpty())
		{
			if(and)
				sql=sql+" AND publisher= '"+publisher+"'";
			else
			{
				sql=sql+"publisher= '"+publisher+"'";
				and=true;
			}
		}

		numbers Numbers=new numbers();
		try {
			rs=stmt.executeQuery(sql);
			while(rs.next()) {
				String id=rs.getString(4);
				if(!id.isEmpty())
					Numbers.Add(id);
			}
				return JSON.toJSONString(Numbers);
		}catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return null;
	}
	
	public String payFine(String userID) {
		String sql="UPDATE user SET loan = 0 WHERE ID = '"+userID+"'";
		try {
		con.setAutoCommit(false);
		pstmt=con.prepareStatement(sql);
		int j=pstmt.executeUpdate();
		if(j>=0) {
			con.commit();
			return JSON.toJSONString(true);
		}else {
			return JSON.toJSONString(false);
		}
		}catch (SQLException e) {
		// TODO �Զ����ɵ� catch ��
		e.printStackTrace();
		}
		return JSON.toJSONString(false);
		}
	
	public void add() {
		this.AddUser("�����", "coolestdxy", "yzy", 0, 0, "", "", "", "", "", "", "", "", "", "");
		this.AddUser("������", "yzy", "yzy", 0, 0, "", "", "", "", "", "", "", "", "", "");
		this.AddBook("�˼�ʧ��", "̫����","����", "66666","ͼ���",9);
		this.AddBook("�˼�ʧ��", "̫����","����", "66667","ͼ���",9);
		this.AddBook("��", "̫����","����", "55557","ͼ���", 9);
		this.AddBook("��", "̫����","����", "55558","ͼ���", 9);
		this.AddBook("�˼�ʧ��", "̫����","����", "66668","ͼ���",9);
		this.AddBook("�˼�ʧ��", "̫����","����", "66669","ͼ���",9);
		this.AddBook("��", "̫����","����", "55555","ͼ���", 9);
//		this.AddBook("��", "̫����","����", "55556","ͼ���", 9);
	}
	
	public void delete() {
		this.DeleteBook("66666");
		this.DeleteBook("66667");
		this.DeleteBook("66668");
		this.DeleteBook("66669");
		this.DeleteBook("55556");
		this.DeleteBook("55555");
		this.DeleteStudent("yzy");
		this.DeleteStudent("coolestdxy");
	}
	
	public static void main(String[] args) throws ParseException {
		// TODO �Զ����ɵķ������
		Database db=new Database();
//		db.delete();
//		db.add();
//		db.CheckBook("66666");
//		db.CheckUser("coolestdxy");
//		db.CheckBookNumber("�˼�ʧ��");
//		db.UpdateNumber("�˼�ʧ��", 3);
//		db.CheckBook("66666");
//		db.Borrow("coolestdxy", "66667");
//		db.Borrow("yzy", "55557");
//		db.Login("dxy", "123456");
//		db.Return("66666");
//		db.Return("55555");
		db.DeleteBook("55558");
	}

}
