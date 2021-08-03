package uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Webcam;

import org.joml.Matrix3f;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.Collections;


public class ConstructCubie {

    public String colour1;
    public String colour2;
    public String colour3;

    public String face1;
    public String face2;
    public String face3;

    //public String colour;

    public int numOfColour = 0;

    public Vector3f position;
    public int index;
    public Matrix3f orientation;

    public void setColour(String colour, String face){
        if(colour == "W" || colour == "Y"){
            colour1 = colour;
            face1 = face;
        } else if(colour == "R" || colour == "O"){
            colour2 = colour;
            face2 = face;
        } else {
            colour3 = colour;
            face3 = face;
        }

        numOfColour++;

        /*
        if(face == "W" || face == "Y"){
            face1 = face;
        } else if(face == "R" || face == "O"){
            face2 = face;
        } else {
            face3 = face;
        }

         */
    }

    public void calculatePosition(int i, int j, int k){
        position = new Vector3f(i,j,k);
    }

    public void calculateIndex(){

        index = 0;

        if(colour1 == null){
            index += 3;
        } else if(colour1 == "W"){
            index += 0;
        } else {
            index += 6;
        }

        if(colour2 == null){
            index += 9;
        } else if(colour2 == "R"){
            index += 0;
        } else {
            index += 18;
        }

        if(colour3 == null){
            index += 1;
        } else if(colour3 == "B"){
            index += 0;
        } else {
            index += 2;
        }
    }

    public void calculateOrientation() {

        orientation = new Matrix3f();

        //size = colourDictionary.size();

        ArrayList<String> moves = new ArrayList<>();

        if (numOfColour == 0 || numOfColour == 1) {
            return;
        } else if (numOfColour == 2) {
            orientationEdge(moves);
        } else {
            orientationCorner(moves);
        }

        for(int i=0; i<moves.size(); i++){
            if(moves.get(i).equals("x")){
                orientation = applyX(orientation);
            } else if(moves.get(i).equals("y")) {
                orientation = applyY(orientation);
            } else {
                orientation = applyZ(orientation);
            }
        }

        //orientation.invert(orientation);
    }


