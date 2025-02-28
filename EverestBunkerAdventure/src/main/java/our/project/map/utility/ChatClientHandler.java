package our.project.map.utility;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import our.project.map.graphicinterface.ChatUI;

/**
 *
 * Gestisce la comunicazione tra server e client della chat
 * 
 * @author RES
 */
public class ChatClientHandler extends Thread {
	
	Socket s;
	DataInputStream din;
	DataOutputStream dout;
	boolean quite = false;
	public ClientData c;
	public ChatUI GUI;
	
        /**
         *
         * @param OurMultiSocket
         * @param gui
        */
        public ChatClientHandler(Socket OurMultiSocket, ChatUI gui)
        {
                s=OurMultiSocket;
                c=new ClientData();
                GUI=gui;
        }
        
        /**
        *
        * Metodo che invia al server
        * 
        * @param Text  stringa presa in input   
        */
	public void ClientOutServerIn(String Text)
	{
		
		try {
                    switch (Text) {
                        case "canale canale":
                            dout.writeUTF(Text);
                            dout.flush();
                            break;
                        case "nuovo utente":
                            dout.writeUTF(Text+":"+c.GetName()+"="+c.GetChannel());
                            dout.flush();
                            break;
                        default:
                            dout.writeUTF(c.GetChannel()+"="+this.getName()+": "+Text);
                            dout.flush();
                            break;
                    }
		} catch (IOException e) {
			
			e.printStackTrace();
		}	
	}
        
        /**
        *
        *  Settaggio del client
        * 
        * @param channel stringa del canale    
        * @param Name stringa del canale    
        */
	public void SetClient(String channel,String Name)
	{
		c.SetName(Name);
		c.SetChannel(channel);
	}
        
        
        /**
        *
        *  Resta in attesa fino quando arriva un messaggio
        *  Thraed sospeso se non ci sono dati in arrivo
        *    
        */
	public void run()
	{
		try {
			din = new DataInputStream(s.getInputStream());
			dout = new DataOutputStream(s.getOutputStream());
			while(!quite)
			{
				try {
					while(din.available()==0)
					{
						try {
							Thread.sleep(1);
						} catch (InterruptedException e) {
							
							e.printStackTrace();
						}
					}
					
                                        
					String reply=din.readUTF();
					String Chan=ExtractChannel(reply);
					String name=ExtractName(reply);
					if(name.equals(""))
					{
						System.out.print("Nuovo utente: "+reply+"\n");
						setChannel(reply);
					}
					else
					{
                                            PrintReply(Chan,reply);
					}
				} catch (IOException e) {
					
					e.printStackTrace();
					try {
						din.close();
						dout.close();
						s.close();
					} catch (IOException e1) {
                                            
						e1.printStackTrace();
					}
				}	
			}
		} catch (IOException e) {
			
			e.printStackTrace();
			try {
				din.close();
				dout.close();
				s.close();
			} catch (IOException x) {
				
				x.printStackTrace();
			}
		}
	}
        
         /**
        *
        *  Chiusura conessione client
        *    
        */
	public void CloseClient()
	{
		try {
			din.close();
			dout.close();
			s.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	public String ExtractName(String x)
	{
		String[]Y=x.split(":");
		return Y[0];
	}
	public String ExtractChannel(String X)
	{
		String[]Y=X.split("=");
		return Y[0];
	}
        
        /**
        *
        * Stampa della risposta
        *    
        */
	public void PrintReply(String Chan,String Rep)
	{
		if(c.GetChannel().equals(Chan))
		{
			String []Y=Rep.split("=");
			GUI.setDisplay(Y[1]);
		}
		
	}
        
        /**
        *
        * Impostazione del canale
        *    
        */
	public void setChannel(String x)
	{
		String []Y=x.split(":");
		String []Z=Y[1].split("=");
		GUI.setUserInChannel(Z[0]);
	}
        
        /**
        *
        * Impostazione canale cambiato
        *    
        */
	public void setChangedChannel()
	{
		GUI.setUserInChannel(c.GetName()+": "+c.GetChannel());
	}
        
        /**
        *
        * Classe che contien i dati 
        * della conessione del client
        *    
        */
	class ClientData
	{
		public String ClientName;
		public String channel;
		
		public void SetChannel(String Chan)
		{
			channel=Chan;
		}
		public void SetName(String name)
		{
			ClientName=name;
		}
		public String GetChannel()
		{
			return channel;
		}
		public String GetName()
		{
			return ClientName;
		}
	}
	
}