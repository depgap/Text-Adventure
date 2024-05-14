import java.util.Scanner;
import java.util.Arrays;

public class TextAdventure {

    private boolean gameOver;
    private Scanner scan;
    private final String[] contextIndependentCommands;
    private Player player;
    private String currentState;

    public TextAdventure() {
        gameOver = false;
        scan = new Scanner(System.in);
        contextIndependentCommands = new String[]{"info", "drop", "help"};
        player = new Player();
        currentState = "cell";
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
                    }
                    else {
                        System.out.println("You don't have a key in your inventory");
                    }
                }
            }
        }
    }
    public void processIndependentCommand(String command) {
        if (command.equals("info")) {
            player.printInfo();
        }
        else if (command.equals("drop")) {
            player.removeItem(command.substring(5));
        }
        else if (command.equals("help")) {
            printHelp();
        }
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