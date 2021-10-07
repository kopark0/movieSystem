package server_client;


public class SeatDto {
	private int seat_id;
	private int movie_id;
	private String seat_detail;
	public SeatDto() {
		super();
	}
	public SeatDto(int seat_id, int movie_id, String seat_detail) {
		super();
		this.seat_id = seat_id;
		this.movie_id = movie_id;
		this.seat_detail = seat_detail;
	}
	public int getSeat_id() {
		return seat_id;
	}
	public void setSeat_id(int seat_id) {
		this.seat_id = seat_id;
	}
	public int getMovie_id() {
		return movie_id;
	}
	public void setMovie_id(int movie_id) {
		this.movie_id = movie_id;
	}
	public String getSeat_detail() {
		return seat_detail;
	}
	public void setSeat_detail(String seat_detail) {
		this.seat_detail = seat_detail;
	}
	@Override
	public String toString() {
		return "SeatDto [seat_id=" + seat_id + ", movie_id=" + movie_id + ", seat_detail=" + seat_detail + "]";
	}

}
