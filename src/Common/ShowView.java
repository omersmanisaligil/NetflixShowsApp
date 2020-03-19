package Common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import Models.Show;
import NFAdminPages.DataAccess;

@ManagedBean
@ViewScoped
public class ShowView extends Show implements Serializable {
	List<Show> searchResults=new ArrayList<Show>();
	//daha sonra getShows'a eşitleyip başlangıçta listede bütün itemların olmasını sağlayabilirsin
	public List<Show> getShows(){			
		return DataAccess.allShows();
	}
	public String searchAction(){
		searchResults=DataAccess.searchShows(getType(),getTitle(),getDirector(),getCast(),getCountry(),getReleaseYear(),getRating(),getDuration(),getListedIn(),getDesc());
		return null;
	}
	public List<Show> getSearchResults(){
		return searchResults;
	}
	
	/*public static void main(String[] args) {
		System.out.println(DataAccess.allShows());
	}*/
}
