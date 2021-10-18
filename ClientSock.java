// A Java program for a Client
import java.net.*;
import java.io.*;

public class ClientSock
{
	// initialize socket and input output streams
	private Socket socket = null;
	private DataOutputStream out = null;
	private DataInputStream in = null;

	public ClientSock()
	{
		socket = null;
		out	 = null;
		in  = null;
	}

	public ClientSock(String address, int port)
	{
		this();
		this.connect(address, port);
	}


	public void connect(String address, int port)
	{
		// establish a connection
		try {
			socket = new Socket(address, port);
			System.out.println("Connected");

			// initializes input and output streams to server
			out = new DataOutputStream(socket.getOutputStream());
			in = new DataInputStream(
				new BufferedInputStream(socket.getInputStream()));
		}
		catch(UnknownHostException u) {
			System.out.println(u);
		}
		catch(IOException i) {
			System.out.println(i);
		}
	}


	public void send(String message)
	{
		try {
			out = new DataOutputStream(socket.getOutputStream());
			out.writeUTF(message);
		}
		catch(Exception i) {
			System.out.println(i);
		}
	}

	public String read()
	{
		try {
			String response = "";
			response = in.readUTF();
			return response;
		}
		catch(IOException i) {
			System.out.println(i);
			return null;
		}
	}

	public String get_response(String query)
	{
		this.send(query);
		String response = this.read();
		return response;
	}

	public void close()
	{
		try {
			in.close();
			out.close();
			socket.close();
		}
		catch(IOException i) {
			System.out.println(i);
		}
	}
}
