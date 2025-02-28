/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package our.project.map.elements;

import java.util.Map;
import javax.swing.JOptionPane;
import our.project.map.engine.GameController;
import our.project.map.utility.AlarmThread;

/**
 *
 * Classe per la gestione degli elementi "fissi" all'interno del gioco
 * 
 * @author Gruppo RES
 */
public class FixedObject extends ObjectGame {
    
    public final String FINALE = "Inserisci la combinazione corretta e finalmente interrompi l'allarme. Ti rilassi "
            + "ma improvvisamente senti provenire dall'esterno il rumore di molti passi che si avvicinano.\n"
            + "Sono militari armati! In poco tempo vieni circondato e vieni catturato.\n"
            + "Un colpo ti stordisce, perdi conoscenza. Nel mentre vieni imprigionato, in un luogo a te sconosciuto.\n"
            + "I soccorritori sulla montagna hanno ritrovato la maggior parte dei tuoi compagni di scalata, e tu vieni"
            + " dato per disperso.";

    /**
     *
     * @param name
     * @param description
     * @param location
     * @param type
     * @param usable
     * @param pickable
     * @param openable
     * @param combinedObject
     * @param container
     * @param objectObtained
     * @param openableRoom
     * @param isTurnedOn
     * @param isOpened
     */
    public FixedObject(String name, String description, int location, TypeObject type, boolean usable, boolean pickable, 
            boolean openable, String combinedObject, String container, String objectObtained,int openableRoom,boolean isTurnedOn,boolean isOpened) {
        super(name, description, location, type, usable, pickable, openable, combinedObject, container, objectObtained,openableRoom,isTurnedOn,isOpened);
    }

    /**
     *
     * Utilizzo degli oggetti di tipo FixedObject
     * 
     * @param game
     * @return Messaggio
     */
    @Override
    public String use(GameController game){
        StringBuilder s = new StringBuilder();
        s.append("Provi ad usare: ");
        s.append(getName());
        s.append("\n\n");
        
        // gli oggetti fissi sono: interruttore, tastierino, pulsantiera
        if (getName().equals("interruttore")) {
            
            if (isTurnedOn()){
                return "Provi ad abbassare la leva per disattivare l'allarme ma non funziona\n\n";
            }
            
            // accende tutte le stanze, alcuni oggetti e fa partire il thread di allarme
            for(Map.Entry<Integer, RoomGame> e : game.getRooms().entrySet()){
                
                setTurnedOn(true);
                
                // la luce viene riattivata in tutte le stanze
                if (!e.getValue().isEnlightened()){
                    e.getValue().setEnlightened(true);
                }
                
            }
            
            // alcuni oggetti si riattivano
                game.getObjectGame().get("computer laboratorio").setTurnedOn(true);
                game.getObjectGame().get("computer controllo").setTurnedOn(true);
                game.getObjectGame().get("tastierino").setTurnedOn(true);
                game.getObjectGame().get("pulsantiera").setTurnedOn(true);
                
                s.append("Hai riattivato l'impianto elettrico del bunker, ma...\n");
                s.append("HAI FATTO SCATTARE UN ALLARME!\n\n");
                
                // start thread
                AlarmThread alarmThread = new AlarmThread();
                alarmThread.start();
        }
        
        if (getName().equals("tastierino")) {
            String validToken = "vega";
            String input = JOptionPane.showInputDialog("Digitare codice di sicurezza\n\nnota: la stella più vicina");
            
            if(input.toLowerCase().equals(validToken)){
                s.append("Valore  valido\n");
                 
                // sblocca porta
                game.getRooms().get(9).setOpened(true);
                 
                s.append("Dalla porta senti una vibrazione, hai sbloccato la porta");
                s.append("\n\n");
                 return s.toString();
            }
            else if (input.toLowerCase().equals("sole")){
                s.append("Effettivamente è vero, ma la porta non si apre");
            }
            else{
                s.append("Valore non valido\n");
                return s.toString();
            }
            

        }
        
        if (getName().equals("pulsantiera")) {
           
            String validToken = "morte nera";
            String input = JOptionPane.showInputDialog("Inserire codice di sblocco\n\nnota: la stella più pericolosa");
            
            if(input.toLowerCase().equals(validToken)){
                 s.append("Valore  valido\n");
                 s.append("\n");
                 s.append(FINALE);
                 s.append("#Finale");
                return s.toString();
            }
            else{
                s.append("Valore non valido\n");
                return s.toString();
            }
            
 
        } 
                
        return s.toString();
    }
    
    /**
     *
     * Tentativo di prendere un'oggetto fisso
     * 
     * @param game
     * @return Stringa
     */
    @Override
    public String pick(GameController game) {
        return "Cerchi di sdradicare l'oggetto ma fallisci";
    }
    

}
