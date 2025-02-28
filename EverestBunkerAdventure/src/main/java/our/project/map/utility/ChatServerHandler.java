

package our.project.map.utility;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * Classe che gestisce la conessione del server
 * 
 * @author RES
 */
public class ChatServerHandler {

	ServerSocket ss;
	boolean quite = false;
	ArrayList<ChatServer> connectedClient = new ArrayList<ChatServer>();
	
	public static void main(String[] args) {
		new ChatServerHandler();

	}
        
        /**
        *
        * Metodo per la ricezione e salvataggio di una nuova conessione
        *
        */
	public ChatServerHandler() {
		try {
			ss = new ServerSocket(3333);
			while(!quite)
			{
                                //accetto una conessione quando ne viene trovata 
				Socket s=ss.accept();
				ChatServer ourConnection = new ChatServer(s,this);
                                //Inizia il  Thread
				ourConnection.start();
                                //aggiungo la connessione alla lista di conessioni attive
				connectedClient.add(ourConnection);

			}
		} catch (IOException e) {
			
                    System.err.println("Errore nell'avvio del server Chat: \n"+e.getMessage());
                    System.exit(0);
		
		}
	}
}