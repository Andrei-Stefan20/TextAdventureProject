/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package our.project.map.elements;
import java.util.Map;
import our.project.map.engine.GameController;

/**
 *
 * Classe per la gestione degli elementi "contenitori" all'interno del gioco
 * 
 * @author Gruppo RES
 */
public class ObjectContainer extends ObjectGame {

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
    public ObjectContainer(String name, String description,int location, TypeObject type, boolean usable, 
            boolean pickable, boolean openable, String combinedObject, String container,String objectObtained,int openableRoom,boolean isTurnedOn,boolean isOpened) {
        super(name, description, location, type, usable, pickable, openable, combinedObject, container,objectObtained,openableRoom,isTurnedOn,isOpened);
    }
    
    /**
     *
     * @param game
     * @return
     */
    @Override
    public String use(GameController game) {
        return "Sei troppo grande per infilarti qui dentro!";
    }

    /**
     *
     * Apertura di un oggetto contenitore
     * 
     * @param game
     * @return s: Stringa contente messaggio di stampa che indica gli oggetti presenti nel contenitore
     */
    @Override
    public String open(GameController game) {
        StringBuilder s = new StringBuilder();
        s.append(getName());
        
        // avvisa l'utente sullo stato del contenitore
        if (!isOpened()){
            setOpened(true);
            s.append(" sbloccato\n");
        }
        
        s.append(" contiene:\n\n");
        // se già aperto, mostro gli elementi contenuti al suo interno
        for (Map.Entry<String, ObjectGame> e : game.getObjectGame().entrySet()){
            if (e.getValue().getContainer().equals(getName())){
                s.append("- ");
                s.append(e.getValue().getName());
                s.append("\n");
            }
        }
        
        return s.toString();
    }

    /**
     *
     * Prendere l'oggetto
     * 
     * @param game
     * @return Messaggio di stampa
     */
    @Override
    public String pick(GameController game) {
        return "Dove vorresti infilarti questo coso?";
    }


   
}

