import java.util.ArrayList;
import java.util.Collections;

public class Player {
    private double health;
    private ArrayList<String> inventory;
    private final int MAX_ITEMS = 5;

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
            System.out.print(inventory.get(i) + " ");
        }
        System.out.println();
    }
    public void addItem(String item) {
        if (inventory.size() < MAX_ITEMS) {
            inventory.add(item);
            sortInventory();
            System.out.println("Added " + item + " to inventory");
        }
        else {
            System.out.println("Your inventory is full");
        }
    }
    public void removeItem(String item) {
        for (int i = 0; i < inventory.size(); i++) {
            if (item.equals(inventory.get(i))) {
                System.out.println("Removed " + inventory.get(i));
                inventory.remove(i);
                sortInventory();
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
    public void sortInventory() {
        Collections.sort(inventory);
    }
    public void setHealth(double health) {
        this.health = health;
    }
    
    
}