package uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Cubies;

import org.joml.Matrix3f;
import org.joml.Matrix4f;
import org.joml.Vector4f;
import uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Cubies.Non_Render_Cubies.Non_Render_Cubies_Types.Centre;
import uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Cubies.Non_Render_Cubies.Non_Render_Cubies_Types.Corner;
import uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Cubies.Non_Render_Cubies.Non_Render_Cubies_Types.Edge;
import uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Cubies.Non_Render_Cubies.Non_Render_Cubies_Types.Unseen;

import java.util.LinkedList;

import static org.lwjgl.opengl.GL11.glDrawElements;
import static org.lwjgl.opengl.GL20.glGetUniformLocation;
import static org.lwjgl.opengl.GL20.glUniformMatrix4fv;

/** Abstract class representing the 26 'cubies' that make up the Rubik's cube */
public abstract class Cubie {

    public int cubieIndex;                                 //unique id of the Cubie (determined by solved position)
    public LinkedList<String> path = new LinkedList<>();   //store of String set of moves to reach this position
    public float scale = 0.5f;                             //Scale factor for rendering of the Cubie

    public Matrix4f ind_cube_rotate_translate = new Matrix4f();  //4x4 matrix representing the orientation and position of the Cubie
    public Vector4f position = new Vector4f();                   //4x1 vector representing the position of the Cubie
    public Matrix3f orientation = new Matrix3f();                //3x3 matrix representing the orientation of the Cubie 

    /** Constructor for Cubies (default orientation)
     *
     * @param index  unique id
     * @param i      x position [-1,1]
     * @param j      y position [-1,1]
     * @param k      z position [-1,1]
     */
    public Cubie(int index, int i, int j, int k) {

        this.cubieIndex = index;

        ind_cube_rotate_translate.translate(i,j,k);

        ind_cube_rotate_translate.getColumn(3,position);
    }

    /** Constructor for Cubies (set orientation)
     *
     * @param index          unique id
     * @param i              x position [-1,1]
     * @param j              y position [-1,1]
     * @param k              z position [-1,1]
     * @param orientation    Cubie orientation represented as a 3x3 matrix
     */
    public Cubie(int index, int i, int j, int k, Matrix3f orientation) {

        this.cubieIndex = index;

        ind_cube_rotate_translate.translate(i,j,k);

        ind_cube_rotate_translate.getColumn(3,position);
        ind_cube_rotate_translate.set3x3(orientation);

        this.orientation = orientation;
        this.position = new Vector4f(i,j,k,1);
    }

    /** Determines whether a Cubie is in its correct position
     *
     * @return A boolean of whether the position is correct
     */
    public boolean cubieCorrect(){
        return false;
    }

    /** Returns a new Cubie object that is a copy of this object
     *
     * @return A Cubie copy of this Cubie
     */
    public Cubie copy(){

        int i = Math.round(position.x);
        int j = Math.round(position.y);
        int k = Math.round(position.z);

        ind_cube_rotate_translate.get3x3(orientation);

        int zeroCount = 0;

        if(i == 0){
            zeroCount++;
        }
        if(j == 0){
            zeroCount++;
        }
        if(k == 0){
            zeroCount++;
        }

        if(zeroCount == 0){
            Corner newCubie = new Corner(cubieIndex,i,j,k,orientation);
            return newCubie;
        } else if(zeroCount == 1){
            Edge newCubie = new Edge(cubieIndex,i,j,k,orientation);
            return newCubie;
        } else if(zeroCount == 2){
            Centre newCubie = new Centre(cubieIndex,i,j,k,orientation);
            return newCubie;
        } else{
            Unseen newCubie = new Unseen(cubieIndex,i,j,k,orientation, (LinkedList<String>) path.clone());
            return newCubie;
        }
    }

