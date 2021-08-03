package uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Rubiks_CFOP.Managers.CROSS_F2L_Manager.F2L_Manager;

import uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Cubies.Cubie;
import uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Rubiks_CFOP.Algorithms.CROSS_F2L.F2L.CFOP_F2L_1;

public class CFOP_F2L_1_Manager {
    public static String[] solveLayer(Cubie[][][] cubieList) {

        CFOP_F2L_1 cfop_f2l_1 = new CFOP_F2L_1();

        String[] cfop_f2l_1_moves = cfop_f2l_1.solveLayer(cubieList);
        return cfop_f2l_1_moves;
    }
}
