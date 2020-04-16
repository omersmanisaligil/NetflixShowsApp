package Common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.catalina.Context;

import Models.Show;
import NFAdminPages.DataAccess;

@ManagedBean
@ViewScoped
public class ShowView extends Show implements Serializable {
	private List<Show> allShows=DataAccess.allShows("");
	private List<Show> searchResults=new ArrayList<Show>();
	private String key="";
	private String category;
	private List<String> categories=new ArrayList<String>(Arrays.asList("Action & Adventure","Classic Movies","Comedies","Dramas","Music & Musicals","International Movies",
			"Cult Movies","Children & Family","Horror","Thrillers","Sci-Fi & Fantasy","Romantic","Documentaries","LBGTQ Movies","Stand-Up Comedy","Sports","Independent Movies","Faith & Spirituality"
			,"Anime Series","Kids' TV","Anime Features","Movies","Romantic TV Shows","TV Dramas","TV Comedies",
			"Crime TV Shows","TV Mysteries","Classic & Cult TV","TV Horror","Reality  TV","Stand-Up Comedy & Talk Shows","Docuseries"
			,"Spanish-Language TV Shows","Science & Nature TV","Korean TV Shows"));	
	private String message;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<Show> getAllShows() {
		return allShows;
	}
	public void setAllShows(List<Show> allShows) {
		this.allShows = allShows;
	}
	public String getKey() {
		return key;
	}
	public List<String> getCategories() {
		return categories;
	}
	public void setCategories(List<String> categories) {
		this.categories = categories;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String search) {
		this.category = search;
	}
	public String categoryCall(String cat) {
		setCategory(cat);
		setAllShows(DataAccess.categorized(cat));
		return null;
	}
	public String tableCall(String in) {
		setAllShows(DataAccess.allShows(in));
		addMessage(in);
		return null;
	}
	public void addMessage(String str) {
		FacesMessage message;
		if(str.length()>1) {
			message=new FacesMessage("Shows that include "+ str);
		}
		else{
			message=new FacesMessage("Shows starting with "+str);
		}
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	public String tableCall() {
		return null;
	}
	public void setKey(String key) {
		this.key = key;
	}
	/*public List<Show> getShows(){
		allShows=DataAccess.allShows(key);
		return allShows;
	}*/
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

