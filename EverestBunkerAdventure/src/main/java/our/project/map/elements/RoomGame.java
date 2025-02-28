/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package our.project.map.elements;

/**
 *
 * Classe per la gestione degli elementi delle stanze all'interno del gioco
 * 
 * @author Gruppo RES
 */
public class RoomGame {
    
    private int id;
    private String name;
    private String description;
    private String observedDescription;
    private boolean enlightened;
    private boolean opened;
    private int  northRoom;
    private int southRoom;
    private int eastRoom;
    private int westRoom;
    private boolean visited = false; // non presente come campo nel DB

    /**
     *
     * @param id
     * @param name
     * @param description
     * @param observedDescription
     * @param enlightened
     * @param opened
     * @param nordRoom
     * @param southRoom
     * @param eastRoom
     * @param westRoom
     */
    public RoomGame(int id, String name, String description, String observedDescription, boolean enlightened, 
            boolean opened, int nordRoom, int southRoom, int eastRoom, int westRoom) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.observedDescription = observedDescription;
        this.enlightened = enlightened;
        this.opened = opened;
        this.northRoom = nordRoom;
        this.southRoom = southRoom;
        this.eastRoom = eastRoom;
        this.westRoom = westRoom;
    }
    
    /**
     *
     * @return id: Intero rappresentante l'id dell'oggetto
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id: Intero rappresentante l'identificativo della stanza
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return name: Stringa contente il nome della stanza
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name: Stringa contenente il nome della stanza
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return Stringa contente la descrizione della stanza
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description: Stringa contente la descrizione da impostare per la stanza.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return observedDescription: Stringa contente la descrizione della stanza
     */
    public String getObservedDescription() {
        return observedDescription;
    }

    /**
     *
     * @param observedDescription: Stringa contente la descrizione da impostare per la stanza
     */
    public void setObservedDescription(String observedDescription) {
        this.observedDescription = observedDescription;
    }

    /**
     *
     * @return enlightened: true: se la stanza è illuminata
     *                      false: altrimenti
     */
    public boolean isEnlightened() {
        return enlightened;
    }

    /**
     *
     * @param enlightened: true: illumini la stanza
     *                     false: la stanza non è illuminata
     */
    public void setEnlightened(boolean enlightened) {
        this.enlightened = enlightened;
    }

    /**
     *
     * @return opened: true: stanza aperta
     *                 false: stanza non aperta
     */
    public boolean isOpened() {
        return opened;
    }

    /**
     *
     * @param opened: true: rendo la stanza aperta
     *                false: rendo la stanza chiusa
     */
    public void setOpened(boolean opened) {
        this.opened = opened;
    }

    /**
     *
     * @return northRoom: intero rappresentante la stanza presente a nord della stanza attuale
     */
    public int getNorthRoom() {
        return northRoom;
    }

    /**
     *
     * Imposto la stanza presente a nord
     * 
     * @param nordRoom
     */
    public void setNorthRoom(int nordRoom) {
        this.northRoom = nordRoom;
    }

    /**
     *
     * @return southRoom: intero rappresentante la stanza presente a sud della stanza attuale
     */
    public int getSouthRoom() {
        return southRoom;
    }

    /**
     *
     * Imposto la stanza presente a sud
     * 
     * @param southRoom
     */
    public void setSouthRoom(int southRoom) {
        this.southRoom = southRoom;
    }

    /**
     *
     * @return eastRoom: intero rappresentante la stanza presente a east della stanza attuale
     */
    public int getEastRoom() {
        return eastRoom;
    }

    /**
     *
     * Imposto la stanza presente a east
     * 
     * @param eastRoom
     */
    public void setEastRoom(int eastRoom) {
        this.eastRoom = eastRoom;
    }

    /**
     *
     * @return weastRoom: intero rappresentante la stanza presente a ovest della stanza attuale
     */
    public int getWestRoom() {
        return westRoom;
    }

    /**
     *
     * Imposto la stanza presente a ovest
     * 
     * @param westRoom
     */
    public void setWestRoom(int westRoom) {
        this.westRoom = westRoom;
    }

    /**
     *
     * @return visited: true: se la stanza è già stata visitata
     *                  false: se la stanza non è ancora stata visitata
     */
    public boolean isVisited() {
        return visited;
    }

    /**
     *
     * Imposto se una stanza è stata visitata o meno
     * 
     * @param visited
     */
    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    
    
}
