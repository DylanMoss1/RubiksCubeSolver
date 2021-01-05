package uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Rubiks_CFOP.Algorithms.OLL_PLL.OLL;

import uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Cubies.Cubie;

import java.util.Arrays;

public class CFOP_OLL_1 extends CFOP_OLL {

    //TODO Remove from OLL 1 and OLL 2
    public CFOP_OLL_1(){
        OLL_Message = "OLL 1 Complete";
        algorithmArray = new String[][]
                {       {},
                        {"F","R","U","RP","UP","FP","f","R","U","RP","UP","fP"},
                        {"F","R","U","RP","UP","FP"},
                        {"f","R","U","RP","UP","fP"},
                };
    }

    @Override
    public boolean completeCriteria(int[][] correctList){
        boolean complete = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(i == 1 || j == 1){
                    if(correctList[i][j] != 1){
                        complete = false;
                    }
                }
            }
        }
        return complete;
    }
}
