import java.util.Scanner;
import java.util.Arrays;

public class TextAdventure {
    // This class contains the core game logic

    private boolean gameOver;
    private Scanner scan;
    private final String[] contextIndependentCommands;
    private Player player;
    private String currentState; // Contains the current game location as a string which is used for interpreting context dependent commands
    Slime slime1 = new Slime();
    Slime slime2 = new Slime();
    private npc dareth;
    private String npcOutcome;
    private Map map;
    private Dragon dragon;

    public TextAdventure() {
        gameOver = false;
        scan = new Scanner(System.in);
        contextIndependentCommands = new String[]{"info", "drop", "help", "use map"}; // This is the list of commands which should be able to be run in any situation
        player = new Player();
        currentState = "cell";
        dareth = new npc();
        map = new Map();
        dragon = new Dragon();
    }

    public void gameLoop() {
        // Continually prompt for commands until the game ends
        System.out.println("\nYou wake up in a dingy, damp prison cell. In front of you, a rusted door is closed. To the right, you notice an iron key.");
        while (!gameOver) {
            System.out.print("> ");
            String command = scan.nextLine();
            processCommand(command);
        }
    }

    public void processCommand(String command) {

        // Short circuit evaluation to check if command is long enough to be one of the context independent commands
        if (command.length() >= 4 && Arrays.asList(contextIndependentCommands).contains(command.substring(0, 4)) || command.equals("use map")) {
            processIndependentCommand(command);
        }
        else {
            if (currentState.equals("cell")) {
                if (command.equals("look key")) {
                    System.out.println("You see a strong iron key that looks like it would fit perfectly into the door in front of you");
                }
                else if (command.equals("take key")) {
                    player.addItem("key");
                }
                else if (command.equals("use key")) {
                    if (player.itemInInventory("key")) {
                        System.out.println("You used the key to open the door.");
                        currentState = "hallway";
                        System.out.println("You are standing in a long hallway. A rusty sword lies on the floor. There are two passages: one to the north and one to the south.");
                    }
                    else {
                        System.out.println("You don't have a key in your inventory");
                    }
                }
                else {
                    processUnrecognizedCommand(command);
                }
            }
            else if (currentState.equals("hallway")) {
                if (command.equals("look sword")) {
                    System.out.println("A sword whose better days are behind it. Not much use in a fight, but better than nothing. ATK: 1");
                }
                else if(command.equals("take sword")) {
                    if (!player.itemInInventory("sword")) {
                        player.addItem("sword");
                    }
                }
                else if (command.equals("go north") || command.equals("go south")) {
                    // Doesn't actually matter if you go north or south because I'm lazy and
                    currentState = "hallway2";
                    System.out.println("You are attacked by a group of slimes! You can run away to the west or fight them.");
                }
                else {
                    processUnrecognizedCommand(command);
                }
            }
            else if (currentState.equals("hallway2")) {
                if (command.equals("look slime")) {
                    System.out.println("Two common slimes. Not very dangerous, but still a risk to fight them.");
                }
                else if (command.equals("go west")) {
                    System.out.println("You arrive in a small room. You see a middle-aged man leaning against the wall. He is holding a greatsword.\n");
                    dareth.talk();
                    npcOutcome = dareth.dialogueTree();

                    if (npcOutcome.equals("combat")) {
                        if (player.itemInInventory("sword") && player.getHealth() >= 110) {
                            System.out.println("You use your sword to disarm Dareth. He lies gasping on the ground, no longer a threat.");
                            System.out.println("The greatsword he drops is much stronger than your current one.");
                            System.out.println("The only remaining path is to the north.");
                            currentState = "wd";
                        }
                        else if (player.itemInInventory("sword")) {
                            System.out.println("Dareth attacks you, dealing 100 damage.");
                            player.changeHealth(-100);
                        }
                        else {
                            System.out.println("You are defenseless against his attacks. You take 50 damage. You are forced to retreat to the north.");
                            player.changeHealth(-50);
                            currentState = "traps";
                            printLocationInfo();
                        }
                    }
                    else if (npcOutcome.equals("allies")) {
                        System.out.println("You go north.");
                        player.addItem("map");
                        currentState = "traps";
                        printLocationInfo();
                    }
                }
                else if (command.equals("use sword") && player.itemInInventory("sword")) {
                    System.out.println("You attack the slimes.");
                    slime1.takeDamage(3);
                    slime2.takeDamage(3);
                    System.out.println("The slimes drop a relic that increases your health.");
                    player.setHealth(120);
                }
                else {
                    processUnrecognizedCommand(command);
                }
            }
            else if (currentState.equals("wd")) {
                if (command.equals("take greatsword")) {
                    player.addItem("greatsword");
                }
                else if (command.equals("go north")) {
                    currentState = "traps";
                    printLocationInfo();
                }
                else {
                    processUnrecognizedCommand(command);
                }
            }
            else if (currentState.equals("traps")) {
                if (command.equals("go left")) {
                    System.out.println("As you step on the left hallway, you hear a click, and suddenly flaming arrows fire out of the holes at you!");
                    currentState = "trapped hallway";
                }
                else if (command.equals("go right")) {
                    System.out.println("The gargoyle statue's eyes light up as you come near at, and its mouth breathes fire at you.");
                    currentState = "trapped hallway";
                }
                else if (command.equals("look pot")) {
                    System.out.println("A strong metal pot that could be used to hold something");
                }
                else if (command.equals("take pot")) {
                    player.addItem("pot");
                }
                else if (command.equals("use pot")){
                    System.out.println("You fill up the pot with some of the nearby water");
                    player.changeItem("pot", "waterpot");
                }
            }
            else if (currentState.equals("trapped hallway")) {
               if (!player.itemInInventory("waterpot")) {
                    System.out.println("You can't put out the fire and you take 10 damage");
                    player.changeHealth(-10);
                    currentState = "arena";
                    printLocationInfo();
                }
                if ((command.equals("use pot") || command.equals("use waterpot")) && player.itemInInventory("waterpot")) {
                    System.out.println("You use the pot to put out the fire, and you don't take any damage.");
                    currentState = "arena";
                    printLocationInfo();
                }
            }
            else if (currentState.equals("boss")) {
                if (command.equals("go east")) {
                    System.out.println("You make a run for it. You're too slow and the dragon catches up. It scoops you in its talons and takes you back to the cell where you started, but this time you don't have a key.");
                }
                else if (command.equals("use sword") && player.itemInInventory("sword")) {
                    System.out.println("The rusty sword bounces off of the dragon's hide, dealing no damage.");
                    player.changeHealth(-50);
                    System.out.println("You take 50 damage.");
                }
                else if (command.equals("use greatsword") && player.itemInInventory("greatsword")) {
                    System.out.println("The greatsword deals a lot of damage.");
                    dragon.takeDamage(500);
                    if (dragon.getHealth() < 0) {
                        System.out.println("Against all odds, you defeat the dragon.");
                        gameOver();
                    }
                    System.out.println("The dragon retaliates and you take 50 damage");
                    player.changeHealth(-50);
                }
                else {
                    processUnrecognizedCommand(command);
                }
            }
        }
    }
    public void processIndependentCommand(String command) {
        // If the command is context independent, determine which command it is and call the correct method
        if (command.length() >= 4) {
            if (command.substring(0, 4).equals("info")) {
                player.printInfo();
                System.out.println("Current location: " + currentState);
            }
            else if (command.substring(0, 4).equals("drop")) {
                player.removeItem(command.substring(5));
            }
            else if (command.substring(0, 4).equals("help")) {
                printHelp();
            }
            else if (command.equals("use map")) {
                if (player.itemInInventory("map")) {
                    System.out.println("map should be printing");
                    map.printMap();
                }
                else {
                    processUnrecognizedCommand(command);
                }
            }
        }
    }
    public void processUnrecognizedCommand(String command) {
        // Standard error message
        System.out.println("'" + command + "'" + " is not a valid command in this context.");
    }

