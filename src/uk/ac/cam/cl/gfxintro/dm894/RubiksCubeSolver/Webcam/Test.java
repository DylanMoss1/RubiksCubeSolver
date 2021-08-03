package uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Webcam;

import uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Rubiks_Cube_Manager.RubiksCubeManager;
import uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Rubiks_Cube_Manager.Rubiks_Cube.RubiksCube;

public class Test {

    public static void main(String[] args) {

        makeCube2();

    }

    public static void makeWebcamCube(RubiksCubeManager rubiksCubeManager) {

        String[][][] face = {{{"O", "W", "R"}, {"Y", "R", "W"}, {"Y", "B", "W"}}, {{"W", "W", "B"}, {"B", "O", "W"}, {"O", "G", "O"}}, {{"Y", "G", "G"}, {"G", "Y", "Y"}, {"Y", "Y", "R"}}, {{"B", "O", "G"}, {"R", "G", "Y"}, {"B", "B", "Y"}}, {{"O", "R", "R"}, {"R", "B", "O"}, {"G", "G", "W"}}, {{"B", "R", "G"}, {"B", "W", "O"}, {"O", "O", "W"}}};


        CubeConstructor.constructCube(rubiksCubeManager, face);
    }























    //****************************//

    public static void makeCube(RubiksCubeManager rubiksCubeManager) {
        System.out.println("TEST");
        String[][][] face = {{{"R", "R", "R"}, {"R", "R", "R"}, {"R", "R", "R"}},
                {{"O", "O", "O"}, {"O", "O", "O"}, {"O", "O", "O"}},
                {{"Y", "Y", "Y"}, {"Y", "Y", "Y"}, {"Y", "Y", "Y"}},
                {{"G", "G", "G"}, {"G", "G", "G"}, {"G", "G", "G"}},
                {{"B", "B", "B"}, {"B", "B", "B"}, {"B", "B", "B"}},
                {{"W", "W", "W"}, {"W", "W", "W"}, {"W", "W", "W"}}};

        CubeConstructor.constructCube(rubiksCubeManager, face);
    }

    public static void makeCube2() {
        //System.out.println("TEST2");
        String[][][] face = {{{"G", "R", "R"}, {"G", "R", "R"}, {"G", "R", "R"}},
                {{"B", "O", "O"}, {"B", "O", "O"}, {"B", "O", "O"}},
                {{"Y", "Y", "Y"}, {"Y", "Y", "Y"}, {"Y", "Y", "Y"}},
                {{"O", "G", "G"}, {"O", "G", "G"}, {"O", "G", "G"}},
                {{"R", "B", "B"}, {"R", "B", "B"}, {"R", "B", "B"}},
                {{"W", "W", "W"}, {"W", "W", "W"}, {"W", "W", "W"}}};


        CubeConstructor.constructCube(face);
    }

    public static void makeCube2(RubiksCubeManager rubiksCubeManager) {
        //System.out.println("TEST2");
        String[][][] face = {{{"G", "R", "R"}, {"G", "R", "R"}, {"G", "R", "R"}},
                {{"B", "O", "O"}, {"B", "O", "O"}, {"B", "O", "O"}},
                {{"Y", "Y", "Y"}, {"Y", "Y", "Y"}, {"Y", "Y", "Y"}},
                {{"O", "G", "G"}, {"O", "G", "G"}, {"O", "G", "G"}},
                {{"R", "B", "B"}, {"R", "B", "B"}, {"R", "B", "B"}},
                {{"W", "W", "W"}, {"W", "W", "W"}, {"W", "W", "W"}}};

        CubeConstructor.constructCube(rubiksCubeManager, face);
    }

