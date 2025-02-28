/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package our.project.map.elements;

import java.util.ListIterator;
import our.project.map.engine.GameController;

/**
 *
 * Classe astratta per la gestione degli elementi comuni a tutti gli oggetti.
 * Tutti gli oggetti specifici ereditano da "ObjectGame"
 * 
 * @author Gruppo RES
 */
public abstract class ObjectGame{
    
    protected final String name;
    protected final String description;
    protected int location;
    protected final TypeObject type;
    protected final boolean usable;
    protected final boolean pickable;
    protected final boolean openable;
    protected boolean turnedOn;
    protected boolean opened;
    protected final String combinedObject;
    protected String container;
    protected final String objectObtained;
    protected int openableRoom;
    
    //GameController game = new GameController();

    /**
     *
     * Oggetto generico del gioco
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
     * @param turnedOn
     * @param opened
     */

    public ObjectGame(String name, String description, int location, TypeObject type, boolean usable, boolean pickable, 
            boolean openable, String combinedObject, String container, String objectObtained,int openableRoom,boolean turnedOn,boolean opened) {
        this.name = name;
        this.description = description;
        this.location = location;
        this.type = type;
        this.usable = usable;
        this.pickable = pickable;
        this.openable = openable;
        this.turnedOn = turnedOn;
        this.opened = opened;
        this.combinedObject = combinedObject;
        this.container = container;
        this.objectObtained = objectObtained;
        this.openableRoom = openableRoom;
    }
    
    /**
     *
     * @return name: Stringa contenete nome del gioco
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return decription: Stringa con decrizione dell'oggetto
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @return location: Intero rappresentante la locazione dell'oggetto
     */
    public int getLocation() {
        return location;
    }

    /**
     *
     * Imposta la locazione dell'oggetto
     * 
     * @param location
     */
    public void setLocation(int location) {
        this.location = location;
    }

    /**
     *
     * @return type: TypeObject, indica il tipo specifico di oggetto
     */
    public TypeObject getType() {
        return type;
    }

    /**
     *
     * @return usable: true: Oggetto utilizzabile
     *                 false: Oggetto non utilizzabile
     */
    public boolean isUsable() {
        return usable;
    }

    /**
     *
     @return pickable: true: Oggetto raccoglibile
     *                 false: Oggetto non raccoglibile
     */
    public boolean isPickable() {
        return pickable;
    }

    /**
     *
     * @return openable: true: Oggetto apribile
     *                   false: Oggetto non apribile
     */
    public boolean isOpenable() {
        return openable;
    }

    /**
     *
     * @return turnedon: true: Oggetto accendibile
     *                   false: Oggetto non accendibile
     */
    public boolean isTurnedOn() {
        return turnedOn;
    }

    /**
     *
     * Imposta un oggetto accesso o spento
     * 
     * @param turnedOn
     */
    public void setTurnedOn(boolean turnedOn) {
        this.turnedOn = turnedOn;
    }

    /**
     *
     * @return opened: true: oggetto aperto
     *                 false: oggetto non aperto
     */
    public boolean isOpened() {
        return opened;
    }

    /**
     *
     * Imposta un oggetto aperto o chiuso
     * 
     * @param opened
     */
    public void setOpened(boolean opened) {
        this.opened = opened;
    }

    /**
     *
     * @return combinedobject: Stringa rappresentante l'oggetto con cui è possibile combinare
     */
    public String getCombinedObject() {
        return combinedObject;
    }

    /**
     *
     * @return container: Stringa rappresentante il contenitore dell'oggetto
     */
    public String getContainer() {
        return container;
    }

    /**
     *
     * Imposta l'oggetto contenitore
     * 
     * @param container
     */
    public void setContainer(String container) {
        this.container = container;
    }

    /**
     *
     * @return openableroom: Intero rappresentante la stanza che è possibile
     *                       aprire con l'oggetto
     */
    public int getOpenableRoom() {
        return openableRoom;
    }

    /**
     *
     * @return objectObtained: Stringa rappresentante l'oggetto ottenuto
     *                         combinando due oggetti
     */
    public String getObjectObtained() {
        return objectObtained;
    }
    
    /**
     *
     * Utilizzo dell'oggetto
     * 
     * @param game
     * @return Stringa contente messaggio
     */
    public String use(GameController game){
        return "Non puoi usare questo oggetto";
    }
    
    /**
     *
     * Apertura dell'oggetto
     * 
     * @param game
     * @return Stringa contente messaggio
     */
    public String open(GameController game){
        return "Non puoi aprire questo oggetto";
    }
    
    /**
     *
     * Raccolta dell'oggetto
     * 
     * @param game
     * @return Stringa contente messaggio
     */
    public String pick(GameController game){
        StringBuilder s = new StringBuilder();
        
        // se in inventario, avvisa l'utente
        ListIterator<String> invIterator = game.getInventory().listIterator();
        while (invIterator.hasNext()){
            if (getName().equals(invIterator.next())){
                s.append(getName());
                s.append(" è già in inventario");
                return s.toString();
            }
        }
        
        // se non in inventario, mette in inventario e avvisa l'utente
        game.getInventory().add(getName());
        
        // se in una stanza, lo rimuove da tale stanza
        if (getLocation() > 0){
            setLocation(0);
        }
        // se in un contenitore, lo rimuove da tale contenitore
        if (getContainer() != null){
            setContainer("");
        }
        
        s.append(getName());
        s.append(" inserito in inventario");
        return s.toString();
    }
    

}
