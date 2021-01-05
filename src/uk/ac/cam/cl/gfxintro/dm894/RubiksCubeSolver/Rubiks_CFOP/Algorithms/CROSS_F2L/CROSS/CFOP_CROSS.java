package uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Rubiks_CFOP.Algorithms.CROSS_F2L.CROSS;

import uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Rubiks_CFOP.Algorithms.CROSS_F2L.CFOP_CROSS_F2L;

public class CFOP_CROSS extends CFOP_CROSS_F2L {

    public CFOP_CROSS() {
        possibleMoves = new String[]{"R", "L", "U", "D", "F", "B", "RP", "LP", "UP", "DP", "FP", "BP", "R2", "L2", "U2", "D2", "F2", "B2"};
        cubieIndex = new int[]{9, 11, 1, 19};
    }
}
