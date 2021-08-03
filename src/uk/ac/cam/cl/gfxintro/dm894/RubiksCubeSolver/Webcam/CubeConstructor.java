package Webcam;

public class CubeConstructor {

    public static void constructCube(String[][][] faces){
        String[][] redFace = faces[0];
        String[][] orangeFace = faces[1];
        String[][] yellowFace = faces[2];
        String[][] greenFace = faces[3];
        String[][] blueFace = faces[4];
        String[][] whiteFace = faces[5];

        constructCubie[][][] cubieList = new constructCubie[3][3][3];

        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                for(int k=0; k<3; k++){
                    cubieList[i][j][k] = new constructCubie();
                }
            }
        }

        setRedFace(cubieList,redFace);
        setOrangeFace(cubieList,orangeFace);
        setYellowFace(cubieList,yellowFace);
        setGreenFace(cubieList,greenFace);
        setBlueFace(cubieList,blueFace);
        setWhiteFace(cubieList,whiteFace);

        calculateCubies(cubieList);
    }

    public static void setRedFace(constructCubie[][][] cubieList, String[][] face){
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                String colour = face[i][j];
                cubieList[i][j][0].setColour(colour,"R");
            }
        }
    }

    public static void setOrangeFace(constructCubie[][][] cubieList, String[][] face){
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                String colour = face[i][j];
                cubieList[i][j][2].setColour(colour,"O");
            }
        }
    }

    public static void setYellowFace(constructCubie[][][] cubieList, String[][] face){
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                String colour = face[i][j];
                cubieList[i][2][j].setColour(colour,"Y");
            }
        }
    }

    public static void setGreenFace(constructCubie[][][] cubieList, String[][] face){
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                String colour = face[i][j];
                cubieList[2][i][j].setColour(colour,"G");
            }
        }
    }

    public static void setBlueFace(constructCubie[][][] cubieList, String[][] face){
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                String colour = face[i][j];
                cubieList[2][i][j].setColour(colour,"B");
            }
        }
    }

    public static void setWhiteFace(constructCubie[][][] cubieList, String[][] face){
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                String colour = face[i][j];
                cubieList[i][0][j].setColour(colour,"W");
            }
        }
    }

    public static void calculateCubies(constructCubie[][][] cubieList){
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                for(int k=0; k<3; k++){
                    cubieList[i][j][k].calculatePosition(i,j,k);
                    cubieList[i][j][k].calculateIndex();
                    cubieList[i][j][k].calculateOrientation();
                }
            }
        }
    }

    //RubiksCubeManager.sendCube(cubieList);
}
