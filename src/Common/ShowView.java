package Common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

import Models.Show;
import NFAdminPages.DataAccess;

@ManagedBean
@ViewScoped
public class ShowView extends Show implements Serializable {
	private List<Show> allShows;
	private List<Show> searchResults=new ArrayList<Show>();
	private String key=".";
	//daha sonra getShows'a eşitleyip başlangıçta listede bütün itemların olmasını sağlayabilirsin
	
	public List<Show> getAllShows() {
		return allShows;
	}
	public void setAllShows(List<Show> allShows) {
		this.allShows = allShows;
	}
	public String getKey() {
		return key;
	}
	public String tableCall(String in) {
		setKey(in);
		return null;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public List<Show> getShows(){
		allShows=DataAccess.allShows(key);
		return allShows;
	}
	public String searchAction(){
		searchResults=DataAccess.searchShows(getType(),getTitle(),getDirector(),getCast(),getCountry(),getReleaseYear(),getRating(),getDuration(),getListedIn(),getDesc());
		return null;
	}
	public List<Show> getSearchResults(){
		return searchResults;
	}
	
	
	public void setSearchResults(List<Show> searchResults) {
		this.searchResults = searchResults;
	}
	
	
	/*public List<Show> getByLetter(String k){
		
	}*/
	
}
	
	/*public static void main(String[] args) {
		System.out.println(DataAccess.allShows());
	}*/

