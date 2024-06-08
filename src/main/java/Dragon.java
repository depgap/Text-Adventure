public class Dragon extends Enemy {

    public Dragon() {
        super("dragon", 1000.0);
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
}
