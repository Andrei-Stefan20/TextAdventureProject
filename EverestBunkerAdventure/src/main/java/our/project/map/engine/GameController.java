/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package our.project.map.engine;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import our.project.map.elements.ObjectGame;
import our.project.map.parser.CommandTypology;
import our.project.map.elements.RoomGame;
import our.project.map.elements.TypeObject;
import our.project.map.parser.Command;
import our.project.map.parser.ParserOutput;
import our.project.map.utility.AlarmThread;
import our.project.map.utility.RadioThread;
import our.project.map.utility.Utility;

/**
 *
 * Classe per la gestione del gioco
 * 
 * @author RES
 */
public class GameController {
    
    private final Utility utility = new Utility();
    
    private  List<Command> commands = new ArrayList<>();
    private  Map<Integer, RoomGame> rooms = new HashMap<>();
    private  List<String> inventory = new ArrayList<>();
    private  Map<String, ObjectGame> objectGame = new HashMap<>();
    
    private int currentRoom;

    /**
     *  Costruttore
     */
    public GameController() {
        
    }

    /**
     *
     * @return Lista contenete i comandi utilizzabili nel gioco
     */
    public List<Command> getCommands() {
        return commands;
    }

    /**
     *
     * Metodo per impostare i comandi utilizzabili nel gioco.
     * Utilizzato in fase di avvio del gioco
     * 
     * @param commands
     */
    public void setCommands(List<Command> commands) {
        this.commands = commands;
    }

    /**
     *
     * @return MAP contente le stanze del gioco rappresentate da numeri interi
     */
    public Map<Integer, RoomGame> getRooms() {
        return rooms;
    }

    /**
     * Metodo per impostare le stanze visitabili nel gioco.
     * Utilizzato in fase di avvio del gioco
     * 
     * @param rooms: Map contente le stanze del gioco
     */
    public void setRooms(Map<Integer, RoomGame> rooms) {
        this.rooms = rooms;
    }
    
    /**
     *
     * @return Lista contente gli oggetti posseduti nell'inventario dal giocatore
     */
    public List<String> getInventory() {
        return inventory;
    }

    /**
     *
     * Metodo per impostare i comandi utilizzabili nel gioco.
     * Utilizzato in fase di avvio del gioco
     * 
     * @param inventory: Lista di oggetti contenuti nell'inventario dal giocatore al primo avvio del gioco
     */
    public void setInventory(List<String> inventory) {
        this.inventory = inventory;
    }
    
    /**
     *
     * Metodo per la ricerca di un oggetto all'interno dell'inventario del giocatore
     * 
     * @param obj: Stringa rappresentante l'oggetto da ricercare
     * @return true: Se l'oggetto è presente nell'inventario del giocatore. false: altrimenti
     */
    public boolean reasearchInvObj(String obj) {
         for (String s : inventory) {
            if (s.contains(obj)) {
                return true;
            }
        }
         return false;
    }
    
    /**
     *
     * @param obj: Stringa rappresentante l'oggetto da ricercare 
     * @return Intero rappresentante la posizione dell'oggetto all'interno dell'inventario
     *         0: se l'oggetto non è presente nell'inventario
     */
    public int getInventoryIndex(String obj) {
        int count = 0;
        for (String s : inventory) {
            if (s.contains(obj)) {
                return count;
            }
            count++;
        }
        return 0;
    }

    /**
     *
     * @return Map contente gli oggetti di gioco rappresentati da una Stringa che è il nome dell'oggetto
     */
    public Map<String, ObjectGame> getObjectGame() {
        return objectGame;
    }

    /**
     *
     * Metodo per impostare gli oggetti di gioco
     * 
     * @param objectGame: MAP con chiave di tipo stringa e valori ObjectGame (oggetti di gioco)
     */
    public void setObjectGame(Map<String, ObjectGame> objectGame) {
        this.objectGame = objectGame;
    }

    /**
     *
     * @return intero rappresentante la stanza corrente di gioco
     */
    public int getCurrentRoom() {
        return currentRoom;
    }

