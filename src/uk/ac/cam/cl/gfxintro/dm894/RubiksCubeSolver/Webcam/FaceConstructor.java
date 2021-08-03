package uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Webcam;

import java.util.Arrays;

public class FaceConstructor {

    static String[][][] cube = new String[6][3][3];
    static boolean[] facesFilled = {false,false,false,false,false,false};

    public static void constructRubiksCube(String[][] face){

        int row = findCentre(face);
        addFace(row,face);
        checkAllFaces();
    }

    public static int findCentre(String[][] face){
        String centreSquare = face[1][1];
        int row;

        switch(centreSquare) {
            case "R":
                row = 0;
                break;
            case "O":
                row = 1;
                break;
            case "Y":
                row = 2;
                break;
            case "G":
                row = 3;
                break;
            case "B":
                row = 4;
                break;
            case "W":
                row = 5;
                break;
            default:
                row = 5;
        }
        return row;
    }

    public static void addFace(int row, String[][] face){
        /*
        if(!facesFilled[row]) {
            System.out.println(Arrays.deepToString(face));
            System.out.println(" ");
        }

         */

        facesFilled[row] = true;

        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                cube[row][i][j] = face[i][j];
            }
        }
    }

    public static void checkAllFaces(){

        for(String[][] printFace : cube){
            System.out.println(Arrays.deepToString(printFace));
        }


        boolean allFilled = true;

        for(boolean row : facesFilled){
            if(!row){
                allFilled = false;
            }
        }

        if(allFilled){
            System.out.println("FINISHED:");
            System.out.println(Arrays.deepToString(cube));
            printTestCode(cube);
            ColourChecker.finished = true;
            CubeConstructor.constructCube(cube);
        }
    }

    public static void printTestCode(String[][][] cube){

        String sides = Arrays.deepToString(cube);

        sides = sides.replace("[","{");
        sides = sides.replace("]","}");
        sides = sides.replace("R","\"R\"");
        sides = sides.replace("O","\"O\"");
        sides = sides.replace("Y","\"Y\"");
        sides = sides.replace("G","\"G\"");
        sides = sides.replace("B","\"B\"");
        sides = sides.replace("W","\"W\"");

        System.out.printf("\nString[][][] face = %s;",sides);
    }
}
