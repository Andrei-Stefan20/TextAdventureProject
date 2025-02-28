package our.project.map.utility;

import java.util.Scanner;
import our.project.map.graphicinterface.ChatUI;


import javax.swing.JFrame;

/**
 *
 * Gestione dell'interfaccia della chat
 * 
 * @author RES
 */
public class ChatClient{
	
	ChatClientHandler ClientThread;
        
       
        /**
        *
        *  Inizializzazione dell'interfaccia grafica della chat.
        * 
        */
	public ChatClient()
	{	
		ChatUI crape = new ChatUI();
		crape.setSize(500,600);
		crape.setVisible(true);
                crape.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}
        
        
        public void ListenForInput()
	{
		//Scanners are used to read input of user from conceal
		@SuppressWarnings("resource")
		Scanner console=new Scanner(System.in);
		while(true)
		{
			//waiting for a line form console
			while(!console.hasNextLine())//only run upon pressing run
			{//make sure not to leave thread awake, my cpu was overloaded XD
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			String input=console.nextLine();//this method will automatically get the new line
			if(input.toLowerCase().equals("quit"))
			{
				break;
			}
			if(input.toLowerCase().equals("change channel"))
			{
				input=console.nextLine();
				ClientThread.c.SetChannel(input);
			}
			else
			{
				ClientThread.ClientOutServerIn(input);
			}
		}
		ClientThread.CloseClient();
	}
}