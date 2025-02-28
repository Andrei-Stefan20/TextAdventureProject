/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package our.project.map.elements;

/**
 *
 * Classe descrittiva delle stelle che compaiono all'interno del gioco
 * 
 * @author Gruppo RES
 */
public class Star {
    
    private final String name;
    private final double longitude;
    private final double latitude;
    private final double declination;
    
    /**
     *
     * @param name
     * @param longitude
     * @param latitude
     * @param declination
     */
    public Star(String name,double longitude,double latitude,double declination){
        
        this.name = name;
        this.declination = declination;
        this.latitude = latitude;
        this.longitude = longitude;
        
    }
    
    /**
     *
     * @return name: Stringa contente il nome della stella
     */
    public String getName(){
        
        return name;
        
    }
    
    /**
     *
     * @return latitude: double rappresentante la latitudine della stella
     */
    public double getLatitude(){
        
        return latitude;
        
    }
    
    /**
     *
     * @return longitude: double rappresentante la longitudine della stella
     */
    public double getLongitude(){
        
        return longitude;
        
    }
    
    /**
     *
     * @return declination: double rappresentante la declinazione della stella
     */
    public double getDeclination(){
        
        return declination;
        
    }
    
}
