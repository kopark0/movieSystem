package server_client;


public class TicketDto {
	private int ticket_id;
	private int seat_id;
	private int payment_id;
	private int movie_id;
	private int ticket_tot;
	
	public TicketDto() {
		super();
	}
	public TicketDto(int ticket_id, int seat_id, int payment_id, int movie_id, int ticket_tot) {
		super();
		this.ticket_id = ticket_id;
		this.seat_id = seat_id;
		this.payment_id = payment_id;
		this.movie_id = movie_id;
		this.ticket_tot = ticket_tot;
	}
	@Override
	public String toString() {
		return "TicketDto [ticket_id=" + ticket_id + ", seat_id=" + seat_id + ", payment_id=" + payment_id
				+ ", movie_id=" + movie_id + ", ticket_tot=" + ticket_tot + "]";
	}
	public int getTicket_id() {
		return ticket_id;
	}
	public void setTicket_id(int ticket_id) {
		this.ticket_id = ticket_id;
	}
	public int getSeat_id() {
		return seat_id;
	}
	public void setSeat_id(int seat_id) {
		this.seat_id = seat_id;
	}
	public int getPayment_id() {
		return payment_id;
	}
	public void setPayment_id(int payment_id) {
		this.payment_id = payment_id;
	}
	public int getMovie_id() {
		return movie_id;
	}
	public void setMovie_id(int movie_id) {
		this.movie_id = movie_id;
	}
	public int getTicket_tot() {
		return ticket_tot;
	}
	public void setTicket_tot(int ticket_tot) {
		this.ticket_tot = ticket_tot;
	}
}
