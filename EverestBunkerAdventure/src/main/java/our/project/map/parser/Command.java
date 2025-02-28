/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package our.project.map.parser;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * Classe per la gestione dei comandi
 * 
 * @author RES
 */
public class Command {
    private final CommandTypology type;

    private final String name;

    private Set<String> alias;

    /**
     *
     * @param type
     * @param name
     */
    public Command(CommandTypology type, String name) {
        this.type = type;
        this.name = name;
    }

    /**
     *
     * @param type
     * @param name
     * @param alias
     */
    public Command(CommandTypology type, String name, Set<String> alias) {
        this.type = type;
        this.name = name;
        this.alias = alias;
    }

    /**
     *
     * @return name: Stringa rappresentante il nome del comando
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return Set di stringhe rappresentanti gli Alias del comando
     */
    public Set<String> getAlias() {
        return alias;
    }

    /**
     *
     * Imposta gli alias del comando
     * 
     * @param alias: Set di stringhe
     */
    public void setAlias(Set<String> alias) {
        this.alias = alias;
    }

    /**
     *
     * Imposta gli alias del comando
     * 
     * @param alias: Array di stringhe
     */
    public void setAlias(String[] alias) {
        this.alias = new HashSet<>(Arrays.asList(alias));
    }

    /**
     *
     * @return type: CommandTypology tipo di comando
     */
    public CommandTypology getType() {
        return type;
    }

}
