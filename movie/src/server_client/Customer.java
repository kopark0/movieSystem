package server_client;


public class Customer {
	
	private int cu_id;
	private String user_id;
	private String user_pw;
	private String name;
	private String phone;
	public Customer() {
		super();
	}
	public Customer(int cu_id, String user_id, String user_pw, String name, String phone) {
		super();
		this.cu_id = cu_id;
		this.user_id = user_id;
		this.user_pw = user_pw;
		this.name = name;
		this.phone = phone;
	}
	public int getCu_id() {
		return cu_id;
	}
	public void setCu_id(int cu_id) {
		this.cu_id = cu_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_pw() {
		return user_pw;
	}
	public void setUser_pw(String user_pw) {
		this.user_pw = user_pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "Customer [cu_id=" + cu_id + ", user_id=" + user_id + ", user_pw=" + user_pw + ", name=" + name
				+ ", phone=" + phone + "]";
	}
	
}
