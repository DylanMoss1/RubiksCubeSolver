package uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Rubiks_CFOP.Managers.OLL_PLL_Manager.OLL_Manager;

import uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Cubies.Cubie;
import uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Rubiks_CFOP.Algorithms.CROSS_F2L.F2L.CFOP_F2L_1;
import uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Rubiks_CFOP.Algorithms.OLL_PLL.OLL.CFOP_OLL_1;

public class CFOP_OLL_1_Manager {
    public static String[] solveLayer(Cubie[][][] cubieList) {
        CFOP_OLL_1 cfop_oll_1 = new CFOP_OLL_1();
        String[] cfop_oll_1_moves = cfop_oll_1.solveLayer(cubieList);
        return cfop_oll_1_moves;
    }
}
