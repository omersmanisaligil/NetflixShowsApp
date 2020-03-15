package Models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListContents {
	private static ArrayList<String> types=new ArrayList(Arrays.asList("TV Show","Movie"));
	private static ArrayList<String> ratings=new ArrayList(Arrays.asList("TV-14","R","PG-13","TV-PG","G","PG","TV-MA","NR","TV-G","TV-Y7","UR","","TV-Y","TV-Y7-FV","NC-17"));
	
	public static ArrayList<String> getTypes() {
		return types;
	}
	public void setTypes(ArrayList<String> types) {
		this.types = types;
	}
	public static ArrayList<String> getRatings() {
		return ratings;
	}
	public void setRatings(ArrayList<String> ratings) {
		this.ratings = ratings;
	}
	
	
}
