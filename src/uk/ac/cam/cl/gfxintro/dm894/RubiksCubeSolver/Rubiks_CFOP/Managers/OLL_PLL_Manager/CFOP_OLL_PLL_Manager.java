package uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Rubiks_CFOP.Managers.OLL_PLL_Manager;

import uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Cubies.Cubie;
import uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Rubiks_CFOP.Algorithms.OLL_PLL.PLL.CFOP_PLL;
import uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Rubiks_CFOP.Managers.CFOP_Manager;
import uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Rubiks_CFOP.Managers.CFOP_Manager_Commands;
import uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Rubiks_CFOP.Managers.OLL_PLL_Manager.OLL_Manager.CFOP_OLL_Manager;
import uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Rubiks_CFOP.Managers.OLL_PLL_Manager.PLL_Manager.CFOP_PLL_Manager;

import java.util.Arrays;

public class CFOP_OLL_PLL_Manager{

    public static String[] solveLayer(Cubie[][][] cubieList) {

        String[] moves1 = CFOP_OLL_Manager.solveLayer(cubieList);

        Cubie[][][] newCubieList = CFOP_Manager_Commands.copyCubieList(cubieList);

        CFOP_Manager_Commands.apply(newCubieList,moves1);

        String[] moves2 = CFOP_PLL_Manager.solveLayer(newCubieList);

        String[] moves = Arrays.copyOf(moves1, moves1.length + moves2.length);
        System.arraycopy(moves2, 0, moves, moves1.length, moves2.length);

        return moves;
    }
}
