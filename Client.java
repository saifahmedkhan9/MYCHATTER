import javax.swing.JOptionPane;
import java.net.*;
import java.util.*;
import java.io.*;

class Client extends Thread
{

	Socket s;
	static DataInputStream din;
	static DataOutputStream dout;
	public final static int FILE_SIZE = 6022386;
	public void run()
	{
		try
		{
			s=new Socket("localhost",10);
			System.out.println(s);
			din=new DataInputStream(s.getInputStream());
			dout=new DataOutputStream(s.getOutputStream());
			String name = JOptionPane.showInputDialog("Please enter your name:");
			JOptionPane.showMessageDialog(null,"Welcome to chat room "+name+" !","Chat room",JOptionPane.PLAIN_MESSAGE);
			
			gui g = new gui(name);
		
			while(true)
			{
				String msgfrmserver=din.readUTF();
				g.printans(msgfrmserver);
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

/*	public void clientchat() throws IOException
	{
		
		String s1;
		do
		{	
			BufferedReader b=new BufferedReader(new InputStreamReader(System.in));
			s1=b.readLine();
			dout.writeUTF(s1);
			dout.flush();
			System.out.println("Me : "+s1);
			System.out.println("Server Message :"+ din.readUTF());
			
		}while(!s1.equals("stop"));
	}*/

	static void printmsg(String msg1)
	{
		try
		{
			dout.writeUTF(msg1);
		}
		catch(Exception e)
		{

		}
	}

	public static void main(String args[])
	{
		Client client = new Client();
		client.start();
	}
}