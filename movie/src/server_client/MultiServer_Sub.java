package server_client;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
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
				String id="";
				String pw="";
				String an_id="";
				String an_pw="";
				int cu_id = 0;
				String login = "";
				String signUp = "";
				while(true) { // 
					bw.write("=====환영합니다=====\n");
					bw.write("=====메 뉴=====\n"); 
					bw.write("1.회원가입 2.로그인 3.예매 4.조회 5.취소 6.나가기\n");
					bw.flush();
					String numM = br.readLine();
					
					switch (numM) {
					case "1":{
						Customer customer = new Customer();
						String idCheck = "";
						String user_id = "";
						bw.write("=====회원가입을 진행하겠습니다.=====\n");
						bw.write("아이디를 입력하세요.\n"); //아이디 중복 해야함
						bw.flush();
						List<Customer> ctList = null;
						try {
							while(!idCheck.equals("중복확인")) {
								user_id = br.readLine();
								ctList = dao.userFindByName(user_id);
								if(ctList.size() != 0) {
									for(Customer ct : ctList) {
										if(user_id.equals(ct.getUser_id())) {
											bw.write("아이디가 중복됐습니다.다시 입력해주세요.\n");
											bw.flush();
											break;
										} 
									}
								} else {
									bw.write("사용할 수 있는 아이디입니다.\n");
									bw.flush();
									System.out.println(user_id);
									idCheck ="중복확인";
								}
							}
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
						
							
							int rows = dao.userInsert(customer); // DB에 추가
							if(rows > 0) {
								System.out.println(rows + " 줄 추가완료");
								bw.write("회원가입 성공!\n");
								bw.flush();
								List<Customer> userLogList = new ArrayList<Customer>(); //
								userLogList = dao.userFindByName(user_id);
								for(Customer user : userLogList) {
									if(user_id.equals(user.getUser_id())) {
										String logComp = dao.userSingUpLog(new Customer(user.getCu_id(), user.getUser_id(), user.getUser_pw(), user.getName(), user.getPhone())); //회원가입 로그 기록
										System.out.println(logComp);
										signUp="회원가입성공";
										System.out.println(user_id+signUp);
									}
								}
							} else {
								bw.write("회원가입 실패!\n");
								bw.flush();
							}
						} catch (ClassNotFoundException e) {e.printStackTrace();} 
						catch (SQLException e) {e.printStackTrace();}
						break;
					}
					case "2" :{
						if(id.equals("")) { // 로그인이 확인
							bw.write("=====로그인을 진행하겠습니다.=====\n");
							bw.write("아이디를 입력해주세요.\n");
							bw.flush();
							id = br.readLine();
							System.out.println(id); //서버에 들어오는 값 확인 용
							
							bw.write("비밀번호를 입력해주세요.\n");
							bw.flush();
							pw = br.readLine();
							System.out.println(pw);
							
							bw.write("로그인 중...\n");
							bw.flush();
							
							try {
								List<Customer> userList = new ArrayList<Customer>();
								userList = dao.userFindByName(id);
								cu_id = 0;
								String user_name = "";
								String user_phone = "";
								for(Customer user : userList) { //DB에 있는 id와 pw를 DB에서 가져오기
									if(id.equals(user.getUser_id()) && pw.equals(user.getUser_pw())) {
										cu_id = user.getCu_id();
										an_id = user.getUser_id();
										an_pw = user.getUser_pw();
										user_name = user.getName();
										user_phone = user.getPhone();
									}
								}
								
								if(id.equals(an_id)) { //DB에서 가져온거 비교
									if(pw.equals(an_pw)) {
										bw.write("로그인 성공!\n");
										bw.flush();
										Customer customer = new Customer(cu_id, id, pw, user_name, user_phone);
										
										String logComp = dao.userLoginLog(customer); //로그인 로그 기록
										System.out.println(logComp);
										
										login="로그인성공";
										System.out.println(an_id+login);
									} else {
										bw.write("로그인 실패! 비밀번호를 확인해주세요.\n");
										bw.flush();
										id="";
									}
								} else {
									bw.write("로그인 실패! 아이디를 확인해주세요.\n");
									bw.flush();
									id="";
								}
							} catch (ClassNotFoundException e) {e.printStackTrace();}
							catch (SQLException e) {e.printStackTrace();}
						} else {// 로그인 조건
							bw.write("이미 로그인되어있습니다.\n");
							bw.write("다른 아이디로 로그인하시겠습니까? y or n \n");
							bw.flush();
							String an =br.readLine();
							if(an.equals("y")) {
								bw.write("로그아웃 되었습니다.\n");
								bw.flush();
								id="";
							}
						}
						break;
					}// 로그인 기능 끝
				
					case "3": {//예매
						if(!id.equals("")) {
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
								String seatCheck = "";
								String seatNum = "";
								List<SeatDto> stList = new ArrayList<SeatDto>();
								while(!seatCheck.equals("좌석중복확인")) {
									seatNum = br.readLine(); 
									stList = dao.selectSeatDetailCheck(Integer.valueOf(movieNum),seatNum);
									
									if(stList.size() != 0) {
										for(SeatDto st : stList) {
											if(seatNum.equals(st.getSeat_detail()) && Integer.valueOf(movieNum) == st.getMovie_id()) {
												bw.write("이미 예약된 좌석입니다! 다른 좌석을 입력해주세요.\n");
												bw.flush();
												break;
											} 
										}
									} else {
										bw.write("좌석 예약 성공\n");
										bw.flush();
										System.out.println(movieNum+seatNum);
										seatCheck ="좌석중복확인";
									}
								}
								
								SeatDto seat = new SeatDto(0, Integer.valueOf(movieNum), seatNum);
								int rowsS = dao.insertSeat(seat); // ?가 3개였음 수정 2개로 
								System.out.println(rowsS + " 좌석 성공"); //DB에 추가성공된거 확인용
								
								bw.write("▶ 결제 중...\n");
								bw.flush();
								PaymentDto payment = new PaymentDto(0, cu_id, 10000, LocalDateTime.now());
								int rowsP = dao.insertPayment(payment);
								System.out.println(rowsP + " 결제 성공"); //DB에 추가성공된거 확인용
								
								SeatDto st = dao.selectSeatDetail(Integer.valueOf(movieNum),seatNum);
								//LocalDateTime date = LocalDateTime.now();
								PaymentDto pt = dao.selectPayment(cu_id);  // 가져오는게 조금 불안함.
								TicketDto ticket = new TicketDto(0, st.getSeat_id() ,pt.getPayment_id(),Integer.valueOf(movieNum),1);
								int cnt = dao.insertTicket(ticket);
								if (cnt != 0) {
									bw.write("▶ 예매 성공\n");
									bw.flush();
								}
								
								TicketDto tt = dao.selectTicketPaymentId(pt.getPayment_id());
								bw.write("▶ 티켓번호 : "+tt.getTicket_id()+"\n");
								bw.flush();
								System.out.println(dao.reservationLog(pt, tt, st)); //예매로그.
								
							} catch (ClassNotFoundException e) {
								e.printStackTrace();
							} catch (SQLException e) {
								e.printStackTrace();
							}
							
						} else {
							bw.write("로그인을 먼저 해주세요.\n");
							bw.flush();
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
						bw.write("▶ 티켓 번호를 입력해주세요.\n");
						bw.flush();
						String ticketId = br.readLine();
						
						try {
							int rows = dao.cancelTicket(Integer.valueOf(ticketId));
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
						user.bw.newLine();
						user.bw.flush();
					}
				} catch (IOException e1) {e1.printStackTrace();}
			}
		}
	}
}
