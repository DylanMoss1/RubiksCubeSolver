package uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Cubies.Non_Render_Cubies;

import org.joml.Matrix3f;
import uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Cubies.Cubie;

import java.util.LinkedList;

public abstract class NonRenderCubie extends Cubie {

    public String colour1;
    public String colour2;
    public String colour3;

    public NonRenderCubie(int index, int i, int j, int k, Matrix3f orientation) {
        super(index, i, j, k, orientation);
    }

    public void addToPath(String move){
        path.add(move);
    }

    public LinkedList<String> getPath(){
        return path;
    }
}
