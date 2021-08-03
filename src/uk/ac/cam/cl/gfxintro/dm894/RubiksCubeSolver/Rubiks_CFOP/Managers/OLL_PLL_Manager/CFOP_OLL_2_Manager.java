package uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Rubiks_CFOP.Managers.OLL_PLL_Manager.OLL_Manager;

import uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Cubies.Cubie;
import uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Rubiks_CFOP.Algorithms.OLL_PLL.OLL.CFOP_OLL_1;
import uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Rubiks_CFOP.Algorithms.OLL_PLL.OLL.CFOP_OLL_2;

public class CFOP_OLL_2_Manager {
    public static String[] solveLayer(Cubie[][][] cubieList) {
        CFOP_OLL_2 cfop_oll_2 = new CFOP_OLL_2();
        String[] cfop_oll_2_moves = cfop_oll_2.solveLayer(cubieList);
        return cfop_oll_2_moves;
    }
}
