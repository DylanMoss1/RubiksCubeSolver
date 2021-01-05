package uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Rubiks_CFOP.Managers.OLL_PLL_Manager.PLL_Manager;

import uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Cubies.Cubie;
import uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Rubiks_CFOP.Algorithms.OLL_PLL.OLL.CFOP_OLL_2;
import uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Rubiks_CFOP.Algorithms.OLL_PLL.PLL.CFOP_PLL;

public class CFOP_PLL_Manager {
    public static String[] solveLayer(Cubie[][][] cubieList) {
        CFOP_PLL cfop_pll = new CFOP_PLL();
        String[] cfop_pll_moves = cfop_pll.solveLayer(cubieList);
        return cfop_pll_moves;
    }
}
