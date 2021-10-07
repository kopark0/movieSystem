package server_client;


public class Movie2Dto {
	private int movie_id;
	private String title;
	private String category;
	private String actor;
	private String director;
	
	public Movie2Dto() {
		super();
	}
	public int getMovie_id() {
		return movie_id;
	}
	public void setMovie_id(int movie_id) {
		this.movie_id = movie_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getActor() {
		return actor;
	}
	public void setActor(String actor) {
		this.actor = actor;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	@Override
	public String toString() {
		return "Movie2Dto [movie_id=" + movie_id + ", title=" + title + ", category=" + category + ", actor=" + actor
				+ ", director=" + director + "]";
	}
	public Movie2Dto(int movie_id, String title, String category, String actor, String director) {
		super();
		this.movie_id = movie_id;
		this.title = title;
		this.category = category;
		this.actor = actor;
		this.director = director;
	}
	

}
