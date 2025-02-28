/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package our.project.map.parser;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Interpretatore di comandi
 * 
 * @author RES
 */
public class Parser {

    public Parser() {}

    /**
     * 
     * Controlla se il comando inserito è presente o meno nella lista dei comandi
     * 
     * @param token: Stringa rappresentante il comando inserito da tastiera
     * @param commands: Lista di CommandType
     * @return Intero: -1: Se il comando non è presente
     *                 i: Indice rappresentante la posizione del comando nella lista
     */
    private int checkForCommand(String token, List<Command> commands) {
        for (int i = 0; i < commands.size(); i++) {
            if (commands.get(i).getName().equals(token) || commands.get(i).getAlias().contains(token)) {
                return i;
            }
        }
        return -1;
    }

    /**
     *
     * Divide la stringa rappresentante il comando in token (componenti)
     * 
     * @param string: Comando inserito
     * @return tokens: Lista di stringhe
     */
    public static List<String> parseString(String string) {
        List<String> tokens = new ArrayList<>();
        String[] split = string.toLowerCase().split("\\s+");
        for(String t : split) {
                tokens.add(t);
        }
        return tokens;
    }
    
    /**
     *
     * Divide il comando appena inserito in una lista di token
     * 
     * @param command
     * @param commands
     * @return ParserOutput Che identifica il comando appena inserito
     */
    public ParserOutput parse(String command, List<Command> commands) {
       List<String> tokens = parseString(command);
       
       if (!tokens.isEmpty()) {
            int ic = checkForCommand(tokens.get(0), commands);
            if (ic > -1) {
                
                if (tokens.size() > 1 && tokens.size() < 3) {
                    return new ParserOutput(commands.get(ic), tokens.get(1));
              
                } else if(tokens.size() > 2 && tokens.size() < 4){
                    return new ParserOutput(commands.get(ic), tokens.get(1), tokens.get(2));
                }else{
                 return new ParserOutput(commands.get(ic));
                }
            } else {
                return new ParserOutput(null, null);
            }
        } else {
            return null;
        }
    }
}
