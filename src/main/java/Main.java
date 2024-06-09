import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Drive code to run the game
        Scanner scan = new Scanner(System.in);
        TextAdventure ta = new TextAdventure();

        System.out.println("-------DUNGEON CRAWLER-------\n");
        ta.printHelp();
        System.out.println("Press any key to play!");
        scan.nextLine();
        ta.gameLoop();
    }
}
