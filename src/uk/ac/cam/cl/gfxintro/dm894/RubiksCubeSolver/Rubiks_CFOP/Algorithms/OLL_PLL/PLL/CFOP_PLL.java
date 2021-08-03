package uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Rubiks_CFOP.Algorithms.OLL_PLL.PLL;

import uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Cubies.Cubie;
import uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Rubiks_CFOP.Algorithms.CFOP;
import uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Rubiks_CFOP.Algorithms.OLL_PLL.CFOP_OLL_PLL;

import java.util.Arrays;

public class CFOP_PLL extends CFOP_OLL_PLL {

    boolean completed = false;
    int numRot = 0;
    int afterRot = 0;

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
                apply(newCubieList, "U");
            }
        }


        if(numRot == 4){
            System.out.println("PLL ERROR");
            return new String[]{};
        }

        int length = algorithm.length;

        String[] rotMoves = {"","U","U2","UP"};

        if(numRot != 0){
            length++;
        }
        if(afterRot != 0){
            length++;
        }

        String[] newMoves = new String[length];

        if(numRot == 0 && afterRot == 0){
            newMoves = algorithm;
        } else if(numRot == 0){
            for(int i=0; i<length-1; i++){
                newMoves[i] = algorithm[i];
            }
            newMoves[length-1] = rotMoves[afterRot];
        } else if(afterRot == 0){
            for(int i=1; i<length; i++){
                newMoves[i] = algorithm[i-1];
            }
            newMoves[0] = rotMoves[numRot];
        } else {
            for(int i=1; i<length-1; i++){
                newMoves[i] = algorithm[i-1];
            }
            newMoves[0] = rotMoves[numRot];
            newMoves[length-1] = rotMoves[afterRot];
        }

        return newMoves;
    }

    @Override
    public boolean checkComplete(Cubie[][][] cubieList){

        boolean complete = true;
        int[][][] correctList = new int[3][3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    if(cubieList[i][j][k].positionCorrect()){
                        correctList[i][j][k] = 1;
                    } else {
                        correctList[i][j][k] = 0;
                    }
                    if(!(cubieList[i][j][k].positionCorrect())){
                        complete = false;
                    }
                }
            }
        }

        return complete;
    }

    @Override
    public String[] tryAlgorithms(Cubie[][][] cubieList){

        String[] returnAlgorithm = {};

        Cubie[][][] newCubieList;

        String[][] algorithmArray =
                               {       {},
                                       {"x","RP","U","RP","D2","R","UP","RP","D2","R2","xP"},
                                       {"xP","R","UP","R","D2","RP","U","R","D2","R2","x"},
                                       {"R2","U","R","U","RP","UP","RP","UP","RP","U","RP"},
                                       {"R","UP","R","U","R","U","R","UP","RP","UP","R2"},
                                       {"M2","U","M2","U2","M2","U","M2"},
                                       {"R","U","RP","UP","RP","F","R2","UP","RP","UP","R","U","RP","FP"},
                                       {"RP","U","LP","U2","R","UP","RP","U2","R","L","UP"},
                                       {"R","U","RP","FP","R","U","RP","UP","RP","F","R2","UP","RP","UP"},
                                       {"L","U2","LP","U2","L","FP","LP","UP","L","U","L","F","L2","U"},
                                       {"RP","U2","R","U2","RP","F","R","U","RP","UP","RP","FP","R2","UP"},
                                       {"RP","U","RP","UP","BP","RP","B2","UP","BP","U","BP","R","B","R"},
                                       {"R2","u","RP","U","RP","UP","R","uP","R2","yP","RP","U","R", "y"},
                                       {"RP","UP","R","y","R2","u","RP","U","R","UP","R","uP","R2","yP"},
                                       {"R2","uP","R","UP","R","U","RP","u","R2","y","R","UP","RP","yP"},
                                       {"R","U","RP","yP","R2","uP","R","UP","RP","U","RP","u","R2","y"},
                                       {"RP","U","R","UP","R2","FP","UP","F","U","R","F","RP","FP","R2","UP"},
                                       {"M2","U","M2","U","MP","U2","M2","U2","MP","U2"},
                                       {"F","R","UP","RP","UP","R","U","RP","FP","R","U","RP","UP","RP","F","R","FP"},
                                       {"L","UP","R","U2","LP","U","RP","L","UP","R","U2","LP","U","RP","U"},
                                       {"RP","U","LP","U2","R","UP","L","RP","U","LP","U2","R","UP","L","UP"},
                                       {"xP","R","UP","RP","D","R","U","RP","u2","RP","U","R","D","RP","UP","R","x"},
                               };

        for(String[] algorithm : algorithmArray){
            for(int i = 0; i<4; i++){
                newCubieList = copyCubieList(cubieList);
                apply(newCubieList,algorithm,i);
                if(checkComplete(newCubieList)){
                    completed = true;
                    afterRot = i;
                    returnAlgorithm = algorithm;
                    break;
                }
            }
        }

        return returnAlgorithm;
    }
}