    /**
     *
     * Metodo per impostare la stanza corrente (stanza in cui si trova il giocatore)
     * 
     * @param currentRoom:Intero rappresentante nuova stanza attuale
     */
    public void setCurrentRoom(int currentRoom) {
        this.currentRoom = currentRoom;
    }
    
    /**
     *
     * Metodo per iniziare una nuova partita
     * 
     */
    public void startNewGame(){
        //Nuova partita, lettura dati da DB.
        initCommands();
        initGameObj();
        initGameRoom();
        initInventory();
    }
    
    /**
     *
     * Metodo per caricare una partita salvata
     * 
     */
    public void loadGame(){
        //Caricamento  di una  partita esistente, lettura dati da file.
        initCommands();
        objectGame = utility.loadObjectFromFile();
        rooms = utility.loadRoomsFromFile();
        inventory = utility.loadInvFromFile();
        currentRoom = utility.loadCurrentRoomFromFile();
        
        verifyThread();
    }
    
    /**
     *
     * Metodo per l'inizializzazione dei comandi di gioco
     * 
     */
    public void initCommands(){
    
        // comandi di spostamento
        Command nord = new Command(CommandTypology.NORTH, "nord");
        nord.setAlias(new String[]{"n", "nord"});
        getCommands().add(nord);
        Command sud = new Command(CommandTypology.SOUTH, "sud");
        sud.setAlias(new String[]{"s", "sud"});
        getCommands().add(sud);
        Command est = new Command(CommandTypology.EAST, "est");
        est.setAlias(new String[]{"e", "est"});
        getCommands().add(est);
        Command ovest = new Command(CommandTypology.WEST, "ovest");
        ovest.setAlias(new String[]{"o", "ovest"});
        getCommands().add(ovest);
        
        // comandi di interazione oggetti
        Command look = new Command(CommandTypology.LOOK_AT, "osserva");
        look.setAlias(new String[]{"guarda", "vedi", "trova", "cerca", "descrivi"});
        getCommands().add(look);
        Command pickup = new Command(CommandTypology.PICK, "raccogli");
        pickup.setAlias(new String[]{"prendi", "conserva"});
        getCommands().add(pickup);
        Command open = new Command(CommandTypology.OPEN, "apri");
        open.setAlias(new String[]{});
        getCommands().add(open);
        Command use = new Command(CommandTypology.USE, "usa");
        use.setAlias(new String[]{"utilizza", "impiega"});
        getCommands().add(use);
        Command combine = new Command(CommandTypology.COMBINE, "combina");
        combine.setAlias(new String[]{"unisci", "crea"});
        getCommands().add(combine);
        
        // apertura inventario
        Command inventario = new Command(CommandTypology.INVENTORY, "inventario");
        inventario.setAlias(new String[]{"inv", "zaino", "borsa"});
        getCommands().add(inventario);
       
    }
    
    /**
     *
     * Metodo per l'inizializzazione degli oggetti di gioco
     * 
     */
    public void initGameObj(){
    
        setObjectGame(utility.getObjectFromDB());
         
        for (Map.Entry<String,ObjectGame> i: objectGame.entrySet()) {
            System.out.println("key: " + i.getKey() + "; value: " + i.getValue().getName());
        }
        
        System.out.println("");
        
    }
    
    /**
     *
     * Metodo per l'inizializzazione delle stanze del gioco
     * 
     */
    public void initGameRoom(){
         
        setRooms(utility.getRoomsFromDB());
         
        for (Map.Entry<Integer,RoomGame> i: rooms.entrySet()) {
            System.out.println("key: " + i.getKey() + "; value: " + i.getValue().getName());
        }
        
        setCurrentRoom(getRooms().get(1).getId());
        
    }    

    /**
     *
     * Metodo per l'inizializzazione dell'inventario con gli oggetti iniziali del gioco
     * 
     */
    public void initInventory(){
        getInventory().add("torcia");
        getInventory().add("radio");
        getInventory().add("palmare");
        getInventory().add("piccozza");
    }
   
