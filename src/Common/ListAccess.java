package Common;

import java.util.ArrayList;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import Models.*;
@ManagedBean
@ApplicationScoped
public class ListAccess {
	private ArrayList<String> ratings=ListContents.getRatings();
	private ArrayList<String> types=ListContents.getTypes();
	
	public ArrayList<String> getRatings() {
		return ratings;
	}
	public void setRatings(ArrayList<String> ratings) {
		this.ratings = ratings;
	}
	public ArrayList<String> getTypes() {
		return types;
	}
	public void setTypes(ArrayList<String> types) {
		this.types = types;
	}
	
	
}
