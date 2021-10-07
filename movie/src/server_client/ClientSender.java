package server_client;


import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientSender extends Thread {
	//소켓을 필드로 가지며
	private Socket socket;

	public ClientSender(Socket socket) {
		this.socket = socket;
	}
	
	@Override
	public void run() {
		//소켓으로 아웃풋 스트림 받아서
		try(BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))){
			//사용자로부터 입력을 받아
			Scanner scan = new Scanner(System.in);
			//서버로 보내는 기능
			while(true) {
				
				bw.write(scan.nextLine());
				bw.newLine();
				bw.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
}
