import java.net.*;
import java.io.*;


public class ServerSock
{
	// constructor with port number
	public ServerSock(int port)
	{
		// starts server and waits for a connection and binds a given port at the server and listens for connections
		try (ServerSocket server = new ServerSocket(port)) {

			System.out.println("Waiting for a client ...");

			while (true) {
				Socket socket = server.accept();
				System.out.println("New Client accepted");

				new ServerThread(socket).start();
			}
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}
}


class ServerThread extends Thread {
    private Socket socket = null;
	private DataInputStream in = null;
	private DataOutputStream out = null;
 
    public ServerThread(Socket socket) {
        this.socket = socket;
    }

	public void run() {
		try {
			// initializes input and output stream from client
			in = new DataInputStream(
				new BufferedInputStream(socket.getInputStream()));
			out = new DataOutputStream(socket.getOutputStream());

			String line = "";

			// reads message from client until "Over" is sent
			while (!line.equals("OVER"))
			{
				try
				{
					try {
						line = in.readUTF();
					}
					catch (SocketException e) {
						System.out.println("Abrupt connection close!");
						break;
					}

					if (!line.equals("OVER")) {
						String response = Server.execute(line);
						out.writeUTF(response);
					}
				}
				catch(IOException i)
				{
					System.out.println(i);
				}
			}

			// close connection
			System.out.println("Closing connection");
			in.close();
			out.close();
			socket.close();
		}

		catch(IOException i)
		{
			System.out.println(i);
		}
	}
}