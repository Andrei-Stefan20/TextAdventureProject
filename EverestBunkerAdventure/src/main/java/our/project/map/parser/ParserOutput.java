/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package our.project.map.parser;

/**
 *
 * Classe che gestisce il comando riconosciuto valido
 * 
 * @author RES
 */
public class ParserOutput {
    private Command command;

    private String  obj1;
    private String obj2;

    /**
     * 
     * 
     * @param command 
     */
    public ParserOutput(Command command) {
        this.command = command;
        this.obj1 = null;
        this.obj2 = null;
    }
    
    /**
     * 
     * 
     * @param command
     * @param object 
     */
    public ParserOutput(Command command, String object) {
        this.command = command;
        this.obj1 = object;
        this.obj2 = null;
    }

    /**
     * 
     * 
     * @param command
     * @param obj1
     * @param obj2 
     */
    public ParserOutput(Command command, String obj1, String obj2) {
        this.command = command;
        this.obj1 = obj1;
        this.obj2 = obj2;
    }

    /**
     * 
     * @return restituisce un enumerativo di tipo Comando che identifica l'azione da eseguire
     */
     public Command getCommand() {
        return command;
    }

     /**
      * 
      * Imposta il comando
      * 
      * @param command 
      */
    public void setCommand(Command command) {
        this.command = command;
    }

    /**
     * 
     * 
     * @return restituisce una stringa che identifica il primo oggetto coinvolto nell'azzione
     */
    public String getObj1() {
        return obj1;
    }

    /**
     * 
     * Imposta il primo oggetto coinvolto nel comando
     * 
     * @param obj
     */
    public void setObj1(String obj) {
        this.obj1 = obj;
    }

    /**
     * 
     * 
     * @return restituisce una stringa che identifica il secondo oggetto coinvolto nell'azzione
     */
    public String getObj2() {
        return obj2;
    }

    /**
     * 
     * Imposta il secondo oggetto coinvolto nel comando
     * 
     * @param obj 
     */
    public void setObj2(String obj) {
        this.obj2 = obj;
    }
   
}
