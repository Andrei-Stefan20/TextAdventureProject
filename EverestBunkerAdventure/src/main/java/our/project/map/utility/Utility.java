/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package our.project.map.utility;

import com.google.gson.Gson;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import our.project.map.elements.*;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * Classe contenente tutti i metodi utili per il funzionamento del gioco
 * 
 * @author RES
 */
public class Utility {
    
    final private static Type typeHashObject = new TypeToken<HashMap<String,TempObject>>(){}.getType();
    final private static Type typeHashRoom = new TypeToken<HashMap<Integer,RoomGame>>(){}.getType();
    final private static Type typeListInv = new TypeToken<ArrayList<String>>(){}.getType();
    final private static Type typeListStar = new TypeToken<ArrayList<Star>>(){}.getType();
    final private static DBManager dbManager = new DBManager();
    final private static FileManager fileManager = new FileManager();
    
    public Utility(){}
    /**
     * 
     * Metodo che costruisce un ObjectGame contente l'istanza di un oggetto di tipo specifico
     * 
     * @param name
     * @param description
     * @param location
     * @param type
     * @param isUsable
     * @param isPickable
     * @param isOpenable
     * @param combinedObject
     * @param container
     * @param openableRoom
     * @param objectObtained
     * @param isTurnedOn
     * @param isOpened
     * @return ObjectGame contente un istanza specifica di oggetto specifico
     */
    private ObjectGame objectCostructor(String name,String description,int location,TypeObject type,
            boolean isUsable,boolean isPickable,boolean isOpenable, String combinedObject,String container,
            int openableRoom,String objectObtained,boolean isTurnedOn,boolean isOpened){
        
        ObjectGame object = null;
        
        switch(type){
            
            case LightingObject:
                object = new LightingObject(name,description,location,type,isUsable,isPickable,isOpenable,
                        combinedObject,container,objectObtained,openableRoom,isTurnedOn,isOpened);
            break;
            
            case PortableObject:
                object = new PortableObject(name,description,location,type,isUsable,isPickable,isOpenable,
                        combinedObject,container,objectObtained,openableRoom,isTurnedOn,isOpened);
            break;
            
            case GunObject:
                object = new GunObject(name,description,location,type,isUsable,isPickable,isOpenable,
                        combinedObject,container,objectObtained,openableRoom,isTurnedOn,isOpened);
            break;
            
            case KeyObject:
                object = new KeyObject(name,description,location,type,isUsable,isPickable,isOpenable,
                        combinedObject,container,openableRoom,objectObtained,isTurnedOn,isOpened);
            break;
            
            case ComputerObject:
                object = new ComputerObject(name,description,location,type,isUsable,isPickable,isOpenable,
                        combinedObject,container,objectObtained,openableRoom,isTurnedOn,isOpened);
            break;
            
            case FixedObject:
                object = new FixedObject(name,description,location,type,isUsable,isPickable,isOpenable,
                        combinedObject,container,objectObtained,openableRoom,isTurnedOn,isOpened);
            break;
            
            case ObjectContainer:
                object = new ObjectContainer(name,description,location,type,isUsable,isPickable,isOpenable,
                        combinedObject,container,objectObtained,openableRoom,isTurnedOn,isOpened);
            break;
            
        }
        
        return object;
        
    }
    
    /**
     *
     * Filtra i nomi delle stelle in base ai criteri disponibili
     * 
     * @param stars
     * @param message
     * @param starFilter
     * @return Stringa contente i nomi delle stelle che rispettano i criteri
     */
    public String starFiltering(List<Star> stars, String message, StarFilter starFilter){
        
        StringBuilder s = new StringBuilder();
        
        s.append(message);
        s.append("\n\n");
        
        Iterator<Star> starIterator = stars.iterator();
        
        while(starIterator.hasNext()){
            
            Star star = starIterator.next();
            
            if(starFilter.filter(star)){
                
                s.append(" - ");
                s.append(star.getName().replaceAll("_"," "));
                s.append("\n");
                
            }
            
        }
        
        return s.toString();
        
    }
    
    /**
     *
     * Recupera i nomi delle stelle dall'interno del file Json
     * 
     * @return Lista di stelle
     */
    public List<Star> getStar(){
    
        Gson jsonConverter = new Gson();
        
        RESTClient rest = new RESTClient();
        
        String stringListStar = rest.getListStarJson();
        
        List<Star> star = null;
        
        if(!stringListStar.equals("")){
            
            star = jsonConverter.fromJson(stringListStar,typeListStar);
            
        }
        
        return star;
                
    }
    
