public class Client {
	public ClientSock clientSock = null;
	public static void main(String args[])
	{
		ClientSock clientSock = new ClientSock("127.0.0.1", 5000);

		try {
			railway program = new railway(clientSock);
			program.run();
		}
		catch (Exception e) {
			System.out.println(e);
		}

		clientSock.send("OVER");
		clientSock.close();
	}
}
