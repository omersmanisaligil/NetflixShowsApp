package Models;

public class Show {
	
	private int show_id,releaseYear;	
	private String type,title,director,cast,country,rating,duration,listedIn,desc;
	
	
	public Show(String type, String title, String director, String cast, String country, int releaseYear,
			String rating, String duration, String listedIn, String desc) {
		this.type=type; this.title=title; this.director=director; this.cast=cast; this.country=country;
		this.releaseYear=releaseYear; this.rating=rating; this.duration=duration; this.listedIn=listedIn; this.desc=desc;
	}
	public Show(int show_id,String type, String title, String director, String cast, String country, int releaseYear,
			String rating, String duration, String listedIn, String desc) {
		this.type=type; this.title=title; this.director=director; this.cast=cast; this.country=country;
		this.releaseYear=releaseYear; this.rating=rating; this.duration=duration; this.listedIn=listedIn; this.desc=desc; this.show_id=show_id;
	}
	public Show() {
		
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getCast() {
		return cast;
	}
	public void setCast(String cast) {
		this.cast = cast;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getReleaseYear() {
		return releaseYear;
	}
	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getListedIn() {
		return listedIn;
	}
	public void setListedIn(String listed_in) {
		this.listedIn = listed_in;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	@Override
	public String toString() {	
		return String.format("%d	%s	%s	%s	%s	%s	%d	%s	%s	%s	%s \n", 
				show_id,type,title,director,cast,country,releaseYear,rating,duration,listedIn,desc);
	}
	
	public void setShow_id(int show_id) {
		this.show_id = show_id;
	}
	public int getShow_id() {
		return show_id;
	}
}
