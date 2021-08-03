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
}
