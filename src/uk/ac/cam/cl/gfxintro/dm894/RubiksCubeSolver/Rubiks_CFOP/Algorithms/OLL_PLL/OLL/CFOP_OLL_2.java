package uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Rubiks_CFOP.Algorithms.OLL_PLL.OLL;

import uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Cubies.Cubie;

import java.util.Arrays;

public class CFOP_OLL_2 extends CFOP_OLL {

    public CFOP_OLL_2(){
        OLL_Message = "OLL 2 Complete";
        algorithmArray = new String[][]
                {       {},
                        {"R","U2","RP","UP","R","UP","RP"},
                        {"R","U","RP","U","R","UP","RP","U","R","U2","RP"},
                        {"xP","R","UP","RP","D","R","U","RP","DP","x"},
                        {"R","U2","R2","UP","R2","UP","R2","U2","R"},
                        {"R","U","RP","U","R","U2","RP"},
                        {"xP","D","R","UP","RP","DP","R","U","RP","x"},
                        {"R2","D","RP","U2","R","DP","RP","U2","RP"},
                };
    }

    @Override
    public boolean completeCriteria(int[][] correctList){
        boolean complete = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(correctList[i][j] != 1){
                    complete = false;
                }
            }
        }
        return complete;
    }
}
