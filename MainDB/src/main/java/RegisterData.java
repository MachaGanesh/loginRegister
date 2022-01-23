import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.security.auth.message.callback.PrivateKeyCallback.Request;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletResponse;

public class RegisterData {
	private String dbUrl = "jdbc:h2:tcp://localhost/~/test";
	private String dbUname = "sa";
	private String dbPassword = "";
	private String dbDriver = "org.h2.Driver";
	
	public void loadDriver(String dbDriver)
	{
		try {
			Class.forName(dbDriver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Connection getConnection()
	{
		Connection con = null;
		try {
			con = DriverManager.getConnection(dbUrl, dbUname, dbPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	public String insert(account acc) {
		loadDriver(dbDriver);
		Connection con=getConnection();
		String result = "Data entered successfully";
		String sql = "insert into account values(?,?,?,?,?,?,?)";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, acc.getFirstname());
			ps.setString(2, acc.getLastname());
			ps.setString(3, acc.getUsername());
			ps.setString(4, acc.getPassword());
			ps.setString(5, acc.getEmail());
			ps.setString(6, acc.getPhonenumber());
			ps.setString(7, acc.getCity());
			ps.executeUpdate();
			

}catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
	result = "Data not entered";
}
return result;

	}
	
}
