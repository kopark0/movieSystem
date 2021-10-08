package server_client;


import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public interface MovieDao {

	//회원가입시 DB에 추가 
	public int userInsert(Customer customer) throws ClassNotFoundException, SQLException;
	
	//로그인시 ID 확인 
	public List<Customer> userFindByName(String user_id) throws ClassNotFoundException, SQLException;

	//회원가입 후 로그 추가 singUpLog.txt (파일 입출력)
	public String userSingUpLog(Customer customer) throws IOException; 
	
	//로그인 후 로그 추가 loginLog.txt (파일 입출력)
	public String userLoginLog(Customer customer) throws IOException; 
	
	//예매 및 결제 후 로그 추가 reservationLog.txt (파일 입출력)
	public String reservationLog(PaymentDto payment, TicketDto ticket, SeatDto seat) throws IOException;
	
	//티켓 취소
	public int cancelTicket(int ticketId) throws ClassNotFoundException, SQLException;
	
	//티켓 id 검색
	public TicketDto ticketFindById(int ticket_id) throws ClassNotFoundException, SQLException;

	//티켓 추가.
	public int insertTicket(TicketDto ticket) throws ClassNotFoundException, SQLException;
	
	//티켓 검색
	public TicketDto selectTicketPaymentId(int payment_id) throws ClassNotFoundException, SQLException;
	
	//영화 검색 (추가)
	public List<Movie2Dto> movieFindAll () throws ClassNotFoundException, SQLException;
	
	//결제 
	public int insertPayment(PaymentDto payment) throws ClassNotFoundException, SQLException;
	
	//결제 검색
	public PaymentDto selectPayment(int customer_id) throws ClassNotFoundException, SQLException; 
	
	//좌석
	public int insertSeat(SeatDto seat) throws ClassNotFoundException,SQLException;
	
	//좌석 검색
	public SeatDto selectSeatDetail(int movieId, String seatDetail) throws ClassNotFoundException, SQLException;
	
	//좌석 중복 검색
	public List<SeatDto> selectSeatDetailCheck(int movieId, String seatDetail) throws ClassNotFoundException, SQLException;
	
}
