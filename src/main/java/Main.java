import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("-------DUNGEON CRAWLER-------\n");
        System.out.println("Commands: ");
        System.out.println("GO {location}: moves to location if possible");
        System.out.println("LOOK {object}: prints description of object");
        System.out.println("TAKE {object}: adds object to inventory");
        System.out.println("USE {object}: uses the main action of an object");
        System.out.println("INFO: prints status of character");

        System.out.println("Press any key to play!");
        scan.nextLine();

        System.out.println("What is your name? ");
        String name = scan.nextLine();
        Player player = new Player(name);
        
        System.out.println("\nYou wake up in a dingy, damp prison cell. In front of you, a rusted door is closed. To the right, you notice an iron key.");
        
        while (true) {
            String response = scan.nextLine().toLowerCase();
            if (response.equals("info")) {
                player.printInfo();
            }

            while (true) {
                if (response.equals("look key")) {
                    System.out.println("This key looks like the perfect fit to your cell door.");
                }
                else if (response.equals("take key")) {
                    System.out.println("You added the key to your inventory");
                    player.addItem("key");
                }
            }
        }
    }
}