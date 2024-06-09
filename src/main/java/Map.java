public class Map {
    // A class for the MAP item

    private final String[][] gameLayout = {
            // Constant game map
            {"[ ]", "[arena]", "[trapped hallway]", "[traps]", "[ ]",},
            {"[ ]", "[     ]", "[hallway2       ]", "[wd   ]", "[ ]",},
            {"[ ]", "[cell ]", "[hallway        ]", "[wd   ]", "[ ]",},
            {"[ ]", "[     ]", "[hallway2       ]", "[wd   ]", "[ ]",},
            {"[ ]", "[     ]", "[               ]", "[     ]", "[ ]",},
    };
    public void printMap() {
        for (int i = 0; i < gameLayout.length; i++) {
            for (int j = 0; j < gameLayout[0].length; j++) {
                System.out.print(gameLayout[i][j] + " ");
            }
            System.out.println();
        }
    }
}
