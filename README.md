# Everest Bunker Adventure

## Description

Everest Bunker Adventure is an interactive text adventure game written in Java. The player is trapped in a bunker on Mount Everest and must solve puzzles, interact with objects and characters to unravel the story and find a way out.

**Please note: This game is only available in Italian.**

**Storyline**
You awaken in an isolated research bunker on Everest, your mind a blank slate. Soon, you discover the bunker was hastily abandoned, leaving behind dark secrets. Explore the environment, solve puzzles, and interact with the remnants of those who lived there to uncover the truth and find a way out.

## Project Structure

The project's structure is organized into several folders, each with a specific purpose:

* **asset**: Contains all the resources used by the game, such as images, databases, and save data.
    * **database**: Database files used to store game information.
    * **img**: Images used in the game's graphical interface.
    * **saveData**: Game save data.
    * **star.json**: Configuration or data file in JSON format.
* **src/main/java/our/project/map**: Contains the Java source code for the game.
    * **elements**: Defines the classes for the various objects and elements in the game.
        * **ComputerObject.java**: Interactive computer object.
        * **FixedObject.java**: Static objects in the game environment.
        * **GunObject.java**: Weapon objects.
        * **KeyObject.java**: Key objects for solving puzzles.
        * **LightingObject.java**: Lighting objects.
        * **ObjectContainer.java**: Objects that can hold other objects.
        * **ObjectGame.java**: Base class for all game objects.
        * **PortableObject.java**: Objects that the player can carry.
        * **RoomGame.java**: Defines the rooms in the bunker.
        * **Star.java**: Game element related to stars or constellations.
        * **TypeObject.java**: Defines the types of objects in the game.
    * **engine**: Contains the main game engine code.
        * **Engine.java**: Main game engine class.
        * **GameController.java**: Manages the game's logic and flow.
    * **graphicinterface**: Contains the code for the graphical user interface.
        * **ChatUl.java**: Manages the user interface for the game's chat.
        * **GameUl.form**: Form file for the graphical user interface.
        * **GameUl.java**: Java class for the graphical user interface.
    * **parser**: Manages the parsing of player commands.
        * **Command.java**: Defines the commands available in the game.
        * **CommandTypology.java**: Defines the types of commands.
        * **Parser.java**: Parses the player's input commands.
        * **ParserOutput.java**: Manages the output of the command parser.
    * **utility**: Contains various utility classes.
        * **AlarmThread.java**: Manages a thread for alarms.
        * **ChatClient.java**: Client for the game's chat.
        * **ChatClientHandler.java**: Manages client connections for the chat.
        * **ChatServer.java**: Server for the game's chat.
        * **ChatServerHandler.java**: Manages server connections for the chat.
        * **DBManager.java**: Manages interaction with the database.
        * **FileManager.java**: Manages file operations.
        * **RadioThread.java**: Manages a thread for the game's radio.

## How to Run the Game

1.  Clone the GitHub repository.
2.  Ensure you have Java installed on your system.
3.  Compile the Java source code.
4.  Run the game's main class.



