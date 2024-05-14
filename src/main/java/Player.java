import java.util.ArrayList;

public class Player {
    private double health;
    private ArrayList<String> inventory;

    public Player() {
        health = 100;
        inventory = new ArrayList<String>();
    }
    public void printInfo() {
        System.out.println("You have " + health + " health remaining");
        System.out.print("Inventory: ");
        if (inventory.isEmpty()) {
            System.out.println("You don't have anything in your inventory");
            return;
        }
        for (int i = 0; i < inventory.size(); i++) {
            System.out.print(inventory.get(i));
        }
        System.out.println();
    }
    public void addItem(String item) {
        inventory.add(item);
        System.out.println("Added " + item + " to inventory");
    }
    public void removeItem(String item) {
        for (int i = 0; i < inventory.size(); i++) {
            if (item.equals(inventory.get(i))) {
                System.out.println("Removed " + inventory.get(i));
                inventory.remove(i);
                return;
            }
        }
        System.out.println("Could not find " + item + " in inventory");
    }
    public boolean itemInInventory(String item) {
        for (int i = 0; i < inventory.size(); i++) {
            if (item.equals(inventory.get(i))) {
                return true;
            }
        }
        return false;
    }
    
    
}