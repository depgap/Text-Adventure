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
            System.out.print(inventory.get(i) + " ");
        }
        System.out.println();
    }
    public void addItem(String item) {
        inventory.add(item);
        sortInventory(inventory, 0, inventory.size() - 1);
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
    public void sortInventory(ArrayList<String> arr, int l, int r) {
        if (l < r) {
            int m = l + (r - l) / 2;

            sortInventory(arr, l, m);
            sortInventory(arr, m + 1, r);
            
            merge(arr, l, m, r);
        }
    }
    public void merge(ArrayList<String> arr, int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;

        ArrayList<String> L = new ArrayList<String>();
        ArrayList<String> R = new ArrayList<String>();

        for (int i = 0; i < n1; i++) {
            L.set(i, arr.get(l + i));
        }
        for (int j = 0; j < n2; j++) {
            R.set(j, arr.get(m + 1 + j));
        }

        int i = 0, j = 0;

        int k = l;
        while (i < n1 && j < n2) {
            if (L.get(i).compareTo(R.get(j)) <= 0) {
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
    
    public void setHealth(double health) {
        this.health = health;
    }
    
    
}