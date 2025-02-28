
package our.project.map.utility;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * Classe per la gestione del server Chat
 * 
 * @author RES
 */
public class ChatServer extends Thread {
	
	Socket s;
	DataInputStream din;
	DataOutputStream dout;
	ChatServerHandler ss;
	boolean quite=false;
	
        
        /**
        *
        *  Thraed di conessione al server
        * 
        * @param OurSocket
        * @param OurServer
        */
	public ChatServer(Socket OurSocket,ChatServerHandler OurServer)
	{
		super("MultiServerConnection");
		this.s = OurSocket;
		this.ss = OurServer;
	}
	
        /**
        *
        *  Metodo che permette di inviare a un singolo client 
        *  connesso di rispedire il messaggio
        * 
        * @param OutText stringa da inviare    
        */
	public void ServerOutClientIn(String OutText)
	{
		try {
			dout.writeUTF(OutText);
			dout.flush();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
        
        /**
        *
        *  Metodo che permette di inviare a tutti i client 
        *  connessi di rispedire il messaggio
        * 
        * @param OutText stringa da inviare    
        */
	public void BroadcastMessage(String OutText)
	{
		for(int i=0;i<ss.connectedClient.size();i++)
		{
			ChatServer Connection=ss.connectedClient.get(i);
			Connection.ServerOutClientIn(OutText);
		}
	}
	
        
        /**
        *
        *  Resta in attesa fino quando arriva un messaggio.
        *  Thraed sospeso se non ci sono dati in arrivo
        *    
        */
        @Override
	public void run()
	{
		try {
			din=new DataInputStream(s.getInputStream());
			dout=new DataOutputStream(s.getOutputStream());
			
			while(!quite)
			{
				while(din.available()==0)
				{
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						
						e.printStackTrace();
					}
				}
				String ComingText=din.readUTF();
				BroadcastMessage(ComingText);
			}
			din.close();
			dout.close();
			s.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
}