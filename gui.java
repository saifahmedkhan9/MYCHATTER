import javax.swing.*;
import javax.swing.text.*;
import javax.swing.text.html.*;
import java.awt.event.*;
import java.awt.Color;
import java.util.*;
import java.net.*;
import java.io.*;
import java.lang.*;

public class gui 
{
	JFrame jfr;
	JTextField jtf;
	String text="";
	JEditorPane editor;
	String ch;
	JScrollPane scrollPane;
	JButton jb,jb1;
	HTMLDocument doc;
	// char clr[]=new char[4]; 
	Random rand = new Random();
	int i=rand.nextInt(4);
	private static JFileChooser fileChooser = new JFileChooser();
	
	public gui(final String username)
	{
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		}
		catch(Exception e){

		}
		jfr =new JFrame(username);
		jfr.setLayout(null);
		fileChooser.setMultiSelectionEnabled(false);
		fileChooser.setCurrentDirectory(new File("E:\\saif\\mychatter\\emicons"));
		jb =new JButton("Emoti");
		jb1=new JButton("Clear");
		jb.setBounds(125,330,60,30);
		jb1.setBounds(125,0,60,30);
		jfr.add(jb);
		jfr.add(jb1);
		jb.setVisible(true);
		jb1.setVisible(true);

		editor = new JEditorPane("text/html",text);
		editor.setEditable(false);
		if(i==0)
		editor.setBackground(Color.PINK);
		else if(i==1)
			editor.setBackground(Color.CYAN);
		else if(i==2)
			editor.setBackground(Color.GRAY);
		else if(i==3)
			editor.setBackground(Color.MAGENTA);


		jfr.add(editor);
		
		jtf=new JTextField();
		jtf.setBounds(0,332,124,30);
		jtf.setBackground(Color.WHITE);
		jfr.add(jtf);

		scrollPane = new JScrollPane(editor);
		scrollPane.setBounds(0,0,185,330);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jfr.add(scrollPane);
		jfr.setSize(200,400);
		jfr.setVisible(true);
		
		

		jb.addActionListener(new ActionListener() {
 	
 			public void actionPerformed(ActionEvent event) 
 			{
 				ch=jtf.getText();
            	String command = event.getActionCommand();
            	if (command.equals("Emoti")) 
            	{
					 fileChooser.showDialog(new JFrame(""),"OK");
					 String path=fileChooser.getSelectedFile().getAbsolutePath();
					  System.out.println(path);
					// File curFile = fileChooser.getSelectedFile();
					  File curFile=new File(path);
					  ch=ch+curFile.getName();
					  jtf.setText(ch);
					 System.out.println(curFile.length());
					 
					 try
					 {
					 	ChatHandler.path(path);
					 }
					 catch(Exception e)
					 {}
            	}
            }
        }); 

