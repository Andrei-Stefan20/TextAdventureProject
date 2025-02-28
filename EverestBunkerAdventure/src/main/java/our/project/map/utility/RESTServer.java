/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package our.project.map.utility;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import javax.swing.JOptionPane;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

/**
 *
 * Gestione del server REST
 * 
 * @author RES
 */
@Path("Star")
public class RESTServer {
 
    private static final String STRING_BUILD_URI = "http://localhost/";
    private static final String PORT = "4321";
    private static final String PATH_JSON = "./asset/star.json";
    
    static final URI uri = UriBuilder.fromUri(STRING_BUILD_URI).port(Integer.parseInt(PORT)).build();
    static final ResourceConfig config = new ResourceConfig(RESTServer.class);
        
    static HttpServer http = GrizzlyHttpServerFactory.createHttpServer(uri,config);
    
    /**
     *
     * Restituisce la lista delle stelle recuperate da file JSON
     * 
     * @return Response
     */
    @Path("/getStar")
    @GET
    @Produces("application/json")
    public Response getListStar(){
        
        String jsonListStar = "";
        
        try{
            
            BufferedReader file = new BufferedReader(new FileReader(PATH_JSON));
            
            try{
                
                jsonListStar = file.readLine();
                
            }
            
            catch(IOException IOex){
                
                JOptionPane.showMessageDialog (null,"Errore: \n"+IOex.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }
        
        }
        
        catch(FileNotFoundException FnFex){
            
            JOptionPane.showMessageDialog (null,"File \""+PATH_JSON+"\" non trovato", "Errore", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
            
        }
        
        return Response.ok(jsonListStar,MediaType.APPLICATION_JSON).build();
        
    }
    
    /**
     * 
     * Avvia il server REST
     * 
     * @param args
     */
    public static void main(String[] args){
        
        try{
            
            http.start();
            System.out.println("Server REST avviato.");
            System.in.read();
            http.shutdown();
            
        }
        
        catch(IOException IOex){
            
            JOptionPane.showMessageDialog (null,"Errore nell'avvio del server.", "Errore", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
            
        }
        
    }
    
    
    
}
