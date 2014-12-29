import java.net.*;
import java.io.*;
import java.util.*;

public class ChatHandler extends Thread
{
	protected Socket s;
	File file;
	static String p="";
	protected DataInputStream i;
	protected DataOutputStream o;
	static FileOutputStream fos;
	

	String username = "";
	int id;
	public ChatHandler(Socket s, int id) throws IOException
	{
		this.s = s;
		this.id = id;
		i= new DataInputStream(new BufferedInputStream(s.getInputStream()));
		o = new DataOutputStream(new BufferedOutputStream(s.getOutputStream()));
	}
	
	static void path(String path) throws IOException
	{
		p=path;
		System.out.println(p);
	}

	protected static Vector handlers = new Vector();

	public void run()
	{
		try
		{
			handlers.add(this);  //Add current connection to dynamic array.
			while(true)
			{
				//Read message from client
				String msg = i.readUTF();
				
					msg = "<font color = \"blue\">" + msg + "</font>";
					sendmessage(msg, this.id);
				
				
			}
		}
		catch (IOException ex)
		{
			// ex.printStackTrace();
		}
		finally
		{
			handlers.remove(this);
			try
			{
				s.close();
			}
			catch (IOException ex)
			{
				ex.printStackTrace();
			}
		}
	}
	public static void sendmessage(String msg, int id)
	{
		synchronized(handlers)
		{
			Iterator e=handlers.iterator();
			while(e.hasNext())
			{
				ChatHandler c = (ChatHandler) e.next();
				try
				{
					synchronized(c.o)
					{
						// Check if client is not the sender
						if(c.id != id)
						{
							// Broadcast: Send to everyone
							c.o.writeUTF(msg);
							
							
						}
					}
					c.o.flush();
				}
				catch(IOException ex)
				{
					c.stop();
				}
			}
		}
	}
}