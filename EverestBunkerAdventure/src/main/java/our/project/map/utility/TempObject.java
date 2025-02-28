/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package our.project.map.utility;

import our.project.map.elements.ObjectGame;
import our.project.map.elements.TypeObject;

/**
 *
 * Tipo di oggetto temporaneo per il recupero da file e conversione a tipo specifico
 * 
 * @author RES
 */
class TempObject extends ObjectGame{
    
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
    public TempObject(String name, String description, int location, TypeObject type, boolean usable, boolean pickable, boolean openable, 
            String combinedObject, String container, String objectObtained, int openableRoom,boolean isTurnedOn,boolean isOpened) {
        super(name, description, location, type, usable, pickable, openable, combinedObject, container, objectObtained, openableRoom,isTurnedOn,isOpened);
    }
    
    /**
     * 
     * @return true: Se l'oggetto è apribile
     *         false: altrimenti
     */
    boolean getOpenable(){
        
        return openable;
        
    }
    
    /**
     * 
     * @return true: Se l'oggetto è prendibile
     *         false: altrimenti
     */
    boolean getPickable(){
        
        return pickable;
        
    }
    
    /**
     * 
     * @return true: Se l'oggetto è utilizzabile
     *         false: altrimenti
     */
    boolean getUsable(){
        
        return usable;
        
    }
    
}