    /** Determines if the Cubie is in the correct position
     *
     * @return A boolean of whether the Cubie is in the correct position
     */
    public boolean positionCorrect(){
        int index = getCubieIndex(); //Finds the index of the Cubie's current position
        return index == cubieIndex;  //Compares this index with the Cubie's current index
    }

    /** Determines if the full orientation of the Cubie is correct
     *
     * @return A boolean of whether the full orientation is correct
     */
    public boolean fullOrientationCorrect(){
        boolean check;
        ind_cube_rotate_translate.get3x3(orientation);
        if(Math.round(orientation.get(1,1)) == 1 && Math.round(orientation.get(0,0)) == 1 && Math.round(orientation.get(2,2)) == 1){
            check = true;
        } else {
            check = false;
        }
        return check;
    }

    /** Determines if the shown orientation of the Cubie is correct
     *
     * @return A boolean of whether the shown orientation is correct
     */
    public boolean orientationCorrect(){
        boolean check;
        ind_cube_rotate_translate.get3x3(orientation);

        if(Math.round(orientation.get(1,1)) == 1){
            check = true;
        } else {
            check = false;
        }

        return check;
    }

    /** Find the index of the position of the Cubie
     *
     * @return An int representing this id
     */
    public int getCubieIndex(){
        return Math.round(position.x) + 1 + (Math.round(position.y) + 1) * 3 + (Math.round(position.z) + 1) * 9;
    }

    /** Make a physical rotation of one of the side's of the cube in the x direction (on a set of Cubies)
     *
     * @param side         side of the cube to move [-1, 1]
     * @param direction    direction to rotate the side
     */
    public void xMove(int side, int direction){

        position = new Vector4f();

        ind_cube_rotate_translate.getColumn(3,position);

        if(Math.round(position.x) == side){
            Matrix4f transformation = new Matrix4f(1,0,0,0,
                    0,(float)Math.cos(Math.PI * scale),(float)Math.sin(Math.PI * scale * direction),0,
                    0,(float)Math.sin(Math.PI * scale * direction * -1),(float)Math.cos(Math.PI * scale),0,
                    0,0,0,1);

            transformation.mul(ind_cube_rotate_translate,ind_cube_rotate_translate);

            ind_cube_rotate_translate.getColumn(3,position);
        }
    }

    /** Make a physical rotation of one of the side's of the cube in the y direction (on a set of Cubies)
     *
     * @param side         side of the cube to move [-1, 1]
     * @param direction    direction to rotate the side
     */
    public void yMove(int side, int direction){
        position = new Vector4f();
        ind_cube_rotate_translate.getColumn(3,position);

        if(Math.round(position.y) == side){
            Matrix4f transformation = new Matrix4f((float)Math.cos(Math.PI * scale),0,(float)Math.sin(Math.PI * scale * direction),0,
                    0,1,0,0,
                    (float)Math.sin(Math.PI * scale * direction * -1),0,(float)Math.cos(Math.PI * scale),0,
                    0,0,0,1);

            transformation.mul(ind_cube_rotate_translate,ind_cube_rotate_translate);

            ind_cube_rotate_translate.getColumn(3,position);
        }
    }

    /** Make a physical rotation of one of the side's of the cube in the z direction (on a set of Cubies)
     *
     * @param side         side of the cube to move [-1, 1]
     * @param direction    direction to rotate the side
     */
    public void zMove(int side, int direction){

        position = new Vector4f();

        ind_cube_rotate_translate.getColumn(3,position);

        if(Math.round(position.z) == side){
            Matrix4f transformation = new Matrix4f((float)Math.cos(Math.PI * scale),(float)Math.sin(Math.PI * scale * direction),0,0,
                    (float)Math.sin(Math.PI * scale * direction * -1),(float)Math.cos(Math.PI * scale),0,0,
                    0,0,1,0,
                    0,0,0,1);

            transformation.mul(ind_cube_rotate_translate,ind_cube_rotate_translate);

            ind_cube_rotate_translate.getColumn(3,position);
        }
    }

