package uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Cubies.Non_Render_Cubies.Non_Render_Cubies_Types;

import org.joml.Matrix3f;
import uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Cubies.Non_Render_Cubies.NonRenderCubie;

public class Edge extends NonRenderCubie {

    public Edge(int index, int i, int j, int k, Matrix3f orientation) {
        super(index, i, j, k, orientation);
    }

    @Override
    public boolean cubieCorrect(){

        //System.out.println("checked");
        //System.out.println(positionCorrect());
        //System.out.println(orientationCorrect());
        //System.out.println(" ");
        if(positionCorrect() && orientationCorrect()){
            return true;
        } else {
            //System.out.println(position.toString());
            return false;
        }
    }
}
