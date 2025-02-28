/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package our.project.map.engine;

import our.project.map.parser.Parser;
import our.project.map.parser.ParserOutput;

/**
 *
 * Classe che sviluppa il motore di gioco
 * 
 * @author Gruppo RES
 */
public class Engine {
    
    private final GameController game = new GameController();
    private final Parser parser = new Parser();
    
    /**
     *
     * Metodo per la pulizia di tutte le stutture di gioco
     * 
     */
    public void clear(){
        
        game.clear();
        
    }
    
    /**
     *
     * Metodo per l'inizio di una nuova partita
     * 
     */
    public void startNewGame() {
        game.startNewGame();
    }
    
    /**
     *
     * Metodo per il ripristino di una partita salvata
     * 
     */
    public void loadGame() {
        game.loadGame();
    }
    
    /**
     *
     * Salvataggio dello stato della partita
     * 
     */
    public String saveGame() {
        return game.saveGame();
    }
    
    /**
     *
     * @param command: Stringa che rappresenta il comando inserito
     * @return Prossima mossa da sviluppare nel gioco
     */
    public String processingInput(String command) {
        
        ParserOutput p = parser.parse(command ,game.getCommands());
        if (p == null || p.getCommand() == null) {
            return "Spiegati meglio";
        }else {         
            return game.nextMove(p);
        }
    }
    
    
    
}
