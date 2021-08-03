package uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Webcam;

import uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Rendering.OpenGLApplication;
import uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.RubiksCubeSolver;
import uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Rubiks_Cube_Manager.RubiksCubeManager;

import java.util.Arrays;

public class CubeConstructor {

    public static void constructCube(String[][][] faces) {
        String[][] redFace = faces[0];
        String[][] orangeFace = faces[1];
        String[][] yellowFace = faces[2];
        String[][] greenFace = faces[3];
        String[][] blueFace = faces[4];
        String[][] whiteFace = faces[5];

        ConstructCubie[][][] cubieList = new ConstructCubie[3][3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    cubieList[i][j][k] = new ConstructCubie();
                }
            }
        }

        setRedFace(cubieList, redFace);
        setOrangeFace(cubieList, orangeFace);
        setYellowFace(cubieList, yellowFace);
        setGreenFace(cubieList, greenFace);
        setBlueFace(cubieList, blueFace);
        setWhiteFace(cubieList, whiteFace);

        ConstructCubie cubie;
    /*
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                for(int k=0; k<3; k++){
                    cubie = cubieList[i][j][k];
                    System.out.println("###");
                    System.out.println(cubie.colour1);
                    System.out.println(cubie.colour2);
                    System.out.println(cubie.colour3);
                }
            }
        }

     */

        calculateCubies(cubieList);
        //MyFrame.rubiksCubeManager.createCube(cubieList);
        //OpenGLApplication.constructCubies = cubieList;
        //RubiksCubeSolver.run();
    }

    public static void constructCube(RubiksCubeManager rubiksCubeManager, String[][][] faces) {
        String[][] redFace = faces[0];
        String[][] orangeFace = faces[1];
        String[][] yellowFace = faces[2];
        String[][] greenFace = faces[3];
        String[][] blueFace = faces[4];
        String[][] whiteFace = faces[5];

        ConstructCubie[][][] cubieList = new ConstructCubie[3][3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    cubieList[i][j][k] = new ConstructCubie();
                }
            }
        }

        setRedFace(cubieList, redFace);
        setOrangeFace(cubieList, orangeFace);
        setYellowFace(cubieList, yellowFace);
        setGreenFace(cubieList, greenFace);
        setBlueFace(cubieList, blueFace);
        setWhiteFace(cubieList, whiteFace);

    /*
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                for(int k=0; k<3; k++){
                    cubie = cubieList[i][j][k];
                    System.out.println("###");
                    System.out.println(cubie.colour1);
                    System.out.println(cubie.colour2);
                    System.out.println(cubie.colour3);
                }
            }
        }

     */

        calculateCubies(cubieList);
        rubiksCubeManager.createCube(cubieList);
    }

    public static void setRedFace(ConstructCubie[][][] cubieList, String[][] face) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String colour = face[i][j];
                cubieList[2-i][2 - j][0].setColour(colour, "R");
            }
        }
    }

    public static void setOrangeFace(ConstructCubie[][][] cubieList, String[][] face) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String colour = face[i][j];
                cubieList[i][2-j][2].setColour(colour, "O");
            }
        }
    }

    public static void setYellowFace(ConstructCubie[][][] cubieList, String[][] face) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String colour = face[i][j];
                cubieList[2-j][2][i].setColour(colour, "Y");
                //j,2,i
            }
        }
    }

    public static void setGreenFace(ConstructCubie[][][] cubieList, String[][] face) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String colour = face[i][j];
                cubieList[2][2 - j][2 - i].setColour(colour, "G");
            }
        }
    }

    public static void setBlueFace(ConstructCubie[][][] cubieList, String[][] face) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String colour = face[i][j];
                cubieList[0][2 - j][i].setColour(colour, "B");
            }
        }
    }

    public static void setWhiteFace(ConstructCubie[][][] cubieList, String[][] face) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String colour = face[i][j];
                cubieList[j][0][i].setColour(colour, "W");
                //i,0,j
            }
        }
    }

    public static void calculateCubies(ConstructCubie[][][] cubieList) {
        /*
        cubieList[0][2][0].calculatePosition(0,2,0);
        cubieList[0][2][0].calculateIndex();
        System.out.println("###");
        ConstructCubie cubie = cubieList[0][2][0];
        System.out.println(cubie.colour1);
        System.out.println(cubie.face1);
        System.out.println("");
        System.out.println(cubie.colour2);
        System.out.println(cubie.face2);
        System.out.println("");
        System.out.println(cubie.colour3);
        System.out.println(cubie.face3);
        System.out.println("");
        cubieList[0][2][0].calculateOrientation();

         */






        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {

                    ConstructCubie constructCubie = cubieList[i][j][k];
                    constructCubie.calculatePosition(i, j, k);
                    constructCubie.calculateIndex();
                    //System.out.println("###");
                    constructCubie.calculateOrientation();
                    /*
                    System.out.println(constructCubie.orientation);
                    System.out.println(constructCubie.position);
                    System.out.println(constructCubie.colour1);
                    System.out.println(constructCubie.face1);
                    System.out.println("");
                    System.out.println(constructCubie.colour2);
                    System.out.println(constructCubie.face2);
                    System.out.println("");
                    System.out.println(constructCubie.colour3);
                    System.out.println(constructCubie.face3);
                    System.out.println(constructCubie.numOfColour);
                    System.out.println(constructCubie.orientation);

                     */
                    //System.out.println(cubieList[i][j][k].position);
                    //System.out.println(cubieList[i][j][k].index);
                    //System.out.println(" ");
                }
            }
        }
    }
}
