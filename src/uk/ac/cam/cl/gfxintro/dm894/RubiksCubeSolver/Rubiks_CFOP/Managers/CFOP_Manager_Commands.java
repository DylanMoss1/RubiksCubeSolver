package uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Rubiks_CFOP.Managers;

import uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Cubies.Cubie;

public class CFOP_Manager_Commands {
    public static Cubie[][][] copyCubieList(Cubie[][][] cubieList){
        Cubie[][][] newCubieList = new Cubie[3][3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    newCubieList[i][j][k] = cubieList[i][j][k].copy();
                }
            }
        }
        return newCubieList;
    }

    public static void apply(Cubie[][][] cubieList, String[] moves){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    for(String move : moves) {
                        if(move.length() == 2){
                            char first = move.charAt(0);
                            if(first == 'z' || first == 'v' || first == 'e' || first == 'q' || first == 'w' || first == 'g' || first == 'o'){
                                cubieList[i][j][k].moveCube(move);
                            } else if(move.charAt(1) == '2') {
                                String newMove = String.valueOf(move.charAt(0));
                                cubieList[i][j][k].moveCube(newMove);
                                cubieList[i][j][k].moveCube(newMove);
                            } else {
                                cubieList[i][j][k].moveCube(move);
                            }
                        } else {
                            cubieList[i][j][k].moveCube(move);
                        }
                    }
                }
            }
        }
    }
}
