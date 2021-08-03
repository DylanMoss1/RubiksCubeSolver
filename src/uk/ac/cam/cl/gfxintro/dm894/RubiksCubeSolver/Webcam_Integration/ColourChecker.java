package uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Webcam;

import java.util.Arrays;

public class ColourChecker {

    public static boolean finished = false;
    public static int calIndex = 0;

    static int[] redColour = {165,31,19};
    static int[] orangeColour = {201,99,46};
    static int[] yellowColour = {170,182,81};
    static int[] greenColour = {4,124,82};
    static int[] blueColour = {7,49,152};
    static int[] whiteColour = {213,208,217};
    static int[] whiteCentreColour = {186,182,183};


    /*
    static int[] redColour = {175,46,1};
    static int[] orangeColour = {218,113,28};
    static int[] yellowColour = {233,208,97};
    static int[] greenColour = {79,128,78};
    static int[] blueColour = {23,56,134};
    static int[] whiteColour = {251,225,208};
    static int[] whiteCentreColour = {221,200,184};

     */


    static String[] colourNameArray = {"R","O","Y","G","B","W"};
    static String[] fullColourNameArray = {"redColour","orangeColour","yellowColour","greenColour","blueColour","whiteColour","whiteCentreColour"};
    static int[][] colourValueArray = {redColour,orangeColour,yellowColour,greenColour,blueColour,whiteColour,whiteCentreColour};

    static int distance = 60;

    public static void colourCondense(int[][][][][] colourPanel){
        int[][][] condensedColourPanel = new int[3][3][3];

        int totalRed;
        int totalBlue;
        int totalGreen;

        for(int a=0; a<3; a++) {
            for (int b = 0; b < 3; b++) {
                totalRed = 0;
                totalBlue = 0;
                totalGreen = 0;
                for (int c = 1; c < 4; c++) {
                    for (int d = 1; d < 4; d++) {
                        totalRed += colourPanel[a][b][c][d][0];
                        totalBlue += colourPanel[a][b][c][d][1];
                        totalGreen += colourPanel[a][b][c][d][2];
                    }
                }
                condensedColourPanel[a][b][0] = totalRed / 9;
                condensedColourPanel[a][b][1] = totalBlue / 9;
                condensedColourPanel[a][b][2] = totalGreen / 9;
            }
        }
        if(MyFrame.calibrationMode){
            if(MyFrame.readFrame) {
                MyFrame.readFrame = false;
                calibration(condensedColourPanel);
            }
        } else {
            colourCheck(condensedColourPanel);
        }
    }

    public static void colourCheck(int[][][] condensedColourPanel){
        String[][] colourConvert = new String[3][3];

        for(int a=0; a<3; a++) {
            for (int b = 0; b<3; b++) {
                int R = condensedColourPanel[a][b][0];
                int G = condensedColourPanel[a][b][1];
                int B = condensedColourPanel[a][b][2];

                boolean centre = a==1 && b == 1;
                /*
                if(a==0 && b==0){
                    System.out.println(R);
                    System.out.println(G);
                    System.out.println(B);
                    System.out.println(" ");
                }

                 */
                colourConvert[a][b] = colourDistance(R,G,B,centre);
            }
        }

        checkFaceComplete(colourConvert);

        if(!finished){
            System.out.println(Arrays.deepToString(colourConvert));
            System.out.println(" ");
        }
    }

    public static String colourDistance(int R,int G,int B, boolean centre){
        double[] distanceArray = new double[6];

        for(int i=0; i<6; i++){
            distanceArray[i] = Math.sqrt(Math.pow((R-colourValueArray[i][0]),2) + Math.pow((G-colourValueArray[i][1]),2) + Math.pow((B-colourValueArray[i][2]),2));
        }

        int minValueIndex = 0;
        double minValue = distanceArray[0];

        for(int i=1; i<6; i++){
            if(distanceArray[i] < minValue){
                minValue = distanceArray[i];
                minValueIndex = i;
            }
        }

        if(distanceArray[minValueIndex] < distance){
            return colourNameArray[minValueIndex];
        } else {
            if(!centre) {
                return " ";
            } else {
                if(Math.sqrt(Math.pow((R-colourValueArray[6][0]),2) + Math.pow((G-colourValueArray[6][1]),2) + Math.pow((B-colourValueArray[6][2]),2)) < distance){
                    return colourNameArray[5];
                } else {
                    return " ";
                }
            }
        }
    }

    public static void checkFaceComplete(String[][] face){
        boolean allColour = true;
        for(String[] row : face){
            for(String square : row){
                if(square == " "){
                    allColour = false;
                }
            }
        }
        if(allColour){
            FaceConstructor.constructRubiksCube(face);
        }
    }

    public static void calibration(int[][][] condensedColourPanel){
        int totalR = 0;
        int totalG = 0;
        int totalB = 0;

        int totalRC = 0;
        int totalGC = 0;
        int totalBC = 0;

        if(calIndex < 6){
            if(calIndex != 5) {
                for (int a = 0; a < 3; a++) {
                    for (int b = 0; b < 3; b++) {
                        totalR += condensedColourPanel[a][b][0];
                        totalG += condensedColourPanel[a][b][1];
                        totalB += condensedColourPanel[a][b][2];
                    }
                }
                System.out.printf("static int[] %s = {%d,%d,%d};\n",fullColourNameArray[calIndex],totalR/9,totalG/9,totalB/9);

            } else {
                for (int a = 0; a < 3; a++) {
                    for (int b = 0; b < 3; b++) {
                        if(!(a==1 && b==1)) {
                            totalR += condensedColourPanel[a][b][0];
                            totalG += condensedColourPanel[a][b][1];
                            totalB += condensedColourPanel[a][b][2];
                        } else {
                            totalRC += condensedColourPanel[a][b][0];
                            totalGC += condensedColourPanel[a][b][1];
                            totalBC += condensedColourPanel[a][b][2];
                        }
                    }
                }

                System.out.printf("static int[] %s = {%d,%d,%d};\n",fullColourNameArray[calIndex],totalR/8,totalG/8,totalB/8);
                System.out.printf("static int[] %s = {%d,%d,%d};\n",fullColourNameArray[calIndex+1],totalRC,totalGC,totalBC);
            }

            calIndex++;
        }
    }
}