    public static void makeCube3(RubiksCubeManager rubiksCubeManager) {
        String[][][] face = {{{"B", "B", "B"}, {"B", "R", "R"}, {"B", "R", "R"}},
                {{"Y", "Y", "G"}, {"O", "O", "G"}, {"O", "O", "G"}},
                {{"Y", "R", "R"}, {"Y", "Y", "G"}, {"Y", "Y", "G"}},
                {{"R", "G", "G"}, {"R", "G", "G"}, {"R", "W", "W"}},
                {{"Y", "B", "B"}, {"Y", "B", "B"}, {"O", "O", "O"}},
                {{"O", "O", "W"}, {"W", "W", "W"}, {"W", "W", "W"}}};

        CubeConstructor.constructCube(rubiksCubeManager, face);
    }

    public static void makeCube4(RubiksCubeManager rubiksCubeManager) {
        System.out.println("TEST2");
        String[][][] face = {{{"G", "W", "W"}, {"G", "R", "R"}, {"G", "R", "R"}},
                {{"B", "O", "O"}, {"B", "O", "O"}, {"B", "Y", "Y"}},
                {{"R", "R", "R"}, {"Y", "Y", "Y"}, {"Y", "Y", "Y"}},
                {{"O", "G", "G"}, {"O", "G", "G"}, {"Y", "G", "G"}},
                {{"W", "B", "B"}, {"R", "B", "B"}, {"R", "B", "B"}},
                {{"W", "W", "O"}, {"W", "W", "O"}, {"W", "W", "O"}}};


        //[[[B, R, R], [B, R, R], [B, G, G]], [[B, O, O], [B, O, O], [B, Y, Y]], [[R, R, R], [Y, Y, Y], [Y, Y, Y]], [[R, G, G], [R, G, G], [G, G, G]], [[W, B, B], [R, B, B], [R, B, B]], [[W, W, O], [W, W, O], [W, W, O]]]

        //[[[G, W, W], [G, R, R], [G, R, R]], [[B, O, Y], [B, O, Y], [B, Y, Y]], [[R, R, R], [Y, Y, Y], [Y, Y, Y]], [[G, R, R], [O, G, G], [O, G, G]], [[W, B, G], [R, B, B], [R, B, B]], [[W, W, O], [W, W, O], [W, W, O]]]
        //[[[B, R, R], [B, R, R], [B, Y, Y]], [[B, O, Y], [B, O, Y], [B, Y, Y]], [[R, R, R], [Y, Y, Y], [Y, Y, Y]], [[B, R, R], [O, G, G], [O, G, G]], [[W, B, B], [R, B, B], [R, B, B]], [[W, W, O], [W, W, O], [W, W, O]]]
        CubeConstructor.constructCube(rubiksCubeManager, face);
    }

    public static void makeCube5(RubiksCubeManager rubiksCubeManager) {
        System.out.println("TEST");
        String[][][] face = {{{"G", "G", "G"}, {"R", "R", "W"}, {"R", "R", "W"}},
                {{"B", "O", "O"}, {"B", "O", "O"}, {"B", "Y", "Y"}},
                {{"W", "B", "B"}, {"Y", "Y", "Y"}, {"Y", "Y", "Y"}},
                {{"O", "G", "G"}, {"O", "G", "G"}, {"R", "R", "R"}},
                {{"W", "W", "O"}, {"R", "B", "B"}, {"R", "B", "B"}},
                {{"G", "G", "Y"}, {"W", "W", "O"}, {"W", "W", "O"}}};

        CubeConstructor.constructCube(rubiksCubeManager, face);
    }

    public static void makeCube6(RubiksCubeManager rubiksCubeManager) {
        System.out.println("TEST");
        String[][][] face = {{{"G", "W", "R"}, {"B", "R", "W"}, {"R", "Y", "W"}},
                {{"G", "R", "Y"}, {"G", "O", "O"}, {"Y", "Y", "O"}},
                {{"O", "O", "G"}, {"Y", "Y", "Y"}, {"B", "R", "W"}},
                {{"R", "G", "B"}, {"R", "G", "W"}, {"W", "R", "W"}},
                {{"Y", "B", "B"}, {"O", "B", "W"}, {"R", "B", "O"}},
                {{"O", "B", "B"}, {"G", "W", "O"}, {"G", "G", "Y"}}};

        CubeConstructor.constructCube(rubiksCubeManager, face);
    }
}

