package uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Rubiks_CFOP.Managers.CROSS_F2L_Manager.CROSS_Manager;

import uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Cubies.Cubie;
import uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Rubiks_CFOP.Algorithms.CROSS_F2L.CROSS.CFOP_CROSS;

public class CFOP_CROSS_Manager {
    public static String[] solveLayer(Cubie[][][] cubieList) {

        CFOP_CROSS cfop_cross = new CFOP_CROSS();
        String[] cfop_cross_moves = cfop_cross.solveLayer(cubieList);
        return cfop_cross_moves;

    }
}