    /** Rotate the cube without rotating any sides in the x direction (on a set of Cubies)
     *
     * @param direction   direction to rotate the cube
     */
    public void xRotate(int direction){

        position = new Vector4f();

        ind_cube_rotate_translate.getColumn(3,position);

        Matrix4f transformation = new Matrix4f(1,0,0,0,
                0,(float)Math.cos(Math.PI * scale),(float)Math.sin(Math.PI * scale * direction),0,
                0,(float)Math.sin(Math.PI * scale * direction * -1),(float)Math.cos(Math.PI * scale),0,
                0,0,0,1);

        transformation.mul(ind_cube_rotate_translate,ind_cube_rotate_translate);

        ind_cube_rotate_translate.getColumn(3,position);
    }

    /** Rotate the cube without rotating any sides in the y direction (on a set of Cubies)
     *
     * @param direction   direction to rotate the cube
     */
    public void yRotate(int direction){

        position = new Vector4f();

        ind_cube_rotate_translate.getColumn(3,position);


        Matrix4f transformation = new Matrix4f((float)Math.cos(Math.PI * scale),0,(float)Math.sin(Math.PI * scale * direction),0,
                0,1,0,0,
                (float)Math.sin(Math.PI * scale * direction * -1),0,(float)Math.cos(Math.PI * scale),0,
                0,0,0,1);

        transformation.mul(ind_cube_rotate_translate,ind_cube_rotate_translate);

        ind_cube_rotate_translate.getColumn(3,position);
    }

    /** Convert Rubik's cube moves into Cubie transformations
     * See https://ruwix.com/the-rubiks-cube/notation/ for notation details
     * Note that P represents prime (')*/

    public void R(){
        xMove(1,-1);
    }

    public void RP(){
        xMove(1,1);
    }

    public void L(){
        xMove(-1,1);
    }

    public void LP(){
        xMove(-1,-1);
    }

    public void U(){
        yMove(1,1);
    }

    public void UP(){
        yMove(1,-1);
    }

    public void D(){
        yMove(-1,-1);
    }

    public void DP(){
        yMove(-1,1);
    }

    public void F(){
        zMove(1,-1);
    }

    public void FP(){
        zMove(1,1);
    }

    public void B(){
        zMove(-1,1);
    }

    public void BP(){
        zMove(-1,-1);
    }

    public void M(){
        xMove(0,-1);
    }

    public void MP(){
        xMove(0,1);
    }

    public void u(){ yMove(1,1); yMove(0,1);}

    public void uP(){ yMove(1,-1); yMove(0,-1);}

    public void f(){
        zMove(1,-1);zMove(0,-1);
    }

    public void fP(){
        zMove(1,1);zMove(0,1);
    }

    public void x(){
        xRotate(-1);
    }

    public void xP(){ xRotate(1); }

    public void y(){
        yRotate(1);
    }

    public void yP(){
        yRotate(-1);
    }

