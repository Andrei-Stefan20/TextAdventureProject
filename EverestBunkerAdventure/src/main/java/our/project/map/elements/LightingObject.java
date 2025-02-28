/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package our.project.map.elements;

import our.project.map.engine.GameController;

/**
 *
 * Classe per la gestione degli elementi "illuminanti" all'interno del gioco
 * 
 * @author Gruppo RES
 */
public class LightingObject extends ObjectGame{

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
    public LightingObject(String name, String description, int location, TypeObject type, boolean usable, boolean pickable, boolean openable, 
            String combinedObject, String container, String objectObtained,int openableRoom,boolean isTurnedOn,boolean isOpened) {
        super(name, description, location, type, usable, pickable, openable, combinedObject, container, objectObtained,openableRoom,isTurnedOn,isOpened);
    }
    
    /**
     *
     * Utilizzo di un oggetto illuminante
     * 
     * @param game
     * @return Messaggio di stampa
     */
    @Override
    public String use(GameController game) {
        StringBuilder s = new StringBuilder();
        
        if (game.getRooms().get(game.getCurrentRoom()).isEnlightened()){
            return "La stanza è ben illuminata, hai forse perso la vista?";
        }
        
        game.getRooms().get(game.getCurrentRoom()).setEnlightened(true);
        s.append("Hai acceso: ");
        s.append(getName());
        s.append("\nOra la zona che ti circonda ti è visibile");
        return s.toString();
    }
    
}
