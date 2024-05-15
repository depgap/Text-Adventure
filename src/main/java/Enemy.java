public class Enemy {

    private String name;
    private double health;
    
    public Enemy(String name, double health) {
        this.name = name;
        this.health = health;
    }
    public double getHealth() {
        return health;
    }
    public void takeDamage(double dmg) {
        health -= dmg;
        if (health <= 0) {
            die();
        }
    }
    public void die() {
        System.out.println(name + " was eliminated");
    }
}