    /**
     *
     * Metodo per il salvataggio della partita corrente
     * 
     * @return Stringa che indica il salvataggio avvenuto con successo o meno
     */
    public String saveGame(){
        StringBuilder s = new StringBuilder();
         
        String gameObjJson = utility.toJson(getObjectGame());
        String roomsJson = utility.toJson(getRooms());
        String inventoryJson = utility.toJson(getInventory());
        String currentRoomJson = utility.toJson(getCurrentRoom());
        
        boolean saved = utility.saveOnFile(gameObjJson,roomsJson,inventoryJson,currentRoomJson);
        
        if(saved){
            
              s.append("PARTITA SALVATA\n"
              + "------------------------------------\n\n");
            
              return s.toString();
        }
        
        else{
            
              s.append("ERRORE DURANTE IL SALVATAGGIO\n"
              + "------------------------------------\n\n");
            
              return s.toString();
            
        }
        
    }
    
    /**
     *
     * Metodo per l'interpretazione della prossima azione da eseguire
     * 
     * @param p: ParserOutput
     * @return Stringa contente un messaggio che seganla l'azione appena eseguita
     */
    public String nextMove(ParserOutput p){
        CommandTypology ct = p.getCommand().getType();
        
        switch (ct){
            case NORTH:
                return changeRoom(rooms.get(getCurrentRoom()).getNorthRoom());
            case SOUTH:
                return changeRoom(rooms.get(getCurrentRoom()).getSouthRoom());
            case EAST:
                return changeRoom(rooms.get(getCurrentRoom()).getEastRoom());
            case WEST:
                return changeRoom(rooms.get(getCurrentRoom()).getWestRoom());
            case INVENTORY:
                return openInventory();
            case USE:
                return verifyObject(p);
            case PICK:
                return pickObject(p);
            case LOOK_AT:
                return lookAtElement(p);
            case OPEN:
                return openObject(p);
            case COMBINE:
                return combineObject(p);
            case END:
                return endGame();
            default:
                return "";
        }
        
    }
    
    /**
     *
     * Metodo per il cambio di stanza dall'attuale alla successiva
     * 
     * @param nextRoomId: Intero rappresentante la stanza successiva
     * @return Stringa contente messaggio che seganala il cambio di stanza
     */
    public String changeRoom(int nextRoomId) {
        StringBuilder s = new StringBuilder();
        
        if (!getRooms().get(getCurrentRoom()).isEnlightened()){
            return "Provando a spostarti al buio, hai sbattuto la testa.\nCredi che sia il caso di accendere una luce?";
        }
        
        if (nextRoomId != 0) {
            
            RoomGame nextRoom = rooms.get(nextRoomId);
            if (nextRoom.isOpened()){
                // nel caso in cui non ho ancora riattivato la corrente, devo disattivare la luce nella stanza che abbandono
                if (!getObjectGame().get("interruttore").isTurnedOn() && getCurrentRoom() != 1 && getCurrentRoom() != 2){
                    getRooms().get(getCurrentRoom()).setEnlightened(false);
                }
                
                setCurrentRoom(nextRoomId);
                
                s.append("Ti trovi in ");
                s.append(nextRoom.getName());
                s.append("\n\n");
                
                if (getRooms().get(getCurrentRoom()).isEnlightened()){
                    s.append(nextRoom.getDescription());
                }
                else {
                    s.append("La stanza è poco illuminata, non riesci a distinguere ciò che ti circonda;\n"
                            + " puoi provare ad utilizzare la torcia che hai con te\n");
                    s.append(nextRoom.getDescription());
                }
                
                s.append("\n\n");
                
                return s.toString();
            }
            else {
                for (String element : getInventory()) {
                    
                    if (getObjectGame().get(element).getType() == TypeObject.KeyObject){
                        int roomId = getObjectGame().get(element).getOpenableRoom();
                        if (roomId == nextRoomId) {
                            
                            getRooms().get(nextRoomId).setOpened(true);
                            
                            setCurrentRoom(nextRoomId);
                            
                            s.append("Hai usato ");
                            s.append(getObjectGame().get(element).getName());
                            s.append("\n");
                            
                            s.append("Ti trovi in ");
                            s.append(nextRoom.getName());
                            s.append("\n\n");
                            s.append(nextRoom.getDescription());
                            s.append("\n\n");
                            
                            return s.toString();
                        }
                    }
                    
                }
                
                return "La porta è chiusa, ti serve la chiave giusta";
            }
        }
        else {
            return "Cerchi di oltrepassare la parete ma fallisci";
        }

    }
    
