import java.io.*;
import java.net.*;

public class Server extends Thread 
{
	ServerSocket ss;
	Socket s;
	int i=0;
	public void run()
	{
		try
		{

			System.out.println("Server Started");	
			ss=new ServerSocket(10);
			while(true)
			{
				s=ss.accept();
				System.out.println(s);
				
				ChatHandler clientHandler = new ChatHandler(s, i);
				clientHandler.start();
				System.out.println("\nClient connected: " + s);
				i++;
			}
			
			//serverChat();
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}

	public static void main(String args[])
	{
		try (Socket s = new Socket("127.0.0.1", 10))
		{
				//Server Running
		}
		catch(IOException e)
		{
			
			Server server=new Server();
			server.start();
		}
	}
}