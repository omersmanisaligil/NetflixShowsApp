package Models;

public class User {
	private int user_id;
	private String username,profilePic,password,email;
	private boolean isAdmin;
	
	public User(String username,String password, String email) {
		this.username=username; this.password=password;	this.email=email;
	}
	public User(int user_id,String username,String password, String email) {
		this.user_id=user_id; this.username=username; this.password=password; this.email=email;
	}
	public User() {
		
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getProfilePic() {
		return profilePic;
	}
	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isAdmin() {
		return isAdmin;
	}

}
