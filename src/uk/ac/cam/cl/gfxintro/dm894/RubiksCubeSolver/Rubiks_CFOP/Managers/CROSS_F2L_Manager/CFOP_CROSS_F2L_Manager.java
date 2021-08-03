package uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Rubiks_CFOP.Managers.CROSS_F2L_Manager;

import uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Cubies.Cubie;
import uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Rubiks_CFOP.Managers.CFOP_Manager;
import uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Rubiks_CFOP.Managers.CFOP_Manager_Commands;
import uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Rubiks_CFOP.Managers.CROSS_F2L_Manager.CROSS_Manager.CFOP_CROSS_Manager;
import uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Rubiks_CFOP.Managers.CROSS_F2L_Manager.F2L_Manager.CFOP_F2L_Manager;

import java.util.Arrays;

public class CFOP_CROSS_F2L_Manager {

    public static String[] solveLayer(Cubie[][][] cubieList) {

        String[] moves1 = CFOP_CROSS_Manager.solveLayer(cubieList);

        Cubie[][][] newCubieList = CFOP_Manager_Commands.copyCubieList(cubieList);

        CFOP_Manager_Commands.apply(newCubieList,moves1);

        String[] moves2 = CFOP_F2L_Manager.solveLayer(newCubieList);

        String[] moves = Arrays.copyOf(moves1, moves1.length + moves2.length);
        System.arraycopy(moves2, 0, moves, moves1.length, moves2.length);

        return moves;
    }
}
