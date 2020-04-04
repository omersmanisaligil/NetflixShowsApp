package Common;

import java.io.Serializable;

import javax.annotation.ManagedBean;
import javax.faces.bean.SessionScoped;

import Models.User;
import NFAdminPages.DataAccess;

@ManagedBean
@SessionScoped
public class UserData extends User implements Serializable{
	
	public String btnSignUp() {
		return "signUp";
	}
	public String btnLogin() {
		return "login";
	}
	public String signUp(){
		if(DataAccess.AddUser(getUsername(),getPassword(),getEmail(),getProfilePic(),isAdmin())){
			return null; 
		}
		else {
			return "error";
		}
	}
	
	public String Login() {
		if(DataAccess.ValidateUser(getUsername(),getPassword()))
			return "index";
		else
			return "error";
	}
	
	
}
