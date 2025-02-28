/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package our.project.map.elements;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import our.project.map.engine.GameController;
import our.project.map.utility.RadioThread;
import our.project.map.utility.ChatClient;

/**
 *
 * Classe per la gestione degli elementi "portatili" all'interno del gioco
 * 
 * @author Gruppo RES
 */
public class PortableObject extends ObjectGame {

    public PortableObject(String name, String description, int location, TypeObject type, boolean usable, boolean pickable,
            boolean openable, String combinedObject, String container, String objectObtained,int openableRoom,boolean isTurnedOn,boolean isOpened) {
        super(name, description, location, type, usable, pickable, openable, combinedObject, container, objectObtained,openableRoom,isTurnedOn,isOpened);
    }
    
    /**
     *
     *  Metodo che permette l'utilizzo dell'oggetto
     * 
     * @param game
     * @return Stringa contente l'utilizzo dell'oggetto
     */
    @Override
    public String use(GameController game) {
        StringBuilder s = new StringBuilder();
        s.append("Provi ad usare: ");
        s.append(getName());
        s.append("\n\n");
        
        if(getName().equals("radio")) {
            if (!isTurnedOn()){
                setTurnedOn(true);
                s.append("Hai acceso la radio, ma ti sei accorto che la leva per modificare la frequenza non funziona\n\n");
                
                // parte il thread associato
                RadioThread radioThread = new RadioThread();
                radioThread.start();
            }
            
            s.append("Radio accesa, il segnale è disturbato e senti un suono fastidioso");
            
        }
        if(getName().equals("palmare")) {
            if (!isTurnedOn()){
                setTurnedOn(true);
            }
            
             s.append("Prendi il palmare e provi a connetterti al primo server disponibile di cui "
                     + "riesci a rilevare il segnale, speri di trovare qualcuno che possa aiutarti");
      
            ChatClient server = new ChatClient();
           
        }
        
        return s.toString();
    }
    
}
