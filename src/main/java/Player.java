import java.util.ArrayList;

public class Player {
    // A class to manage the player and inventory system
    private double health;
    private ArrayList<String> inventory;
    private final int MAX_ITEMS = 5;

    public Player() {
        health = 100;
        inventory = new ArrayList<String>();
    }
    public void printInfo() {
        // Called by TextAdventure, prints out current status
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
        // Adds item to inventory if the item isn't already there
        if (!itemInInventory(item)) {
            if (inventory.size() < MAX_ITEMS) {
                inventory.add(item);
                sortInventory();
                System.out.println("Added " + "'" + item + "'" + " to inventory");
            } else {
                System.out.println("Your inventory is full");
            }
        }
        else {
            System.out.println(item + " is already in your inventory");
        }
    }
    public void removeItem(String item) {
        // Removes an item
        for (int i = 0; i < inventory.size(); i++) {
            if (item.equals(inventory.get(i))) {
                System.out.println("Removed " +  "'" + inventory.get(i) + "'");
                inventory.remove(i);
                sortInventory();
                return;
            }
        }
        System.out.println("Could not find " +  "'" + item +  "'" + " in inventory");
    }
    public void changeItem(String prevItem, String newItem) {
        // Modifies an item to a new state
        if (itemInInventory(prevItem)) {
            for (int i = 0; i < inventory.size(); i++) {
                if (inventory.get(i).equals(prevItem)) {
                    inventory.set(i, newItem);
                }
            }
            sortInventory();
        }
    }
    public boolean itemInInventory(String item) {
        // Returns true if an item is in the inventory
        for (int i = 0; i < inventory.size(); i++) {
            if (item.equals(inventory.get(i))) {
                return true;
            }
        }
        return false;
    }
    public void sortInventory() {
        // Sorts the inventory
        sort(inventory, 0, inventory.size() - 1);
    }
    public void merge(ArrayList<String> arr, int l, int m, int r) {
        // Helper method, merge sort
        int n1 = m - l + 1;
        int n2 = r - m;

        ArrayList<String> L = new ArrayList<String>(n1);
        ArrayList<String> R = new ArrayList<String>(n2);

        for (int i = 0; i < n1; i++) {
            L.add(arr.get(l + i));
        }
        for (int j = 0; j < n2; j++) {
            R.add(arr.get(m + 1 + j));
        }

        int i = 0;
        int j = 0;

        int k = l;
        while (i < n1 && j < n2) {
            if (L.get(i).compareTo(R.get(j)) < 0) {
                arr.set(k, L.get(i));
                i++;
            }
            else {
                arr.set(k, R.get(j));
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr.set(k, L.get(i));
            i++;
            k++;
        }
        while (j < n2) {
            arr.set(k, R.get(j));
            j++;
            k++;
        }
    }
    public void sort(ArrayList<String> arr, int l, int r) {
        if (l < r) {
            int m = l + (r - l) / 2;
            sort(arr, l, m);
            sort(arr, m + 1, r);

            merge(arr, l, m, r);
        }
    }
    // Setters and getters
    public void setHealth(double health) {
        this.health = health;
    }
    public void changeHealth(double delta) {
        health += delta;
        if (health <= 0) {
            System.out.println("GAME OVER");
            System.exit(0);
        }
    }
    public double getHealth() {
        return health;
    }
}