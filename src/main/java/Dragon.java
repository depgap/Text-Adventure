import java.util.Scanner;

public class Dragon extends Enemy {

    private Scanner scan;

    public Dragon() {
        super("dragon", 1000.0);
        scan = new Scanner(System.in);
    }
    public void print() {
        // Art shamelessly stolen from https://www.asciiart.eu/mythology/dragons
        System.out.println("                 ___====-_  _-====___");
        System.out.println("           _--^^^#####//      \\\\#####^^^--_");
        System.out.println("        _-^##########// (    ) \\\\##########^-_");
        System.out.println("       -############//  |\\^^/|  \\\\############-");
        System.out.println("     _/############//   (@::@)   \\\\############\\_");
        System.out.println("    /#############((     \\\\//     ))#############\\");
        System.out.println("   -###############\\\\    (oo)    //###############-");
        System.out.println("  -#################\\\\  / VV \\  //#################-");
        System.out.println(" -###################\\\\/      \\//###################-");
        System.out.println("_#/|##########/\\######(   /\\   )######/\\##########|\\#_");
        System.out.println("|/ |#/\\#/\\#/\\/  \\#/\\##\\  |  |  /##/\\#/  \\/\\#/\\#/\\#| \\|");
        System.out.println("`  |/  V  V  `   V  \\#\\| |  | |/#/  V   '  V  V  \\|  '");
        System.out.println("   `   `  `      `   / | |  | | \\   '      '  '   '");
        System.out.println("                    (  | |  | |  )");
        System.out.println("                   __\\ | |  | | /__");
        System.out.println("                  (vvv(VVV)(VVV)vvv)");
    }
    public String talk() {
        System.out.println("DRAGON: Few adventurers have made it this far. If you can answer this riddle and prove yourself worthy, perhaps I will let you pass.");
        System.out.println("DRAGON: I’m not alive, but I grow;");
        System.out.println("DRAGON: I don't have lungs, but I need air;");
        System.out.println("DRAGON: I don't have a mouth, but water kills me;");
        System.out.println("DRAGON: What am I?");

        String response = scan.nextLine();

        if (response.equalsIgnoreCase("fire")) {
            System.out.println("DRAGON: Correct. You may leave in peace.");
            return "correct";
        }
        else {
            System.out.println("DRAGON: Incorrect. You will soon find out what the correct answer is.");
            return "incorrect";
        }
    }
}
