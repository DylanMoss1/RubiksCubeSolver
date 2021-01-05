package uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Cubies.Non_Render_Cubies.Non_Render_Cubies_Types;

import org.joml.Matrix3f;
import uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Cubies.Non_Render_Cubies.NonRenderCubie;

import java.util.LinkedList;

public class Unseen extends NonRenderCubie {

    public Unseen(int index, int i, int j, int k, Matrix3f orientation, LinkedList<String> path) {
        super(index, i, j, k, orientation);
        this.path = path;
    }

    @Override
    public boolean cubieCorrect(){
        return true;
    }
}