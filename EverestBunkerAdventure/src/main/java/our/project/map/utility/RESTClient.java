/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package our.project.map.utility;

import javax.swing.JOptionPane;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * Classe che gestisce il client RESTs
 * 
 * @author RES
 */
class RESTClient {
    
    private final String pathService = "http://localhost:4321/Star";
    private final String pathServiceGetStar = "/getStar";
    
    RESTClient(){}
    
    /**
     * 
     * Restituisce la Lista di stelle in JSON
     * 
     * @return Stringa contenente la lista in JSON
     */
    String getListStarJson(){
        
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(pathService);
        String jsonListStar = "";
        
        try{
            
            Response resp = target.path(pathServiceGetStar).request(MediaType.APPLICATION_JSON).get();
            
            jsonListStar = resp.readEntity(String.class);
            
        }
        
        catch(ProcessingException ex){
            
            JOptionPane.showMessageDialog (null,"Errore:\n- Prova ad avviare il Server prima di utilizzare il computer", "Errore", JOptionPane.ERROR_MESSAGE);
            return jsonListStar;
            
        }
        
        
        
        return jsonListStar;
        
    }
  
}