    /**
     *
     * Metodo per visualizzare gli oggetti contenuti nell'inventario
     * 
     * @return Stringa contente messaggio che segnala gli oggetti contenuti nell'inventario
     */
    public String openInventory(){
        StringBuilder s = new StringBuilder();
        s.append("Il tuo inventario contiene: \n");
        
        
        ListIterator<String> lit = getInventory().listIterator();
        while (lit.hasNext()){
            s.append("- ");
            s.append(lit.next());
            s.append("\n");
        }
        
        
        return s.toString();
    }
    
    /**
     *
     * Metodo che permette l'utilizzo degli oggetti.
     * Controlla che gli oggetti siano all'interno dell'inventario, della stanza
     * o contenitore.
     * 
     * @param p
     * @return Stringa contente messaggio che la descrizione dell'utilizzo dell'oggetto
     */
    public String verifyObject(ParserOutput p){
        StringBuilder s = new StringBuilder();
        
        // bisogna gestire il caso in cui usi i computer
        
        if (p.getObj1() != null){
            if (getObjectGame().get(p.getObj1()) != null){
                if (checkPresence(p.getObj1())){
                    s.append(getObjectGame().get(p.getObj1()).use(this));
                    s.append("\n\n");
                    return s.toString();
                }
                else {
                    return "Mi sa che hai le allucinazioni!";
                }
            }
            if (p.getObj1().equals("computer")) {
                // verificare in che stanza ti trovi
                
                // caso in cui ti trovi in sala controllo
                if (getCurrentRoom() == 6){
                    s.append(getObjectGame().get("computer controllo").use(this));
                }
                // caso in cui ti trovi in laboratorio
                else if (getCurrentRoom() == 10) {
                    s.append(getObjectGame().get("computer laboratorio").use(this));
                }
                
                else {
                    s.append("non c'è nessun computer qui");
                }
            }
            else {
                return "Mi sa che hai le allucinazioni!";
            }
        }
        
        return s.toString();
    }
    
    /**
     *
     * Metodo per prendere oggetti presenti nella stanza attuale o in un contenitore
     * 
     * @param p
     * @return Stringa contente messaggio che indica se l'oggetto è stato preso o meno
     */
    public String pickObject(ParserOutput p) {
        StringBuilder s = new StringBuilder();
        // invoco il metodo pick sul primo oggetto che ricevo in input
        if (p.getObj1() != null){
            if (getObjectGame().get(p.getObj1()) != null){
                if (checkPresence(p.getObj1())){
                    s.append(getObjectGame().get(p.getObj1()).pick(this));
                    s.append("\n\n");
                    return s.toString();
                }
                else if (p.getObj1().equals("pistola")) {
                    if (checkPresence("pistola scarica")){
                        s.append(getObjectGame().get("pistola scarica").pick(this));
                        s.append("\n\n");
                        return s.toString();
                    }
                }
                
                else {
                    return "Mi sa che hai le allucinazioni!";
                }
            }
            
            
            
            else if (p.getObj1().equals("chiave")){
                // controllo se c'è una chiave all'interno della stanza
                for (Map.Entry<String, ObjectGame> e : getObjectGame().entrySet()){
                    if (e.getValue().getType() == TypeObject.KeyObject && e.getValue().getLocation() == getCurrentRoom()){
                        s.append(getObjectGame().get(e.getKey()).pick(this));
                        return s.toString();
                    }
                }
                // controllo se c'è una chiave all'interno di un contenitore aperto della stanza
                for (Map.Entry<String, ObjectGame> e : getObjectGame().entrySet()){
                    if (e.getValue().getType() == TypeObject.ObjectContainer && e.getValue().isOpened() && e.getValue().getLocation() == getCurrentRoom()){
                        for (Map.Entry<String, ObjectGame> f : getObjectGame().entrySet()){
                            if (f.getValue().getType() == TypeObject.KeyObject && f.getValue().getContainer().equals(e.getValue().getName())){
                                s.append(getObjectGame().get(f.getKey()).pick(this));
                                return s.toString();
                            }
                        }
                    }
                }

                return "Quale chiave?";

            }
            
            else if (p.getObj1().equals("computer")){
                if (checkPresence("computer laboratorio") || checkPresence("computer controllo")){
                    s.append(getObjectGame().get("computer controllo").pick(this));
                    return s.toString();
                }
            }
            
            else {
                return "Mi sa che hai le allucinazioni";
            }
        }
        
        return "";
    }
    
