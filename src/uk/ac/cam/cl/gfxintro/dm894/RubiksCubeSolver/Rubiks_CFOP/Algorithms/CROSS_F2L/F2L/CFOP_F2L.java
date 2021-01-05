package uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Rubiks_CFOP.Algorithms.CROSS_F2L.F2L;

import uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Cubies.Cubie;
import uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Rubiks_CFOP.Algorithms.CROSS_F2L.CFOP_CROSS_F2L;

public abstract class CFOP_F2L extends CFOP_CROSS_F2L {

    int[] oldCubieIndex;

    public Cubie[] getOldLayerCubie(Cubie[][][] cubieList){
        Cubie[] oldLayerCubie = new Cubie[oldCubieIndex.length];

        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                for(int k=0; k<3; k++){
                    for(int l=0; l<oldCubieIndex.length; l++){
                        if(cubieList[i][j][k].cubieIndex == oldCubieIndex[l]){
                            oldLayerCubie[l] = cubieList[i][j][k];
                        }
                    }
                }
            }
        }
        return oldLayerCubie;
    }

    @Override
    public int getScore(Cubie[][][] cubieList){
        Cubie[] oldLayerCubie = getOldLayerCubie(cubieList);
        Cubie[] layerCubie = getLayerCubie(cubieList);
        boolean check = true;
        int score = 0;

        for(Cubie oldCubie : oldLayerCubie){
            if(!oldCubie.cubieCorrect()){
                check = false;
            }
        }

        for(Cubie cubie : layerCubie){
            if(cubie.cubieCorrect()){
                score++;
            }
        }

        if(!check){
            score = 0;
        }

        return score;
    }

    /*
    @Override
    public String[] branch(Cubie[][][][] rubiksCubeList) {
        //System.out.println(currentScore);
        //System.out.println(rubiksCubeList.length);
        //System.out.println(" ");
        System.out.println("###");
        System.out.println(++iter);
        System.out.println(currentScore);
        System.out.println(rubiksCubeList.length);
        Cubie[][][][] newRubiksCubeList = new Cubie[rubiksCubeList.length * possibleMoves.length][3][3][3];
        Cubie[][][] newRubiksCube;

        int maxScore = -1;
        Cubie[][][] maxRubiksCube = new Cubie[3][3][3];

        for (int i = 0; i < rubiksCubeList.length; i++) {
            //NonRenderCubie centreCubie1 = (NonRenderCubie) rubiksCubeList[0][1][1][1];
            //System.out.println(centreCubie1.getPath().toString());
            for (int j = 0; j < possibleMoves.length; j++) {

                newRubiksCube = algorithm(rubiksCubeList[i], possibleMoves[j]);

                NonRenderCubie centreCubie = (NonRenderCubie) newRubiksCube[1][1][1];
                centreCubie.addToPath(possibleMoves[j]);

                int score = getScore(newRubiksCube);

                //.out.println("");
                //System.out.println("");
                if (score > maxScore) {
                    maxScore = score;
                    maxRubiksCube = newRubiksCube;
                }

                newRubiksCubeList[i * possibleMoves.length + j] = newRubiksCube;
            }
        }


        for (Cubie[][][] rubiksCube : newRubiksCubeList) {
            int score = getScore(rubiksCube);
            if (score > maxScore) {
                maxScore = score;
                maxRubiksCube = rubiksCube;
            }
        }

        //System.out.println(maxScore);
        //System.out.println(currentScore);

        //System.out.println(maxScore);

        if (maxScore > currentScore) {
            System.out.println("Max score");
            System.out.println(maxScore);
            printScore(maxRubiksCube);
            NonRenderCubie centreCubie = (NonRenderCubie) maxRubiksCube[1][1][1];
            movesList = centreCubie.getPath();
            //System.out.println(movesList.toString());
            moves = new String[movesList.size()];
            for (int i = 0; i < movesList.size(); i++) {
                moves[i] = movesList.get(i);
            }
            return moves;
        }
        return branch(newRubiksCubeList);
            /*
            System.out.println("SCORES:");
            printScore(maxRubiksCube);
            //System.out.println(maxScore);
            //System.out.println("Add move:");
            currentScore = maxScore;
            //movesList.add(maxMove);
            if(maxScore == 4) {
                NonRenderCubie centreCubie = (NonRenderCubie) maxRubiksCube[1][1][1];
                movesList = centreCubie.getPath();
                //System.out.println(movesList.toString());
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

        if(newRubiksCubeList.length < 10000) {
            return branch(newRubiksCubeList);
        } else {
            System.out.println("Size error");
            return new String[]{};
        }


    }
    */
}
