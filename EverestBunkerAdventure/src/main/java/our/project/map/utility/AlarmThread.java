/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package our.project.map.utility;

import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * Classe che gestisce i messaggi che vengono emessi dalla radio durante il gioco.
 * 
 * @author RES
 */
public class AlarmThread extends Thread{
    private boolean doStop = false;
    
    private final List<String> messages = new ArrayList<String>(){{
        add("ALLARME: LANCIO MISSILE IMMINENTE");
        add("ALLARME: COORDINATE DI LANCIO: 41°27'42.7\"N 15°33'09.1\"E");
        add("ALLARME: PROCEDURA DI LANCIO IN CORSO");
        add("ALLARME: LANCIO IMMINENTE, SBLOCCARE LA PULSANTIERA DI LANCIO PER ANNULLARE");
        // aggiungere altri messaggi
    }};
    
    public AlarmThread(){}
    
    /**
     *
     *  Ferma il funzionamento della radio
     * 
     */
    public synchronized void doStop() {
        this.doStop = true;
    }
    
    /**
     * 
     * Controlla che la radio sia ancora in esecuzione
     *
     * @return true: se la radio è ancora in esecuzione
     *         false: altrimenti
     */
    private synchronized boolean keepRunning() {
        return this.doStop == false;
    }
    
    /**
     * 
     * Restituisce un messaggio casuale tra quelli pre-impostati
     * 
     * @return Stringa contente il messaggio da visualizzare
     */
    public String getRandomMessage() {
        int i = (int) (Math.random()*messages.size());
        return messages.get(i);
    }
    
    /**
     * 
     * Avvia il thread che gestisce i messaggi emessi dalla radio
     * 
     */
    @Override
    public void run() {
        // stampare a video un messaggio casuale
        while(keepRunning()) {
            // il messaggio deve essere stampato nella textArea
            
             final ImageIcon radio = new ImageIcon("./asset/img/alarm.png");
             JOptionPane.showMessageDialog(null,getRandomMessage(),"ALLARME",JOptionPane.INFORMATION_MESSAGE, radio);
            
            
            try {
                Thread.sleep(50L * 1000L);
            } catch (InterruptedException e) {
                JOptionPane.showMessageDialog (null,"Provato a interrompere un thread in sleep.", "Errore", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
