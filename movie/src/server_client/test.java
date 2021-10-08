package server_client;

import java.sql.SQLException;

public class test {
	public static void main(String[] args) {
		MovieDao dao = MovieDaoImpl.getInstance();
		
		try {
			PaymentDto pt = dao.selectPayment(14); 
			
			System.out.println(pt);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
