package Common;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;


import Models.User;
import NFAdminPages.DataAccess;

@ManagedBean
@SessionScoped
public class UserData extends User implements Serializable{
	private boolean loggedIn=true;
	//private boolean isAdmin=false;
	public String btnS() {
		return "signup";
	}
	public String btnL() {
		return "login";
	}
	public String signUp(){
		if(DataAccess.AddUser(getUsername(),getPassword(),getEmail(),isAdmin())){//profilepic, isAdmin
			return "index"; 
		}
		else {
			return "error";
		}
	}
	public String getUsername() {
		return super.getUsername();
	}
	
	/*public boolean isAdmin() {
		return isAdmin();
	}
	/*public void setAdmin(boolean admin) {
		this.isAdmin=admin;
	}*/
	
	public boolean isLoggedIn() {
		return loggedIn;
	}
	public void setLoggedIn(Boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
	public String toProfile(){
		return "user-profile";
	}
	public String validateUsernamePassword() {
		boolean valid=DataAccess.validate(getUsername(),getPassword());
		if (valid) {
			HttpSession session = Common.SessionUtils.getSession();
			session.setAttribute("username", getUsername());
			
			if(getUsername().equals("NFAdmin"))
				session.setAttribute("isAdmin", true);
			else
				session.setAttribute("isAdmin", false);
			
			setLoggedIn(false);
			return "index";
		} else {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Incorrect Username and Password",
							"Please enter correct username and Password"));
			return "login";
		}
	}

	public String logout() {
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		setLoggedIn(true);
		return "login";
	}
	public boolean listener(){
		HttpSession session=SessionUtils.getSession();
		return (boolean) session.getAttribute("isAdmin");
	}
	public void verifyLogin() {
		if(this.loggedIn)
			doRedirect("login.jsf");
	}
	private void doRedirect(String url) {
		try {
			FacesContext fc=FacesContext.getCurrentInstance();
			fc.getExternalContext().redirect(url);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}



