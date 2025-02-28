/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package our.project.map.elements;

import our.project.map.engine.GameController;

/**
 *
 * Classe per la gestione degli elementi "chiave" all'interno del videogioco
 * 
 * @author Gruppo RES
 */
public class KeyObject extends ObjectGame{

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
     * @param openableRoom
     * @param objectObtained
     * @param isTurnedOn
     * @param isOpened
     */
    public KeyObject(String name, String description, int location, TypeObject type, boolean usable, boolean pickable, 
            boolean openable, String combinedObject, String container, int openableRoom,String objectObtained,boolean isTurnedOn,boolean isOpened) {
        super(name, description, location, type, usable, pickable, openable, combinedObject, container, objectObtained,openableRoom,isTurnedOn,isOpened);
    }

    
}
