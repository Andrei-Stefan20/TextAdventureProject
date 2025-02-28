/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package our.project.map.elements;

import java.util.List;
import javax.swing.JOptionPane;
import our.project.map.engine.GameController;
import our.project.map.utility.Utility;

/**
 *
 * Classe per la gestione degli elementi "computer" all'interno del gioco
 * 
 * @author Gruppo RES
 */
public class ComputerObject extends ObjectGame{
    
    private final String NOTE = "Usa la barra di ricerca per filtrare:\n"
            + "-- vicino\n-- osservabile\n-- pericolo\n-- esci";

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
    public ComputerObject(String name, String description, int location, TypeObject type, boolean usable, boolean pickable, 
            boolean openable, String combinedObject, String container, String objectObtained,int openableRoom,boolean isTurnedOn,boolean isOpened) {
        super(name, description, location, type, usable, pickable, openable, combinedObject, container, objectObtained,openableRoom,isTurnedOn,isOpened);
    }
    
    /**
     *
     * Utilizzo degli oggetti ComputerObject
     * 
     * @param game
     * @return Stringa
     */
    @Override
    public String use(GameController game) {
        
        final Utility utils = new Utility();
        
        StringBuilder s = new StringBuilder();
        String message = "";
        
        if (!isTurnedOn()){
            return "Senza corrente non puoi utilizzare il computer";
        }
        
        s.append("Stai usando il computer\n");
        
        // implementare logiche diverse per i due tipi di computer
        if (getName().equals("computer laboratorio")) {

            boolean computerEx = true;
            List<Star> starToFilter = utils.getStar();
            
            if(starToFilter != null){
                
                while (computerEx){

                String search = JOptionPane.showInputDialog(NOTE);

                if(search == null){

                    search = "";

                }

                switch(search){
                    case "vicino":
                        // ricerca su latitudine compresa tra 0 e 100 e longitudine tra 0 e 100 -> stampa stelle
                        message = utils.starFiltering(starToFilter, "Le stelle con latitudine compresa tra 0 e 100 e longitudine tra 0 e 100 sono: ", 
                                (Star star) -> {return (star.getLatitude() > 0 && star.getLatitude() <= 100)
                                        && (star.getLongitude() > 0 && star.getLongitude() <= 100); } );
                        JOptionPane.showMessageDialog (null, message, "Stelle vicine", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    case "osservabile":
                        // ricerca su declinazione compresa tra -20 e +40 -> stampa stelle
                        message = utils.starFiltering(starToFilter, "Le stelle con declinazione compresa tra -20 e +40 sono: ", 
                                (Star star) -> {return star.getDeclination() > -20 && star.getDeclination() <= +40; } );
                        JOptionPane.showMessageDialog (null, message, "Stelle osservabili", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    case "pericolo":
                        // ricerca su nome "morte nera" -> stampa stelle
                        message = utils.starFiltering(starToFilter, "Le stelle con latitudine compresa tra 0 e 100 e longitudine tra 0 e 300 sono: ", 
                                (Star star) -> {return star.getName().equalsIgnoreCase("morte_nera"); } );
                        JOptionPane.showMessageDialog (null, message, "Stelle pericolose", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    case "esci":
                        computerEx = false;
                        break;
                    default:
                        JOptionPane.showMessageDialog (null, "Inserire uno dei criteri di ricerca in elenco", "Errore", JOptionPane.INFORMATION_MESSAGE);
                }
            
            }
            
            s.append("stai usando computer laboratorio");
                
            }
            
            
        }
        if (getName().equals("computer controllo")) {
            
            s.append("Questo computer sembra non funzionare.\nUna pallina rimbalzante sullo schermo mi impedisce qualsiasi azione.");
        }
        
        return s.toString();
    }
    
    /**
     *
     * Prendere l'oggetto
     * 
     * @param game
     * @return Messaggio
     */
    @Override
    public String pick(GameController game) {
        return "Non è un computer portatile!";
    }
    
}
