/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package our.project.map.utility;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * Classe che gestisce il caricamento dai file di salvataggio JSON
 * 
 * @author RES
 */
class FileManager {
    
    private static final String ROOTSAVESDIR = "./asset/saveData";
    private static final String GAMEOBJFILE = "/gameObject.json";
    private static final String ROOMGAMEFILE = "/roomGame.json";
    private static final String INVENTORYFILE = "/inventory.json";
 
    FileManager(){
       
        File temp = new File(ROOTSAVESDIR);
        
        if(!temp.exists()){
            
            temp.mkdirs();
            System.out.format("Cartella %s creato correttamente.\n\n",temp.getName());
            
        }
        
        else{
            
            System.out.format("Cartella %s già esistente.\n\n",temp.getName());
            
        }
        
        try{
            
            creationSaveFile(ROOTSAVESDIR.concat(GAMEOBJFILE));
            creationSaveFile(ROOTSAVESDIR.concat(ROOMGAMEFILE));
            creationSaveFile(ROOTSAVESDIR.concat(INVENTORYFILE));
            
        }
        
        catch(IOException ex){
            
            JOptionPane.showMessageDialog (null,"Errore nella creazione della cartella\""+ROOTSAVESDIR
                    +"\"", "Errore", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
            
        }
        
    }
    /**
     * 
     * Controlla che i file di salvataggio JSON non siano vuoti
     * 
     * @return true: se i file non sono vuoti
     *         false: se i file sono vuoti
     */
    boolean notEmpty(){
        
        File fileObject = new File(ROOTSAVESDIR.concat(GAMEOBJFILE));
        File fileRooms = new File(ROOTSAVESDIR.concat(ROOMGAMEFILE));
        File fileInventory = new File(ROOTSAVESDIR.concat(INVENTORYFILE));
        
        boolean notEmpty = true;
        
        if(fileObject.length() == 0){
            
            notEmpty = false;
            
        } else if(fileRooms.length() == 0){
            
            notEmpty = false;
            
        } else if(fileInventory.length() == 0){
            
            notEmpty = false;
            
        }
        
        return notEmpty;
        
    }
    /**
     * 
     * Metodo per la creazione dei file di salvataggio JSON
     * 
     * @param root Percorso in cui si vouole creare i file
     * @throws IOException 
     */
    final void creationSaveFile(String root) throws IOException {
        
        File create = new File(root);
        
        if(!create.exists()){
            
            create.createNewFile();
            System.out.format("File: %s creato correttamente.\n\n",root);
            
        }
        
        else{
            
            System.out.format("File: %s già esistente.\n\n",root);
            
        }
        
    }
    
    /**
     * 
     * Carica dai file di salvataggio le varie informazioni di stato della partita
     * 
     * @return Array di stringhe contente i vari contenuti letti dai file in formato JSON
     */
    String[] loadFromFileJson(){
    
        String[] stringJson = {null,null,null,null};
        
        try{
        
            BufferedReader inGameObjJson = new BufferedReader(new FileReader(ROOTSAVESDIR.concat(GAMEOBJFILE)));
            BufferedReader inRoomJson = new BufferedReader(new FileReader(ROOTSAVESDIR.concat(ROOMGAMEFILE)));
            BufferedReader inInventory = new BufferedReader(new FileReader(ROOTSAVESDIR.concat(INVENTORYFILE)));
            
            try{
            
                stringJson[TypeJson.objJson.ordinal()] = inGameObjJson.readLine();
                stringJson[TypeJson.roomJson.ordinal()] = inRoomJson.readLine();
                stringJson[TypeJson.currentRoomJson.ordinal()] = inRoomJson.readLine();
                stringJson[TypeJson.invJson.ordinal()] = inInventory.readLine();
            
            }
            
            catch(IOException IOex){
            
                JOptionPane.showMessageDialog (null,"Errore:\n"+IOex.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
                
            }
            
            finally{
                
                try{
                
                    inGameObjJson.close();
                    inRoomJson.close();
                    inInventory.close();
                
                }
                
                catch(IOException IOex){
                    
                    JOptionPane.showMessageDialog (null,"Errore:\n"+IOex.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
                    System.exit(0);
                    
                }
                
            }
            
        }
    
        catch(FileNotFoundException FnFex){
        
            JOptionPane.showMessageDialog (null,"Non sono stati trovati i file di salvataggio.", "Errore", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        
        }
        
        return stringJson;
        
    }
    
    /**
     * 
     * Salva lo stato della partita all'interno dei file JSON
     * 
     * @param gameObjJson
     * @param gameRoomsJson
     * @param inventoryJson
     * @param currentRoomJson
     * @return true: se il salvataggio avviene con successo
     *         false: altrimenti
     */
    boolean saveOnFileJson(String gameObjJson,String gameRoomsJson, String inventoryJson,String currentRoomJson){
        
        boolean saved = false;
       
        try{
            
            BufferedWriter outGameObjJson = new BufferedWriter(new FileWriter(ROOTSAVESDIR.concat(GAMEOBJFILE)));
            BufferedWriter outRoomsJson = new BufferedWriter(new FileWriter(ROOTSAVESDIR.concat(ROOMGAMEFILE)));
            BufferedWriter outInventory = new BufferedWriter(new FileWriter(ROOTSAVESDIR.concat(INVENTORYFILE)));
            
            try{
                
                outGameObjJson.write(gameObjJson);
                outRoomsJson.write(gameRoomsJson+"\n"+currentRoomJson);
                outInventory.write(inventoryJson);
                
                saved = true;
                
            }
            
            catch(IOException IOex){
                
                JOptionPane.showMessageDialog (null,"Errore:\n"+IOex.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
                
            }
            
            finally{
                
                try{
                    
                    outGameObjJson.close();
                    outRoomsJson.close();
                    outInventory.close();
                    
                }
                
                catch(IOException IOex){
                    
                    JOptionPane.showMessageDialog (null,"Errore:\n"+IOex.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
                    System.exit(0);
                    
                }
                
                
                
            }
            
        }
        
        catch(IOException IOex){
            
            JOptionPane.showMessageDialog (null,"Errore:\n"+IOex.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
            
        }
        
        return saved;
        
    }
    
}