    /**
     * 
     * Converte gli oggetti recuperati dal file Json da oggetti temporanei a quelli specifici
     * 
     * @param obj
     * @return Map contente come chiave una stringa rappresentante il nome dell'oggetto e valori gli oggetti
     */
    private Map<String,ObjectGame> conversionObject(Map<String,TempObject> obj){
        
        Map<String,ObjectGame> objMap = new HashMap<>();
        
        for(Map.Entry<String,TempObject> i: obj.entrySet()){
            
            TempObject temp = i.getValue();
            
            ObjectGame objConverted = objectCostructor(temp.getName(),temp.getDescription(),
                    temp.getLocation(),temp.getType(),temp.getUsable(),temp.getPickable(),temp.getOpenable(),
                    temp.getCombinedObject(),temp.getContainer(),temp.getOpenableRoom(),temp.getObjectObtained(),
                    temp.isTurnedOn(),temp.isOpened());
            
            objMap.put(i.getKey(),objConverted);
            
        }
        
        return objMap;
        
    }
    
    /**
     *
     * Carica la stanza corrente dai file di salvataggio
     * 
     * @return Intero rappresentante l'identificativo della stenza che deve essere corrente
     */
    public int loadCurrentRoomFromFile(){
        
        String[] jsonString = fileManager.loadFromFileJson();
        Gson jsonConvert = new Gson();
        
        int currentRoom = jsonConvert.fromJson(jsonString[TypeJson.currentRoomJson.ordinal()], int.class);
        
        return currentRoom; 
        
    }
    
    /**
     *
     * Carica gli oggetti dai file di salvataggio
     * 
     * @return Map contente come chiave una stringa rapprentente il nome dell'oggetto e come valore l'oggetto di tipo ObjectGame
     */
    public Map<String,ObjectGame> loadObjectFromFile(){
        
        String[] jsonString = fileManager.loadFromFileJson();
        Gson jsonConvert = new Gson();
        
        Map<String,TempObject> tempObj = jsonConvert.fromJson(jsonString[TypeJson.objJson.ordinal()]
                , typeHashObject);
        
        Map<String,ObjectGame> gameObj = conversionObject(tempObj);
        
        return gameObj;
        
    }
    
    /**
     *
     * Carica dai file di salvataggio le stanze
     * 
     * @return Map contente come chiave intero che rappresenta l'id ella stanza e come valori gli oggetti di tipo RoomGame
     */
    public Map<Integer,RoomGame> loadRoomsFromFile(){
        
        String[] jsonString = fileManager.loadFromFileJson();
        Gson jsonConvert = new Gson();
        
        Map<Integer,RoomGame> room = jsonConvert.fromJson(jsonString[TypeJson.roomJson.ordinal()],typeHashRoom);
        
        return room;
        
    }
    
    /**
     *
     * Carica dai file di salvataggio lo stato dell'inventario
     * 
     * @return Lista contente stringhe che rappresentano il nome degli oggetti presenti nell'inventario
     */
    public List<String> loadInvFromFile(){
        
        String[] jsonString = fileManager.loadFromFileJson();
        Gson jsonConvert = new Gson();
        
        List<String> inv = jsonConvert.fromJson(jsonString[TypeJson.invJson.ordinal()],typeListInv);
        
        return inv;
        
    }
    
    /**
     *
     * Metodo per il salvataggio su file dello stato attuale
     * 
     * @param gameObjJson
     * @param gameRoomsJson
     * @param inventoryJson
     * @param currentRoomJson
     * @return true: Se il salvataggio è avvenuto con successo
     *         false: altrimenti
     */
    public boolean saveOnFile(String gameObjJson,String gameRoomsJson,String inventoryJson,String currentRoomJson){
        
        return fileManager.saveOnFileJson(gameObjJson, gameRoomsJson, inventoryJson, currentRoomJson);
        
    }
    
    /**
     *
     * Converte gli oggetti passaati per argomento in Stringhe JSON
     * @param <T>
     * @param objToConvert
     * @return Stringa contente l'oggetto in JSON
     */
    public <T> String toJson(T objToConvert){
        
        Gson gson = new Gson();
        
        String objToJson = gson.toJson(objToConvert);
        
        return objToJson;
        
    }
    