    public void orientationEdge(ArrayList<String> moves){

        String newFace1;
        String newFace2;

        String mainColour1;
        String mainColour2;
        String checkFace1;
        String checkFace2;

        //System.out.println(face2);

        if(colour1 == null){
            mainColour1 = colour2;
            mainColour2 = colour3;
            checkFace1 = face2;
            checkFace2 = face3;
        } else if(colour2 == null){
            mainColour1 = colour1;
            mainColour2 = colour3;
            checkFace1 = face1;
            checkFace2 = face3;
        } else {
            mainColour1 = colour1;
            mainColour2 = colour2;
            checkFace1 = face1;
            checkFace2 = face2;
        }
        //System.out.println("");

        //System.out.println(mainColour2);
        //System.out.println(checkFace2);

        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
                for(int k=0; k<4; k++){
                    newFace1 = move(i, j, k, mainColour1);
                    newFace2 = move(i, j, k, mainColour2);
                    if(checkOrientation(newFace1, newFace2, checkFace1, checkFace2)) {
                        for (int m = 0; m < i; m++) {
                            moves.add("x");
                            //System.out.println("change x");
                        }
                        for (int l = 0; l < j; l++) {
                            moves.add("y");
                            //System.out.println("change y");
                        }
                        for (int n = 0; n < k; n++) {
                            moves.add("z");
                            //System.out.println("change z");
                        }
                        //System.out.println("found orientation");
                        /*
                        System.out.println("### - Edge (complete)");
                        System.out.println(index);
                        System.out.println(colour1);
                        System.out.println(colour2);
                        System.out.println(colour3);
                        System.out.println(position);

                         */
                        return;
                    }
                }
            }
        }
        //System.out.println("not found orientation");
        /*
        System.out.println("### - Edge (uncomplete)");
        System.out.println(index);
        System.out.println(colour1);
        System.out.println(colour2);
        System.out.println(colour3);
        System.out.println(position);

         */


        //System.out.println(position);
        /*
        System.out.println("orientation not found - Edge");
        System.out.println(colour1);
        System.out.println(colour2);
        System.out.println(colour3);
        System.out.println(position);
        System.out.println("");

         */
    }


    public void orientationCorner(ArrayList<String> moves) {

        String newFace1;
        String newFace2;
        String newFace3;


        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
                for(int k=0; k<4; k++){
                    newFace1 = move(i, j, k, colour1);
                    newFace2 = move(i, j, k, colour2);
                    newFace3 = move(i, j, k, colour3);
                    if(checkOrientation(newFace1, newFace2, newFace3)) {
                        for (int m = 0; m < i; m++) {
                            moves.add("x");
                            //System.out.println("change x");
                        }
                        for (int l = 0; l < j; l++) {
                            moves.add("y");
                            //System.out.println("change y");
                        }
                        for (int n = 0; n < k; n++) {
                            moves.add("z");
                            //System.out.println("change z");
                        }


                        //System.out.println("found orientation");
                        /*
                        System.out.println("### - Corner (complete)");
                        System.out.println(index);
                        System.out.println(colour1);
                        System.out.println(colour2);
                        System.out.println(colour3);
                        System.out.println(position);

                         */
                        return;
                    }
                }
            }
        }
        //System.out.println("not found orientation");

        /*
        System.out.println("### - Corner (uncomplete)");
        System.out.println(index);
        System.out.println(colour1);
        System.out.println(colour2);
        System.out.println(colour3);
        System.out.println(position);

         */


        /*
        System.out.println("orientation not found - Corner");
        System.out.println(colour1);
        System.out.println(colour2);
        System.out.println(colour3);
        System.out.println(position);
        System.out.println("");

         */
    }

    public boolean checkOrientation(String newFace1, String newFace2, String newFace3){
        /*
        System.out.println("###");
        System.out.println(newFace1);
        System.out.println(face1);
        System.out.println(" ");
        System.out.println(newFace2);
        System.out.println(face2);
        System.out.println(" ");
        System.out.println(newFace3);
        System.out.println(face3);
        System.out.println(" ");
         */
        return (newFace1 == face1 && newFace2 == face2 && newFace3 == face3);
    }

    public boolean checkOrientation(String newFace1, String newFace2, String checkFace1, String checkFace2){
        //System.out.println("FACES : ");
        //System.out.println(newFace2);
        //System.out.println(checkFace2);
        return (newFace1 == checkFace1 && newFace2 == checkFace2);
    }

    public String move(int numx, int numy, int numz, String move){
        return zMove(numz, (yMove(numy,xMove(numx, move))));
    }

    public String xMove (int num, String move) {
        for(int i=0; i<num; i++) {
            if (move == "R") {
                move = "Y";
            } else if (move == "Y") {
                move = "O";
            } else if (move == "O") {
                move = "W";
            } else if (move == "W") {
                move = "R";
            }
        }
        return move;
    }

    public String yMove (int num, String move){
        for(int i=0; i<num; i++) {
            if (move == "R") {
                move = "B";
            } else if (move == "B") {
                move = "O";
            } else if (move == "O") {
                move = "G";
            } else if (move == "G") {
                move = "R";
            }
        }
        return move;
    }

    public String zMove (int num, String move){
        for(int i=0; i<num; i++) {
            if (move == "Y") {
                move = "G";
            } else if (move == "G") {
                move = "W";
            } else if (move == "W") {
                move = "B";
            } else if (move == "B") {
                move = "Y";
            }
        }
        return move;
    }

    public Matrix3f applyX(Matrix3f matrix){
        Matrix3f xMatrix = new Matrix3f(1,0,0,0,0,-1,0,1,0);
        return xMatrix.mul(matrix);
    }

    public Matrix3f applyY(Matrix3f matrix){
        Matrix3f yMatrix = new Matrix3f(0,0,1,0,1,0,-1,0,0);
        return yMatrix.mul(matrix);
    }

    public Matrix3f applyZ(Matrix3f matrix){
        Matrix3f xMatrix = new Matrix3f(0,-1,0,1,0,0,0,0,1);
        return xMatrix.mul(matrix);
    }





        /*
        if(colour1 == "W"){
            if(face1 == "W"){
                orientationCorner2(moves, "", "W", k);
            } else if(face1 == "Y"){
                moves.add("x2");
                orientationCorner2(moves, "x2", "W", k);
            } else if(face1 == "R"){
                moves.add("xP");
                orientationCorner2(moves, "xP", "W", k);
            } else if(face1 == "O"){
                moves.add("x");
                orientationCorner2(moves, "x", "W", k);
            } else if(face1 == "B"){

            } else if(face1 == "G"){

            }
        } else {
            if(face1 == "Y"){
                orientationCorner2(moves);
            } else if(face1 == "W"){
                moves.add("x2");
                orientationCorner2(moves);
            } else{

            }
        }


    }

    public void orientationCorner2(ArrayList<String> moves, String move, String mainFace, int k) {

    }

*/
        /*
        if(mainFace == "W"){
            if(move == "x2")
                if(k == 2){
                    if(face2 == "O"){
                        moves.add(getRotation2(colour2));
                    } else if(face3 == "O"){
                        moves.add(getRotation2(colour3));
                    }
                } else {
                    if(face2 == "R"){
                        moves.add(getRotation1(colour2));
                    } else if(face3 == "R"){
                        moves.add(getRotation1(colour3));
                    }
                }
            else if(move == "") {
                if (k == 2) {
                    if (face2 == "O") {
                        moves.add(getRotation1(colour2));
                    } else if (face3 == "O") {
                        moves.add(getRotation1(colour3));
                    }
                } else {
                    if (face2 == "R") {
                        moves.add(getRotation2(colour2));
                    } else if (face3 == "R") {
                        moves.add(getRotation2(colour3));
                    }
                }
            }
        }





        if(mainFace == "Y"){
            if(move == "x2")
                if(k == 2){
                    if(face2 == "O"){
                        moves.add(getRotation2(colour2));
                    } else if(face3 == "O"){
                        moves.add(getRotation2(colour3));
                    }
                } else {
                    if(face2 == "R"){
                        moves.add(getRotation1(colour2));
                    } else if(face3 == "R"){
                        moves.add(getRotation1(colour3));
                    }
                }
            else {

            }
        }

         */

    /*
    }
     */

    /*
    public String getRotation1(String currentColour){
        if(currentColour == "O"){
            return "";
        } else if(currentColour == "G"){
            return "y";
        } else if(currentColour == "B"){
            return "yP";
        } else {
            return "y2";
        }
    }

    public String getRotation2(String currentColour){
        if(currentColour == "R"){
            return "";
        } else if(currentColour == "B"){
            return "y";
        } else if(currentColour == "G"){
            return "yP";
        } else {
            return "y2";
        }
    }


     */


        /*
        int count = 0;

        if(colour1 == face1){
            count++;
        }
        if(colour2 == face2){
            count++;
        }
        if(colour3 == face3){
            count++;
        }

         */

        /*
        if(count == 3){
            return;
        } else if(count == 2){

        } else if(count == 3){

        }

         */


        /*
        String colour;
        size = colourDictionary.size();

        if(size == 0 || size == 1){
            return;
        } else if (size == 2){
            //Handle edge
        } else {

            int colour1Check = 0;
            int colour2Check = 0;

            if(face1 == "W"){
                colour = colourDictionary.get("W");
                if(colour == "W"){
                    colour1Check = 0;
                } else if(colour == "Y"){
                    colour1Check = 3;
                } else if(colour == "")
            } else { //face1 == "Y"

            }
        }

         */
}