		jb1.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e)
            {
                //Clear the Editor Area when button is pressed
                editor.setText("");
                text="";
            }
        }); 
		jtf.addKeyListener(new KeyAdapter()
		{	
			public void keyPressed(KeyEvent e)
			{
				
				String ragex=":)";

				String substr="<img src ='C:/Users/SA/Downloads/smily.jpg' height=50px,weight=50px>";
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					ch=jtf.getText();
					if(!ch.equals(""))
					{
						try
						{
							editor.setContentType("text/html");
							HTMLEditorKit hek=new HTMLEditorKit();
							editor.setEditorKit(hek);
							doc=(HTMLDocument)editor.getDocument();
							// doc.insertString(0,"Test String",null);
							Element[] roots=doc.getRootElements();
							Element body=null;
							for(int i=0;i<roots[0].getElementCount();i++)
							{

								Element element=roots[0].getElement(i);
								if(element.getAttributes().getAttribute(StyleConstants.NameAttribute)==HTML.Tag.BODY)
								{
									body=element;
									break;
								}
							}
						}

						catch(Exception E){}
						
						if(ch.contains(":)")|| ch.contains("smile"))
						{

							//System.out.println("It contains Smily");
										
							try
							{
								ch=ch.replace("smile.JPG","<img src="+ClassLoader.getSystemResource("emicons/smile.jpg").toString()+" height=10px, width=10px>");	
								ch=ch.replace(":)","<img src="+ClassLoader.getSystemResource("emicons/smile.jpg").toString()+" height=10px, width=10px>");	
								
								// ch=ch+"<img src="+ClassLoader.getSystemResource("emicons/smile.jpg").toString()+" height=10px, width=10px>";
						    }
							
							catch(Exception e1){}	
						
						}
						else if (ch.contains(":p")|| ch.contains("playful"))
						{

							//System.out.println("It contains Smily");
							
							try
							{
								ch=ch.replace("playful.JPG","<img src="+ClassLoader.getSystemResource("emicons/playful.jpg").toString()+" height=10px, width=10px>");	
								ch=ch.replace(":p","<img src="+ClassLoader.getSystemResource("emicons/playful.jpg").toString()+" height=10px, width=10px>");	
								// ch=ch+"<img src="+ClassLoader.getSystemResource("emicons/playful.jpg").toString()+" height=20px, width=20px>";
							}
							
							catch(Exception e1){}	
						}

						else if(ch.contains(":D") || ch.contains("laugh1"))
						{
							try
							{
								ch=ch.replace("laugh1.JPG","<img src="+ClassLoader.getSystemResource("emicons/laugh1.jpg").toString()+" height=10px, width=10px>");	
								ch=ch.replace(":D","<img src="+ClassLoader.getSystemResource("emicons/laugh1.jpg").toString()+" height=10px, width=10px>");	
							}
							
							catch(Exception e1){}		
						}
						else if(ch.contains("hero"))
						{
							try
							{
								ch=ch.replace("hero.JPG","<img src="+ClassLoader.getSystemResource("emicons/hero.jpg").toString()+" height=10px, width=10px>");	
							}
							
							catch(Exception e1){}	
						}
						else if(ch.contains("joke"))
						{
							try
							{
								ch=ch.replace("joke.JPG","<img src="+ClassLoader.getSystemResource("emicons/joke.jpg").toString()+" height=10px, width=10px>");	
							}
							
							catch(Exception e1){}	
						}

						else if(ch.contains("cry"))
						{
							try
							{
								ch=ch.replace("cry.JPG","<img src="+ClassLoader.getSystemResource("emicons/cry.jpg").toString()+" height=10px, width=10px>");	
							}
							
							catch(Exception e1){}	
						}
						else if(ch.contains("kiss")|| ch.contains(":*"))
						{
							try
							{
								ch=ch.replace("kiss.JPG","<img src="+ClassLoader.getSystemResource("emicons/kiss.jpg").toString()+" height=10px, width=10px>");	
								ch=ch.replace(":*","<img src="+ClassLoader.getSystemResource("emicons/kiss.jpg").toString()+" height=10px, width=10px>");	
															
							}
							
							catch(Exception e1){}	
						}
						else if(ch.contains("love1"))
						{
							try
							{
								ch=ch.replace("love1.JPG","<img src="+ClassLoader.getSystemResource("emicons/love1.jpg").toString()+" height=10px, width=10px>");	
							}
							
							catch(Exception e1){}	
						}
						else if(ch.contains("pdhaku"))
						{
							try
							{
								ch=ch.replace("pdhaku.JPG","<img src="+ClassLoader.getSystemResource("emicons/pdhaku.jpg").toString()+" height=10px, width=10px>");	
							}
							
							catch(Exception e1){}	
						}
						else if(ch.contains("planner"))
						{
							try
							{
								ch=ch.replace("planner.JPG","<img src="+ClassLoader.getSystemResource("emicons/planner.jpg").toString()+" height=10px, width=10px>");	
							}
							
							catch(Exception e1){}	
						}
						else if(ch.contains("bigshock"))
						{
							try
							{
								ch=ch.replace("bigshock.JPG","<img src="+ClassLoader.getSystemResource("emicons/bigshock.jpg").toString()+" height=10px, width=10px>");	
							}
							
							catch(Exception e1){}	
						}
						else if(ch.contains("shock"))
						{
							try
							{
								ch=ch.replace("shock.JPG","<img src="+ClassLoader.getSystemResource("emicons/shock.jpg").toString()+" height=10px, width=10px>");	
							}
							
							catch(Exception e1){}	
						}
						
						else if(ch.contains("angry"))
						{
							try
							{
								ch=ch.replace("angry.JPG","<img src="+ClassLoader.getSystemResource("emicons/angry.jpg").toString()+" height=10px, width=10px>");	
							}
							
							catch(Exception e1){}	
						}
						Client.printmsg("<strong>" + username + ":</strong>"+ch);
						ch="<b>You</b> :" + ch;
						text=text + "<br>" + ch;
						editor.setText(text);
						jtf.setText(" ");	
						
					}
				}
			}
		});
		jfr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void printans(String msg_to_display)
	{
		text=text+ "<br>" + msg_to_display;
		editor.setText(text);

	}
}