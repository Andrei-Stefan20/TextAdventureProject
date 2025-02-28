/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package our.project.map.utility;

import java.util.Properties;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * Classe che gestisce la comunicazione con il Database
 * 
 * @author RES
 */
class DBManager {
    
    private static final String DBDRIVER = "jdbc:h2:./asset/database/gameDescription";
    private static final String QUERYROOMS = "SELECT stanze.* FROM stanze;";
    private static final String QUERYOBJECT = "SELECT oggetti.* FROM oggetti;";
    
    private static final String QUERYRETRIEVINGNAME = "SELECT name FROM oggetti WHERE id = ?;";
    
    private Connection connectionDB;
    private Properties prop = new Properties();
    
    DBManager(){
        
        prop.setProperty("user", "gameDB");
        prop.setProperty("password", "Nuke123!");
        
        try{
            
            connectionDB = DriverManager.getConnection(DBDRIVER,prop);   
            
            System.out.println("Connessione al DB riuscita.");
            
            connectionDB.close();
            
        }
        
        catch(SQLException sqlEx){
            
            JOptionPane.showMessageDialog (null, "Errore nella connessione con il database:\n- Codice errore: "+sqlEx.getErrorCode()
                    , "Errore", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
            
        }
       
    }
    
    /**
     * 
     * Inizia una connessione con il Database manager
     * 
     * @throws SQLException Errore nella comunicazione con il Database
     */
    void startConection() throws SQLException{
         
        connectionDB = DriverManager.getConnection(DBDRIVER,prop);        
    }
     
    /**
     * 
     * Chiude la comunicazione con il Database manager
     * 
     * @throws SQLException Errore nella comunicazione con il Database
     */
    void closeConection() throws SQLException{
        
        connectionDB.close();
                
    }

    /**
     * 
     * Metodo per recuperare il nome di un oggetto mediante il suo identificativo numerico.
     * 
     * @param objectID
     * @return Stringa contente il nome dell'oggetto
     * @throws SQLException Errore nella comunicazione con il Database
     */
    String retrivingObjectNameById(final int objectID) throws SQLException{
        
        String containerName = "null";
        
        if(objectID > 0){
            
            PreparedStatement state = connectionDB.prepareStatement(QUERYRETRIEVINGNAME);
        
            state.setInt(1, objectID);
        
            ResultSet set = state.executeQuery();
        
            set.next();
            
            containerName = set.getString("name");
        
            set.close();
            state.close();
            
        }
        
        return containerName;
        
    }
    
    /**
     * 
     * Recupera gli oggetti dal database
     * 
     * @return un ResultSet contente le tuple recuperate
     * @throws SQLException Errore nella comunicazione con il Database Manager
     */
    ResultSet getObjectFromDB() throws SQLException{
        
        ResultSet set;
        
        Statement state = connectionDB.createStatement();
        
        set = state.executeQuery(QUERYOBJECT);
        
        return set;
        
    }
    
    /**
     * 
     * Recupera le stanze dal database
     * 
     * @return un ResultSet contente le tuple recuperate
     * @throws SQLException 
     */
    ResultSet getRoomFromDB() throws SQLException{
        
        ResultSet set;
            
        Statement state = connectionDB.createStatement();
               
        set = state.executeQuery(QUERYROOMS);
        
        return set;
        
        
    }
    
}