    /**
     *
     * Metodo per la pulizia delle strutture, utilizzato per quando si abbandona il gioco
     * 
     */
    public void clear(){
        
        objectGame.clear();
        rooms.clear();
        inventory.clear();
        currentRoom = 1;
        
    }
    
    /**
     *
     * Metodo che permette di osservare un oggetto o una stanza.
     * 
     * @param p
     * @return Stringa contente la descrizione dell'oggetto o della stanza
     */
    public String lookAtElement(ParserOutput p) {
        StringBuilder s = new StringBuilder();
        
        if (p.getObj1() != null){
            if (p.getObj1().equals("chiave")){
                // stampo tutte le chiavi che ho in inventario
                s.append("Hai raccolto le seguenti chiavi:\n");
                
                ListIterator<String> lit = getInventory().listIterator();
                int count = 0;
                while (lit.hasNext()){
                    String key = lit.next(); 
                    if (getObjectGame().get(key).getType() == TypeObject.KeyObject){
                        s.append("- ");
                        s.append(key);
                        s.append(": ");
                        s.append(getObjectGame().get(key).getDescription());
                        s.append("\n");
                        count++;
                    }
                }
                
                if (count == 0){
                    s.append("No, non hai chiavi con te");
                }
                
                return s.toString();  
            }
            
            else if (p.getObj1().equals("computer")) {
                if (checkPresence("computer laboratorio")){
                    s.append(getObjectGame().get("computer laboratorio").getDescription());
                }
                
                else if (checkPresence("computer controllo")){
                    s.append(getObjectGame().get("computer controllo").getDescription());
                }
                
                else {
                    s.append("mi sa che hai le allucinazioni");
                }
                
                return s.toString();
            }
            
            else if (p.getObj1().equals("pistola") && reasearchInvObj("pistola scarica")){
                ListIterator<String> lit = getInventory().listIterator();
                
                while (lit.hasNext()) {
                    String element = lit.next();
                    
                    
                    if (element.equals("pistola scarica")){
                        s.append(getObjectGame().get(element).getDescription());
                        s.append("\n\n");
                    }
                    
                }
                
                return s.toString();
            }
            
            
            else if (getObjectGame().get(p.getObj1()) != null){
                if (checkPresence(p.getObj1())){
                    s.append(getObjectGame().get(p.getObj1()).getDescription());
                    s.append("\n\n");
                    return s.toString();
                }
                else {
                    return "Mi sa che hai le allucinazioni!";
                }
            }
            else {
                return "Mi sa che hai le allucinazioni!";
            }
        }
        
        else {
            
            // caso in cui la stanza è buia
            if (!getRooms().get(getCurrentRoom()).isEnlightened()){
                return "La stanza è buia, non riesci a vedere cosa ti circonda\nDovresti accendere una luce";
            }
            
            s.append(getRooms().get(getCurrentRoom()).getObservedDescription());
            s.append("\n\n");
            return s.toString();
        }
        
    }
    
    /**
     *
     * Metodo che permette l'apertura di oggetti contenitori
     * 
     * @param p
     * @return Stringa contente gli oggetti presenti nel contenitore nel caso di buona
     * riuscita di apertura
     */
    public String openObject(ParserOutput p) {
        StringBuilder s = new StringBuilder();
        
        if (p.getObj1() != null){
            
            if (p.getObj1().equals("quadro") && getCurrentRoom() == 6){
                    s.append(getObjectGame().get("quadro elettrico").open(this));
                    return s.toString();
            }
            
            if (getObjectGame().get(p.getObj1()) != null){
                if (checkPresence(p.getObj1())){
                    s.append(getObjectGame().get(p.getObj1()).open(this));
                    s.append("\n\n");
                    return s.toString();
                }
                else {
                    return "Mi sa che hai le allucinazioni!";
                }
            }
            else {
                return "Mi sa che hai le allucinazioni!";
            }
        }
        
        return s.toString();
    }
    
