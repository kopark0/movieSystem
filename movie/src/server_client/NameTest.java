package server_client;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NameTest {
	public static void main(String[] args) {
		MovieDao dao = MovieDaoImpl.getInstance();
		
		try {
			List<Customer> userList = new ArrayList<Customer>();
			
			userList = dao.userFindByName("아");
			
			for(Customer user : userList) {
				System.out.println(user);
			}
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
