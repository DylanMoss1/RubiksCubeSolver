package uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Rubiks_CFOP.Algorithms.OLL_PLL;

import uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Cubies.Cubie;
import uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Rubiks_CFOP.Algorithms.CFOP;

public abstract class CFOP_OLL_PLL extends CFOP {

    @Override
    public abstract String[] solveLayer(Cubie[][][] cubieList);

    public abstract String[] tryAlgorithms(Cubie[][][] cubieList);

    public abstract boolean checkComplete(Cubie[][][] cubieList);
}
