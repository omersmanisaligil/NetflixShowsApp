package Common;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

import Models.User;
import NFAdminPages.DataAccess;

@ManagedBean
@SessionScoped
public class UserData extends User implements Serializable{
	
	public String btnS() {
		return "signup";
	}
	public String btnL() {
		return "login";
	}
	public String signUp(){
		if(DataAccess.AddUser(getUsername(),getPassword(),getEmail())){//profilepic, isAdmin
			return "index"; 
		}
		else {
			return "error";
		}
	}
	
	public String Login() {
		if(DataAccess.LoginUser(getUsername(),getPassword()))
			return "index";
		else
			return "error";
	}
	
	
}