    public void printLocationInfo(){
        // Prints information based on the current game state
        if (currentState.equals("traps")) {
            System.out.println("You arrive at a long hallway. An empty pot lies on the ground, next to a pool of water. You can go to the left, where you see some suspicious looking holes in the walls. Or you can go the right, where you see a creepy gargoyle statue.");
        }
        else if (currentState.equals("arena")) {
            System.out.println("You arrive in a large open space. It is eerily empty, and quiet. An eerie countdown begins.");
            try {
                Thread.sleep(1000);
                for (int i = 5; i >= 1; i--) {
                    System.out.print(i);
                    for (int j = 0; j < 5; j++) {
                        Thread.sleep(250);
                        System.out.print(".");
                    }
                    System.out.println();
                }
            }
            catch (Exception e) {
                System.out.println("Error");
            }
            dragon.print();
            System.out.println("A dragon swoops down from the sky.");
            String dragonOutcome = dragon.talk();
            if (dragonOutcome.equals("correct")) {
                System.out.println("You exit the arena, into the sunlight which you haven't seen for a while. ");
                gameOver = true;
                gameOver();
            }
            else {
                currentState = "boss";
                System.out.println("The dragon attacks you, breathing fire out of its mouth. You can try to make a run for it to the exit to the east or stand and fight.");
            }
        }
    }

    public void gameOver() {
        System.out.println("GAME OVER");
    }
    
    public void printHelp() {
        // Documentation for the user
        System.out.println("Commands: ");
        System.out.println("GO {location}: moves to location if possible");
        System.out.println("LOOK {object}: prints description of object");
        System.out.println("TAKE {object}: adds object to inventory");
        System.out.println("USE {object}: uses the main action of an object");
        System.out.println("DROP {object}: removes item from inventory (there is limited inventory space)");
        System.out.println("INFO: prints status of character");
        System.out.println("HELP: prints this message");
    }
}