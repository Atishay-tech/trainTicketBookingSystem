import java.sql.*;


public class Server {
	public static synchronized String execute(String query) {
		Connection c = connect_db();
		String result = "";

		try {
			if (query.startsWith("select")) {
				Statement s = c.createStatement();
				ResultSet r = s.executeQuery(query);
				ResultSetMetaData meta = r.getMetaData();

				while (r.next()) {
					for (int i=1; i <= meta.getColumnCount(); i++)
						result += r.getString(i) + ";";
					result += "\n";
				}
			}
			else {
				PreparedStatement st = c.prepareStatement(query);
            	st.executeUpdate();
			}
		}
		catch (Exception e){
			System.out.println("Error!! Invalid Query");
			System.out.println(e);
		}

		return result;
	}

	public static Connection connect_db()
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}
		catch (Exception e) {
			System.out.println(e);
			System.exit(1);
		}

		try {
			//Change username and password as per your database
			Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/railway_database?autoReconnect=true&useSSL=false","railway_manager","");

			return c;
		}
		catch (Exception e) {
			System.out.println("Could not connect to database");
			return null;
		}
	}

	public static void main(String args[])
	{
		System.out.println("Server started");

		while (true) {
			ServerSock server = new ServerSock(5000);
		}		
	}
}