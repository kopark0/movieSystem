package server_client;


import java.time.LocalDateTime;

public class PaymentDto {
	private int payment_id;
	private int customer_id;
	private int price ;
	private LocalDateTime payment_date;
	
	public PaymentDto() {
		super();
	}
	public PaymentDto(int payment_id, int customer_id, int price, LocalDateTime payment_date) {
		super();
		this.payment_id = payment_id;
		this.customer_id = customer_id;
		this.price = price;
		this.payment_date = payment_date;
	}
	public int getPayment_id() {
		return payment_id;
	}
	public void setPayment_id(int payment_id) {
		this.payment_id = payment_id;
	}
	public int getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public LocalDateTime getPayment_date() {
		return payment_date;
	}
	public void setPayment_date(LocalDateTime payment_date) {
		this.payment_date = payment_date;
	}
	@Override
	public String toString() {
		return "PaymentDto [payment_id=" + payment_id + ", customer_id=" + customer_id + ", price=" + price
				+ ", payment_date=" + payment_date + "]";
	}
	

}
