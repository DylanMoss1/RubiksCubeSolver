package uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Rubiks_CFOP.Managers.CROSS_F2L_Manager.F2L_Manager;

import uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Cubies.Cubie;
import uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Rubiks_CFOP.Algorithms.CROSS_F2L.F2L.CFOP_F2L_2;

public class CFOP_F2L_2_Manager {
    public static String[] solveLayer(Cubie[][][] cubieList) {

        CFOP_F2L_2 cfop_f2l_2 = new CFOP_F2L_2();

        String[] cfop_f2l_2_moves = cfop_f2l_2.solveLayer(cubieList);
        return cfop_f2l_2_moves;
    }
}
