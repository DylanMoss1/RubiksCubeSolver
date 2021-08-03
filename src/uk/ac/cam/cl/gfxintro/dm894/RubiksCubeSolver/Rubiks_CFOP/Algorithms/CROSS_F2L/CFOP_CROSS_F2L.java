package uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Rubiks_CFOP.Algorithms.CROSS_F2L;

import uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Cubies.Cubie;
import uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Cubies.Non_Render_Cubies.NonRenderCubie;
import uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Rubiks_CFOP.Algorithms.CFOP;

import java.util.LinkedList;

public abstract class CFOP_CROSS_F2L extends CFOP {

    public int[] cubieIndex;
    public String[] possibleMoves;
    public LinkedList<String> movesList;
    public String[] moves;
    public int currentScore = 0;
    public int iter = 0;

    public String[] solveLayer(Cubie[][][] cubieList){

        movesList = new LinkedList<>();
        Cubie[][][][] rubiksCubeList = {copyCubieList(cubieList)};

        int score = getScore(rubiksCubeList[0]);

        if(score == 4){
            return new String[]{};
        } else {
            currentScore = score;
            return branch(rubiksCubeList);
        }
    }

    public String[] branch(Cubie[][][][] rubiksCubeList){
        Cubie[][][][] newRubiksCubeList = new Cubie[rubiksCubeList.length * possibleMoves.length][3][3][3];
        Cubie[][][] newRubiksCube;

        int maxScore = -1;
        Cubie[][][] maxRubiksCube = new Cubie[3][3][3];

        for(int i=0; i<rubiksCubeList.length; i++) {
            for (int j = 0; j < possibleMoves.length; j++) {

                newRubiksCube = algorithm(rubiksCubeList[i],possibleMoves[j]);

                NonRenderCubie centreCubie = (NonRenderCubie) newRubiksCube[1][1][1];
                centreCubie.addToPath(possibleMoves[j]);

                int score = getScore(newRubiksCube);

                if(score > maxScore){
                    maxScore = score;
                    maxRubiksCube = newRubiksCube;
                }

                newRubiksCubeList[i * possibleMoves.length + j] = newRubiksCube;
            }
        }


        for(Cubie[][][] rubiksCube : newRubiksCubeList){
            int score = getScore(rubiksCube);
            if(score > maxScore){
                maxScore = score;
                maxRubiksCube = rubiksCube;
            }
        }

        if(maxScore > currentScore){
            currentScore = maxScore;
            if(maxScore == 4) {
                NonRenderCubie centreCubie = (NonRenderCubie) maxRubiksCube[1][1][1];

                movesList = centreCubie.getPath();
                moves = new String[movesList.size()];

                for(int i=0; i< movesList.size(); i++){
                    moves[i] = movesList.get(i);
                }
                return moves;
            } else {
                newRubiksCubeList = new Cubie[][][][]{maxRubiksCube};
            }
        }
        return branch(newRubiksCubeList);
    }

    public Cubie[][][] algorithm(Cubie[][][] oldRubiksCube,String move){
        Cubie[][][] newRubiksCube = copyCubieList(oldRubiksCube);
        apply(newRubiksCube,move);
        return newRubiksCube;
    }

    public Cubie[] getLayerCubie(Cubie[][][] cubieList){
        Cubie[] layerCubie = new Cubie[cubieIndex.length];

        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                for(int k=0; k<3; k++){
                    for(int l=0; l<cubieIndex.length; l++){
                        if(cubieList[i][j][k].cubieIndex == cubieIndex[l]){
                            layerCubie[l] = cubieList[i][j][k];
                        }
                    }
                }
            }
        }


        return layerCubie;
    }

    public int getScore(Cubie[][][] cubieList){
        Cubie[] layerCubie = getLayerCubie(cubieList);
        int score = 0;

        for(Cubie cubie : layerCubie){
            if(cubie.cubieCorrect()){
                score++;
            }
        }
        return score;
    }

    public void printScore(Cubie[][][] cubieList){
        Cubie[] layerCubie = getLayerCubie(cubieList);

        for(Cubie cubie : layerCubie){
            if(cubie.cubieCorrect()){
                System.out.println(cubie.cubieIndex);
                System.out.println(cubie.position);
                System.out.println(cubie.positionCorrect());
                System.out.println("");
            }
        }
    }
}