    /**
     *
     * Metodo che permette la combinazione degli oggetti combinabili
     * 
     * @param p
     * @return Stringa contenente messaggio che indica la buona riuscita o meno della combinazione
     */
    public String combineObject(ParserOutput p) {
        StringBuilder s = new StringBuilder();
        
        if (reasearchInvObj(p.getObj1()) && reasearchInvObj(p.getObj2())){ 
            s.append("Provi a combinare ");
            s.append(p.getObj1());
            s.append(" e ");
            s.append(p.getObj2());
            s.append("\n\n");
        }
        else {
            return "Non hai tali oggetti in inventario";
        }
        
        
        if (p.getObj1().equals("pistola") && p.getObj2().equals("caricatore") || p.getObj1().equals("caricatore") && p.getObj2().equals("pistola")){
                if (reasearchInvObj("pistola scarica") && reasearchInvObj("caricatore")){
                    // metti in inventario il nuovo oggetto ottenuto
                        getInventory().add(getObjectGame().get("pistola scarica").getObjectObtained());
                        // rimuovi dall'inventario i due oggetti utilizzati
                        getInventory().remove(getInventoryIndex("pistola scarica"));
                        getInventory().remove(getInventoryIndex("caricatore"));
                        
                        s.append("Hai ottenuto ");
                        s.append(getObjectGame().get("pistola scarica").getObjectObtained());
                }
            }

        
        if (p.getObj1() != null && p.getObj2() != null){
            if (getObjectGame().get(p.getObj1()) != null && getObjectGame().get(p.getObj2()) != null){
                if (reasearchInvObj(p.getObj1()) && reasearchInvObj(p.getObj2())){
                    
                    if (getObjectGame().get(p.getObj1()).getCombinedObject().equals(p.getObj2())){
                        // metti in inventario il nuovo oggetto ottenuto
                        getInventory().add(getObjectGame().get(p.getObj1()).getObjectObtained());
                        // rimuovi dall'inventario i due oggetti utilizzati
                        getInventory().remove(getInventoryIndex(p.getObj1()));
                        getInventory().remove(getInventoryIndex(p.getObj2()));
                        
                        s.append("Hai ottenuto ");
                        s.append(getObjectGame().get(p.getObj1()).getObjectObtained());
                    }
                    else {
                        s.append("I due oggetti non sono compatibili");
                    }
                    
                }
               
            }
            
            else {
                s.append("Mi sa che hai le allucinazioni!");
            }
            
            return s.toString();
        }
        
        return s.toString();
    }
    
    public String endGame() {
        return "";
    }
    
    /**
     *
     * Metodo che controlla se un oggetto è presente o meno all'interno di un contenitore
     * 
     * @param object
     * @return true: se l'oggetto è presente all'interno di un contenitore o nella stanza
     *         false: se l'oggetot non è presente
     */
    public boolean checkPresence(String object){
        
        if (getObjectGame().get(object).getLocation() == getCurrentRoom()){
            return true;
        }
        
        // prendo il contenitore dell'oggetto
        String container = getObjectGame().get(object).getContainer();
        if(!container.equals("") && !container.equals("null")){
            int roomOfContainer = getObjectGame().get(container).getLocation();
            if (roomOfContainer == getCurrentRoom()){
                if (getObjectGame().get(container).isOpened()){
                    return true;
                }
            }
        }
        
        for (String element : getInventory()){
            if (object.equals(element)){
                return true;
            }
        }
        
        return false;
    }
    
    /**
     *
     * Controlla se un thread è attivo o meno
     * 
     */
    public void verifyThread(){
        if (getObjectGame().get("radio").isTurnedOn()){
            System.out.println("Il thread è attivo");
            RadioThread radioThread = new RadioThread();
            radioThread.start();
        }
        
        if (getObjectGame().get("interruttore").isTurnedOn()){
            AlarmThread alarmThread = new AlarmThread();
            alarmThread.start();
        }
    }
}
