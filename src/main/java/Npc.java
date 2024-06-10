import java.util.Scanner;

public class Npc extends Enemy {
    // This class contains methods and attributes that the NPC uses

    private Scanner scan = new Scanner(System.in);

    public Npc() {
        super("Dareth", 200);
    }
    
    public void talk() {
        System.out.println("NPC: Greetings traveller. I am Dareth the Forsaken. What brings you to this wretched place? Ah, no matter. Perhaps with our combined efforts we can find a way out of this sorrowful place. What do you say? (Y/N)");
    }
    public String dialogueTree() {
        // Returns a string that contains the outcome of the dialogue tree to be further processed by the main class
        // The while loops are necessary to return in case of incorrect user input
        while (true) {
            String response = scan.nextLine().toLowerCase();

            if (response.equals("y")) {
                while (true) {
                    System.out.println("NPC: A wise decision. We should continue north.");
                    System.out.println("NPC: Take this map. It will help you find your way in your journey.");

                    System.out.println("1. Ask who he is.");
                    System.out.println("2. Do you know anything about this place?");
                    response = scan.nextLine();

                    if (response.equals("1")) {
                        System.out.println("NPC: I am a former soldier who fought in the Great War 20 years ago. ");
                        return "allies";
                    }
                    else if (response.equals("2")) {
                        System.out.println("NPC: This is a place where enemies of the state are imprisoned. It is strange that you would be able to leave your cell.");
                        return "allies";
                    }
                    else {
                        System.out.println("NPC: I didn't understand that");
                    }
                }
            }
            else if (response.equals("n")) {
                    System.out.println("NPC: Unfortunate. However, it is wise not to trust strangers.");
                    System.out.println("1. Attack Dareth.");
                    System.out.println("2. Leave.");
                    System.out.println("3. Ask what he is doing here.");

                while (true) {

                    response = scan.nextLine();
                    if (response.equals("1")) {
                        System.out.println("You attack Dareth. He responds quickly and without hesitation.");
                        return "combat";
                    }
                    else if (response.equals("2")) {
                        return "leave";
                    }
                    else if (response.equals("3")) {
                        while (true) {
                            System.out.println("NPC: The same as you, I suppose. Even if you do not want to join forces, I still have a word of advice: beware the ");
                            return "leave";
                        }
                    }
                    else {
                        System.out.println("NPC: What do you mean?");
                    }
                }
            }
            else {
                System.out.println("NPC: Hmm, I didn't understand that.");
            }
        }
    }
}