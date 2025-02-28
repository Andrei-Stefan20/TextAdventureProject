/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package our.project.map.graphicinterface;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import our.project.map.utility.ChatClientHandler;



@SuppressWarnings("serial")
public class ChatUI extends JFrame{

	private JTextField item1;
	private JTextField username;
	private JTextField item3;
	private JTextArea display;
	private JTextArea UserNames;
	private Container c;
	ChatClientHandler ClientThread;
	private JLabel label;
	private JLabel label1;
	private final static String newline = "\n";

	public ChatUI(){
		super("Palm Pilot 1000");
		
		setLayout(new FlowLayout());
		
		display = new JTextArea(30, 30);
		JScrollPane scrollPane = new JScrollPane(display); 
		display.setEditable(false);
		
		add(scrollPane);
		
		item1 = new JTextField(20);
		item1.setEditable(false);
		add(item1);
                item1.setVisible(false);

                username = new JTextField(20);
                username.setEditable(true);
		add(username);

		label=new JLabel("Chat");
		label1=new JLabel("Nome");
		
		c = this.getContentPane(); 
		c.setLayout(new FlowLayout());
		c.add(username);
                c.add(label1);
		
		item3 = new JTextField(20);
		item3.setEditable(false);
                item3.setVisible(false);
			
		c = this.getContentPane(); 
		c.setLayout(new FlowLayout());
		c.add(label);
		c.add(item3);
		
                label.setVisible(false);
		thehandler handler = new thehandler();
		item1.addActionListener(handler);
		username.addActionListener(handler); 
		item3.addActionListener(handler);
                Socket s = null;
		try {
			s = new Socket("localhost",3333);
                        ClientThread = new ChatClientHandler(s,this);
                        ClientThread.start();
		} catch (UnknownHostException e) {
                    
			 JOptionPane.showMessageDialog (null, "Server non avviato riprovare dopo che il server è stato avviato", "Sever non avviato", JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException e) {
                    
			 JOptionPane.showMessageDialog (null, "Server non avviato riprovare dopo che il server è stato avviato", "Sever non avviato", JOptionPane.INFORMATION_MESSAGE);
		}
	}	

	private class thehandler implements ActionListener{
                @Override
		public void actionPerformed(ActionEvent event){

			String string = "";

			if(event.getSource() == item1)
			{
				string=String.format("%s", event.getActionCommand());
				String text= item1.getText();
				ClientThread.ClientOutServerIn(text);
				item1.setText("");
			}
			else if(event.getSource()==username) {
				string=String.format("%s", event.getActionCommand());
				if(string.matches("[0-9]*"))
				{
					JOptionPane.showMessageDialog(null,"formato non accettato");
					username.setText("");
				}
				else
				{
                                    if(ClientThread != null){
					ClientThread.setName(string);
					ClientThread.SetClient("channel0",string);
					JOptionPane.showMessageDialog(null, "Il nome è stato impostato: "+string);
					username.setText("");
                                        label1.setText("");
                                        label1.setVisible(false);
                                        label.setVisible(true);
					username.setEditable(false);
                                        username.setVisible(false);
					item1.setEditable(true);
					item3.setEditable(true);
					ClientThread.ClientOutServerIn("Nuovo utente");
                                        item1.setVisible(true);
                                    }else{
                                         JOptionPane.showMessageDialog (null, "Server non avviato chiudere la chat e riprovare dopo l'avvio del server", "Chat non conessa", JOptionPane.INFORMATION_MESSAGE);
                                    }
				}
			}
			else if(event.getSource()==item3) {
				string=String.format("%s", event.getActionCommand());
				if(string.matches("[a-z A-Z]"))
				{
					JOptionPane.showMessageDialog(null,"formato non accettato");
					item3.setText("");
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Canale impostato: canale"+string);
					item3.setText("");
					ClientThread.ClientOutServerIn("canale canale");
				}
			}
		}
	}
        
        /**
        *
        * Stanpa in finestra la stringa ricevuta
        *    
        */
	public void setDisplay(String x)
	{
		display.append(x + newline); 
	}
        
        
        /**
        *
        * Mostra a video diversi utenti presenti nel canale
        *    
        */
	public void setUserInChannel(String x)
	{
		UserNames.append(x + newline);
	}
        
        /**
        *
        * Pulisce il display
        *    
        */
	public void ClearDisplay()
	{
		UserNames.setText("");
	}
}