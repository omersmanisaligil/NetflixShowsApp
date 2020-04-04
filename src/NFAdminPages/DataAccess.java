package NFAdminPages;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Models.Show;
import Models.User;


public class DataAccess {
	
	//bu metoddan connection döndürdüğümüz zaman "closed connection" hatası aldığımız için artık kullanmıyoruz
	private static Connection getConnection() {
		System.out.println("Loading driver...");
		
		try {
			Class.forName(DbSettings.driver);
		}catch(ClassNotFoundException e) {
		    throw new IllegalStateException("Cannot find the driver in the classpath!", e);
		}
		try(Connection connection=DriverManager.getConnection(DbSettings.url,DbSettings.username,DbSettings.password)) {
			System.out.println("success");
			return connection;
		}catch(Exception e) {
			System.err.println("fail");
		    throw new IllegalStateException("Cannot connect the database!", e);
		}
	}
	
	
	
	public static void main(String[] args) throws ClassNotFoundException {
		//test
	    //getConnection();
		System.out.println(AddShow("asassa","asdfasda","asdsa","a,a,a,a","sdsada",1,"as","as","asd","asd"));
	}
	
	public static boolean AddShow(String type, String title, String director,String cast,String country,int releaseYear,String rating,String duration,
			String listedIn,String desc) throws ClassNotFoundException {
		//Show s1=new Show(type,title,director,cast,country,releaseYear,rating,duration,listedIn,desc);
		try {
			Class.forName(DbSettings.driver);
			Connection conn=DriverManager.getConnection(DbSettings.url,DbSettings.username,DbSettings.password);
			String add="INSERT INTO tblShows(type,title,director,cast,country,release_year,rating,duration,listed_in,description) VALUES(?,?,?,?,?,?,?,?,?,?)";
			
			PreparedStatement statement=conn.prepareStatement(add);
			
			statement.setString(1, type);
			statement.setString(2, title);
			statement.setString(3, director);
			statement.setString(4, cast);
			statement.setString(5, country);
			statement.setInt(6, releaseYear);
			statement.setString(7, rating);
			statement.setString(8, duration);
			statement.setString(9, listedIn);
			statement.setString(10, desc);
			
			statement.executeUpdate();
			
			conn.close();
			
			return true;
		} catch (SQLException|ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	public static List<Show> allShows(){
		List<Show> allShows=new ArrayList<Show>();
		
		try {
			Class.forName(DbSettings.driver);
			Connection conn=DriverManager.getConnection(DbSettings.url,DbSettings.username,DbSettings.password);
			String sql="SELECT * FROM NFBase.tblShows";
;
			Statement statement=conn.createStatement();
			
			ResultSet rs=statement.executeQuery(sql);
			
			while(rs.next()) {
				allShows.add(new Show(rs.getInt("show_id"),rs.getString("type"),rs.getString("title"),rs.getString("director"),rs.getString("cast"),
						rs.getString("country"),rs.getInt("release_year"),rs.getString("rating"),rs.getString("duration"),
						rs.getString("listed_in"),rs.getString("description")));
			}
			conn.close();
		}catch(SQLException|ClassNotFoundException e) {
			e.printStackTrace();
		}
		return allShows;
	}


	public static List<Show> searchShows(String type, String title, String director, String cast, String country,
			int releaseYear, String rating, String duration, String listedIn, String desc) {
		List<Show> resultList=new ArrayList<Show>();
		try {
			Class.forName(DbSettings.driver);
			Connection conn=DriverManager.getConnection(DbSettings.url,DbSettings.username,DbSettings.password);
			String search="SELECT * FROM tblShows WHERE type=? AND INSTR(title,?) AND INSTR(director,?)"
					+ " AND INSTR(cast,?) AND INSTR(country,?) AND release_year=? AND rating=? "
					+ "AND INSTR(duration,?)"
					+"AND INSTR(listed_in,?) AND INSTR(description,?)";
			PreparedStatement statement=conn.prepareStatement(search);
			
			statement.setString(1, type); 
			statement.setString(2, title);
			statement.setString(3, director);
			statement.setString(4, cast);
			statement.setString(5, country);
			statement.setInt(6, releaseYear);
			statement.setString(7, rating);
			statement.setString(8, duration);
			statement.setString(9, listedIn);
			statement.setString(10, desc);

			ResultSet rs=statement.executeQuery();
			
			while(rs.next()) {//null değerler varsa null gösterecek, onu ayarlamak lazım
				resultList.add(new Show(rs.getInt("show_id"),rs.getString("type"), rs.getString("title"),rs.getString("director"),rs.getString("cast"),rs.getString("country"),
						rs.getInt("release_year"),
						rs.getString("rating"),rs.getString("duration"),rs.getString("listed_in"),rs.getString("description")));
			}
			conn.close();
			
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return resultList;
	}
	
	public static Show findShow(int id) {
		Show result=null;
		try {
			Class.forName(DbSettings.driver);
			Connection conn=DriverManager.getConnection(DbSettings.url,DbSettings.username,DbSettings.password);
			
			String find="SELECT * FROM tblShows WHERE show_id=?";
			PreparedStatement statement=conn.prepareStatement(find);
			
			statement.setInt(1, id);
			
			ResultSet rs=statement.executeQuery();
			rs.next();
			result=new Show(rs.getInt("show_id"),rs.getString("type"), rs.getString("title"),rs.getString("director"),rs.getString("cast"),rs.getString("country"),rs.getInt("release_year"),
					rs.getString("rating"),rs.getString("duration"),rs.getString("listed_in"),rs.getString("description"));
			
			conn.close();
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return result;
	}
	public static boolean updateShow(int show_id,String type, String title, String director, String cast, String country,
			int releaseYear, String rating, String duration, String listedIn, String desc) {
		try {
			Class.forName(DbSettings.driver);
			Connection conn=DriverManager.getConnection(DbSettings.url,DbSettings.username,DbSettings.password);
			
			//show_id çek işlemi ona göre yap
			String update="UPDATE tblShows SET type=?,title=?,director=?,cast=?,country=?,release_year=?,rating=?,duration=?,listed_in=?,description=? WHERE show_id=? ";
			PreparedStatement statement=conn.prepareStatement(update);
			
			statement.setString(1, type);
			statement.setString(2, title);
			statement.setString(3, director);
			statement.setString(4, cast);
			statement.setString(5, country);
			statement.setInt(6, releaseYear);
			statement.setString(7, rating);
			statement.setString(8, duration);
			statement.setString(9, listedIn);
			statement.setString(10, desc);
			statement.setInt(11, show_id);
			
			statement.executeUpdate();
			conn.close();
			return true;
			
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	public static boolean AddUser(String username,String password,String email,String profilePic,boolean isAdmin) {
		try {
			Class.forName(DbSettings.driver);
			Connection conn=DriverManager.getConnection(DbSettings.url,DbSettings.username,DbSettings.password);
			String add="INSERT INTO tblUsers(username,password,profilepic,email,isAdmin) VALUES(?,?,?,?,?,?)";
			
			PreparedStatement statement=conn.prepareStatement(add);
			
			statement.setString(1, username);
			statement.setString(2, password);
			statement.setString(3, profilePic);
			statement.setString(4, email);
			statement.setBoolean(5, isAdmin);
			
			statement.executeUpdate();
			conn.close();
			
			return true;
		}catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean ValidateUser(String username,String password) {
			try {
				Class.forName(DbSettings.driver);
				Connection conn=DriverManager.getConnection(DbSettings.url,DbSettings.username,DbSettings.password);
				
				String query="SELECT username,password FROM tblUsers WHERE username=? and password=?";
				PreparedStatement statement=conn.prepareStatement(query);
				
				statement.setString(1, username);
				statement.setString(2,password);
				
				statement.executeUpdate();
				conn.close();
			}catch(SQLException | ClassNotFoundException e) {
				e.printStackTrace();
				return false;
			}
		return true;
	}
	/*public static List<Show> searchByChar(String c){
		List<Show> resultList=new ArrayList<Show>();
		
		try {
			Class.forName(DbSettings.driver);
			Connection conn=DriverManager.getConnection(DbSettings.url,DbSettings.username,DbSettings.password);
			
			String search="SELECT * FROM tblShows "; //get shows ve bunu aynı metoda taşıyarak 
			PreparedStatement statement=conn.prepareStatement(search);
			
			statement.setString(1,c);
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return resultList;
	}*/

	
}

