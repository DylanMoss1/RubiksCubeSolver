package uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Webcam;

import java.util.Arrays;

public class ColourChecker {

    public static int calIndex = 0;

    //New video
    /*
    static int[] redColour = {131,24,34};
    static int[] orangeColour = {144,56,31};
    static int[] yellowColour = {110,129,66};
    static int[] greenColour = {0,95,51};
    static int[] blueColour = {0,46,131};
    static int[] whiteColour = {97,129,150};
    static int[] whiteCentreColour = {64,80,95};

     */


    //New picker
    
    /*
    static int[] redColour = {156,34,47};
    static int[] orangeColour = {172,77,61};
    static int[] yellowColour = {125,166,107};
    static int[] greenColour = {0,132,126};
    static int[] blueColour = {0,89,196};
    static int[] whiteColour = {110,166,224};
    static int[] whiteCentreColour = {85,102,114};
     */
    
    //Old video
    /*
    static int[] redColour = {120,20,17};
    static int[] orangeColour = {174,94,55};
    static int[] yellowColour = {153,159,81};
    static int[] greenColour = {0,75,42};
    static int[] blueColour = {0,30,122};
    static int[] whiteColour = {109,139,168};
    static int[] whiteCentreColour = {91,115,134};
     */

    /*
    static int[] redColour = {216,61,44};
    static int[] orangeColour = {254,128,45};
    static int[] yellowColour = {255,234,90};
    static int[] greenColour = {90,162,108};
    static int[] blueColour = {9,89,186};
    static int[] whiteColour = {254,249,254};
    static int[] whiteCentreColour = {230,221,223};

     */

    static int[] redColour = {142,53,89};
    static int[] orangeColour = {158,100,102};
    static int[] yellowColour = {127,174,139};
    static int[] greenColour = {0,121,125};
    static int[] blueColour = {0,51,232};
    static int[] whiteColour = {81,168,255};
    static int[] whiteCentreColour = {68,151,244};

    static String[] colourNameArray = {"R","O","Y","G","B","W"};
    static String[] fullColourNameArray = {"redColour","orangeColour","yellowColour","greenColour","blueColour","whiteColour","whiteCentreColour"};
    static int[][] colourValueArray = {redColour,orangeColour,yellowColour,greenColour,blueColour,whiteColour,whiteCentreColour};

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

        System.out.println(Arrays.deepToString(colourConvert));
        System.out.println(" ");


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

        if(distanceArray[minValueIndex] < 50){
            return colourNameArray[minValueIndex];
        } else {
            if(!centre) {
                return " ";
            } else {
                if(Math.sqrt(Math.pow((R-colourValueArray[6][0]),2) + Math.pow((G-colourValueArray[6][1]),2) + Math.pow((B-colourValueArray[6][2]),2)) < 50){
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