    /**
     *
     * Carica dal database i vari oggetti appartenenti al gioco all'inizio di una nuova partita
     * 
     * @return Map contente come chiave stringhe rapprentanti il nome dell'oggetto e come valore oggetti di tipo ObjectGame
     */
    public Map<String,ObjectGame> getObjectFromDB(){
        
        Map<String,ObjectGame> objectGame = new HashMap<>();
        
        try{
            
            dbManager.startConection();
            
            ResultSet result = dbManager.getObjectFromDB();
            
            while(result.next()){
                
                String name = result.getString("name");
                String description = result.getString("description");
                
                int location = result.getInt("location");
                
                int typeInt = result.getInt("type");
                TypeObject type = TypeObject.parseTypeObject(typeInt);
                
                boolean isUsable = result.getBoolean("isusable");
                boolean isPickable = result.getBoolean("ispickable");
                boolean isOpenable = result.getBoolean("isopenable");
                
                String combinedObject = dbManager.retrivingObjectNameById(result.getInt("combinedobject"));
                String container = dbManager.retrivingObjectNameById(result.getInt("container"));
                String objectObtained = dbManager.retrivingObjectNameById(result.getInt("objectobtained"));
                
                int openableRoom = result.getInt("openableroom");
                
                boolean isTurnedOn = result.getBoolean("turnedon");
                boolean isOpened = result.getBoolean("opened");
                
                ObjectGame object = objectCostructor(name,description,location,type,isUsable,isPickable,isOpenable,
                        combinedObject,container,openableRoom,objectObtained,isTurnedOn,isOpened);
                
                objectGame.put(object.getName(), object);
                
            }
            
            dbManager.closeConection();
            
        }
       
        catch(SQLException sqlEx){
            
            JOptionPane.showMessageDialog (null, "Errore nella connessione con il database:\n- Codice errore: "+sqlEx.getErrorCode()
                    , "Errore", JOptionPane.ERROR_MESSAGE);
            System.exit(sqlEx.getErrorCode());
            
        }
        
        return objectGame;
        
    }
    
    /**
     *
     * Carica dal database le stanze appartenenti al gioco all'inizio di una nuova partita
     * 
     * @return Map contente come chiave un intero rappresentante l'id ella stanza e come valore oggetti di tipo RoomGame
     */
    public Map<Integer,RoomGame> getRoomsFromDB() {
        
        Map<Integer,RoomGame> rooms = new HashMap<>();
        
        try{
            
            dbManager.startConection();
         
            ResultSet result = dbManager.getRoomFromDB();
         
            //String name, String description, String observedDescription, boolean enlightened, 
            //boolean opened, RoomGame nordRoom, RoomGame southRoom, RoomGame eastRoom, RoomGame westRoom
            while(result.next()){

                //identificatore stanza
                int id = result.getInt("id");

                //Descrizioni
                String name = result.getString("name");
                String description = result.getString("description");
                String observedDescription = result.getString("observedDescription");

                //Flag 
                boolean enlightened = result.getBoolean("isenlightened");
                boolean opened = result.getBoolean("isopened");

                //Riferimento stanze adiacenti
                int nordRoom = result.getInt("nordRoom");
                int southRoom = result.getInt("southRoom");
                int eastRoom = result.getInt("estRoom");
                int westRoom = result.getInt("westRoom");


                RoomGame room = new RoomGame(id, name, description, observedDescription, 
                        enlightened, opened, nordRoom, southRoom, eastRoom, westRoom);

                rooms.put(room.getId(), room);
         
            }
         
            dbManager.closeConection();
            
        }
        
        catch(SQLException sqlEx){
            
            JOptionPane.showMessageDialog (null, "Errore nella connessione con il database:\n- Codice errore: "+sqlEx.getErrorCode()
                    , "Errore", JOptionPane.ERROR_MESSAGE);
            System.exit(sqlEx.getErrorCode());
            
        }
        
        return rooms;
        
    }
   
    /**
     *
     * Controlla che i file di salvataggio non siano vuoti
     * 
     * @return
     */
    public boolean notEmptyFile(){
       
       return fileManager.notEmpty();
       
   }
    
}
