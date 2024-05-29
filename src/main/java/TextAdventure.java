import java.util.Scanner;
import java.util.Arrays;

public class TextAdventure {

    private boolean gameOver;
    private Scanner scan;
    private final String[] contextIndependentCommands;
    private Player player;
    private String currentState;
    Slime slime1 = new Slime();
    Slime slime2 = new Slime();
    private npc dareth;
    private String npcOutcome;

    public TextAdventure() {
        gameOver = false;
        scan = new Scanner(System.in);
        contextIndependentCommands = new String[]{"info", "drop", "help"};
        player = new Player();
        currentState = "cell";
        dareth = new npc();
    }

    public void gameLoop() {
        System.out.println("\nYou wake up in a dingy, damp prison cell. In front of you, a rusted door is closed. To the right, you notice an iron key.");
        while (!gameOver) {
            System.out.print("> ");
            String command = scan.nextLine();
            processCommand(command);
        }
    }

    public void processCommand(String command) {
        if (Arrays.asList(contextIndependentCommands).contains(command)) {
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
                        if (player.itemInInventory("sword")) {
                            System.out.println("You use your sword to disarm Dareth. He lies gasping on the ground, no longer a threat.");
                            System.out.println("The greatsword he drops is much stronger than your current one.");
                            System.out.println("The only remaining path is to the north.");
                            currentState = "wd";
                        }
                        else {
                            System.out.println("You are defenseless against his attacks. You take 50 damage. You are forced to retreat to the north.");
                            player.changeHealth(-50);
                            currentState = "traps";
                        }
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
                    currentState.equals("traps");
                }
                else {
                    processUnrecognizedCommand(command);
                }
            }
            else if (currentState.equals("traps")) {
            }
        }
    }
    public void processIndependentCommand(String command) {
        if (command.equals("info")) {
            player.printInfo();
            System.out.println("Current location: " + currentState);
        }
        else if (command.equals("drop")) {
            player.removeItem(command.substring(5));
        }
        else if (command.equals("help")) {
            printHelp();
        }
    }
    public void processUnrecognizedCommand(String command) {
        System.out.println(command + " is not a valid command in this context.");
    }
    
    public void printHelp() {
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