    /** Convert Rubik's cube String notation into Rubik's cube moves
     *
     * @param move   the String notation of a Rubik's cube move
     */
    public void moveCube(String move){

        switch(move){
            case "R":
                R();
                break;
            case "RP":
                RP();
                break;
            case "L":
                L();
                break;
            case "LP":
                LP();
                break;
            case "U":
                U();
                break;
            case "UP":
                UP();
                break;
            case "D":
                D();
                break;
            case "DP":
                DP();
                break;
            case "F":
                F();
                break;
            case "FP":
                FP();
                break;
            case "B":
                B();
                break;
            case "BP":
                BP();
                break;
            case "M":
                M();
                break;
            case "MP":
                MP();
                break;
            case "x":
                x();
                break;
            case "xP":
                xP();
                break;
            case "u":
                u();
                break;
            case "uP":
                uP();
                break;
            case "y":
                y();
                break;
            case "yP":
                yP();
                break;
            case "f":
                f();
                break;
            case "fP":
                fP();
                break;
            case "R2":
                R();
                R();
                break;
            case "L2":
                L();
                L();
                break;
            case "U2":
                U();
                U();
                break;
            case "D2":
                D();
                D();
                break;
            case "F2":
                F();
                F();
                break;
            case "B2":
                B();
                B();
                break;
            case "q1":
                U();
                R();
                UP();
                RP();
                UP();
                FP();
                U();
                F();
                break;
            case "q2":
                y();
                U();
                R();
                UP();
                RP();
                UP();
                FP();
                U();
                F();
                yP();
                break;
            case "q3":
                y();
                y();
                U();
                R();
                UP();
                RP();
                UP();
                FP();
                U();
                F();
                yP();
                yP();
                break;
            case "q4":
                yP();
                U();
                R();
                UP();
                RP();
                UP();
                FP();
                U();
                F();
                y();
                break;
            case "e1":
                UP();
                LP();
                UP();
                L();
                U();
                F();
                U();
                FP();
                break;
            case "e2":
                y();
                UP();
                LP();
                UP();
                L();
                U();
                F();
                U();
                FP();
                yP();
                break;
            case "e3":
                y();
                y();
                UP();
                LP();
                UP();
                L();
                U();
                F();
                U();
                FP();
                yP();
                yP();
                break;
            case "e4":
                yP();
                UP();
                LP();
                UP();
                L();
                U();
                F();
                U();
                FP();
                y();
                break;
            case "w1":
                FP();
                UP();
                F();
                break;
            case "w2":
                y();
                FP();
                UP();
                F();
                yP();
                break;
            case "w3":
                y();
                y();
                FP();
                UP();
                F();
                yP();
                yP();
                break;
            case "w4":
                yP();
                FP();
                UP();
                F();
                y();
                break;
            case "z1":
                F();
                U();
                FP();
                break;
            case "z2":
                y();
                F();
                U();
                FP();
                yP();
                break;
            case "z3":
                y();
                y();
                F();
                U();
                FP();
                y();
                y();
                break;
            case "z4":
                yP();
                F();
                U();
                FP();
                y();
                break;
            case "v1":
                FP();
                LP();
                U();
                U();
                L();
                F();
                break;
            case "v2":
                y();
                FP();
                LP();
                U();
                U();
                L();
                F();
                yP();
                break;
            case "v3":
                y();
                y();
                FP();
                LP();
                U();
                U();
                L();
                F();
                yP();
                yP();
                break;
            case "v4":
                yP();
                FP();
                LP();
                U();
                U();
                L();
                F();
                y();
                break;
            case "o1":
                R();
                U();
                RP();
                UP();
                break;
            case "o2":
                y();
                R();
                U();
                RP();
                UP();
                yP();
                break;
            case "o3":
                y();
                y();
                R();
                U();
                RP();
                UP();
                yP();
                yP();
                break;
            case "o4":
                yP();
                R();
                U();
                RP();
                UP();
                y();
                break;
            case "g1":
                R();
                U();
                RP();
                U();
                U();
                R();
                U();
                U();
                RP();
                U();
                FP();
                UP();
                F();
                break;
            case "g2":
                y();
                R();
                U();
                RP();
                U();
                U();
                R();
                U();
                U();
                RP();
                U();
                FP();
                UP();
                F();
                yP();
                break;
            case "g3":
                y();
                y();
                R();
                U();
                RP();
                U();
                U();
                R();
                U();
                U();
                RP();
                U();
                FP();
                UP();
                F();
                yP();
                yP();
                break;
            case "g4":
                yP();
                R();
                U();
                RP();
                U();
                U();
                R();
                U();
                U();
                RP();
                U();
                FP();
                UP();
                F();
                y();
                break;
            case "":
                break;
            default:
                System.out.println(move);
                System.out.println("Not a move");
                break;
        }
    }
}
