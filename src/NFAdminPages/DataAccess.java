package NFAdminPages;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Models.Show;


public class DataAccess {
	
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
	
	public static boolean AddShow(String type, String title, String director,String cast,String country,int releaseYear,String rating,String duration,String listedIn,String desc) throws ClassNotFoundException {
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
		} catch (Exception e) {
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
				allShows.add(new Show(rs.getString("type"),rs.getString("title"),rs.getString("director"),rs.getString("cast"),
						rs.getString("country"),rs.getInt("release_year"),rs.getString("rating"),rs.getString("duration"),
						rs.getString("listed_in"),rs.getString("description")));
			}
			conn.close();
		}catch(Exception e) {
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
			//director, cast, country falan için INSTR fonksiyonunu kullan.
			/*String search="SELECT * FROM tblShows WHERE type=? AND title=? AND director=? AND cast=? AND country=? AND release_year=? AND rating=? AND duration=?"
					+ " AND listed_in=? AND description=?";*/
			String search="SELECT * FROM tblShows WHERE type=? AND INSTR(title,?) AND INSTR(director,?) AND INSTR(cast,?) AND INSTR(country,?) AND release_year=? AND rating=? "
					+ "AND duration=?"
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
				resultList.add(new Show(rs.getString("type"), rs.getString("title"),rs.getString("director"),rs.getString("cast"),rs.getString("country"),rs.getInt("release_year"),
						rs.getString("rating"),rs.getString("duration"),rs.getString("listed_in"),rs.getString("description")));
			}
			conn.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return resultList;
	}
	

}

