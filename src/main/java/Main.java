import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Driver code to run the game
        Scanner scan = new Scanner(System.in);
        TextAdventure ta = new TextAdventure();

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("|-------DUNGEON CRAWLER-------|");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

        ta.printHelp();
        System.out.println("Press any key to play!");
        scan.nextLine();
        ta.gameLoop();
    }
}
