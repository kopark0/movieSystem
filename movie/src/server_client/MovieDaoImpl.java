package server_client;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MovieDaoImpl implements MovieDao {
	private static MovieDaoImpl instance = new MovieDaoImpl();
	private MovieDaoImpl() {}
	public static MovieDaoImpl getInstance() {
		
		return instance;
	}

	@Override
	public int userInsert(Customer customer) throws ClassNotFoundException, SQLException {
		//customer_id, user_id, user_pw, name, phone)
		String sql = "insert into customer value(?,?,?,?,?)";
		
		try(Connection conn = DbConn.getConn();
			PreparedStatement pst = conn.prepareStatement(sql)){
			
			pst.setInt(1, 0);
			pst.setString(2, customer.getUser_id());
			pst.setString(3, customer.getUser_pw());
			pst.setString(4, customer.getName());
			pst.setString(5, customer.getPhone());
			
			return pst.executeUpdate();
		}
	}
	
	@Override
	public List<Customer> userFindByName(String user_id) throws ClassNotFoundException, SQLException {
		String sql = "select * from customer where user_id = ?";
		
		try(Connection conn = DbConn.getConn();
			PreparedStatement pst = conn.prepareStatement(sql)){
			
			pst.setString(1, user_id);
			
			try(ResultSet rs = pst.executeQuery()){
				List<Customer> userList = new ArrayList<Customer>();
				
				while(rs.next()) {
					userList.add(new Customer(rs.getInt("customer_id"), 
						rs.getString("user_id"), 
						rs.getString("user_pw"), 
						rs.getString("name"), 
						rs.getString("phone")));
				}
				
				return userList;
			}
		}
	}

	@Override
	public String userSingUpLog(Customer customer) throws IOException {
		LocalDateTime today = LocalDateTime.now();
		Date nowDate = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("HH시 mm분 ss초");
		
		File pathFolder = new File("src/log/"+today.format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
		if (!pathFolder.exists()) {
			pathFolder.mkdirs(); 
		}
		
		File logFile = new File(pathFolder, "singUpLog.txt"); 
		
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(logFile, true))){
			bw.append(customer.toString()+" "+sf.format(nowDate)+" \r");
			bw.flush();
			
			return "회원가입성공 로그 기록!";
		}
	}

	@Override
	public String userLoginLog(Customer customer) throws IOException {
		LocalDateTime today = LocalDateTime.now();
		Date nowDate = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("HH시 mm분 ss초");
		
		File pathFolder = new File("src/log/"+today.format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
		if (!pathFolder.exists()) {
			pathFolder.mkdirs();
		}
		
		File logFile = new File(pathFolder, "loginLog.txt"); 
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(logFile, true))){
			bw.append(customer.toString()+" "+sf.format(nowDate)+" \r");
			bw.flush();
			
			return "로그인성공 로그 기록!";
		}
	}

	@Override
	public String reservationLog(PaymentDto payment, TicketDto ticket, SeatDto seat) throws IOException {
		LocalDateTime today = LocalDateTime.now();
		Date nowDate = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("HH시 mm분 ss초");
		
		File pathFolder = new File("src/log/"+today.format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
		if (!pathFolder.exists()) {
			pathFolder.mkdirs();
		}
		
		File logFile = new File(pathFolder, "reservationLog.txt"); 
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(logFile, true))){
			bw.append(payment.toString()+" "+ticket.toString()+" "+seat.toString()+" "+sf.format(nowDate)+" \r");
			bw.flush();
			
			return "예매성공 로그 기록!";
		}
		
	}
	
	@Override
	public int cancelTicket(int ticketId) throws ClassNotFoundException,SQLException{
		String sql = "delete from ticket where ticket_id = ?";
		
		try(Connection conn = DbConn.getConn();
			PreparedStatement pst = conn.prepareStatement(sql)){
			pst.setInt(1, ticketId);
			
			return pst.executeUpdate();
		}
	}
	
	public TicketDto ticketFindById(int ticket_id) throws ClassNotFoundException,SQLException {
		String sql = "select * from ticket where ticket_id = ?";
		
		try(Connection conn = DbConn.getConn();
			PreparedStatement pst = conn.prepareStatement(sql)){
			
			pst.setInt(1, ticket_id);
			
			try(ResultSet rs = pst.executeQuery()){
				
				TicketDto ticket = null;
				if(rs.next()) {
					ticket = new TicketDto(ticket_id,
							rs.getInt("seat_id"),
							rs.getInt("payment_id"),
							rs.getInt("movie_id"),
							rs.getInt("ticket_tot"));
				}
				return ticket;
			}
		}
	}

	@Override
	public int insertTicket(TicketDto ticket) throws ClassNotFoundException,SQLException {
	     String sql = "insert into ticket value(?,?,?,?,?)";
	     
	     try(Connection conn = DbConn.getConn();
	    	PreparedStatement pst = conn.prepareStatement(sql)){
	    	 
	    	 pst.setInt(1, ticket.getTicket_id());
	    	 pst.setInt(2, ticket.getSeat_id());
	    	 pst.setInt(3, ticket.getPayment_id());
	    	 pst.setInt(4, ticket.getMovie_id());
	    	 pst.setInt(5, ticket.getTicket_tot());
	    	 
	    	 return pst.executeUpdate();
	     }
	}
	
	@Override
	public TicketDto selectTicketPaymentId(int payment_id) throws ClassNotFoundException, SQLException {
		String sql = "select * from ticket where payment_id = ?";
		try(Connection conn = DbConn.getConn();
			PreparedStatement pst = conn.prepareStatement(sql)){
			
			pst.setInt(1, payment_id);
			
			try(ResultSet rs = pst.executeQuery()){
				TicketDto ticket = null;
				
				if(rs.next()) {
					ticket = new TicketDto(rs.getInt("ticket_id"), 
							rs.getInt("seat_id"),
							rs.getInt("payment_id"),
							rs.getInt("movie_id"),
							rs.getInt("ticket_tot"));
				}
				return ticket;
			}
		}
	}
	
	@Override
	public List<Movie2Dto> movieFindAll() throws ClassNotFoundException,SQLException{
		String sql = "select * from movie";
		
		try(Connection conn = DbConn.getConn();
				PreparedStatement pst = conn.prepareStatement(sql);
				ResultSet rs = pst.executeQuery()){
			List<Movie2Dto> movieList = new ArrayList<Movie2Dto>();
			
			while(rs.next()) {
				movieList.add(new Movie2Dto(rs.getInt("movie_id"),
						rs.getString("title"),
						rs.getString("category"),
						rs.getString("actor"),
						rs.getString("director")));	
			}
			return movieList;
		}
	}
	
	@Override
	public int insertPayment(PaymentDto payment) throws ClassNotFoundException,SQLException {
		String sql = "insert into payment value(?,?,?,?)";
		
		try(Connection conn = DbConn.getConn();
			PreparedStatement pst = conn.prepareStatement(sql)){
			
			pst.setInt(1, payment.getPayment_id());
			pst.setInt(2, payment.getCustomer_id());				
			pst.setInt(3, payment.getPrice());
			pst.setString(4, payment.getPayment_date().toString());
			
			return pst.executeUpdate();
		}		
	}
	
	@Override
	public PaymentDto selectPayment(int customer_id) throws ClassNotFoundException, SQLException{
		String sql = "select * from payment where customer_id = ? order by payment_id desc limit 1"; //보완이 필요함.
		
		try(Connection conn = DbConn.getConn();
			PreparedStatement pst = conn.prepareStatement(sql)){
			
			pst.setInt(1, customer_id);
			
			try(ResultSet rs = pst.executeQuery()){
				PaymentDto payment = null;
				
				if(rs.next()) {
					payment = new PaymentDto(rs.getInt("payment_id"),
							rs.getInt("customer_id"),
							rs.getInt("price"),
							rs.getTimestamp("payment_date").toLocalDateTime());
				}
				return payment;
			}
		}
	}
	
	@Override
	public int insertSeat(SeatDto seat) throws ClassNotFoundException, SQLException {
		String sql = "insert into seat value (?,?,?)";
		
		try(Connection conn = DbConn.getConn();
			PreparedStatement pst = conn.prepareStatement(sql)){
			
			pst.setInt(1, seat.getSeat_id());
			pst.setInt(2, seat.getMovie_id());
			pst.setString(3, seat.getSeat_detail());
			
			return pst.executeUpdate();
		}
	}

	@Override
	public SeatDto selectSeatDetail(int movieId, String seatDetail) throws ClassNotFoundException, SQLException {
		String sql = "select * from seat where movie_id = ? and seat_detail = ?";
		
		try(Connection conn = DbConn.getConn();
			PreparedStatement pst = conn.prepareStatement(sql)){
			
			pst.setInt(1, movieId);
			pst.setString(2, seatDetail);
			
			try(ResultSet rs = pst.executeQuery()){
				SeatDto seat = null;
				
				if(rs.next()) {
					seat = new SeatDto(rs.getInt("seat_id"),
							rs.getInt("movie_id"),
							rs.getString("seat_detail"));
				}
				return seat;
			}
		}
	}
	
	@Override
	public List<SeatDto> selectSeatDetailCheck(int movieId, String seatDetail) throws ClassNotFoundException, SQLException{
		String sql = "select * from seat where movie_id = ? and seat_detail = ?";
		
		try(Connection conn = DbConn.getConn();
			PreparedStatement pst = conn.prepareStatement(sql)){
			
			pst.setInt(1, movieId);
			pst.setString(2, seatDetail);
			
			List<SeatDto> seatList = new ArrayList<SeatDto>();
			try(ResultSet rs = pst.executeQuery()){
				
				while(rs.next()) {
					seatList.add(new SeatDto(rs.getInt("seat_id"),
							rs.getInt("movie_id"),
							rs.getString("seat_detail")));
				}
				return seatList;
			}
		}
	}
}
