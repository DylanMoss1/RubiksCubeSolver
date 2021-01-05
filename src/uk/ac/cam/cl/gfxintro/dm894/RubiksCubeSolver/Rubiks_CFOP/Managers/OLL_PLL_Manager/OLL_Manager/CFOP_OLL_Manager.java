package uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Rubiks_CFOP.Managers.OLL_PLL_Manager.OLL_Manager;

import uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Cubies.Cubie;
import uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Rubiks_CFOP.Algorithms.CFOP;
import uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Rubiks_CFOP.Algorithms.OLL_PLL.OLL.CFOP_OLL_1;
import uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Rubiks_CFOP.Algorithms.OLL_PLL.OLL.CFOP_OLL_2;
import uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Rubiks_CFOP.Managers.CFOP_Manager;
import uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Rubiks_CFOP.Managers.CFOP_Manager_Commands;

import java.util.Arrays;

public class CFOP_OLL_Manager {
    public static String[] solveLayer(Cubie[][][] cubieList) {

        String[] moves1 = CFOP_OLL_1_Manager.solveLayer(cubieList);
        //System.out.println(moves1);

        Cubie[][][] newCubieList = CFOP_Manager_Commands.copyCubieList(cubieList);

        CFOP_Manager_Commands.apply(newCubieList, moves1);

        String[] moves2 = CFOP_OLL_2_Manager.solveLayer(newCubieList);

        String[] moves = Arrays.copyOf(moves1, moves1.length + moves2.length);
        System.arraycopy(moves2, 0, moves, moves1.length, moves2.length);

        return moves;
    }
}