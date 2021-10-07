package server_client;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MultiServer_Sub {
	private ServerSocket serverSocket;
	private List<MultiServer_Sub.User> userList; //여기에 회원가입한 유저를 넣어야겠지?
	
	
	public MultiServer_Sub(int port) throws IOException {
		serverSocket = new ServerSocket(port);
		userList = new ArrayList<MultiServer_Sub.User>();
	}
	
	//서버의 기능 구현
	public void runServer() throws IOException {
		//접속을 지속적으로 대기하는 코드
		while(true) {
			System.out.println("접속 대기중...");
			Socket socket = serverSocket.accept();
			System.out.println("접속 : "+socket.getInetAddress()+"-"+socket.getPort());
			
			//접속된 소켓을 넣어서 user객체를 생성
			User user = new User(socket);
			
			//유저를 리스트로 담음
			userList.add(user);

			//스레드로 실행
			user.start();
		}		
	}
	//접속자에 대한 정보를 저장하는 클래스
	class User extends Thread { //접속을 하는 기능
		private String id;
		private String pw;
		private String an_id;
		private String an_pw;
		private Socket socket;
		private BufferedWriter bw;
		
		public User(Socket socket) throws IOException {
			this.socket = socket;
			bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			
		}
		@Override
		public void run() {
			//socket과 연결된 아이가 쓴 글을 읽을 수 있도록 기능을 구현한다.
			System.out.println("--- 서버 스레드 실행 ---");
			MovieDao dao = MovieDaoImpl.getInstance();
			
			try (BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()))){
				
				int cu_id = 0;
				String login = "";
				String signUp = "";
				while(true) { // if문에 문제일까
					bw.write("=====환영합니다=====\n");
					bw.write("=====메 뉴=====\n"); 
					bw.write("1.회원가입 2.로그인 3.예매 4.조회 5.취소 6.나가기\n");
					bw.flush();
					String numM = br.readLine();
					
					switch (numM) {
					case "1":{
						Customer customer = new Customer();
						bw.write("=====회원가입을 진행하겠습니다.=====\n");
						bw.write("아이디를 입력하세요.\n"); //아이디 중복 해야함
						bw.flush();
						String user_id = br.readLine();
						
						System.out.println(user_id);
						customer.setCu_id(0);
						customer.setUser_id(user_id);
						
						bw.write("비밀번호를 입력하세요.\n");
						bw.flush();
						String user_pw = br.readLine();
						System.out.println(user_pw);
						customer.setUser_pw(user_pw);
						
						bw.write("이름을 입력해주세요.\n");
						bw.flush();
						String name = br.readLine();
						System.out.println(name);
						customer.setName(name);
						
						bw.write("전화번호를 입력해주세요.\n");
						bw.flush();
						String phone = br.readLine();
						System.out.println(phone);
						customer.setPhone(phone);
						
						try {
							int rows = dao.userInsert(customer); // DB에 추가
							if(rows > 0) {
								System.out.println(rows + " 줄 추가완료");
								bw.write("회원가입 성공!\n");
								bw.flush();
								
								String logComp = dao.userSingUpLog(customer); 
								System.out.println(logComp);
								signUp="회원가입성공";
								System.out.println(user_id+signUp);
							} else {
								bw.write("회원가입 실패!\n");
								bw.flush();
							}
						} catch (ClassNotFoundException e) {e.printStackTrace();} 
						catch (SQLException e) {e.printStackTrace();}
						break;
					}
					case "2" :{	
						bw.write("=====로그인을 진행하겠습니다.=====\n");
						bw.write("아이디를 입력해주세요.\n");
						bw.flush();
						this.id = br.readLine();
						System.out.println(this.id); //서버에 들어오는 값 확인 용
						
						bw.write("비밀번호를 입력해주세요.\n");
						bw.flush();
						this.pw = br.readLine();
						System.out.println(this.pw);
						
						bw.write("로그인 중...\n");
						bw.flush();
						
						try {
							List<Customer> userList = new ArrayList<Customer>();
							userList = dao.userFindByName(this.id);
							cu_id = 0;
							String user_name = "";
							String user_phone = "";
							for(Customer user : userList) { //DB에 있는 id와 pw를 DB에서 가져오기
								if(this.id.equals(user.getUser_id()) && this.pw.equals(user.getUser_pw())) {
									cu_id = user.getCu_id();
									this.an_id = user.getUser_id();
									this.an_pw = user.getUser_pw();
									user_name = user.getName();
									user_phone = user.getPhone();
								}
							}
							
							if(this.id.equals(this.an_id)) { //DB에서 가져온거 비교
								if(this.pw.equals(this.an_pw)) {
									bw.write("로그인 성공!\n");
									bw.flush();
									Customer customer = new Customer(cu_id, this.id, this.pw, user_name, user_phone);
									
									String logComp = dao.userLoginLog(customer);
									System.out.println(logComp);
									
									login="로그인성공";
									System.out.println(this.an_id+login);
								} else {
									bw.write("로그인 실패! 비밀번호를 확인해주세요.\n");
									bw.flush();
								}
							} else {
								bw.write("로그인 실패! 아이디를 확인해주세요.\n");
								bw.flush();
							}
						} catch (ClassNotFoundException e) {e.printStackTrace();}
						catch (SQLException e) {e.printStackTrace();}	
						break;
					}// 로그인 기능 끝
				
					case "3": {//예매
						bw.write("=====영화 예매=====\n");
						bw.flush();
						try {
							List<Movie2Dto> movieList = dao.movieFindAll();
							for (Movie2Dto movie2Dto : movieList) {
								bw.write(movie2Dto.toString()+"\n");
								bw.flush();
							}
						
							bw.write("▶ 영화 번호를 입력해주세요.\n");
							bw.flush();
							String movieNum = br.readLine(); 
						
							bw.write("▶ A ~ E 좌석을 입력해주세요.\n"); 
							bw.flush();
							String seatNum = br.readLine(); 
							SeatDto seat = new SeatDto(0, Integer.valueOf(movieNum), seatNum);
						
							int rowsS = dao.insertSeat(seat); // ?가 3개였음 수정 2개로 
							System.out.println(rowsS + " 좌석 성공"); //DB에 추가성공된거 확인용
							
							bw.write("▶ 결제 중...\n");
							bw.flush();
							PaymentDto payment = new PaymentDto(0,cu_id,10000,LocalDateTime.now());
							int rowsP = dao.insertPayment(payment);
							System.out.println(rowsP + " 결제 성공"); //DB에 추가성공된거 확인용
						
							SeatDto st = dao.selectSeatDetail(seatNum);
							PaymentDto pt = dao.selectPayment(cu_id); 
							TicketDto ticket = new TicketDto(0, st.getSeat_id() ,pt.getPayment_id(),Integer.valueOf(movieNum),1);
							int cnt = dao.insertTicket(ticket);
							if (cnt != 0) {
								bw.write("▶ 예매 성공\n");
								bw.flush();
							}
							
							TicketDto tt = dao.selectTicketPaymentId(pt.getPayment_id());
							bw.write("▶ 티켓번호 : "+tt.getTicket_id()+"\n");
							bw.flush();
							} catch (ClassNotFoundException e) {
								e.printStackTrace();
							} catch (SQLException e) {
								e.printStackTrace();
							}
					break;
					}
					case "4": {
						bw.write("====예매 조회====\n");
						bw.write("▶ 티켓 번호를 입력해주세요.\n");
						bw.flush();
						String ti_num = br.readLine();
						
						try {
							TicketDto ticket = dao.ticketFindById(Integer.valueOf(ti_num));
							bw.write("====티켓 정보====\n");
							bw.write(ticket.toString()+"\n");
							bw.flush();
						} catch (ClassNotFoundException e) {
							e.printStackTrace();
						} catch (SQLException e) {
							e.printStackTrace();
						}
						break;
					}
					case "5": {
						bw.write("====예매 취소====\n");
						bw.write("▶ 회원 번호를 입력해주세요.\n");
						bw.flush();
						String cu_num = br.readLine();
						
						try {
							int rows = dao.cancel(Integer.valueOf(cu_num));
							bw.write(rows+" 개, 예매가 취소되었습니다.\n");
							bw.flush();	
							
						} catch (ClassNotFoundException e) {
							e.printStackTrace();
						} catch (SQLException e) {
							e.printStackTrace();
						}
						break;		
					} case "6":{
						bw.write("=====나가기=====\n");
						bw.flush();
						run(); // 시퀀스 재실행 
						break;
					} default : {
						bw.write("입력한 번호는 잘못된 번호입니다.\n");
						bw.flush();
						break;
					}
					}
					
					//사용자가 입력했을 때 메시지를 보냄
//					String msg = null;
//					while(true) {
//						//연결되어 있는 사람의 메세지를 읽고
//						msg = br.readLine();
//						if(msg == null) {
//							break;
//						}
//					}

				}//예매 끝
				
			} catch (Exception e) {
				//예외가 발생했다면 해당되는 소켓과 연결이 끊긴것임..
				userList.remove(this);
				try {
					for(User user : userList) {
						user.bw.write(id+"님이 방을 나갔습니다.");
						user.bw.newLine();
						user.bw.flush();
					}
				} catch (IOException e1) {e1.printStackTrace();}
			}
		}
	}
}
