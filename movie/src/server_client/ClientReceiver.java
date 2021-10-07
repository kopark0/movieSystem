package server_client;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientReceiver extends Thread {
	//소켓을 필드로 가지며
	private Socket socket;

	public ClientReceiver(Socket socket) {
		this.socket = socket;
	}
	
	@Override
	public void run() {
		//서버로 부터 온 메세지를
		try(BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()))){
			//콘솔에 출력하는 기능
			while(true) {
				System.out.println("섭값>> "+br.readLine());
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}
