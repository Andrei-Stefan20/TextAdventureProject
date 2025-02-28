/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package our.project.map.elements;

import our.project.map.engine.GameController;

/**
 *
 * Classe per la gestione degli elementi "Pistola" all'itnerno del gioco
 * 
 * @author Gruppo RES
 */
public class GunObject extends ObjectGame{
    
    private final String FINALE = "Hai premuto il grilletto senza badare a dove stavi mirando. "
            + "Hai colpito una parete, il proiettile ha rimbalzato su un'altra parete, poi un altra "
            + "e alla fine il proiettile ti ha colpito.\n "
            + "Non sei riuscito ad evitare la catastrofe e in più sei morto, ora il mondo intero è scoppiato "
            + "in una guerra ed è tutta colpa tua!";

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
    public GunObject(String name, String description,int location, TypeObject type, boolean usable, boolean pickable, 
        boolean openable, String combinedObject, String container, String objectObtained,int openableRoom,boolean isTurnedOn,boolean isOpened) {
        super(name, description, location, type, usable, pickable, openable, combinedObject, container, objectObtained,openableRoom,isTurnedOn,isOpened);
    }

    /**
     *
     * Utilizzo di oggetti di tipo GunObject
     * 
     * @param game
     * @return Messaggio di stampa
     */
    @Override
    public String use(GameController game) {
        // l'unico oggetto appartenente a questo tipo che deve fare effettivamente qualcosa è la pistola
        // dobbiamo gestire il comportamento della pistola separatamente
        
        StringBuilder s = new StringBuilder();
        
         // gli oggetti fissi sono: interruttore, tastierino, pulsantiera
        if (getName().equals("pistola")) {
            s.append(FINALE);
            s.append("#Finale pistola");
            return s.toString();
        }
        

        s.append("Alan usa ");
        s.append(getName());
        s.append("\nNon è molto efficace");
        
        return s.toString();
    }
    
}
