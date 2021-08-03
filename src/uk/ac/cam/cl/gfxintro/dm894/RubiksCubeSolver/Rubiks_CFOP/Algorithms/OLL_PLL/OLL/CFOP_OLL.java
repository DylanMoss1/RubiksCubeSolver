package uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Rubiks_CFOP.Algorithms.OLL_PLL.OLL;

import uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Cubies.Cubie;
import uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Rubiks_CFOP.Algorithms.CFOP;
import uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Rubiks_CFOP.Algorithms.OLL_PLL.CFOP_OLL_PLL;

import java.util.Arrays;

public abstract class CFOP_OLL extends CFOP_OLL_PLL {

    boolean completed = false;
    int numRot = 0;
    String OLL_Message = "";
    String[][] algorithmArray;

    @Override
    public String[] solveLayer(Cubie[][][] cubieList) {

        Cubie[][][] newCubieList;
        String[] algorithm = new String[]{};
        completed = false;

        newCubieList = copyCubieList(cubieList);

        for(int i=0; i<4; i++) {
            algorithm = tryAlgorithms(newCubieList);
            if(completed) {
                break;
            } else {
                numRot++;
                apply(newCubieList,"U");
            }
        }

        String[] rotMoves = {"","U","U2","UP"};

        String[] newMoves;

        if(numRot == 0){
            newMoves = algorithm;
        } else {
            newMoves = new String[algorithm.length + 1];
            for(int i=0; i<algorithm.length; i++){
                newMoves[i+1] = algorithm[i];
            }
            newMoves[0] = rotMoves[numRot];
        }
        return newMoves;
    }

    @Override
    public String[] tryAlgorithms(Cubie[][][] cubieList){

        String[] returnAlgorithm = {};

        Cubie[][][] newCubieList;

        for(String[] algorithm : algorithmArray){
            newCubieList = copyCubieList(cubieList);
            apply(newCubieList,algorithm);
            if(checkComplete(newCubieList)){
                completed = true;
                returnAlgorithm = algorithm;
                break;
            }
        }

        return returnAlgorithm;
    }

    public boolean checkComplete(Cubie[][][] cubieList){

        Cubie[][] topLayer = getTopLayer(cubieList);

        int[][] correctList = new int[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(topLayer[i][j].orientationCorrect()){
                    correctList[i][j] = 1;
                } else {
                    correctList[i][j] = 0;
                }
            }
        }

        return completeCriteria(correctList);
    }

    public abstract boolean completeCriteria(int[][] correctList);

    public Cubie[][] getTopLayer(Cubie[][][] cubieList){
        Cubie[][] topLayer = new Cubie[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    if(Math.round(cubieList[i][j][k].position.y) == 1){
                        topLayer[Math.round(cubieList[i][j][k].position.x) + 1][Math.round(cubieList[i][j][k].position.z) + 1] = cubieList[i][j][k];
                    }
                }
            }
        }
        return topLayer;
    }
}
