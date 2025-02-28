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
 * Classe per la gestione dei thread. 
 * Utilizzati per la stampa di messaggi provenienti dalla Radio.
 * 
 * @author RES
 */
public class RadioThread extends Thread {
    private boolean doStop = false;
    
    private final List<String> messages = new ArrayList<String>(){{
        add("RADIO: NON... MI RICEVET... PRON... TO...");
        add("RADIO: generale, stiamo ricevendo segnale da...");
        add("RADIO: Riuscite a sen... do... tro...");
        add("RADIO: TI SEI ...TO?!");
        add("RADIO: PROV... SALVARE LA PAR... PER NON PE... PRO...SI");
        add("RADIO: IO, IO, TE L'HO DETTO CA...O!");
        add("RADIO: generale, l'intruso si muo... da...");
        add("RADIO: generale, qualcuno sta monopol... la c...");
        // aggiungere altri messaggi
    }};
    
    public RadioThread(){}
    
    /**
     *
     * Imposta 
     * 
     */
    public synchronized void doStop() {
        this.doStop = true;
    }
    
    private synchronized boolean keepRunning() {
        return this.doStop == false;
    }
    
    public String getRandomMessage() {
        int i = (int) (Math.random()*messages.size());
        return messages.get(i);
    }
    
    @Override
    public void run() {
        // stampare a video un messaggio casuale
        while(keepRunning()) {
            // il messaggio deve essere stampato nella textArea
            final ImageIcon radio = new ImageIcon("./asset/img/radio_icon.png");
            JOptionPane.showMessageDialog(null,"La radio sta ricevendo qualcosa....\n\n "+getRandomMessage(),"RADIO", JOptionPane.INFORMATION_MESSAGE, radio);       
            
            try {
                Thread.sleep(100L * 1000L);
            } catch (InterruptedException e) {
                JOptionPane.showMessageDialog (null, "Provato ad interrompere un thread in sleep", "Errore", JOptionPane.ERROR_MESSAGE);
            
            }
        }
    }
}
