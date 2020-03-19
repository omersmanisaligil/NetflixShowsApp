package Common;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import Models.Show;
import NFAdminPages.DataAccess;

@ManagedBean
@RequestScoped
public class ShowData extends Show {
	private String message;
	public String addShow() throws ClassNotFoundException {
		if(DataAccess.AddShow(getType(), getTitle(), getDirector(), getCast(), getCountry(), getReleaseYear(), getRating(), getDuration(), getListedIn(), getDesc())) {
			this.message="show added";
		}
		else {
			this.message="unable to add the show";
		}
		return null;	
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}	
}
