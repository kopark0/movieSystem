package server_client;


import java.io.IOException;

public class ServerMain {
	public static void main(String[] args) {
		try {
			//서버의 기능을 하는 클래스로 객체를 만들고
			MultiServer_Sub server = new MultiServer_Sub(7777);
			
			//서버 실행!
			server.runServer();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
