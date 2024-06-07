public class Map {

    private final String[][] gameLayout = {
            {"[ ]", "[arena]", "[trapped hallway]", "[traps]", "[ ]",},
            {"[ ]", "[     ]", "[hallway2       ]", "[wd   ]", "[ ]",},
            {"[ ]", "[cell ]", "[hallway        ]", "[wd   ]", "[ ]",},
            {"[ ]", "[     ]", "[hallway2       ]", "[wd   ]", "[ ]",},
            {"[ ]", "[     ]", "[               ]", "[     ]", "[ ]",},
    };
    public void printMap() {
        for (int i = 0; i < gameLayout.length; i++) {
            for (int j = 0; j < gameLayout[0].length; j++) {
                System.out.println(gameLayout[i][j]);
            }
        }
    }
}
