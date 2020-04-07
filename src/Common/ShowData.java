package Common;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

import Models.Show;
import NFAdminPages.DataAccess;

@ManagedBean
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
	public String editShow(int id) {
		Show s=DataAccess.findShow(id);
		this.setShow_id(s.getShow_id());
		this.setType(s.getType());
		this.setTitle(s.getTitle());
		this.setReleaseYear(s.getReleaseYear());
		this.setRating(s.getRating());
		this.setListedIn(s.getListedIn());
		this.setDuration(s.getDuration());
		this.setDirector(s.getDirector());
		this.setDesc(s.getDesc());
		this.setCountry(s.getCountry());
		this.setCast(s.getCast());
		
		
		return "adminEdit";
	}

	public String updateShow() {
		DataAccess.updateShow(getShow_id(),getType(), getTitle(), getDirector(), getCast(), getCountry(), getReleaseYear(), getRating(), getDuration(), getListedIn(), getDesc());
		
		return "viewAll";//şimdilik buraya geri döndürüyorum
	}
	
	public String showPage(int id) {
		Show s=DataAccess.findShow(id);
		this.setShow_id(s.getShow_id());
		this.setType(s.getType());
		this.setTitle(s.getTitle());
		this.setReleaseYear(s.getReleaseYear());
		this.setRating(s.getRating());
		this.setListedIn(s.getListedIn());
		this.setDuration(s.getDuration());
		this.setDirector(s.getDirector());
		this.setDesc(s.getDesc());
		this.setCountry(s.getCountry());
		this.setCast(s.getCast());
		return "show-page";
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}	
}
